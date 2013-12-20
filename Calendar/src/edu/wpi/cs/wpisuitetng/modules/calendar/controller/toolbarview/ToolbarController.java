/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.ManageFiltersPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCategoryPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddInvitationPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddSchedulerPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.AddCategoryPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.AddEventPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.AddFilterPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.AddSchedulerPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.InvitaionPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ToolbarController.
 */
public class ToolbarController implements ActionListener {

  /** The instance. */
  private static ToolbarController instance;
  
  /** The add event button. */
  private JButton                  addEventButton;
  
  /** The add commitment button. */
  private JButton                  addCommitmentButton;
  
  /** The add scheduler button. */
  private JButton                  createInvitationButton;
  
  /** The invitation button. */
  private JButton                  invitationButton;
  
  /** The manage filters button. */
  private JButton                  manageFiltersButton;
  
  /** The manage category button. */
  private JButton                  manageCategoryButton;



  /**
   * Sets the manage category button.
   *
   * @param manageCategoryButton the manageCategoryButton to set
   */
  public final void setManageCategoryButton(JButton manageCategoryButton) {
    this.manageCategoryButton = manageCategoryButton;
  }



  /**
   * Gets the adds the event button.
   *
   * @return the adds the event button
   */
  public final JButton getAddEventButton() {
    return addEventButton;
  }



  /**
   * Gets the adds the commitment button.
   *
   * @return the adds the commitment button
   */
  public final JButton getAddCommitmentButton() {
    return addCommitmentButton;
  }



  /**
   * Sets the adds the event button.
   *
   * @param addEventButton the new adds the event button
   */
  public final void setAddEventButton(JButton addEventButton) {
    this.addEventButton = addEventButton;
  }



  /**
   * Sets the adds the commitment button.
   *
   * @param addCommitmentButton the new adds the commitment button
   */
  public final void setAddCommitmentButton(JButton addCommitmentButton) {
    this.addCommitmentButton = addCommitmentButton;
  }



  /**
   * Gets the manage filters button.
   *
   * @return the manage filters button
   */
  public final JButton getManageFiltersButton() {
    return manageFiltersButton;
  }



  /**
   * Gets the manage category button.
   *
   * @return the manage category button
   */
  public final JButton getManageCategoryButton() {
    return manageCategoryButton;
  }



  /**
   * Sets the manage filters button.
   *
   * @param manageFiltersButton the new manage filters button
   */
  public final void setManageFiltersButton(JButton manageFiltersButton) {
    this.manageFiltersButton = manageFiltersButton;
  }



  /**
   * Gets the adds the scheduler button.
   *
   * @return the adds the scheduler button
   */
  public final JButton getAddSchedulerButton() {
    return createInvitationButton;
  }



  /**
   * Sets the adds the scheduler button.
   *
   * @param addSchedulerButton the new adds the scheduler button
   */
  public final void setAddSchedulerButton(JButton addSchedulerButton) {
    this.createInvitationButton = addSchedulerButton;
  }



  /**
   * Gets the invitation button.
   *
   * @return the invitation button
   */
  public final JButton getInvitationButton() {
    return invitationButton;
  }



  /**
   * Sets the invitation button.
   *
   * @param invitationButton the new invitation button
   */
  public final void setInvitationButton(JButton invitationButton) {
    this.invitationButton = invitationButton;
  }



  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public final void actionPerformed(ActionEvent e) {
    // If add event was clicked - open the panel in a new tab
    if (e.getSource() == addEventButton) {
      AddEventPanel newEventPanel = new AddEventPanel(new MigLayout());
      try {
        Thread.sleep(500);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        System.out.println(ex);
      }
      newEventPanel = new AddEventPanel(new MigLayout());
      AddEventPanelController.getInstance().getTabbedPane().add(newEventPanel);
      AddEventPanelController
          .getInstance()
          .getTabbedPane()
          .setTitleAt(
              AddEventPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1, "Add Event");
      AddEventPanelController
          .getInstance()
          .getTabbedPane()
          .setSelectedIndex(
              AddEventPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1);
      newEventPanel.initiateFocus();
    }
   // If add commitment was clicked - open the panel in a new tab
    if(e.getSource() == addCommitmentButton) {
      AddCommitmentPanel commitmentPanel = new AddCommitmentPanel(
          new MigLayout());
      try {
        Thread.sleep(500);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        System.out.println(ex);
      }
      commitmentPanel = new AddCommitmentPanel(new MigLayout());
      AddCommitmentPanelController.getInstance().getTabbedPane()
          .add(commitmentPanel);
      AddCommitmentPanelController
          .getInstance()
          .getTabbedPane()
          .setTitleAt(
              AddCommitmentPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1, "Add Commitment");
      AddCommitmentPanelController
          .getInstance()
          .getTabbedPane()
          .setSelectedIndex(
              AddCommitmentPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1);
      commitmentPanel.initiateFocus();
    }
    // If mange filters was clicked - open the panel in a new tab (NOT USED IN RELEASE)
    if(e.getSource() == manageFiltersButton) {
      final AddFilterPanel AddFilterPanel = new AddFilterPanel(new MigLayout());
      ManageFiltersPanelController.getInstance().getTabbedPane()
          .add(AddFilterPanel);
      ManageFiltersPanelController
          .getInstance()
          .getTabbedPane()
          .setTitleAt(
              ManageFiltersPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1, "Manage Filters");
      ManageFiltersPanelController
          .getInstance()
          .getTabbedPane()
          .setSelectedIndex(
              ManageFiltersPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1);
      AddFilterPanel.initiateFocus();
    }
    // If schedule event was clicked - open the panel in a new tab
    if(e.getSource() == createInvitationButton) {
      final AddSchedulerPanel newSchedulerPanel = new AddSchedulerPanel(
          new MigLayout());
      AddSchedulerPanelController.getInstance().getTabbedPane()
          .add(newSchedulerPanel);
      AddSchedulerPanelController
          .getInstance()
          .getTabbedPane()
          .setTitleAt(
              AddSchedulerPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1, "Add Scheduler");
      AddSchedulerPanelController
          .getInstance()
          .getTabbedPane()
          .setSelectedIndex(
              AddSchedulerPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1);
      newSchedulerPanel.initiateFocus();
    }
    // If invitations was clicked - open the panel in a new tab
    if(e.getSource() == invitationButton) {
      InvitaionPanel invitationPanel = new InvitaionPanel();
      try {
        Thread.sleep(500);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        System.out.println(ex);

      }
      invitationPanel = new InvitaionPanel();
      AddInvitationPanelController.getInstance().getTabbedPane()
          .add(invitationPanel);
      AddInvitationPanelController
          .getInstance()
          .getTabbedPane()
          .setTitleAt(
              AddInvitationPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1, "Invitation");
      AddInvitationPanelController
          .getInstance()
          .getTabbedPane()
          .setSelectedIndex(
              AddInvitationPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1);
    }
    // If manage categories was clicked - open the panel in a new tab
    if(e.getSource() == manageCategoryButton) {
      AddCategoryPanel categoryPanel = new AddCategoryPanel();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        System.out.println(ex);

      }
      categoryPanel = new AddCategoryPanel();
      AddCategoryPanelController.getInstance().getTabbedPane()
          .add(categoryPanel);
      AddCategoryPanelController
          .getInstance()
          .getTabbedPane()
          .setTitleAt(
              AddCategoryPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1, "Manage Category");
      AddCategoryPanelController
          .getInstance()
          .getTabbedPane()
          .setSelectedIndex(
              AddCategoryPanelController.getInstance().getTabbedPane()
                  .getTabCount() - 1);
    }

  }



  /**
   * Gets the single instance of ToolbarController.
   *
   * @return single instance of ToolbarController
   */
  public static ToolbarController getInstance() {
    if(instance == null) {
      instance = new ToolbarController();
    }

    return instance;
  }
}
