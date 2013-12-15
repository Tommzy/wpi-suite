/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors: CalDev
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

public class FilterEntityManager implements EntityManager<Filter> {

  /** The db. */
  private Data db;



  /**
   * Instantiates a new Filter entity manager.
   *
   * @param data the data
   */ 
  public FilterEntityManager(Data data) {
    db = data;

  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
   */
  public Filter makeEntity(Session s, String content)
      throws BadRequestException, ConflictException, WPISuiteException {

    // Parse the Filter from JSON
    final Filter newFilter;
    try {
      newFilter = Filter.fromJSON(content);
    } catch(JsonSyntaxException e){ // the JSON conversion failed
      throw new BadRequestException("The Filter creation string had invalid formatting. Entity String: " + content);      
    }
    
    if(newFilter.getUserId() == "-1"){
      newFilter.setUserId(s.getUsername());
      this.save(newFilter);
    }else{
      this.save(s,newFilter);
    }

    // Return the newly created Filter (this gets passed back to the client)
    return newFilter;
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
   */
  public void save(Session s, Filter model) throws WPISuiteException {
    assignUniqueID(model); // Assigns a unique ID to the Req if necessary

    // Save the Filter in the database if possible, otherwise throw an exception
    // We want the Filter to be associated with the project the user logged in to
    if (!db.save(model, s.getProject())) {
      throw new WPISuiteException("Unable to save Filter.");
    }
    System.out.println("The Filter saved!    " + model.toJSON());
  }

  public void save(Filter model) throws WPISuiteException {
    assignUniqueID(model); // Assigns a unique ID to the Req if necessary

    // Save the Filter in the database if possible, otherwise throw an exception
    // We want the Filter to be associated with the project the user logged in to
    if (!db.save(model)) {
      throw new WPISuiteException("Unable to save Filter.");
    }
    System.out.println("The Filter saved!    " + model.toJSON());
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


  /** Takes a Filter and assigns a unique id if necessary
   * 
   * @param Filter The Filter that possibly needs a unique id
   * @throws WPISuiteException "Count failed"
   */
  public void assignUniqueID(Filter Filter) throws WPISuiteException{
    if (Filter.getId() == -1){// -1 is a flag that says a unique id is needed            
      Filter.setId(HighestId() + 1); // Assures that the Filter ID will be unique
    }
  }



  /** Returns the highest Id of all Filter in the database.
   * @return The highest Id
   * @throws WPISuiteException "Retrieve all failed"
   */
  public int HighestId() throws WPISuiteException {
    List<Filter> FilterList = db.retrieveAll(new Filter(" ", null));
    Iterator<Filter> itr = FilterList.iterator();
    int maxId = 0;
    while (itr.hasNext())
    {
      Filter next = itr.next();
      if (next.getId() > maxId)
      {
        maxId = next.getId();
      }
    }
    return maxId;
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
   */
  public int Count() throws WPISuiteException {
    // Passing a dummy Filter lets the db know what type of object to retrieve
    //System.out.println("Here is the session passed into the Count() method"+db.retrieveAll(new Filter(null, null)));
    return db.retrieveAll(new Filter(" ", null)).size();
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
   */
  public Filter[] getAll(Session s) throws WPISuiteException  {
    // Ask the database to retrieve all objects of the type Filter.
    // Passing a dummy Filter lets the db know what type of object to retrieve
    // Passing the project makes it only get Filter from that project
    // Return the list of Filters as an array
    //    System.out.println("Here is the session passed into the getAll() method" + s.toString());
    
    
    //TODO: not sure if Filters can be team/personal
    Filter[] personal = null;
    Filter[] team = null;
    Collection<Filter> combined = new ArrayList<Filter>();
    try{// return combined personal and team Filters
      personal = db.retrieve(Filter.class, "userID", s.getUsername()).toArray(new Filter[0]);
      team =  db.retrieveAll(new Filter(" ", null), s.getProject()).toArray(new Filter[0]);
      combined.addAll(Arrays.asList(personal));
      combined.addAll(Arrays.asList(team));
      return combined.toArray(new Filter[] {});
    }catch(WPISuiteException e){
      System.out.println("No personal Filter yet");
      return db.retrieveAll(new Filter(" ", null), s.getProject()).toArray(new Filter[0]);
    }
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
   */
  public Filter[] getEntity(Session s, String id) throws NotFoundException, WPISuiteException {

    final int intId = Integer.parseInt(id);
    if(intId < 1) {
      throw new NotFoundException("The Filter with the specified id was not found:" + intId);
    }
    Filter[] Filters = null;

    // Try to retrieve the specific Filter
    try {
      Filters = db.retrieve(Filter.class, "id", intId).toArray(new Filter[0]);
    } catch (WPISuiteException e) { // caught and re-thrown with a new message
      e.printStackTrace();
      throw new WPISuiteException("There was a problem retrieving from the database." );
    }

    // If a Filter was pulled, but has no content
    if(Filters.length < 1 || Filters[0] == null) {
      throw new NotFoundException("The Filter with the specified id was not found:" + intId);
    }
    return Filters;
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
   */
  public Filter update(Session s, String content) throws WPISuiteException {
    // If there is no session
    if(s == null){
      throw new WPISuiteException("Null session.");
    }
    // The following code was modified from the requirement entity manager
    Filter updatedFilter = Filter.fromJSON(content);

    List<Model> oldFilters = db.retrieve(Filter.class, "id", updatedFilter.getId());
    if(oldFilters.size() < 1 || oldFilters.get(0) == null) {
      throw new BadRequestException("Filter with ID does not exist.");
    }

    Filter existingFilter = (Filter)oldFilters.get(0);   


    existingFilter.copy(updatedFilter);

    if(!db.save(existingFilter, s.getProject())) {
      throw new WPISuiteException();
    }

    return existingFilter;

  } 



  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
   */
  public boolean deleteEntity(Session s, String id) throws WPISuiteException {
    // Attempt to get the entity, NotFoundException or WPISuiteException may be thrown        

    Filter oldCat = getEntity(s, id)[0];
    Filter catToBeDel = new Filter(" ", null);
    catToBeDel.setId(oldCat.getId());
    catToBeDel.setUserId(oldCat.getUserId());
    if (db.delete(catToBeDel)!=null){
      return true; // the deletion was successful
    }
    return false; // The deletion was unsuccessful
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
   */
  public void deleteAll(Session s) throws WPISuiteException  {
    ensureRole(s, Role.ADMIN);
    db.deleteAll(new Filter(" ", null));
  }

  //The following methods are not implemented but required by the "EntityManager" interface:

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
   */
  public String advancedGet(Session s, String[] args)
      throws WPISuiteException {
    throw new NotImplementedException();
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
   */
  public String advancedPut(Session s, String[] args, String content)
      throws WPISuiteException {
    throw new NotImplementedException();
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
   */
  public String advancedPost(Session s, String string, String content)
      throws WPISuiteException {
    throw new NotImplementedException();
  }


}
