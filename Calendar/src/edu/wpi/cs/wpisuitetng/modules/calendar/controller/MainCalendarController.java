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

package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.MainCalendarModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CalendarYearView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.MainCalendarView;

/**
 * This controller responds to actions from view MainCalendarView and
 * changes values in MainCalendarModel
 * 
 * @author Yuchen Zhang
 *
 */
public class MainCalendarController implements ActionListener{
	MainCalendarModel model;
	MainCalendarView view;
	
	/**
	 * Create a MainCalendarController. 
	 * 
	 * @param model 
	 * @param view
	 */
	public MainCalendarController(MainCalendarModel model, MainCalendarView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Changed view when time period toggle button changes. 
	 * 
	 * @param toggleButton The toggle button that is changed to. 
	 */
	public void timePeriodChanged(JToggleButton toggleButton) {
		resetToggleButton();
		toggleButton.setSelected(true);
		switch (toggleButton.getText()) {
			case "YEAR" :
				CalendarYearView yearView = new CalendarYearView();
				view.getCalendarView().add(yearView);
				break;
			case "MONTH" :
				break;
			case "WEEK" :
				break;
			case "DAY" :
				break;
			default: break;
		}
		view.getCalendarView().revalidate();
		view.getCalendarView().repaint();
	}
	
	/**
	 * Helper function for function timePeriodChanged(JToggleButton). 
	 * This function resets all the toggle buttons in calendar part. 
	 */
	private void resetToggleButton() {
		for (int i = 0; i < view.getToggleButtonPeriod().size(); i++) {
			view.getToggleButtonPeriod().get(i).setSelected(false);
		}
		view.getCalendarView().removeAll();
	}
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent) 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JToggleButton) {
			timePeriodChanged((JToggleButton) e.getSource());
		}
	}
}
