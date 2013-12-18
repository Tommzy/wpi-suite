/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    team3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview.ToolbarController;


/**
 * The Class ToolbarPanel.
 */
@SuppressWarnings("serial")
public class ToolbarPanel extends JPanel {

	/** The add event button. */
	private final JButton btnAddEvent;

	/** The add event button. */
	private final JButton btnAddCommitment;

	/** The add event button. */
	private final JButton btnScheduleEvent;

	/** The add event button. */
	private final JButton btnManageFilter;

	private JButton invitationButton;

	/**
	 * Construct the panel.
	 *
	 */
	public ToolbarPanel() {

		// Make this panel transparent, we want to see the JToolbar gradient beneath
		// it
		this.setOpaque(false);

		// Construct the refresh button and add it to this panel
		btnAddEvent = new JButton("Add Event");

		btnAddCommitment = new JButton("Add Commitment");

		btnScheduleEvent = new JButton("Schedule Event");

		btnManageFilter = new JButton("Manage Filters");
		invitationButton = new JButton("Invitations");
		// Add the get messages controller to the button
		// btnRefresh.addActionListener(new GetMessagesController(boardModel));

		// Add the button to this panel
		add(btnAddEvent);
		add(btnAddCommitment);
		add(btnScheduleEvent);
		add(invitationButton);
		add(btnManageFilter);
		

		ToolbarController.getInstance().setAddEventButton(btnAddEvent);
		btnAddEvent.addActionListener(ToolbarController.getInstance());
		ToolbarController.getInstance().setAddSchedulerButton(btnScheduleEvent);
		btnScheduleEvent.addActionListener(ToolbarController.getInstance());
		ToolbarController.getInstance().setAddCommitmentButton(btnAddCommitment);
		btnAddCommitment.addActionListener(ToolbarController.getInstance());
    ToolbarController.getInstance().setInvitationButton(invitationButton);
    invitationButton.addActionListener(ToolbarController.getInstance());
		ToolbarController.getInstance().setManageFiltersButton(btnManageFilter);
		btnManageFilter.addActionListener(ToolbarController.getInstance());
	}
}
