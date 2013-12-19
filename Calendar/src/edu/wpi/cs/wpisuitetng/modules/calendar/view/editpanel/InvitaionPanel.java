/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.getcontroller.GetInvitationController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;


@SuppressWarnings("serial")
public class InvitaionPanel extends JPanel implements AncestorListener{
  SchedulerList schedulerList;
  boolean doubleClicked = false;
  GetInvitationController getController = new GetInvitationController();
	/**
	 * Instantiates a new scheduler panel.
	 */
	public InvitaionPanel() {

	  // TODO change the invitation
		schedulerList = new SchedulerList(new MigLayout());

		schedulerList.setAlignmentX(LEFT_ALIGNMENT);
		
		add(schedulerList);
	}
  @Override
  public void ancestorAdded(AncestorEvent arg0) {
    // TODO Auto-generated method stub
    getController.retrieveInvitations();
    
  }
  @Override
  public void ancestorMoved(AncestorEvent arg0) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void ancestorRemoved(AncestorEvent arg0) {
    // TODO Auto-generated method stub
    
  }
}
