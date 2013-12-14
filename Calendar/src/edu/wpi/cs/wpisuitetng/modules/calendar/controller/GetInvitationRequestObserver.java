/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller;


import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;


/**
 * An asynchronous update interface for receiving notifications
 * about GetInvitationRequest information as the GetInvitationRequest is constructed.
 * @author Eric
 */
public class GetInvitationRequestObserver implements RequestObserver{

  /** The controller. */
  public GetInvitationController controller;
  
  
  /**
   * This method is called when information about an GetInvitationRequest
   * which was previously requested using an asynchronous
   * interface becomes available.
   *
   * @param controller the controller
   */
  public GetInvitationRequestObserver(GetInvitationController controller) {
    this.controller = controller;
  }

  
  /*
   * Parse the messages out of the response body and pass them to the controller
   * 
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseSuccess(IRequest iReq) {
    Invitation[] items = Invitation.fromJsonArray(iReq.getResponse().getBody());
  
    System.out.println("Success! Here is GetInvitationRequestController in the JSON way"+ "   " + iReq.getResponse().getBody());
    controller.receivedInvitations(items);
  }
  
  /*
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseError(IRequest iReq) {
  //  fail(iReq, null);
    System.err.println("The request to get invitations Errored. " + iReq.toString());
  
  }


  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
   */
  @Override
  public void fail(IRequest iReq, Exception exception) {
    System.err.println("The request to get invitaitons failed.");
    
  }


}

