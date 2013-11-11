/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.MainCalendarModel;

@SuppressWarnings("serial")
public class AddEventTabPanel extends JTabbedPane {

	JPanel addEventPanel;
	JPanel calendarPanel;
	JPanel tablesPanel;
	JPanel contentView;

	
	public AddEventTabPanel() {
		
		addEventPanel = new AddEventPanel(new MigLayout());
		
		contentView = new JPanel();
		contentView.setLayout(new BoxLayout(contentView, BoxLayout.X_AXIS));
		
		calendarPanel = new MainCalendarView(new MainCalendarModel());
		calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
		
		tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		EventTable eventTable = new EventTable();
		tablesPanel.add(eventTable);
		TaskTable taskTable = new TaskTable();
		tablesPanel.add(taskTable);
		FiltersTable filtersTable = new FiltersTable();
		tablesPanel.add(filtersTable);
		
		contentView.add(calendarPanel);	
		contentView.add(tablesPanel);
		
		addEventPanel.setLayout(new FlowLayout());
		this.add(contentView, 0);
		this.add(addEventPanel, 1);
		
		this.setTitleAt(0, "Calendar");
		
		this.setTitleAt(1, "New Event");
		
		//this.setPreferredSize(getMaximumSize());
		//this.setSize(getMaximumSize());
		
		this.setPreferredSize(new Dimension(800, 700));
		
	}
}
