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

import edu.wpi.cs.wpisuitetng.modules.calendar.invitation.InvitationModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;



/**
 * The Class AddInvitationController.
 * 
 * @author Eric
 */
public class AddInvitationController implements ActionListener{

  /** The model. */
  private final InvitationModel model;

  /** The invitations to be added. */
  private final Invitation invitationsToBeAdded;

  /**
   * Instantiates a new adds the invitation controller.
   *
   * @param model the model
   * @param invitationsToBeAdded the invitations to be added
   */
  public AddInvitationController(InvitationModel model, Invitation invitationsToBeAdded) {
    this.model = model;
    this.invitationsToBeAdded = invitationsToBeAdded;
  }

  /** The observer. */
  AddInvitationRequestObserver observer = new AddInvitationRequestObserver(this);


  /**
   * Adds the invitation to database.
   *
   * @param invite the invite
   */
  public void addInvitationToDatabase(Invitation invite){
    final Request request = Network.getInstance().makeRequest("calendar/invitation", HttpMethod.PUT); // PUT == create
    request.setBody(invite.toJSON()); // put the new message in the body of the request
    request.addObserver(new AddInvitationRequestObserver(this)); // add an observer to process the response
    request.send();
  }

  /**
   * Test return.
   *
   * @return the invitation
   */
  public Invitation testReturn(){
    return observer.testReturn();
  }


  /* 
   * This method is called when the user clicks the Submit button
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    // Add the message to the model
    final Request request = Network.getInstance().makeRequest("calendar/invitation", HttpMethod.PUT); // PUT == create
    request.setBody(invitationsToBeAdded.toJSON()); // put the new message in the body of the request
    request.addObserver(new AddInvitationRequestObserver(this)); // add an observer to process the response
    request.send(); // send the request
    System.out.println("from AddInvitationController." + request.getBody());
  }


  /**
   * Adds the invitation to model.
   *
   * @param invite the invite
   */
  public void addInvitationToModel(Invitation invite) {
    model.addInvitation(invite);
  }
}


