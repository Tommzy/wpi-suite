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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import edu.wpi.cs.wpisuitetng.modules.calendar.invitation.InvitationModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * The Class GetInvitationController.
 * 
 * @author Eric
 */
public class GetInvitationController implements ActionListener {

  /** The observer. */
  private GetInvitationRequestObserver observer;

  /**
   * Instantiates a new gets the invitation controller.
   */
  public GetInvitationController() {
    observer = new GetInvitationRequestObserver(this);;
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Send a request to the core to save this message
	
	  
    final Request request = Network.getInstance().makeRequest("calendar/invitation", HttpMethod.GET); // GET == read
    request.addObserver(observer); // add an observer to process the response
    request.send(); // send the request
    }
  
  /**
   * Retrieve invitations.
   */
  public void retrieveInvitations() {
    final Request request = Network.getInstance().makeRequest("calendar/invitation", HttpMethod.GET); // GET == read
    request.addObserver(observer); // add an observer to process the response
    request.send(); // send the request
  }
  
  /**
   * Received invitations.
   *
   * @param invites the invites
   */
  public void receivedInvitations(Invitation[] invites) {
    // Empty the local model to eliminate duplications
    InvitationModel.getInstance().emptyModel();
    
    // Make sure the response was not null
    if (invites != null) {
      // add the invitations to the local model
      InvitationModel.getInstance().addInvitations(invites);
    }
  }

  
  
  

}

