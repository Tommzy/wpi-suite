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

import java.util.Calendar;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * This is a class for a Calendar structure. It includes all information related to one calendar.
 * 
 * @author Team3
 * 
 */
public abstract class CalendarModel implements Model {
  SortedEventList events;
  SortedCommitmentList commitments;
  Project project;
  
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setPermission(Permission p, User u) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Project getProject() {
    return project;
  }

  @Override
  public void setProject(Project p) {
    project = p;
  }
  
  //----------------------- Non-Interface Functions---------------------------
  
  /**
   * Copy all calendar data from the donor calendar into this calendar.
   */
  public void copyFrom(CalendarModel donor) {
    //First, copy the project
    setProject(donor.getProject());
    
    //Next, copy the list of events.
    copyEvents(donor);
    
    //Lastly, copy the list of commitments.
    copyCommitments(donor);
    
    //NOTE: When adding to Calendar, update this function! PWA!
    
  }
  
  /**
   * Copy all of the events from the donor calendar into this calendar.
   */
  public void copyEvents(CalendarModel donor) {
      this.events = donor.events;
  }
  
  /**
   * Copy all of the commitments from the donor calendar into this calendar.
   */
  public void copyCommitments(CalendarModel donor) {
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
