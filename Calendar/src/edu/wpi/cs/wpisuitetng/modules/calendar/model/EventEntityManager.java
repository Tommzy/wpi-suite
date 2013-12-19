/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 *Based on source provided by Robert Dabrowski 11/21/13
 * 
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonSyntaxException;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.NotImplementedException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;


// TODO: Auto-generated Javadoc
/**
 * The Class EventEntityManager.
 */
public class EventEntityManager implements EntityManager<Event> {

	/** The db. */
	private Data db;



	/**
	 * Instantiates a new Event entity manager.
	 *
	 * @param data the data
	 */	
	public EventEntityManager(Data data) {
		db = data;

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Event makeEntity(Session s, String content)
			throws BadRequestException, WPISuiteException {

		// Parse the Event from JSON
		final Event newEvent;
		try {
			newEvent = Event.fromJSON(content);
		} catch(JsonSyntaxException e){ // the JSON conversion failed
			throw new BadRequestException("The Event creation string had invalid formatting. Entity String: " + content);			
		}

		// Saves the Event in the database
		if(newEvent.isTeamEvent()){
			this.save(s,newEvent); // An exception may be thrown here if we can't save it
		}else{
			newEvent.setUsername(s.getUsername());
			this.save(newEvent);
		}

		// Return the newly created Event (this gets passed back to the client)
		return newEvent;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
	 */
	public void save(Session s, Event model) throws WPISuiteException {
		assignUniqueID(model); // Assigns a unique ID to the Req if necessary

		// Save the Event in the database if possible, otherwise throw an exception
		// We want the Event to be associated with the project the user logged in to
		if (!db.save(model, s.getProject())) {
			throw new WPISuiteException("Unable to save Event.");
		}
		System.out.println("The Event saved!    " + model.toJSON());
	}

	/**
	 * Save.
	 *
	 * @param model the model
	 * @throws WPISuiteException the wPI suite exception
	 */
	public void save(Event model) throws WPISuiteException {
		assignUniqueID(model); // Assigns a unique ID to the Req if necessary

		// Save the Event in the database if possible, otherwise throw an exception
		// We want the Event to be associated with the project the user logged in to
		if (!db.save(model)) {
			throw new WPISuiteException("Unable to save Event.");
		}
		System.out.println("The Event saved!    " + model.toJSON());
	}


	/**
	 * Ensure role.
	 *
	 * @param session the session
	 * @param role the role
	 * @throws WPISuiteException the wPI suite exception
	 */
	private void ensureRole(Session session, Role role) throws WPISuiteException {
		User user = (User) db.retrieve(User.class, "username", session.getUsername()).get(0);
		if(!user.getRole().equals(role)) {
			throw new UnauthorizedException();
		}
	}


	/**
	 * Takes a Event and assigns a unique id if necessary.
	 *
	 * @param Event The Event that possibly needs a unique id
	 * @throws WPISuiteException "Count failed"
	 */
	public void assignUniqueID(Event Event) throws WPISuiteException{
		if (Event.getId() == -1){// -1 is a flag that says a unique id is needed            
			Event.setId(HighestId() + 1); // Assures that the Event's ID will be unique
		}
	}



	/** Returns the highest Id of all Events in the database.
	 * @return The highest Id
	 * @throws WPISuiteException "Retrieve all failed"
	 */
	public int HighestId()  {
		List<Event> commitList = db.retrieveAll(new Event(null, null, null,null,null));
		Iterator<Event> itr = commitList.iterator();
		int maxId = 0;
		while (itr.hasNext())
		{
			Event next = itr.next();
			if (next.getId() > maxId)
			{
				maxId = next.getId();
			}
		}
		return maxId;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
	 */
	public int Count()  {
		// Passing a dummy Event lets the db know what type of object to retrieve
		//System.out.println("Here is the session passed into the Count() method"+db.retrieveAll(new Event(null, null, null)));
		return db.retrieveAll(new Event(null, null, null,null,null)).size();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	public Event[] getAll(Session s)  {
		// Ask the database to retrieve all objects of the type Event.
		// Passing a dummy Event lets the db know what type of object to retrieve
		// Passing the project makes it only get Events from that project
		// Return the list of Events as an array
		//		System.out.println("Here is the session passed into the getAll() method" + s.toString());
		Event[] personal = null;
		Event[] team = null;
		Collection<Event> combined = new ArrayList<Event>();
		try{// return combined personal and team commitments
			personal = db.retrieve(Event.class, "username", s.getUsername()).toArray(new Event[0]);
			team =  db.retrieveAll(new Event(null, null, null, null, null), s.getProject()).toArray(new Event[0]);
			System.out.println("Team "+team.toString());
			System.out.println("personal "+personal.toString());
			//
			combined.addAll(Arrays.asList(personal));
			combined.addAll(Arrays.asList(team));
//			Iterator<Event> itr = combined.iterator();
//			if(itr.hasNext()){
//				Event next = itr.next();
//				if (!isCategoryExist(s,next.getCategory())){
//					next.setCategory(null);
//					update(s, next.toJSON());
//				}
//			}
			return combined.toArray(new Event[] {});
		}catch(WPISuiteException e){// no personal commitments found
			System.out.println("No Personal Events yet" + e);
			return db.retrieveAll(new Event(null, null, null, null, null), s.getProject()).toArray(new Event[0]);
		}

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Event[] getEntity(Session s, String id) throws NotFoundException, WPISuiteException {

		final int intId = Integer.parseInt(id);
		if(intId < 1) {
			throw new NotFoundException("The Event with the specified id was not found:" + intId);
		}
		Event[] Events = null;

		// Try to retrieve the specific Event
		try {
			Events = db.retrieve(Event.class, "id", intId).toArray(new Event[0]);
		} catch (WPISuiteException e) { // caught and re-thrown with a new message
			e.printStackTrace();
			throw new WPISuiteException("There was a problem retrieving from the database." );
		}

		// If a Event was pulled, but has no content
		if(Events.length < 1 || Events[0] == null) {
			throw new NotFoundException("The Event with the specified id was not found:" + intId);
//		}else if (!isCategoryExist(s,Events[0].getCategory())){
//			Events[0].setCategory(null);
//			update(s, Events[0].toJSON());
//		}
		}
		
		return Events;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Event update(Session s, String content) throws WPISuiteException {
		// If there is no session
		if(s == null){
			throw new WPISuiteException("Null session.");
		}
		// The following code was modified from the requirement entity manager
		Event updatedEvent = Event.fromJSON(content);

		List<Model> oldEvents = db.retrieve(Event.class, "id", updatedEvent.getId());
		if(oldEvents.size() < 1 || oldEvents.get(0) == null) {
			throw new BadRequestException("Event with ID does not exist.");
		}

		Event existingEvent = (Event)oldEvents.get(0);		


		existingEvent.copy(updatedEvent);
		//System.out.println(existingEvent);

		if(existingEvent.isTeamEvent()){
			existingEvent.setUsername(null);
			if(!db.save(existingEvent, s.getProject())) {
				throw new WPISuiteException();
			}

			return existingEvent;
		}else{
			existingEvent.setUsername(s.getUsername());
			if(!db.save(existingEvent)) {
				throw new WPISuiteException();
			}

			return existingEvent;
		}

	} 



	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// Attempt to get the entity, NotFoundException or WPISuiteException may be thrown	    	
		ensureRole(s, Role.ADMIN);

		Event oldEvent = getEntity(s,   id    )[0];
		if(oldEvent.isTeamEvent()){
			ensureRole(s, Role.ADMIN);
			Event eventToBeDel = new Event(null, null, null,null,null);
			eventToBeDel.setId(oldEvent.getId());

			if (db.delete(eventToBeDel)!=null){
				return true; // the deletion was successful
			}	    
		}else{
			System.out.println("From personal i want to delete "+ oldEvent.toJSON());
			Event eventToBeDel = new Event(null, null, null, null,null);
			eventToBeDel.setId(oldEvent.getId());
			eventToBeDel.setUsername(s.getUsername());
			eventToBeDel.setTeamEvent(false);
			if (db.delete(eventToBeDel)!=null){
				return true; // the deletion was successful
			}

		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	public void deleteAll(Session s) throws WPISuiteException  {
		ensureRole(s, Role.ADMIN);
		db.deleteAll(new Event(null, null, null,null,null), s.getProject());
	}

	//The following methods are not implemented but required by the "EntityManager" interface:

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
	 */
	public String advancedGet(Session s, String[] args)
			throws NotImplementedException	 {
		throw new NotImplementedException();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
	 */
	public String advancedPut(Session s, String[] args, String content)
			throws NotImplementedException {
		throw new NotImplementedException();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
	 */
	public String advancedPost(Session s, String string, String content)
			throws NotImplementedException {
		throw new NotImplementedException();
	}
	
//	/**check is the category in this Event is exist or not
//	 * @param s
//	 * @param category
//	 * @return
//	 */
//	public boolean isCategoryExist(Session s, Category category){
//		Category[] personal = null;
//		Category[] team = null;
//		Collection<Category> combined = new ArrayList<Category>();
//		try{// return combined personal and team categories
//			personal = db.retrieve(Category.class, "userID", s.getUsername()).toArray(new Category[0]);
//			team =  db.retrieveAll(new Category(null, false,null), s.getProject()).toArray(new Category[0]);
//			combined.addAll(Arrays.asList(personal));
//			combined.addAll(Arrays.asList(team));
//			combined.toArray(new Category[] {});
//		}catch(WPISuiteException e){
//			System.out.println("No personal Category yet");
//			team =  db.retrieveAll(new Category(null, false,null), s.getProject()).toArray(new Category[0]);
//			combined.addAll(Arrays.asList(team));
//			combined.toArray(new Category[] {});
//		}
//		
//		Iterator<Category> itr = combined.iterator();
//		while(itr.hasNext()){
//			Category next = itr.next();
//			if((next.getName().equals(category.getName()))&&
//					(next.getColor().equals(category.getColor()))&&
//					(next.getProject().equals(category.getProject()))&&
//					(next.getUserId().equals(category.getUserId()))&&
//					((next.getId() == (category.getId())))){
//			}else{
//				return false;
//			}
//		}
//		
//		return true;
//		
//	}


}