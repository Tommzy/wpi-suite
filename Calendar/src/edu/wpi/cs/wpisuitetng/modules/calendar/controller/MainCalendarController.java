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

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CalendarWeekView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CalendarYearView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.CalendarDayView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.MainCalendarView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview.MonthView;

/**
 * This controller responds to actions from view MainCalendarView and
 * changes values in MainCalendarModel
 * 
 * @author Yuchen Zhang
 *
 */
public class MainCalendarController implements ActionListener{
	Commitment commitmentModel;
	MainCalendarView view;
	CalendarYearView yearView;
	MonthView monthView;
	CalendarDayView dayView;
	CalendarWeekView weekView;
	MainView mainView;

	public static MainCalendarController instance;
	private DateController dateController = new DateController();
	private CalendarTimePeriod selectedCalendarView;
	
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
	/*
	public MainCalendarController(CalendarItemListModel model, MainCalendarView view) {
		this.model = model;
		this.view = view;
		yearView = new CalendarYearView();
		monthView = new MonthView();
		dayView = new CalendarDayView(sampleEvent, view.getCalendarView());
		weekView = new CalendarWeekView(sampleEvent);
		
	}
	*/
	public MainCalendarController() {
		
	}
	
	/**
	 * @see package edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController
	 * @return
	 */
	public DateController getDateController() {
		return dateController;
	}
	
	public static MainCalendarController getInstance() {
		if (instance == null) {
			instance = new MainCalendarController();
		}
		return instance;
	}
//	public Commitment getCommitmentModel() {
//		return commitmentModel;
//	}
//
//
//	public void setModel(CalendarItemListModel model) {
//		this.model = model;
//	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	public MainCalendarView getView() {
		return view;
	}


	public void setView(MainCalendarView view) {
		this.view = view;
	}


	public CalendarYearView getYearView() {
		return yearView;
	}


	public void setYearView(CalendarYearView yearView) {
		this.yearView = yearView;
	}


	public MonthView getMonthView() {
		return monthView;
	}


	public void setMonthView(MonthView monthView) {
		this.monthView = monthView;
	}


	public CalendarDayView getDayView() {
		return dayView;
	}


	public void setDayView(CalendarDayView dayView) {
		this.dayView = dayView;
	}


	public CalendarWeekView getWeekView() {
		return weekView;
	}


	public void setWeekView(CalendarWeekView weekView) {
		this.weekView = weekView;
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
				selectedCalendarView = CalendarTimePeriod.Year;
				break;
			case "Month" :
				view.getCalendarView().add(monthView, "span");
				selectedCalendarView = CalendarTimePeriod.Month;
				break;
			case "Week" :
				view.getCalendarView().add(weekView);
				selectedCalendarView = CalendarTimePeriod.Week;
				break;
			case "Day" :
				view.getCalendarView().add(dayView);		
				selectedCalendarView = CalendarTimePeriod.Day;
				break;
			default: break;
		}
		view.getCalendarView().revalidate();
		view.getCalendarView().repaint();

		try { 
			mainView.getMainTabPane().getCommitmentTable().update();
		} catch (NullPointerException e) {
			
		}

		
		monthView.updateMonthView();
		//weekView.updateWeekView();
		//dayView.updateDayView();

	}
	
	public CalendarTimePeriod getSelectedCalendarView() {
		return selectedCalendarView;
	}

	public void setSelectedCalendarView(CalendarTimePeriod selectedCalendarView) {
		this.selectedCalendarView = selectedCalendarView;
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
