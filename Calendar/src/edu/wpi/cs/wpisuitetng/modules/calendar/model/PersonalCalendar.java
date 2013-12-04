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
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * This is a class for a Personal Calendar structure. It includes information about the user it belongs to.
 * 
 * @author Team3
 * 
 */

public class PersonalCalendar extends AbstractModel{
  //What data is associated with this calendar?
  String userID;
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

  /**
   * identify: true if the argument o is equal this object's unique identifier or this object
   * this method was created for use with the mock database
   * 
   * implementations overriding this method should check if o is either a unique identifier, or an instance of this class
   * if o is an instance of this class, this method should check if it contains the same unique identifier
   * 
   * @param o - a unique identifier belonging to an object
   * @return true if the o is equal to this Model's unique identifier, else false
   */
  @Override
  public Boolean identify(Object o) {
    //First check if this object and the one being passed in are the same thing!
    if (this.equals(o)) {
      return true; //They refer to the same object, so they are the same.
    }
    
    //Next check if this is a different PersonalCalendar. 
    else if (o instanceof PersonalCalendar) {
      
      //Now we need to check if their projID matches ours.
      if ( (((PersonalCalendar) o).getUserID()).equals(this.getUserID())) {
        return true; //the projID matches!
      }
      else {
        return false; //the projID doesn't match!
      }
    }//end else if 
    
    //Next check if it's a string.
    else if (o instanceof String) {
      if (((String) o).equals(this.getUserID())) { //Does it match our ID?
        return true; //it matches!
      }
      else {
        return false; //It doesn't match!
      }
    }//end else if
    
    else {//It's not a PersonalCalendar, and it's not a string
      return false; //It's probably not what we want.
    }//end else
    
  }

  /**
   * @return the permission associated with this PersonalCalendar
   */
  @Override
  public Permission getPermission(User u) {
    return permissionMap.get(u);
  }

  @Override
  public void setPermission(Permission p, User u) {
    permissionMap.put(u, p);
  }

  /**
   * @return the project associated with this PersonalCalendar
   */
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
   * Returns an instance of PersonalCalendar constructed using the given
   * JSON string.
   * 
   * @param json the json-encoded PersonalCalendar to parse
   * @return the PersonalCalendar contained in the given JSON string
   */
  public static PersonalCalendar fromJSON(String json) {
    final Gson parser = new Gson();
    return parser.fromJson(json, PersonalCalendar.class);
  }
  
  /**
   * @return a JSON-encoded string representation of this message object
   */
  @Override
  public String toJSON() {
    return new Gson().toJson(this, PersonalCalendar.class);
  }

  /**
   * Copy all calendar data from the donor calendar into this calendar.
   * @param donor The TeamCalendar we want to copy from.
   */
  public void copyFrom(PersonalCalendar donor) {
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
  public void copyEvents(PersonalCalendar donor) {
      this.events = donor.events;
  }
  
  /**
   * Copy all of the commitments from the donor calendar into this calendar.
   */
  public void copyCommitments(PersonalCalendar donor) {
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
//    events.addEvent(newEvent);
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
  
  /**
   * @return the number ID of the user this PersonalCalendar belongs to.
   */
  public String getUserID() {
    return this.userID;
  }
  
  /**
   * Sets the PersonalCalendar's userID to the given value
   * @param newId string value to set userID to
   */
  public void setUserID(String newId) {
    userID = newId;
  }
  
  /** Method toString. Outputs a JSON string.
   * @return String
   * @see edu.wpi.cs.wpisuitetng.modules.Model#toString()
   */
  @Override
  public String toString() {
          return this.toJSON();
  }
  
}
