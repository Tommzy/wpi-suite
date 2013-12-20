/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:Team 3
 ******************************************************************************/



package edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateInvitationController.
 * @version $Revision: 1.0 $
 * @author Andrew Paon
 */
public class UpdateInvitationController implements ActionListener{
  
  /** The instance. */
  private static UpdateInvitationController instance = getInstance();
  
  /** The observer. */
 final UpdateInvitationRequestObserver observer;
  
  /** The updated invite. */
  private static Invitation updatedInvite = getInstance().getUpdatedInvitation();
  
  /**
   * Instantiates a new update invitation controller.
   *
   * @param invite the invite
   */
  @SuppressWarnings("static-access")
  public UpdateInvitationController(Invitation invite) {
    observer = new UpdateInvitationRequestObserver(this);
    updatedInvite = invite;
  }
  
  /**
   * Gets the single instance of UpdateInvitationController.
   *
   * @return single instance of UpdateInvitationController
   */
  public static UpdateInvitationController getInstance()
  {
    if(instance == null)
    {
      instance = new UpdateInvitationController(updatedInvite);
    }
    
    return instance;
  }
  
  /**
   * Gets the updated invitation.
   *
   * @return the updated invitation
   */
  @SuppressWarnings("static-access")
  public Invitation getUpdatedInvitation(){
    return UpdateInvitationController.updatedInvite;
  }

  /**
   * Update invitation.
   *
   * @param invite the invite
   */
  public void updateInvitation(Invitation invite) 
  {
    final Request request = Network.getInstance().makeRequest("calendar/invitation", HttpMethod.POST); // POST == update
    request.setBody(invite.toJSON()); // put the new invitation in the body of the request
    request.addObserver(observer); // add an observer to process the response
    request.send(); 
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // get invitation
    UpdateInvitationController.getInstance()
    .updateInvitation(UpdateInvitationController.getInstance()
    .getUpdatedInvitation());
    
  }
  
  /**
   * Update sucess.
   *
   * @param invite the invite
   * @return true, if successful
   */
  public boolean updateSucess(Invitation invite){
    return UpdateInvitationController.getInstance().getUpdatedInvitation().equals(invite);
  }
}

