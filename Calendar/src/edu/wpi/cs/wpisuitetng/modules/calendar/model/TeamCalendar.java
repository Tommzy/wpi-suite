/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Brittany Owens
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.model;


import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * This is a class for a Team Calendar structure. It includes information about the project it belongs to.
 * Each project should have only one TeamCalendar. The TeamCalendar can be viewed by all members of the team,
 * and members with the correct permissions will be able to add events and commitments to the TeamCalendar.
 * 
 * @author Team3
 * 
 */
public class TeamCalendar implements Model{

  String projID;
  SortedEventList events;
  SortedCommitmentList commitments;
  

  private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
  private Project project;

  // ------------------Interface Functions-----------------
  
  @Override
  public void save() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void delete() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public Boolean identify(Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Permission getPermission(User u) {
    return permissionMap.get(u);
  }

  @Override
  public void setPermission(Permission p, User u) {
    permissionMap.put(u, p);
  }

  @Override
  public Project getProject() {
    return project;
  }

  @Override
  public void setProject(Project p) {
    this.project = p;
  }
  
  //----------------------- Non-Interface Functions---------------------------
  
  /**
   * Constructor
   */
  TeamCalendar() {
    projID = "0";
  }
  
  /**
   * @return a JSON-encoded string representation of this message object
   */
  @Override
  public String toJSON() {
    return new Gson().toJson(this, TeamCalendar.class);
  }

  /**
   * Returns an instance of TeamCalendar constructed using the given
   * TeamCalendar encoded as a JSON string.
   * 
   * @param json the json-encoded TeamCalendar to parse
   * @return the TeamCalendar contained in the given JSON
   */
  public static TeamCalendar fromJSON(String json) {
    final Gson parser = new Gson();
    return parser.fromJson(json, TeamCalendar.class);
  }

  /**
   * @return the number ID of the project this TeamCalendar belongs to.
   */
  public String getProjID() {
    return this.projID;
  }
  
  /**
   * Sets the TeamCalendar's projID to the given value
   * @param newId string value to set projID to
   */
  public void setProjID(String newId) {
    projID = newId;
  }
  
  /**
   * Copy all calendar data from the donor calendar into this calendar.
   * @param donor The TeamCalendar we want to copy from!
   */
  public void copyFrom(TeamCalendar donor) {
    //First, copy the project
    setProject(donor.getProject());
    
    //Next, copy the list of events.
    copyEvents(donor);
    
    //Lastly, copy the list of commitments.
    copyCommitments(donor);
    
    //NOTE: When adding to Calendar, update this function! TODO
    
  }
  
  /**
   * Copy all of the events from the donor calendar into this calendar.
   */
  public void copyEvents(TeamCalendar donor) {
      this.events = donor.events;
  }
  
  /**
   * Copy all of the commitments from the donor calendar into this calendar.
   */
  public void copyCommitments(TeamCalendar donor) {
      this.commitments = donor.commitments;
  }
  
  /**
   * Add a commitment to the commitment list. 
   */
  public void addCommitment(Commitment newCommitment) {
    commitments.addCommitment(newCommitment);
  }
  
  /**
   * Add an event to the event list. 
   */
  public void addEvent(Event newEvent) {
    events.addEvent(newEvent);
  }
  
  /**
   * @return sorted list of the events that belong to this calendar.
   */
  public SortedEventList getEvents() {
    return events;
  }
  
  /**
   * @return sorted list of the commitments that belong to this callendar.
   */
  public SortedCommitmentList getCommitments() {
    return commitments;
  }
  
}