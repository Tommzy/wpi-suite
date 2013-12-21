
/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request
 * to the server to add a invitation.
 * @author Eric
 * @version v1.0
 */
public class AddInvitationRequestObserver implements RequestObserver {

  /** The controller. */
  private final AddInvitationController controller;
                
  /**
   * This method is called when information about an AddInvitationRequest
   * which was previously requested using an asynchronous
   * interface becomes available.
   *
   * @param controller the controller
   */
  public AddInvitationRequestObserver(AddInvitationController controller) {
    this.controller = controller;
  }
  
  /** The test item. */
  private Invitation testItem;

  
  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseSuccess(IRequest iReq) {
    // Get the response to the given request
    final ResponseModel response = iReq.getResponse();
    
    //Parse the calendar item out of the response body
    final Invitation item = Invitation.fromJSON(response.getBody());
    //Pass the messaged back to the controller
    //Needs to put invitation back into the system
    controller.addInvitationToModel(item);
    System.out.print("From AddInvitationObserver." + response.getBody());
  } 
  
  /**
   * This method is called when information about an AddInvitationRequest
   * which was previously requested using an asynchronous
   * interface becomes available.
   *
   * @return the invitation
   */
  public Invitation testReturn(){
    return testItem;
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
   */
  @Override
  public void responseError(IRequest iReq) {
    System.err.println(iReq.getBody());
    System.err.println(iReq.getResponse().getStatusMessage());
    System.err.println("The request to add an invitation errored."); 
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
   */
  @Override
  public void fail(IRequest iReq, Exception exception) {
    System.err.println("The request to add an invitation failed.");
  }
}
