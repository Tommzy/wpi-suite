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

public class ToolbarController implements ActionListener {

   private static ToolbarController instance;
   private JButton addEventButton;
   private JButton addCommitmentButton;
   private JButton addSchedulerButton;
   private JButton invitationButton;
   private JButton manageFiltersButton;
   private JButton manageCategoryButton;

  

   /**
    * @param manageCategoryButton the manageCategoryButton to set
    */
   public void setManageCategoryButton(JButton manageCategoryButton) {
	   this.manageCategoryButton = manageCategoryButton;
   }

  public JButton getAddEventButton() {
    return addEventButton;
  }

  public JButton getAddCommitmentButton() {
    return addCommitmentButton;
  }

  public void setAddEventButton(JButton addEventButton) {
    this.addEventButton = addEventButton;
  }

  public void setAddCommitmentButton(JButton addCommitmentButton) {
    this.addCommitmentButton = addCommitmentButton;
  }
  
  public JButton getManageFiltersButton() {
		return manageFiltersButton;
  }
  
  public JButton getManageCategoryButton() {
	  return manageCategoryButton;
  }

  public void setManageFiltersButton(JButton manageFiltersButton) {
		this.manageFiltersButton = manageFiltersButton;
  }
  
  public JButton getAddSchedulerButton() {
	  return addSchedulerButton;
  }
  
  public void setAddSchedulerButton(JButton addSchedulerButton) {
	  this.addSchedulerButton = addSchedulerButton;
  }
  
  public JButton getInvitationButton() {
	  return invitationButton;
  }
  
  public void setInvitationButton(JButton invitationButton) {
	  this.invitationButton = invitationButton;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
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
        AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Event");
        AddEventPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
        newEventPanel.initiateFocus();
    }

    if (e.getSource() == addCommitmentButton) {
	      AddCommitmentPanel commitmentPanel = new AddCommitmentPanel(new MigLayout());
	      try {
	    		Thread.sleep(500);
	    	} catch (InterruptedException ex) {
	    		Thread.currentThread().interrupt();
				System.out.println(ex);

	    	}
	      commitmentPanel = new AddCommitmentPanel(new MigLayout());
	      AddCommitmentPanelController.getInstance().getTabbedPane().add(commitmentPanel);
	      AddCommitmentPanelController.getInstance().getTabbedPane().setTitleAt(AddCommitmentPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Commitment");
	      AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddCommitmentPanelController.getInstance().getTabbedPane().getTabCount() - 1);
	      commitmentPanel.initiateFocus();
      }
    if (e.getSource() == manageFiltersButton) {
    	AddFilterPanel AddFilterPanel = new AddFilterPanel(new MigLayout());
    	ManageFiltersPanelController.getInstance().getTabbedPane().add(AddFilterPanel);
    	ManageFiltersPanelController.getInstance().getTabbedPane().setTitleAt(ManageFiltersPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Manage Filters");
    	ManageFiltersPanelController.getInstance().getTabbedPane().setSelectedIndex(ManageFiltersPanelController.getInstance().getTabbedPane().getTabCount() - 1);
    	AddFilterPanel.initiateFocus();  
    }
    if (e.getSource() == addSchedulerButton) {
    	AddSchedulerPanel newSchedulerPanel = new AddSchedulerPanel(new MigLayout());
    	AddSchedulerPanelController.getInstance().getTabbedPane().add(newSchedulerPanel);
    	AddSchedulerPanelController.getInstance().getTabbedPane().setTitleAt(AddSchedulerPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Scheduler");
    	AddSchedulerPanelController.getInstance().getTabbedPane().setSelectedIndex(AddSchedulerPanelController.getInstance().getTabbedPane().getTabCount() - 1);
    	newSchedulerPanel.initiateFocus();
    }
    if (e.getSource() == invitationButton) {
    	InvitaionPanel invitationPanel = new InvitaionPanel(); 
    	try {
    		Thread.sleep(500);
    	} catch (InterruptedException ex) {
    		Thread.currentThread().interrupt();
			System.out.println(ex);

    	}
    	invitationPanel = new InvitaionPanel();
    	AddInvitationPanelController.getInstance().getTabbedPane().add(invitationPanel);
    	AddInvitationPanelController.getInstance().getTabbedPane().setTitleAt(AddInvitationPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Invitation");
    	AddInvitationPanelController.getInstance().getTabbedPane().setSelectedIndex(AddInvitationPanelController.getInstance().getTabbedPane().getTabCount() - 1);
    }
    if (e.getSource() == manageCategoryButton) {
    	AddCategoryPanel categoryPanel = new AddCategoryPanel();
    	try {
    	    Thread.sleep(1000);
    	} catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
			System.out.println(ex);

    	}
    	categoryPanel = new AddCategoryPanel();
    	AddCategoryPanelController.getInstance().getTabbedPane().add(categoryPanel);
    	AddCategoryPanelController.getInstance().getTabbedPane().setTitleAt(AddCategoryPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Manage Category");
    	AddCategoryPanelController.getInstance().getTabbedPane().setSelectedIndex(AddCategoryPanelController.getInstance().getTabbedPane().getTabCount() - 1);
    }

  }

  public static ToolbarController getInstance() {
    if (instance == null) {
      instance = new ToolbarController();
    }

    return instance;
  }
}
