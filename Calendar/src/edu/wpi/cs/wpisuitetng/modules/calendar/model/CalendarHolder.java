/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: CalDev
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddTeamCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetTeamCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateTeamCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.TeamCalendar;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.PersonalCalendar;

/**
 * Singleton holds local copies of personal and team calendars pulled from the server.
 */

public class CalendarHolder {
  
  // the static object to allow the CalendarHolder to be a singleton
  private static CalendarHolder instance;
  
  //The team calendar for the project that this user belongs to.
  private TeamCalendar teamCalendar;
  
  //The personal calendar for the current user.
  private PersonalCalendar personalCalendar;
  
  private CalendarHolder() {
    GetTeamCalendarController.getInstance().retrieveTeamCalendars();
    //GetPersonalCalendarController.getInstance.retrievePersonalCalendars();
    
  }
  
  /**
   * @return the instance of the CalendarHolder singleton. */
  public static CalendarHolder getInstance() {
    if (instance == null) {
      instance = new CalendarHolder();
    }
    return instance;
  }
  
  public void addTeamCalendar(TeamCalendar newTeamCalendar) {
    teamCalendar = newTeamCalendar;
  }
  
  public SortedEventList getTeamEvents() {
    return teamCalendar.getEvents();
  }
  
  public SortedEventList getPersonalEvents() {
    return personalCalendar.getEvents();
  }

  public SortedCommitmentList getTeamCommitments() {
    return teamCalendar.getCommitments();
  }
  
  public SortedCommitmentList getPersonalCommitments() {
    return personalCalendar.getCommitments();
  }
  
  public void addTeamCommitment (Commitment newCommitment) {
    teamCalendar.addCommitment(newCommitment);
    this.updateTeamCalendar();
  }
  
  public void addPersonalCommitment (Commitment newCommitment) {
    personalCalendar.addCommitment(newCommitment);
  }

  public void addTeamEvent (Event newEvent) {
    teamCalendar.addEvent(newEvent);
    this.updateTeamCalendar();
  }
  
  public void addPersonalEvent (Event newEvent) {
    personalCalendar.addEvent(newEvent);
  }
  /**
   * Updates the whole TeamCalendar in the database.
   */
  public void updateTeamCalendar() {
    UpdateTeamCalendarController.getInstance().updateTeamCalendar(teamCalendar);
  }
  
  //TODO: updatePersonalCalendar()
  //TODO: update functions for specific fields of each calendar.
  
  /**
   * This function is only called if the getTeamCalendarController can't find a TeamCalendar.
   * Creates a new TeamCalendar, sends it to the database, then retrieves it 
   * (since its projID will be updated when it is first sent to the database).
   */
  public void makeTeamCalendar () {
    AddTeamCalendarController.getInstance().addTeamCalendar(new TeamCalendar());
    GetTeamCalendarController.getInstance().retrieveTeamCalendars(); //If this can't retrieve the calendar made by the above function, then infinite loop may happen!
  }
  
}
