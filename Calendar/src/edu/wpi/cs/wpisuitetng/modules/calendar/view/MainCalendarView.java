/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Yuchen Zhang
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItemListModel;

/**
 * This Panel sets the calendar area within main view of Calendar module.
 * It contains a sub panel CalendarView and 
 * buttons to switch among day-view, week-view, month-view and year-view. 
 * 
 * @author Yuchen Zhang
 *
 */
@SuppressWarnings("serial")
public class MainCalendarView extends JPanel {
	List<JToggleButton> tglbtnPeriod = new ArrayList<JToggleButton>();
	JPanel calendarView = new JPanel();

	
	/**
	 * Create the calendar area within main panel.
	 * 
	 * @param mainCalendarModel
	 */
	public MainCalendarView(CalendarItemListModel mainCalendarModel) {
		// Create toggle buttons for different calendar view
		JPanel calendarViewSwitch = new JPanel();
		calendarViewSwitch.setLayout(new BoxLayout(calendarViewSwitch, BoxLayout.X_AXIS));
		for (CalendarTimePeriod value : CalendarTimePeriod.values()) {
			JToggleButton tglbtn = new JToggleButton(value.name());
			tglbtnPeriod.add(tglbtn);
			tglbtn.addActionListener(new MainCalendarController(mainCalendarModel, this));
			calendarViewSwitch.add(tglbtn);
		}
		add(calendarViewSwitch);
		
		add(calendarView);
		
		initialize();
	}
	
	/**
	 * Getter for tglbtnPeriod
	 * @return tglbtnPeriod
	 */
	public List<JToggleButton> getToggleButtonPeriod() {
		return tglbtnPeriod;
	}
	
	public JPanel getCalendarView() {
		return calendarView;
	}
	
	private void initialize() {
		tglbtnPeriod.get(3).doClick();
	}

}
