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

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview.ToolbarController;


// TODO: Auto-generated Javadoc
/**
 * The Class ToolbarPanel.
 */
@SuppressWarnings("serial")
public class ToolbarPanel extends JPanel {

  /** The add event button. */
  private final JButton btnAddEvent;

  /** The add event button. */
  private final JButton btnAddTask;

  /** The add event button. */
  private final JButton btnScheduleEvent;

  /** The add event button. */
  private final JButton btnManageFilter;

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

    btnAddTask = new JButton("Add Commitment");

    btnScheduleEvent = new JButton("Schedule Event");

    btnManageFilter = new JButton("Manage Filters");

    // Add the get messages controller to the button
    // btnRefresh.addActionListener(new GetMessagesController(boardModel));

    // Add the button to this panel
    add(btnAddEvent);
    add(btnAddTask);
    add(btnScheduleEvent);
    add(btnManageFilter);

    ToolbarController.getInstance().setAddEventButton(btnAddEvent);
    btnAddEvent.addActionListener(ToolbarController.getInstance());
  }
}
