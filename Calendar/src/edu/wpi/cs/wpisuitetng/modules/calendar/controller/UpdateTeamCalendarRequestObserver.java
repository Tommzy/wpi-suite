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
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateTeamCalendarController;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request
 * to the server to update a TeamCalendar.
 */

public class UpdateTeamCalendarRequestObserver implements RequestObserver{

  private final UpdateTeamCalendarController controller;
  
  /**
   * Constructs the observer given an UpdateTeamCalendarController
   * @param controller the controller used to update TeamCalendars
   */
  public UpdateTeamCalendarRequestObserver(UpdateTeamCalendarController controller) {
    this.controller = controller;
  }
  
  /**
   * Parse the TeamCalendar that was received from the server then pass them to
   * the controller.
   * 
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseSuccess(IRequest iReq) {
    // Get the response to the given request
    final ResponseModel response = iReq.getResponse();
    
    // Parse the Iteration out of the response body
    final TeamCalendar teamCalendar = TeamCalendar.fromJson(response.getBody());		
  }
  
  /**
   * Takes an action if the response results in an error.
   * Specifically, outputs that the request failed.
   * @param iReq IRequest
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(IRequest) */
  @Override
  public void responseError(IRequest iReq) {
    System.err.println(iReq.getResponse().getStatusMessage());
    System.err.println("The request to update a TeamCalendar failed.");
  }

  /**
   * Takes an action if the response fails.
   * Specifically, outputs that the request failed.
   * @param iReq IRequest
   * @param exception Exception
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(IRequest, Exception) */
  @Override
  public void fail(IRequest iReq, Exception exception) {
    System.err.println("The request to update a TeamCalendar failed.");
  }

}
