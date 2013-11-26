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
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarHolder;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetTeamCalendarRequestObserver;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the TeamCalendars
 * from the server. Hopefully there is only one associated with the current project.
 */

public class GetTeamCalendarController {

  private GetTeamCalendarRequestObserver observer;
  private static GetTeamCalendarController instance;
  
  /**
   * Constructs the controller given a TeamCalendar
   */
  private GetTeamCalendarController() {
	
    observer = new GetTeamCalendarRequestObserver(this);
  }
  
  /**
   * @return the instance of the GetTeamCalendarController or creates one if it does not
   * exist. */
  public static GetTeamCalendarController getInstance()
  {
    if(instance == null)
    {
      instance = new GetTeamCalendarController();
    }
  	
    return instance;
  }

  /**
   * Sends an HTTP request to store a TeamCalendar when the
   * update button is pressed
   * @param e ActionEvent
   * @see java.awt.event.ActionListener#actionPerformed(ActionEvent) */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Send a request to the core to save this Iteration
    final Request request = Network.getInstance().makeRequest("calendar/teamcalendar", HttpMethod.GET); // GET == read
    request.addObserver(observer); // add an observer to process the response
    request.send(); // send the request
  }
  
  /**
   * Sends an HTTP request to retrieve all Iterations
   */
  public void retrieveIterations() {
    final Request request = Network.getInstance().makeRequest("calendar/teamcalendar", HttpMethod.GET); // GET == read
    request.addObserver(observer); // add an observer to process the response
    request.send(); // send the request
  }
  
  /**
   * Add the given TeamCalendars to the local model (they were received from the core).
   * This method is called by the GetTeamCalendarRequestObserver
   * 
   * @param teamCalendar array of TeamCalendar received from the server
   */
  public void receivedTeamCalendars(TeamCalendar[] teamCalendar) {
    // Make sure the response was not null
    if (teamCalendar != null) 
    {	
      // add the Iterations to the local model
      CalendarHolder.getInstance().addTeamCalendar(teamCalendar[0]);
    }
  }
}
