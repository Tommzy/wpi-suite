/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview.MonthView;

/**
 * the controller for month view.
 * responsible for setting the date controller in 
 * main calendar controller to the given date
 * when user click on one of the grid panel
 * @author Hongbo
 *
 */
public class MonthViewController implements ActionListener {
	private static MonthViewController instance;
	private MonthView monthView;
	private JButton btnNext, btnToday, btnBefore;
	
	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}

	public JButton getBtnToday() {
		return btnToday;
	}

	public void setBtnToday(JButton btnToday) {
		this.btnToday = btnToday;
	}

	public JButton getBtnBefore() {
		return btnBefore;
	}

	public void setBtnBefore(JButton btnBefore) {
		this.btnBefore = btnBefore;
	}

	public MonthView getMonthView() {
		return monthView;
	}

	public void setMonthView(MonthView monthView) {
		this.monthView = monthView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DateController date = MainCalendarController.getInstance().getDateController();
		if (e.getSource() == btnNext) {
			date.setToNextMonth();
		} else if (e.getSource() == btnBefore) {
			date.setToPreviousMonth();
		} else if (e.getSource() == btnToday) {
			date.setToToday();
		}
		// as the commitment table and event table 
		// needs to change, here calls the updateAll method
		MainCalendarController.getInstance().updateAll();
	}
	
	public static MonthViewController getInstance() {
		if (instance == null) {
			instance = new MonthViewController();
		}

		return instance;
	}

}
