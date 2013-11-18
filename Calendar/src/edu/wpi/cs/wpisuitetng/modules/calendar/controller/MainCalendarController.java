/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JToggleButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.MainCalendarModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.fakeModel.FakeModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CalendarMonthView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CalendarWeekView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CalendarYearView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CalendarDayView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.MainCalendarView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview.MonthView;

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
	CalendarYearView yearView;
	MonthView monthView;
	
	// for test display use
	DayEvent[] sampleEvent = {
			new DayEvent("Whoops", new GregorianCalendar(2013, 5, 16, 20, 50, 0), new GregorianCalendar(2013, 5, 16, 21, 5, 0)), 
			new DayEvent("Innebandy", new GregorianCalendar(2013, 5, 16, 15, 50, 0), new GregorianCalendar(2013, 5, 16, 16, 5, 0)), 
			new DayEvent("Abcd", new GregorianCalendar(2013, 5, 16, 15, 55, 0), new GregorianCalendar(2013, 5, 16, 16, 15, 0)), 
			new DayEvent("Efgh", new GregorianCalendar(2013, 5, 16, 15, 56, 0), new GregorianCalendar(2013, 5, 16, 16, 16, 0)),
			new DayEvent("Hey", new GregorianCalendar(2013, 5, 18, 8, 50, 0), new GregorianCalendar(2013, 5, 18, 10, 5, 0)) };
	
	/**
	 * Create a MainCalendarController. 
	 * 
	 * @param model 
	 * @param view
	 */
	public MainCalendarController(MainCalendarModel model, MainCalendarView view) {
		this.model = model;
		this.view = view;
		yearView = new CalendarYearView();
		monthView = new MonthView(view.getCalendarView(), FakeModel.getInstance().getCurrentDate());
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
			case "Year" :
				view.getCalendarView().add(yearView);
				break;
			case "Month" :
				view.getCalendarView().add(monthView, "span");
				break;
			case "Week" :
				CalendarWeekView weekView = new CalendarWeekView(sampleEvent);
				view.getCalendarView().add(weekView);
				break;
			case "Day" :
				CalendarDayView dayView = new CalendarDayView(sampleEvent);
				view.getCalendarView().add(dayView);
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
