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
public class CategoryEntityManager implements EntityManager<Commitment> {

	/** The db. */
	private Data db;



	/**
	 * Instantiates a new category entity manager.
	 *
	 * @param data the data
	 */	
	public CategoryEntityManager(Data data) {
		db = data;

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Category makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {

		// Parse the Commitment from JSON
		final Category newCategory;
		try {
			newCategory = Category.fromJSON(content);
		} catch(JsonSyntaxException e){ // the JSON conversion failed
			throw new BadRequestException("The Category creation string had invalid formatting. Entity String: " + content);			
		}

		// Saves the Commitment in the database
		if(newCategory.isTeamCommitment()){
			this.save(s,newCategory); // An exception may be thrown here if we can't save it
		}else{
			this.save(newCategory);// assume personal commitment
		}

		// Return the newly created Category (this gets passed back to the client)
		return newCategory;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
	 */
	public void save(Session s, Category model) throws WPISuiteException {
		assignUniqueID(model); // Assigns a unique ID to the Req if necessary

		// Save the Category in the database if possible, otherwise throw an exception
		// We want the Category to be associated with the project the user logged in to
		if (!db.save(model, s.getProject())) {
			throw new WPISuiteException("Unable to save Category.");
		}
		System.out.println("The Category saved!    " + model.toJSON());
	}

	public void save(Category model) throws WPISuiteException {
		assignUniqueID(model); // Assigns a unique ID to the Req if necessary

		// Save the Category in the database if possible, otherwise throw an exception
		// We want the Category to be associated with the project the user logged in to
		if (!db.save(model)) {
			throw new WPISuiteException("Unable to save Category.");
		}
		System.out.println("The Category saved!    " + model.toJSON());
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


	/** Takes a Category and assigns a unique id if necessary
	 * 
	 * @param category The Category that possibly needs a unique id
	 * @throws WPISuiteException "Count failed"
	 */
	public void assignUniqueID(Category category) throws WPISuiteException{
		if (category.getId() == -1){// -1 is a flag that says a unique id is needed            
			category.setId(HighestId() + 1); // Assures that the category ID will be unique
		}
	}



	/** Returns the highest Id of all category in the database.
	 * @return The highest Id
	 * @throws WPISuiteException "Retrieve all failed"
	 */
	public int HighestId() throws WPISuiteException {
		List<Category> categoryList = db.retrieveAll(new Category(null, null)); //TODO : fix arguments if necessary
		Iterator<Category> itr = categoryList.iterator();
		int maxId = 0;
		while (itr.hasNext())
		{
			Category next = itr.next();
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
		//System.out.println("Here is the session passed into the Count() method"+db.retrieveAll(new Category(null, null)));
		return db.retrieveAll(new Category(null, null)).size();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	public Category[] getAll(Session s) throws WPISuiteException  {
		// Ask the database to retrieve all objects of the type Commitment.
		// Passing a dummy Category lets the db know what type of object to retrieve
		// Passing the project makes it only get Category from that project
		// Return the list of Commitments as an array
		//		System.out.println("Here is the session passed into the getAll() method" + s.toString());
		Category[] personal = null;
		Category[] team = null;
		Collection<Category> combined = new ArrayList<Category>();
		try{// return combined personal and team commitments
			personal = db.retrieve(Category.class, "username", s.getUsername(),s.getProject()).toArray(new Category[0]);
			team =  db.retrieveAll(new Category(null, null), s.getProject()).toArray(new Category[0]);
			combined.addAll(Arrays.asList(personal));
			combined.addAll(Arrays.asList(team));
			return combined.toArray(new Category[] {});
		}catch(WPISuiteException e){
			System.out.println("No Category yet");
			return db.retrieveAll(new Category(null, null, null), s.getProject()).toArray(new Category[0]);
		}
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public Category[] getEntity(Session s, String id) throws NotFoundException, WPISuiteException {

		final int intId = Integer.parseInt(id);
		if(intId < 1) {
			throw new NotFoundException("The Category with the specified id was not found:" + intId);
		}
		Category[] Categories = null;

		// Try to retrieve the specific Commitment
		try {
			Categories = db.retrieve(Category.class, "id", intId, s.getProject()).toArray(new Category[0]);
		} catch (WPISuiteException e) { // caught and re-thrown with a new message
			e.printStackTrace();
			throw new WPISuiteException("There was a problem retrieving from the database." );
		}

		// If a Category was pulled, but has no content
		if(Category.length < 1 || Category[0] == null) {
			throw new NotFoundException("The Category with the specified id was not found:" + intId);
		}
		return Categories;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	// TODO This needs to be changed to allow for personal calendar
	public Category update(Session s, String content) throws WPISuiteException {
		// If there is no session
		if(s == null){
			throw new WPISuiteException("Null session.");
		}
		// The following code was modified from the requirement entity manager
		Category updatedCategory = Category.fromJSON(content);

		List<Model> oldCategories = db.retrieve(Category.class, "id", updatedCategory.getId(), s.getProject());
		if(oldCategories.size() < 1 || oldCategories.get(0) == null) {
			throw new BadRequestException("Category with ID does not exist.");
		}

		Category existingCategory = (Category)oldCategories.get(0);		


		existingCategory.copy(updatedCategory);

		if(!db.save(existingCategory, s.getProject())) {
			throw new WPISuiteException();
		}

		return existingCategory;

	} 



	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// Attempt to get the entity, NotFoundException or WPISuiteException may be thrown	    	
		ensureRole(s, Role.ADMIN);
		Category oldCat = getEntity(s,   id    )[0];
		Category commToBeDel = new Category(null, null, null);
		catToBeDel.setId(oldCat.getId());

		if (db.delete(catToBeDel)!=null){
			return true; // the deletion was successful
		}	    
		return false; // The deletion was unsuccessful
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	public void deleteAll(Session s) throws WPISuiteException  {
		ensureRole(s, Role.ADMIN);
		db.deleteAll(new Category(null, null, null), s.getProject());
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