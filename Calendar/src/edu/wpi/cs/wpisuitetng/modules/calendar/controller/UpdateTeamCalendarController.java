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

import edu.wpi.cs.wpisuitetng.modules.calendar.model.TeamCalendar;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateTeamCalendarRequestObserver;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user updates something on the TeamCalendar by
 * adding the contents of the TeamCalendar to the model as a new
 * TeamCalendar.
 */

public class UpdateTeamCalendarController {

  private static UpdateTeamCalendarController instance;
  private UpdateTeamCalendarRequestObserver observer;

  /**
   * Construct an UpdateTeamCalendarController for the given model.
   */
  private UpdateTeamCalendarController() {
    observer = new UpdateTeamCalendarRequestObserver(this);
  }

  /**
   * @return the instance of the UpdateTeamCalendarController or creates one if it does not
   * exist. */
  public static UpdateTeamCalendarController getInstance()
  {
    if(instance == null)
    {
      instance = new UpdateTeamCalendarController();
    }
    return instance;
  }

  /**
   * This method updates a TeamCalendar to the server.
   * @param newTeamCalendar is the TeamCalendar to be updated to the server.
   */
  public void updateTeamCalendar(TeamCalendar newTeamCalendar) 
  {
    Request request = Network.getInstance().makeRequest("calendar/teamcalendar", HttpMethod.POST); // POST == update
    request.setBody(newTeamCalendar.toJSON()); // put the new Iteration in the body of the request
    request.addObserver(observer); // add an observer to process the response
    request.send(); 
  }
  
  
}
