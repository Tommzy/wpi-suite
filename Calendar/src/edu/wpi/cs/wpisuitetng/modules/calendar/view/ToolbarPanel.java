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

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview.ToolbarController;

/**
 * The Class ToolbarPanel where all navigation buttons go.
 */
@SuppressWarnings("serial")
public class ToolbarPanel extends JPanel {

  /** The add event button. */
  private final JButton btnAddEvent;

  /** The add commitment button. */
  private final JButton btnAddTask;

  /** The schedule event button. */
  private final JButton btnScheduleEvent;

  /** The manage filter button. */
  private final JButton btnManageFilter;

  /** The manage category button. */
  private final JButton btnManageCategory;

  /** The invitations button. */
  private final JButton btnInvitations;



  /**
   * Construct the panel with all buttons.
   * 
   */
  public ToolbarPanel() {

    // Make this panel transparent, we want to see the JToolbar gradient beneath
    // it
    this.setOpaque(false);

    // Construct all buttons
    btnAddEvent = new JButton("Add Event");
    btnAddTask = new JButton("Add Commitment");
    btnScheduleEvent = new JButton("Schedule Event");
    btnManageFilter = new JButton("Manage Filters");
    btnManageCategory = new JButton("Manage Category");
    btnInvitations = new JButton("Invitations");

    // images for the buttons
    Image img;
    try {
      img = ImageIO.read(getClass().getResource("events.png"));
      btnAddEvent.setIcon(new ImageIcon(img));
      img = ImageIO.read(getClass().getResource("commitment.png"));
      btnAddTask.setIcon(new ImageIcon(img));
      img = ImageIO.read(getClass().getResource("sched.png"));
      btnScheduleEvent.setIcon(new ImageIcon(img));
      img = ImageIO.read(getClass().getResource("when2meet.png"));
      btnInvitations.setIcon(new ImageIcon(img));
      img = ImageIO.read(getClass().getResource("settings.png"));
      btnManageFilter.setIcon(new ImageIcon(img));
      img = ImageIO.read(getClass().getResource("settings.png"));
      btnManageCategory.setIcon(new ImageIcon(img));
    } catch (Exception e) {
      System.out.println("failed to set icon" + e);
    }

    // Add the button to this panel
    add(btnAddEvent);
    add(btnAddTask);
    add(btnScheduleEvent);
    add(btnInvitations);
    // add(btnManageFilter); // not implemented
    add(btnManageCategory);

    // add controller to the buttons
    ToolbarController.getInstance().setAddEventButton(btnAddEvent);
    btnAddEvent.addActionListener(ToolbarController.getInstance());
    ToolbarController.getInstance().setAddSchedulerButton(btnScheduleEvent);
    btnScheduleEvent.addActionListener(ToolbarController.getInstance());
    ToolbarController.getInstance().setInvitationButton(btnInvitations);
    btnInvitations.addActionListener(ToolbarController.getInstance());
    ToolbarController.getInstance().setAddCommitmentButton(btnAddTask);
    btnAddTask.addActionListener(ToolbarController.getInstance());
    ToolbarController.getInstance().setManageFiltersButton(btnManageFilter);
    btnManageFilter.addActionListener(ToolbarController.getInstance());
    ToolbarController.getInstance().setManageCategoryButton(btnManageCategory);
    btnManageCategory.addActionListener(ToolbarController.getInstance());
  }
}
