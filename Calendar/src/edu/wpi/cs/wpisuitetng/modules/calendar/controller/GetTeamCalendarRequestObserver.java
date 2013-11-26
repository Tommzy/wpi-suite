/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.TeamCalendar;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetTeamCalendarController;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer handles responses to requests for all TeamCalendars
 */

public class GetTeamCalendarRequestObserver {

  private GetTeamCalendarController controller;
  
  /**
   * Constructs the observer given a GetTeamCalendarController
   * @param controller the controller used to retrieve Iterations
   */
  public GetTeamCalendarRequestObserver(GetTeamCalendarController controller) {
    this.controller = controller;
  }

  /**
   * Parse the TeamCalendars out of the response body and pass them to the controller
   * 
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseSuccess(IRequest iReq) {
    // Convert the JSON array of Iterations to a Iteration object array
    TeamCalendar[] teamCalendars = TeamCalendar.fromJsonArray(iReq.getResponse().getBody());	

    // Pass these Iterations to the controller
    controller.receivedTeamCalendars(teamCalendars);
  }

  /**
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseError(IRequest iReq) {
    fail(iReq, null);
  }

  /**
   * Put an error TeamCalendar in the CalendarHolder if the request fails.
   * 
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
   */
  @Override
  public void fail(IRequest iReq, Exception exception) {
    TeamCalendar[] errorTeamCalendar = { new TeamCalendar() };
    controller.receivedTeamCalendars(errorTeamCalendar);
  }

}
