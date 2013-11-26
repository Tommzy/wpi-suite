/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CalDev
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.List;

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
import edu.wpi.cs.wpisuitetng.modules.calendar.model.TeamCalendar;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class TeamCalendarEntityManager implements EntityManager<TeamCalendar> {
  
  //The database
  Data db;
  
  //-------------Functions------------
  /**
   * Receives a message in JSON form and parses it into an actual TeamCalendar,
   *   then saves it to the database.
   *   
   * @param s Session object containing info about the user making the request
   * @param content JSON string to be converted.
   * 
   * @return A new TeamCalendar derived from the JSON string
   * 
   */
  @Override
  public TeamCalendar makeEntity(Session s, String content)
      throws BadRequestException, ConflictException, WPISuiteException {

    // Parse the message from JSON
    final TeamCalendar newTeamCalendar = TeamCalendar.fromJSON(content);

    // Save the message in the database if possible, otherwise throw an exception
    // We want the message to be associated with the project the user logged in to
    if (!db.save(newTeamCalendar, s.getProject())) {
      throw new WPISuiteException();
    }

    // Return the newly created message (this gets passed back to the client)
    return newTeamCalendar;
  }

  /**
   * Retrieves an array of a single TeamCalendar from the database
   * @param s the session
   * @param id the id number of the TeamCalendar to retrieve
   * @return the TeamCalendar matching the given id 
   * @throws NotFoundException
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(Session, String) 
   */
  @Override
  public TeamCalendar[] getEntity(Session s, String id)
    throws NotFoundException, WPISuiteException {
      final int intId = Integer.parseInt(id);
      if(intId < 1) {
        throw new NotFoundException();
      }
      TeamCalendar[] TeamCalendars = null;
      try {
        TeamCalendars = db.retrieve(TeamCalendar.class, "ProjID", intId, s.getProject()).toArray(new TeamCalendar[0]);
      } catch (WPISuiteException e) {
        e.printStackTrace();
      }
      if(TeamCalendars.length < 1 || TeamCalendars[0] == null) {
        throw new NotFoundException();
      }
      return TeamCalendars;
  }

  /**
   * @param s Info about the user making the request
   * @return all TeamCalendars that have been saved in the database
   */
  @Override
  public TeamCalendar[] getAll(Session s) throws WPISuiteException {

    // Ask the database to retrieve all objects of the type TeamCalendar.
    // Passing a dummy TeamCalendar lets the db know what type of object to retrieve
    // Passing the project makes it only get the Calendar(s?) from that project
    List<Model> tCalendars = db.retrieveAll(new TeamCalendar(), s.getProject());

    // Return the list of messages as an array
    return tCalendars.toArray(new TeamCalendar[0]);
  }

  /**
   * Updates the given TeamCalendar in the database
   * @param session the session the TeamCalendar to be updated is in
   * @param content the updated TeamCalendar as a Json string
   * @return the old TeamCalendar prior to updating 
   * @throws WPISuiteException
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(Session, String) */
  @Override
  public TeamCalendar update(Session s, String content)
      throws WPISuiteException {

		TeamCalendar updatedTeamCalendar = TeamCalendar.fromJson(content);

		 // Because of the disconnected objects problem in db4o, we can't just save updatedTeamCalendar.
		 // We have to get the original TeamCalendar from db4o, copy properties from updatedTeamCalendar,
		 // then save the original TeamCalendar again.

		List<Model> oldTeamCalendars = db.retrieve(TeamCalendar.class, "projID", TeamCalendar.getProjID(), session.getProject());
		if(oldTeamCalendars.size() < 1 || oldTeamCalendars.get(0) == null) {
			throw new BadRequestException("TeamCalendar with ID does not exist.");
		}
				
		TeamCalendar existingTeamCalendar = (TeamCalendar)oldTeamCalendars.get(0);		

		// copy values to old TeamCalendar and fill in our changeset appropriately
		existingTeamCalendar.copyFrom(updatedTeamCalendar);
		
		if(!db.save(existingTeamCalendar, session.getProject())) {
			throw new WPISuiteException();
		}
		
		return existingTeamCalendar;
  }

  /**
   * Save the given calendar in the database
   */
  @Override
  public void save(Session s, TeamCalendar model) throws WPISuiteException {
    
    db.save(model);
  }
  
  /**
  * Deletes a TeamCalendar from the database
  * @param s the session
  * @param id the id of the TeamCalendar to delete
  * @return true if the deletion was successful 
  * @throws WPISuiteException
  * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(Session, String) */
  @Override
  public boolean deleteEntity(Session s, String id) throws WPISuiteException {
    ensureRole(s, Role.ADMIN);
    return (db.delete(getEntity(s, id)[0]) != null) ? true : false;
  }

  @Override
  public String advancedGet(Session s, String[] args)
	  throws NotImplementedException {
    throw new NotImplementedException();
  }

  
  /**
   * Deletes all TeamCalendars from the database
   * @param s the session	
   * @throws WPISuiteException
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(Session)
   */
  @Override
  public void deleteAll(Session s) throws WPISuiteException {
    ensureRole(s, Role.ADMIN);
  db.deleteAll(new TeamCalendar(), s.getProject());
  }

  /**
   * @return the number of TeamCalendars currently in the database
   */
  @Override
  public int Count() throws WPISuiteException {
    return db.retrieveAll(new TeamCalendar()).size();
  }

  @Override
  public String advancedPut(Session s, String[] args, String content)
	  throws NotImplementedException {
		throw new NotImplementedException();
  }

  @Override
  public String advancedPost(Session s, String string, String content)
	  throws NotImplementedException {
		throw new NotImplementedException();
  }

  /**
   * Ensures that a user is of the specified role
   * @param session the session
   * @param role the role being verified
   * @throws WPISuiteException user isn't authorized for the given role 
   */
  private void ensureRole(Session session, Role role) throws WPISuiteException {
    User[] userArray = new User[2];
    User user = db.retrieve(User.class, "username", session.getUsername()).toArray(userArray)[0];
    if(!user.getRole().equals(role)) {
      throw new UnauthorizedException();
    }
  }
  
}
