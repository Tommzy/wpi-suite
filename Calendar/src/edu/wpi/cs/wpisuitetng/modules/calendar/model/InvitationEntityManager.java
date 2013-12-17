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
 * The Class InvitationEntityManager.
 * 
 * @author Eric
 */
public class InvitationEntityManager implements EntityManager<Invitation> {

  /** The db. */
  private Data db;



  /**
   * Instantiates a new invitation entity manager.
   *
   * @param data the data
   */ 
  public InvitationEntityManager(Data data) {
    db = data;
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
   */
  public Invitation makeEntity(Session s, String content)
      throws BadRequestException, ConflictException, WPISuiteException {

    // Parse the Invitation from JSON
    final Invitation invite;
    try {
      invite = Invitation.fromJSON(content);
    } catch(JsonSyntaxException e){ // the JSON conversion failed
      throw new BadRequestException("The Invitation creation string had invalid formatting. Entity String: " + content);      
    }

    this.save(s,invite);
    

    // Return the newly created Invitation (this gets passed back to the client)
    return invite;
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
   */
  public void save(Session s, Invitation model) throws WPISuiteException {
    assignUniqueID(model); // Assigns a unique ID to the Req if necessary

    // Save the Invitation in the database if possible, otherwise throw an exception
    // We want the Invitation to be associated with the project the user logged in to
    if (!db.save(model, s.getProject())) {
      throw new WPISuiteException("Unable to save invitation.");
    }
    System.out.println("The invitation saved!    " + model.toJSON());
  }

  /**
   * Save.
   *
   * @param model the model
   * @throws WPISuiteException the wPI suite exception
   */
  public void save(Invitation model) throws WPISuiteException {
    assignUniqueID(model); // Assigns a unique ID to the Req if necessary

    // Save the Invitation in the database if possible, otherwise throw an exception
    // We want the Invitation to be associated with the project the user logged in to
    if (!db.save(model)) {
      throw new WPISuiteException("Unable to save invitaiton.");
    }
    System.out.println("The invitation saved!    " + model.toJSON());
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
   * Takes a Invitation and assigns a unique id if necessary.
   *
   * @param invite the invite
   * @throws WPISuiteException "Count failed"
   */
  public void assignUniqueID(Invitation invite) throws WPISuiteException{
    if (invite.getId() == -1){// -1 is a flag that says a unique id is needed            
      invite.setId(HighestId() + 1); // Assures that the Invitation ID will be unique
    }
  }



  /** Returns the highest Id of all Invitation in the database.
   * @return The highest Id
   * @throws WPISuiteException "Retrieve all failed"
   */
  public int HighestId() throws WPISuiteException {
    List<Invitation> inviteList = db.retrieveAll(new Invitation(null, null, null));
    Iterator<Invitation> itr = inviteList.iterator();
    int maxId = 0;
    while (itr.hasNext())
    {
      Invitation next = itr.next();
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
    // Passing a dummy Invitation lets the db know what type of object to retrieve
    //System.out.println("Here is the session passed into the Count() method"+db.retrieveAll(new Invitation(null, null, null)));
    return db.retrieveAll(new Invitation(null, null, null)).size();
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
   */
  public Invitation[] getAll(Session s) throws WPISuiteException  {
    // Ask the database to retrieve all objects of the type Invitation.
    // Passing a dummy Invitation lets the db know what type of object to retrieve
    // Passing the project makes it only get Invitations from that project
    // Return the list of Invitations as an array
      return db.retrieveAll(new Invitation(null, null, null), s.getProject()).toArray(new Invitation[0]);
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
   */
  public Invitation[] getEntity(Session s, String id) throws NotFoundException, WPISuiteException {

    final int intId = Integer.parseInt(id);
    if(intId < 1) {
      throw new NotFoundException("The invitation with the specified id was not found:" + intId);
    }
    Invitation[] invites = null;

    // Try to retrieve the specific Invitation
    try {
      invites = db.retrieve(Invitation.class, "id", intId, s.getProject()).toArray(new Invitation[0]);
    } catch (WPISuiteException e) { // caught and re-thrown with a new message
      e.printStackTrace();
      throw new WPISuiteException("There was a problem retrieving from the database." );
    }

    // If a Invitation was pulled, but has no content
    if(invites.length < 1 || invites[0] == null) {
      throw new NotFoundException("The invitaiton with the specified id was not found:" + intId);
    }
    return invites;
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
   */
  // TODO This needs to be changed to allow for personal calendar
  public Invitation update(Session s, String content) throws WPISuiteException {
    // If there is no session
    if(s == null){
      throw new WPISuiteException("Null session.");
    }
    // The following code was modified from the requirement entity manager
    Invitation updatedInvite = Invitation.fromJSON(content);

    List<Model> oldInvite = db.retrieve(Invitation.class, "id", updatedInvite.getId(), s.getProject());
    if(oldInvite.size() < 1 || oldInvite.get(0) == null) {
      throw new BadRequestException("Invitation with ID does not exist.");
    }

    Invitation existingInvite = (Invitation)oldInvite.get(0);    


    existingInvite.copy(updatedInvite);

    if(!db.save(existingInvite, s.getProject())) {
      throw new WPISuiteException();
    }

    return existingInvite;

  } 



  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
   */
  public boolean deleteEntity(Session s, String id) throws WPISuiteException {
    // Attempt to get the entity, NotFoundException or WPISuiteException may be thrown        
    ensureRole(s, Role.ADMIN);
    Invitation oldInvite = getEntity(s,   id    )[0];
    Invitation inviteToBeDel = new Invitation(null, null, null);
    inviteToBeDel.setId(oldInvite.getId());

    if (db.delete(inviteToBeDel)!=null){
      return true; // the deletion was successful
    }     
    return false; // The deletion was unsuccessful
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
   */
  public void deleteAll(Session s) throws WPISuiteException  {
    ensureRole(s, Role.ADMIN);
    db.deleteAll(new Invitation(null, null, null), s.getProject());
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