/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: CalDev
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.TeamCalendar;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user creates a new project
 * to make a new TeamCalendar linked with that project
 * and save it to the database.
 */


public class AddTeamCalendarController implements ActionListener{
  
  private static AddTeamCalendarController instance;
  private AddTeamCalendarRequestObserver observer;
  
  /**
   * Construct an AddTeamCalendarController for the given model, view pair
   */
  private AddTeamCalendarController() {
    observer = new AddTeamCalendarRequestObserver(this);
  }
  
  /**
   * @return the instance of the AddTeamCalendarController or creates one if it does not
   * exist. */
  public static AddTeamCalendarController getInstance()
  {
    if(instance == null)
    {
      instance = new AddTeamCalendarController();
    }
    
    return instance;
  }

  /**
   * This method adds a TeamCalendar to the server.
   * @param newIteration is the TeamCalendar to be added to the server.
   */
  public void addTeamCalendar(TeamCalendar newTeamCalendar) 
  {
    final Request request = Network.getInstance().makeRequest("calendar/teamcalendar", HttpMethod.PUT); // PUT == create
    request.setBody(newTeamCalendar.toJSON()); // put the new Iteration in the body of the request		
    request.addObserver(observer); // add an observer to process the response
    request.send(); 
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  } 

}
