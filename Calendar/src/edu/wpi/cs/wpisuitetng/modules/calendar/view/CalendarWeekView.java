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

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;

/**
 * Generate day calendar view. 
 * 
 */
@SuppressWarnings("serial")
public class CalendarWeekView extends JPanel {
	CalendarDay[] week = new CalendarDay[8];
	Calendar date = GregorianCalendar.getInstance();
	String[] weekdays = new DateFormatSymbols().getWeekdays();
	
	/**
	 * Constructor
	 * Empty calendar
	 */
	public CalendarWeekView() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		week[0] = new CalendarDay();
		week[0].initTimeLabels();
		week[0].add(new JLabel("<HTML><p>&nbsp;<br />&nbsp;</p></HTML>"), BorderLayout.NORTH);
		add(week[0]);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay();
			date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek() + i - 1);
			week[i].initHeader(date);
//			week[i].view.setSize(new Dimension(200, week[i].view.getMaximumSize().height));
			add(week[i]);
		}
	}
	
	/**
	 * Constructor that consumes a list of DayEvents
	 * @param events A list of events to be added to calendar
	 */
	public CalendarWeekView(ArrayList<DayEvent> events) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		week[0] = new CalendarDay();
		week[0].initTimeLabels();
		week[0].add(new JLabel("<HTML><p>&nbsp;<br />&nbsp;</p></HTML>"), BorderLayout.NORTH);
		add(week[0]);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(events);
			date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek() + i - 1);
			week[i].initHeader(date);
			add(week[i]);
		}
	}

	/**
	 * 
	 * Constructor that consumes an array of DayEvents
	 * @param events An array of events to be added to calendar
	 */
	public CalendarWeekView (DayEvent[] events) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		week[0] = new CalendarDay();
		week[0].initTimeLabels();
		week[0].add(new JLabel("<HTML><p>&nbsp;<br />&nbsp;</p></HTML>"), BorderLayout.NORTH);
		add(week[0]);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(events);
			date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek() + i - 1);
			week[i].initHeader(date);
			add(week[i]);
		}
	}
	
	//Test the detailed view, adding some new events
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarWeekView d = new CalendarWeekView();
		d.week[1].addEvent(new DayEvent("Whoops", new GregorianCalendar(2013, 5, 21, 20, 50, 0), new GregorianCalendar(2013, 5, 21, 22, 5, 0))); 
		d.week[2].addEvent(new DayEvent("Innebandy", new GregorianCalendar(2013, 5, 21, 15, 50, 0), new GregorianCalendar(2013, 5, 21, 16, 5, 0))); 
		d.week[3].addEvent(new DayEvent("Abcd", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.week[2].addEvent(new DayEvent("Efgh", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.week[1].addEvent(new DayEvent("Hey", new GregorianCalendar(2013, 5, 21, 8, 40, 0), new GregorianCalendar(2013, 5, 21, 9, 15, 0))); 
		
		JScrollPane scroll = new JScrollPane(d);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(scroll);
		
		frame.pack();
		frame.setVisible(true);

	}
}