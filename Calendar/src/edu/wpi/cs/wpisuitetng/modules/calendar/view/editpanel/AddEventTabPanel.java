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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.ManageFiltersPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCategoryPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddInvitationPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddSchedulerPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview.ToolbarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CommitmentTable;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.EventTable;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.FiltersTable;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.calendarview.MainCalendarView;


/**
 * The Class AddEventTabPanel.
 */
@SuppressWarnings("serial")
public class AddEventTabPanel extends JTabbedPane {

	
	/** The add event panel. */
	JPanel addEventPanel;
	
	/** The calendar panel. */
	JPanel calendarPanel;
	
	/** The tables panel. */
	JPanel tablesPanel;
	
	/** The content view. */
	JPanel contentView;
	
	/** The event scroll. */
	JScrollPane eventScroll;
	
	/** The task scroll. */
	JScrollPane taskScroll;
	
	/** The filter scroll. */
	JScrollPane filterScroll;
	
	/** The commitment table. */
	CommitmentTable commitmentTable;
	/**
	 * Adds the event tab panel.
	 */
	public AddEventTabPanel() {
		
		// add eventTabPanel to AddEventPanelController
		AddEventPanelController.getInstance().setTabbedPane(this);
		AddCommitmentPanelController.getInstance().setTabbedPane(this);
		AddCategoryPanelController.getInstance().setTabbedPane(this);
		ManageFiltersPanelController.getInstance().setTabbedPane(this);
		AddSchedulerPanelController.getInstance().setTabbedPane(this);
		AddInvitationPanelController.getInstance().setTabbedPane(this);
		
		//Content Viewer
		contentView = new JPanel();
		contentView.setLayout(new BoxLayout(contentView, BoxLayout.X_AXIS));
		
		//Calendar
		//Removed arguments. should be updated
		calendarPanel = new MainCalendarView();
		calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
		
		//Tables
		tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		
		// Events
		EventTable eventTable = new EventTable();
		eventTable.setLayout(new BoxLayout(eventTable, BoxLayout.Y_AXIS));
		tablesPanel.add(eventTable);
		// Tasks
		commitmentTable = new CommitmentTable();
		commitmentTable.setLayout(new BoxLayout(commitmentTable, BoxLayout.Y_AXIS));
		tablesPanel.add(commitmentTable);
		// Filters
		FiltersTable filtersTable = new FiltersTable();
		filtersTable.setLayout(new BoxLayout(filtersTable, BoxLayout.Y_AXIS));
		tablesPanel.add(filtersTable);
		
		contentView.add(calendarPanel);	
		contentView.add(tablesPanel);
		
		
		this.add(contentView, 0);
		
		this.setTitleAt(0, "Calendar");
		
		
		this.setPreferredSize(new Dimension(800, 700));
		
	}
	
}
