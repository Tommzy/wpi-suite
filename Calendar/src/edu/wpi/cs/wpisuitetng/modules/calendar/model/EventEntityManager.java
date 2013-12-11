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

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

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
			throws BadRequestException, ConflictException, WPISuiteException {

		// Parse the Event from JSON
		final Event newEvent;
		try {
			newEvent = Event.fromJSON(content);
		} catch(JsonSyntaxException e){ // the JSON conversion failed
			throw new BadRequestException("The Event creation string had invalid formatting. Entity String: " + content);			
		}

		// Saves the Event in the database
		this.save(s,newEvent); // An exception may be thrown here if we can't save it

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


	/** Takes a Event and assigns a unique id if necessary
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
	public int HighestId() throws WPISuiteException {
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
	public int Count() throws WPISuiteException {
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
		return db.retrieveAll(new Event(null, null, null,null,null), s.getProject()).toArray(new Event[0]);

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
			Events = db.retrieve(Event.class, "id", intId, s.getProject()).toArray(new Event[0]);
		} catch (WPISuiteException e) { // caught and re-thrown with a new message
			e.printStackTrace();
			throw new WPISuiteException("There was a problem retrieving from the database." );
		}

		// If a Event was pulled, but has no content
		if(Events.length < 1 || Events[0] == null) {
			throw new NotFoundException("The Event with the specified id was not found:" + intId);
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

		List<Model> oldEvents = db.retrieve(Event.class, "id", updatedEvent.getId(), s.getProject());
		if(oldEvents.size() < 1 || oldEvents.get(0) == null) {
			throw new BadRequestException("Event with ID does not exist.");
		}

		Event existingEvent = (Event)oldEvents.get(0);		


		existingEvent.copy(updatedEvent);

		if(!db.save(existingEvent, s.getProject())) {
			throw new WPISuiteException();
		}

		return existingEvent;

	} 



	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// Attempt to get the entity, NotFoundException or WPISuiteException may be thrown	    	
		ensureRole(s, Role.ADMIN);
		Event oldComm = getEntity(s,   id    )[0];
		Event commToBeDel = new Event(null, null, null,null,null);
		commToBeDel.setId(oldComm.getId());
		
		if (db.delete(commToBeDel).equals(commToBeDel)){
			return true; // the deletion was successful
		}	    
		return false; // The deletion was unsuccessful
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
			throws WPISuiteException {
		throw new NotImplementedException();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
	 */
	public String advancedPut(Session s, String[] args, String content)
			throws WPISuiteException {
		throw new NotImplementedException();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
	 */
	public String advancedPost(Session s, String string, String content)
			throws WPISuiteException {
		throw new NotImplementedException();
	}


}