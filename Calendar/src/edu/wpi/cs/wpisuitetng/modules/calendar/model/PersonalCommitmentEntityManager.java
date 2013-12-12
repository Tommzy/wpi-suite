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
 *Authors: E.J. Murphy
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
 * The Class CommitmentEntityManager.
 */
public class PersonalCommitmentEntityManager implements EntityManager<Commitment> {
	
	/** The db. */
	private Data db;



	/**
	 * Instantiates a new commitment entity manager.
	 *
	 * @param data the data
	 */	
	public PersonalCommitmentEntityManager(Data data) {
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
			newCommitment.setUsername(s.getUsername());
		} catch(JsonSyntaxException e){ // the JSON conversion failed
			throw new BadRequestException("The Commitment creation string had invalid formatting. Entity String: " + content);			
		}

		// Saves the Commitment in the database
		this.save(newCommitment); // An exception may be thrown here if we can't save it

		// Return the newly created Commitment (this gets passed back to the client)
		return newCommitment;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
	 */
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
	public Commitment[] getAll(Session s) throws NotFoundException, WPISuiteException  {
		// Ask the database to retrieve all objects of the type Commitment.
		// Passing a dummy Commitment lets the db know what type of object to retrieve
		// Passing the project makes it only get Commitments from that project
		// Return the list of Commitments as an array
		//		System.out.println("Here is the session passed into the getAll() method" + s.toString());
		return db.retrieveAll(new Commitment(null, null, null), s.getProject()).toArray(new Commitment[0]);

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Commitment[] getEntity(Session s) throws NotFoundException, WPISuiteException {
		/*
		final int intId = Integer.parseInt(id);
		if(intId < 1) {
			throw new NotFoundException("The Commitment with the specified id was not found:" + intId);
		}
		*/
		Commitment[] Commitments = null;

		// Try to retrieve the specific Commitment
		try {
			Commitments = db.retrieve(Commitment.class, "username", s.getUser().getUsername(), s.getProject()).toArray(new Commitment[0]);
		} catch (WPISuiteException e) { // caught and re-thrown with a new message
			e.printStackTrace();
			throw new WPISuiteException("There was a problem retrieving from the database." );
		}

		// If a Commitment was pulled, but has no content
		if(Commitments.length < 1 || Commitments[0] == null) {
			throw new NotFoundException("The Commitment with the specified username was not found:" + intId);
		}
		return Commitments;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Commitment update(Session s, String content) throws WPISuiteException {
		// If there is no session
		if(s == null){
			throw new WPISuiteException("Null session.");
		}
		// The following code was modified from the requirement entity manager
		Commitment updatedCommitment = Commitment.fromJSON(content);

		List<Model> oldCommitments = db.retrieve(Commitment.class, "id", updatedCommitment.getId(), s.getProject());
		if(oldCommitments.size() < 1 || oldCommitments.get(0) == null) {
			throw new BadRequestException("Commitment with ID does not exist.");
		}

		Commitment existingCommitment = (Commitment)oldCommitments.get(0);		


		existingCommitment.copy(updatedCommitment);

		if(!db.save(existingCommitment, s.getProject())) {
			throw new WPISuiteException();
		}

		return existingCommitment;

	} 



	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public boolean deleteEntity(Session s, String username) throws WPISuiteException {
		// Attempt to get the entity, NotFoundException or WPISuiteException may be thrown	    	
		ensureRole(s, Role.ADMIN);
		Commitment oldComm = getEntity(s,   username    )[0];
		Commitment commToBeDel = new Commitment(null, null, null);
		commToBeDel.setUsername(oldComm.getUsername());
		
		if (db.delete(commToBeDel)!=null){
			return true; // the deletion was successful
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

	@Override
	public Commitment[] getEntity(Session s, String id)
			throws NotFoundException, WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Session s, Commitment model) throws WPISuiteException {
		// TODO Auto-generated method stub
		
	}


}