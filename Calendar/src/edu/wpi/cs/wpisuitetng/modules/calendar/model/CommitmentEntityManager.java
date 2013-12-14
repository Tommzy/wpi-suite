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
 * The Class CommitmentEntityManager.
 */
public class CommitmentEntityManager implements EntityManager<Commitment> {

	/** The db. */
	private Data db;



	/**
	 * Instantiates a new commitment entity manager.
	 *
	 * @param data the data
	 */	
	public CommitmentEntityManager(Data data) {
		db = data;

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Commitment makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {

		// Parse the Commitment from JSON
		final Commitment newCommitment;
		try {
			newCommitment = Commitment.fromJSON(content);
		} catch(JsonSyntaxException e){ // the JSON conversion failed
			throw new BadRequestException("The Commitment creation string had invalid formatting. Entity String: " + content);			
		}

		// Saves the Commitment in the database
		if(newCommitment.isTeamCommitment()){
			this.save(s,newCommitment); // An exception may be thrown here if we can't save it
		}else{
			newCommitment.setUsername(s.getUsername());
			this.save(newCommitment);// assume personal commitment
		}

		// Return the newly created Commitment (this gets passed back to the client)
		return newCommitment;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
	 */
	public void save(Session s, Commitment model) throws WPISuiteException {
		assignUniqueID(model); // Assigns a unique ID to the Req if necessary

		// Save the Commitment in the database if possible, otherwise throw an exception
		// We want the Commitment to be associated with the project the user logged in to
		if (!db.save(model, s.getProject())) {
			throw new WPISuiteException("Unable to save Commitment.");
		}
		System.out.println("The Commitment saved!    " + model.toJSON());
	}

	public void save(Commitment model) throws WPISuiteException {
		assignUniqueID(model); // Assigns a unique ID to the Req if necessary

		// Save the Commitment in the database if possible, otherwise throw an exception
		// We want the Commitment to be associated with the project the user logged in to
		if (!db.save(model)) {
			throw new WPISuiteException("Unable to save Commitment.");
		}
		System.out.println("The Commitment saved!    " + model.toJSON());
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

	//	/**
	//	 * Ensures that a user is of the specified project
	//	 * @param session the session
	//	 * @param User the user supposed in the project
	//	 * @throws WPISuiteException user isn't authorized for the given role */
	//	
	//	private void ensureMember(Session session, User user) throws WPISuiteException {
	//		User usera[] = db.retrieve(User.class, "username", session.getUsername(), session.getProject()).toArray(new User[0]);
	//		User userb = session.getUser();
	//		
	//		
	//		System.out.println(usera.toString());
	//
	//		int counter = usera.length;
	//		for (int i = 0; i < counter; i++) {
	//			if (!usera[i].getUsername().equals(user.getUsername())) {
	//				throw new UnauthorizedException();
	//			}
	//		}
	//	}


	/** Takes a Commitment and assigns a unique id if necessary
	 * 
	 * @param commitment The Commitment that possibly needs a unique id
	 * @throws WPISuiteException "Count failed"
	 */
	public void assignUniqueID(Commitment commitment) throws WPISuiteException{
		if (commitment.getId() == -1){// -1 is a flag that says a unique id is needed            
			commitment.setId(HighestId() + 1); // Assures that the Commitment's ID will be unique
		}
	}



	/** Returns the highest Id of all commitments in the database.
	 * @return The highest Id
	 * @throws WPISuiteException "Retrieve all failed"
	 */
	public int HighestId() throws WPISuiteException {
		List<Commitment> commitList = db.retrieveAll(new Commitment(null, null, null));
		Iterator<Commitment> itr = commitList.iterator();
		int maxId = 0;
		while (itr.hasNext())
		{
			Commitment next = itr.next();
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
		// Passing a dummy Commitment lets the db know what type of object to retrieve
		//System.out.println("Here is the session passed into the Count() method"+db.retrieveAll(new Commitment(null, null, null)));
		return db.retrieveAll(new Commitment(null, null, null)).size();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	public Commitment[] getAll(Session s) throws WPISuiteException  {
		// Ask the database to retrieve all objects of the type Commitment.
		// Passing a dummy Commitment lets the db know what type of object to retrieve
		// Passing the project makes it only get Commitments from that project
		// Return the list of Commitments as an array
		//		System.out.println("Here is the session passed into the getAll() method" + s.toString());
		Commitment[] personal = null;
		Commitment[] team = null;
		Collection<Commitment> combined = new ArrayList<Commitment>();
		try{// return combined personal and team commitments
			personal = db.retrieve(Commitment.class, "username", s.getUsername()).toArray(new Commitment[0]);
			team =  db.retrieveAll(new Commitment(null, null, null), s.getProject()).toArray(new Commitment[0]);
			System.out.println("Team "+team.toString());
			System.out.println("personal "+personal.toString());
			//
			combined.addAll(Arrays.asList(personal));
			combined.addAll(Arrays.asList(team));
			System.out.println("combined "+combined.toString());
			return combined.toArray(new Commitment[] {});
		}catch(WPISuiteException e){// no personal commitments found
			System.out.println("No Personal Commitments yet");
			return db.retrieveAll(new Commitment(null, null, null), s.getProject()).toArray(new Commitment[0]);
		}
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Commitment[] getEntity(Session s, String id) throws NotFoundException, WPISuiteException {

		final int intId = Integer.parseInt(id);
		if(intId < 1) {
			throw new NotFoundException("The Commitment with the specified id was not found:" + intId);
		}
		Commitment[] Commitments = null;

		// Try to retrieve the specific Commitment
		try {
			Commitments = db.retrieve(Commitment.class, "id", intId).toArray(new Commitment[0]);
		} catch (WPISuiteException e) { // caught and re-thrown with a new message
			e.printStackTrace();
			throw new WPISuiteException("There was a problem retrieving from the database." );
		}

		// If a Commitment was pulled, but has no content
		if(Commitments.length < 1 || Commitments[0] == null) {
			throw new NotFoundException("The Commitment with the specified id was not found:" + intId);
		}
		return Commitments;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	// TODO This needs to be changed to allow for personal calendar
	public Commitment update(Session s, String content) throws WPISuiteException {
		// If there is no session
		if(s == null){
			throw new WPISuiteException("Null session.");
		}
		// The following code was modified from the requirement entity manager
		Commitment updatedCommitment = Commitment.fromJSON(content);

		List<Model> oldCommitments = db.retrieve(Commitment.class, "id", updatedCommitment.getId());
		if(oldCommitments.size() < 1 || oldCommitments.get(0) == null) {
			throw new BadRequestException("Commitment with ID does not exist.");
		}

		Commitment existingCommitment = (Commitment)oldCommitments.get(0);		


		existingCommitment.copy(updatedCommitment);

		if(existingCommitment.isTeamCommitment()){
			existingCommitment.setUsername(null);// case: switching from personal to team commitment

			if(!db.save(existingCommitment, s.getProject())) {
				throw new WPISuiteException();
			}

			return existingCommitment;
		}else{
			existingCommitment.setUsername(s.getUsername());// case: switching from team to personal
			if(!db.save(existingCommitment)) {
				throw new WPISuiteException();
			}

			return existingCommitment;

		}

	} 



	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// Attempt to get the entity, NotFoundException or WPISuiteException may be thrown	    	

		Commitment oldComm = getEntity(s,   id    )[0];
		if(oldComm.isTeamCommitment()){
			ensureRole(s, Role.ADMIN);
			System.out.println("From teamdelete i want to delete "+ oldComm.toJSON());
			Commitment commToBeDel = new Commitment(null, null, null);
			commToBeDel.setId(oldComm.getId());
			if (db.delete(commToBeDel)!=null){
				return true; // the deletion was successful
			}
		}else{
			System.out.println("From personal i want to delete "+ oldComm.toJSON());
			Commitment commToBeDel = new Commitment(null, null, null);
			commToBeDel.setId(oldComm.getId());
			commToBeDel.setUsername(s.getUsername());
			commToBeDel.setTeamCommitment(false);
			if (db.delete(commToBeDel)!=null){
				return true; // the deletion was successful
			}

		}

		return false; // The deletion was unsuccessful
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	public void deleteAll(Session s) throws WPISuiteException  {
		ensureRole(s, Role.ADMIN);
		db.deleteAll(new Commitment(null, null, null), s.getProject());
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