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

/** This is the entity manager for Commitments in the CommitmentEntityManager module. The provided
 *  methods include functionality for creating, updating, getting specific Commitments, and 
 *  getting all Commitments. Current, Commitments are project specific, so
 *  Commitments pulled from the database will only be for the current current project.
 *     
 * This is the entity manager for the Commitment in the
 * Calendar module.
 *
 * @version $Revision: 1.0 $
 * @author Hui Zheng & EJ
 */
public class CommitmentEntityManager implements EntityManager<Commitment> {
	/** The database */
	private Data db;



	/** Constructs the entity manager. This constructor is called by
	 * {@link edu.wpi.cs.wpisuitetng.ManagerLayer#ManagerLayer()}. 
	 * To make sure this happens, be sure to place add this entity 
	 * manager to the map in the ManagerLayer file.
	 * 
	 * NOTE: This expects that the data passed is valid and does no error checking!
	 *
	 * @param data Database in the core
	 */	
	public CommitmentEntityManager(Data data) {
		db = data;

	}

	/** Takes an encoded Commitment(as a string) and converts it back to a 
	 *  Commitment and saves it in the database
	 *  
	 *	@param s The current user session
	 *	@param content The Commitment that comes in the form of a string to be recreated
	 *	@return the Commitment that originally came as a string
	 * 	@throws BadRequestException "The Commitment creation string had invalid formatting. Entity String: " + content
	 * 	@throws ConflictException "A Commitment with the given ID already exists. Entity String: " + content
	 * 	@throws WPISuiteException "Unable to save Commitment."
	 * 	@see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(Session, String)
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
		this.save(s,newCommitment); // An exception may be thrown here if we can't save it

		// Return the newly created Commitment (this gets passed back to the client)
		return newCommitment;
	}

	/** Saves the given Commitment into the database if possible.
	 * 
	 *  @param s The current user session
	 *  @param model The Commitment to be saved to the database
	 *  @throws WPISuiteException  "Unable to save Commitment."
	 *  **********************************HERE WE Changed The Code!!!!!!************************************************
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
	
	
	/**
	 * Ensures that a user is of the specified role
	 * @param session the session
	 * @param role the role being verified
	
	 * @throws WPISuiteException user isn't authorized for the given role */
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
			commitment.setId(Count() + 1); // Makes first Commitment have id = 1
//			commitment.setId(300);
		}
//		commitment.setId(Count() + 1);
	}

	/** Returns the number of Commitments currently in the database. Disregards
	 *  the current user session
	 * 
	 * @return The number of Commitments currently in the database
	 * @throws WPISuiteException "Retrieve all failed"
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
	 */
	public int Count() throws WPISuiteException {
		// Passing a dummy Commitment lets the db know what type of object to retrieve
		//System.out.println("Here is the session passed into the Count() method"+db.retrieveAll(new Commitment(null, null, null)));
		return db.retrieveAll(new Commitment(null, null, null)).size();
	}

	/** Takes a session and returns an array of all the Commitments contained
	 * 
	 * @param s The current user session
	 * @return An array of all Commitments in the Database
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session)
	 */
	public Commitment[] getAll(Session s)  {
		// Ask the database to retrieve all objects of the type Commitment.
		// Passing a dummy Commitment lets the db know what type of object to retrieve
		// Passing the project makes it only get Commitments from that project
		// Return the list of Commitments as an array
//		System.out.println("Here is the session passed into the getAll() method" + s.toString());
		return db.retrieveAll(new Commitment(null, null, null), s.getProject()).toArray(new Commitment[0]);

	}

	/**  For the current user session, Takes a specific id for a Commitment and returns it 
	 *   in an array.	
	 *  
	 *  @param s  The current user session
	 *  @param id Points to a specific Commitment
	 *  @return An array of Commitments
	 *	@throws NotFoundException  "The Commitment with the specified id was not found:" + intId
	 *	@throws WPISuiteException  "There was a problem retrieving from the database."
	 *	@see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(Session, String)
	 */
	public Commitment[] getEntity(Session s, String id) throws NotFoundException, WPISuiteException {

		final int intId = Integer.parseInt(id);
		if(intId < 1) {
			throw new NotFoundException("The Commitment with the specified id was not found:" + intId);
		}
		Commitment[] Commitments = null;

		// Try to retrieve the specific Commitment
		try {
			Commitments = db.retrieve(Commitment.class, "id", intId, s.getProject()).toArray(new Commitment[0]);
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

	/**  Updates a Commitment already in the database
	 *   
	 *  @param s The current user session
	 *  @param content The Commitment to be update + the updates
	 * 	@return the changed Commitment
	 *  @throws WPISuiteException  "There was a problem retrieving from the database."   or "Null session."
	 *  @throws NotFoundException  "The Commitment with the specified id was not found:" + intId
	 *	@see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(Session, String)
	 */
	public Commitment update(Session s, String content) throws WPISuiteException {
		// If there is no session
		if(s == null){
			throw new WPISuiteException("Null session.");
		} 

		// Try to parse the message
		final Commitment updatedCommitment;
		try {
			updatedCommitment = Commitment.fromJSON(content);
		} catch(JsonSyntaxException e){ // the JSON conversion failed
			throw new BadRequestException("The Commitment update string had invalid formatting. Entity String: " + content);			
		}
		/*
		// Pull out the name of the current user
		String currentUser = s.getUser().getName();
		// Attempt to get the entity, NotFoundException or WPISuiteException may be thrown	    	
		Commitment oldReq = getEntity(s, Integer.toString(  reqUpdate.getId()  )  )[0];

		oldReq.setStartTime(reqUpdate.getStartTime());
		oldReq.setName(reqUpdate.getName());
		oldReq.setDescription(reqUpdate.getDescription());
		*/
		//TODO put this back in
		// Copy new field values into old Commitment. This is because the "same" model must
		// be saved back into the database
//		oldReq.updateReq(reqUpdate);

		// Attempt to save. WPISuiteException may be thrown
		this.save(s,updatedCommitment);

		return updatedCommitment;
	}

	/** Deletes a Commitment from the database permanently. It is not advised that this
	 *  implementation be used because the current unique ID system depends on all Commitments
	 *  existing in the DB forever. In the future, a stronger unique ID system could be implemented
	 *  and this method would be valuable at that time. In the current GUI implementation, there is 
	 *  no way to delete a Commitment other than setting its status, in which case it still 
	 *  exists, but has a deleted status. 
	 *  
	 *  @param s The current user session
	 *  @param id The unique of the Commitment to delete
	 *  @return TRUE if successful or FALSE if it fails
	 *	@throws WPISuiteException  "There was a problem retrieving from the database."   or "Null session."
	 *  @throws NotFoundException  "The Commitment with the specified id was not found:" + intId
	 *	@see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(Session, String)
	 */
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// Attempt to get the entity, NotFoundException or WPISuiteException may be thrown	    	
		ensureRole(s, Role.ADMIN);
		Commitment oldReq = getEntity(s,   id    )[0];

		if (db.delete(oldReq) == oldReq){
			return true; // the deletion was successful
		}	    
		return false; // The deletion was unsuccessful
	}

	/** Deletes ALL Commitment from the database permanently. It is not advised that this
	 *  implementation be used because the current unique ID system depends on all Commitments
	 *  existing in the DB forever. In the future, a stronger unique ID system could be implemented
	 *  and this method would be valuable at that time. In the current GUI implementation, there is 
	 *  no way to delete a Commitment other than setting its status, in which case it still 
	 *  exists, but has a deleted status. 
	 * 
	 *  @param s The current user session
	 * @throws WPISuiteException 
	 *  @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(Session)
	 */
	public void deleteAll(Session s) throws WPISuiteException  {
		ensureRole(s, Role.ADMIN);
		db.deleteAll(new Commitment(null, null, null), s.getProject());
	}

	//The following methods are not implemented but required by the "EntityManager" interface:
	
	/** Method advancedGet. (Not implemented but required by the "EntityManager" interface)
	 * @param s Session
	 * @param args String[]
	 * @return String
	 * @throws WPISuiteException
	 * @throws WPISuiteException, NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(Session, String[], String)
	 */
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		throw new NotImplementedException();
	}

	/** Method advancedPut. (Not implemented but required by the "EntityManager" interface)
	 * @param s Session
	 * @param args String[]
	 * @param content String
	 * @return String
	 * @throws WPISuiteException
	 * @throws WPISuiteException, NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(Session, String[], String)
	 */
	public String advancedPut(Session s, String[] args, String content)
			throws WPISuiteException {
		throw new NotImplementedException();
	}

	/** Method advancedPost. (Not implemented but required by the "EntityManager" interface)
	 * @param s Session
	 * @param string String
	 * @param content String
	 * @return String
	 * @throws WPISuiteException
	 * @throws WPISuiteException, NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(Session, String, String)
	 */
	public String advancedPost(Session s, String string, String content)
			throws WPISuiteException {
		throw new NotImplementedException();
	}


}