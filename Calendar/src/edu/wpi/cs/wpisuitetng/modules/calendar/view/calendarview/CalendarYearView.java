/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view.calendarview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.EventsModel;

/**
 * The Class CalendarYearView for displaying the year.
 */

public class CalendarYearView extends JPanel {

	/** The next year. */
	private JButton nextYear;

	/** The prev year. */
	private JButton prevYear;

	/** The today. */
	private JButton today;

	/** The calendar month. */
	CalendarMonth calendarMonth;

	/** commitment list prepared for commitment table */
	private List<Commitment> cmtList = new ArrayList<Commitment>();

	public List<Commitment> getCmtList() {
		return cmtList;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	/** event list prepared for commitment table */
	private List<Event> eventList = new ArrayList<Event>();

	/**
	 * Constructor for IterationCalendarPanel.
	 * 
	 */
	public CalendarYearView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel buttonPanel = new JPanel();

		nextYear = new JButton(">");
		nextYear.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		today = new JButton("Today");
		today.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		prevYear = new JButton("<");
		prevYear.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		setupButtonListeners();

		buttonPanel.add(prevYear);
		buttonPanel.add(today);
		buttonPanel.add(nextYear);

		add(buttonPanel);

		calendarMonth = new CalendarMonth();
		calendarMonth.setFont(calendarMonth.getFont().deriveFont(6f));
		add(calendarMonth);

		updateTables();
	}

	public void updateTables() {
		cmtList.clear();
		eventList.clear();
		List list = CommitmentsModel.getInstance().getAllCommitment();
		for (Commitment cmt : (List<Commitment>) list) {
			if (cmt.getStartTime().get(GregorianCalendar.YEAR) == MainCalendarController
					.getInstance().getDateController().getYear()) {
				cmtList.add(cmt);
			}
		}
		list = EventsModel.getInstance().getAllEvent();
		for (Event event : (List<Event>) list) {
			if (event.getStartTime().get(GregorianCalendar.YEAR) == MainCalendarController
					.getInstance().getDateController().getYear()) {
				eventList.add(event);
			}
		}
	}

	/**
	 * Adds action listeners to the year control buttons for the calendar view.
	 */
	private void setupButtonListeners() {
		nextYear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
				MainCalendarController.getInstance().updateAll();
			}
		});

		prevYear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previous();
				MainCalendarController.getInstance().updateAll();
			}
		});

		today.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				today();
				MainCalendarController.getInstance().updateAll();
			}
		});

	}

	/**
	 * Switches the calendar to the previous year.
	 */
	public void previous() {
		MainCalendarController.getInstance().getDateController()
				.setToPreviousYear();

		Calendar cal = calendarMonth.getCalendar();
		cal.set(GregorianCalendar.YEAR, MainCalendarController.getInstance()
				.getDateController().getYear());
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}

	/**
	 * Switches the calendar to the current date.
	 */
	public void today() {
		MainCalendarController.getInstance().getDateController().setToToday();

		Calendar cal = calendarMonth.getCalendar();
		cal.set(GregorianCalendar.YEAR, MainCalendarController.getInstance()
				.getDateController().getYear());
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}

	/**
	 * Switches the calendar to the next year.
	 */
	public void next() {
		MainCalendarController.getInstance().getDateController()
				.setToNextYear();

		Calendar cal = calendarMonth.getCalendar();
		cal.set(GregorianCalendar.YEAR, MainCalendarController.getInstance()
				.getDateController().getYear());
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		if (this.getParent() == null) {
			return;
		}

		calendarMonth.setFont(calendarMonth.getFont().deriveFont(
				Math.max(8f, (float) this.getParent().getSize().width / 85)));

		super.paint(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#repaint()
	 */
	public void repaint() {
		if (this.getParent() == null) {
			return;
		}

		if (Math.abs(calendarMonth.getHeight() - getHeight()) < 30) {
			this.setPreferredSize(new Dimension((int) (this.getParent()
					.getSize().getWidth() * 0.9), (int) (this.getParent()
					.getSize().getHeight() * 0.95)));
			System.out.println(1);
		} else {

			this.setPreferredSize(new Dimension((int) (this.getParent()
					.getSize().getWidth() * 0.9), Math.max((int) (this
					.getParent().getSize().getWidth() * 0.9 * 0.75),
					(int) (this.getParent().getSize().getHeight() * 0.95))));

		}
	}

}
