/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    
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
  EventList events;
  CommitmentList commitments;
  
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setProject(Project p) {
    // TODO Auto-generated method stub
    
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
    int eventsToCopy = donor.events.itemList.size();
    
    for (int i = 0; i < eventsToCopy; i++) {
      this.events.itemList[i] = donor.events.itemList[i];
    }
    //TODO: Fix this function. It doesn't look right.
  }
  
}
  /**
   * Copy all of the commitments from the donor calendar into this calendar.
   */
  public void copyCommitments(CalendarModel donor) {
    int commitsToCopy = donor.commitments.itemList.size();
  
    for (int i = 0; i < commitsToCopy; i++) {
      this.commitments.itemList[i] = donor.commitments.itemList[i];
    }
    //TODO: Fix this function. It doesn't look right.
  }

}
