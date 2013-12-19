/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications
 * about UpdateInvitationRequest information as the UpdateInvitationRequest is constructed.
 */
public class UpdateInvitationRequestObserver implements RequestObserver {
  
  /** The controller. */
  private final UpdateInvitationController controller;
  
  /**
   * This method is called when information about an UpdateInvitationRequest
   * which was previously requested using an asynchronous
   * interface becomes available.
   *
   * @param controller the controller
   */
  public UpdateInvitationRequestObserver(UpdateInvitationController controller) {
    this.controller = controller;
  }
  
  
  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseSuccess(IRequest iReq) {
    // Get the response to the given request
    final ResponseModel response = iReq.getResponse();
    
    // Parse the invitation out of the response body
    final Invitation invite = Invitation.fromJSON(response.getBody());  
    UpdateInvitationController.getInstance().updateSucess(invite);
    System.out.println("Success! Here is GetInvitationRequestController in the JSON way"+ "   " + iReq.getResponse().getBody());
  }
  

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseError(IRequest iReq) {
    System.err.println(iReq.getResponse().getStatusMessage());
    System.err.println("The request to update an invitation failed.");
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
   */
  @Override
  public void fail(IRequest iReq, Exception exception) {
    System.err.println("The request to update an invitation failed.");
  }

}
