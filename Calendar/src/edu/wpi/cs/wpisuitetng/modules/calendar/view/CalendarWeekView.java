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

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

/**
 * Generate day calendar view. 
 * 
 */
@SuppressWarnings("serial")
public class CalendarWeekView extends JPanel {
	CalendarDay[] week = new CalendarDay[8];
	DateController date = MainCalendarController.getInstance().getDateController();
	String[] weekdays = new DateFormatSymbols().getWeekdays();
	JLabel header = new JLabel("<HTML><div>&nbsp;<br />&nbsp;</div></HTML>");
	
	/**
	 * Constructor
	 * Empty calendar
	 */
	public CalendarWeekView() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.lightGray));
		week[0] = new CalendarDay(date);
		week[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		week[0].add(header, BorderLayout.NORTH);
		add(week[0]);
		date = setFirstDayOfWeek(date);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date);
			week[i].initHeader();
			week[i].view.setPreferredSize(new Dimension(100, 450));
			add(week[i]);
			date = date.getNextDate();
		}
	}
	
	/**
	 * Constructor that consumes a list of DayEvents
	 * @param events A list of events to be added to calendar
	 */
	public CalendarWeekView(ArrayList<DayEvent> events) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.lightGray));
		week[0] = new CalendarDay(date);
		week[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		week[0].add(header, BorderLayout.NORTH);
		add(week[0]);
		date = setFirstDayOfWeek(date);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date);
			week[i].initHeader();
			week[i].view.setPreferredSize(new Dimension(100, 450));
			add(week[i]);
			date = date.getNextDate();
		}
		
		for (int i=0; i < events.size(); i++) {
			addEvent(events.get(i), events.get(i).getStartTime().get(GregorianCalendar.DAY_OF_WEEK));
		}
	}

	/**
	 * 
	 * Constructor that consumes an array of DayEvents
	 * @param events An array of events to be added to calendar
	 */
	public CalendarWeekView (DayEvent[] events) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.lightGray));
		week[0] = new CalendarDay(date);
		week[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		week[0].add(header, BorderLayout.NORTH);
		add(week[0]);
		date = setFirstDayOfWeek(date);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date);
			week[i].initHeader();
			week[i].view.setPreferredSize(new Dimension(130, 450));
			add(week[i]);
			date = date.getNextDate();
		}
		
		for (int i=0; i < events.length; i++) {
			addEvent(events[i], events[i].getStartTime().get(GregorianCalendar.DAY_OF_WEEK));
		}
		
		
	}
	
	/**
	 * Add an event to calendar
	 * @param event Event to be added.
	 */
	private void addEvent (DayEvent event, int dayOfWeek) {
		week[dayOfWeek].addEvent(event);
	}
	
	/**
	 * Set first day of week. 
	 * @param dc
	 * @return
	 */
	private DateController setFirstDayOfWeek (DateController dc) {
		DateController temp = new DateController(dc.getYear(), dc.getMonth(), dc.getDayOfMonth());
		temp.set(Calendar.DAY_OF_WEEK, dc.getFirstDayOfWeek());
		return temp;
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
	
	/**
	 * Resize panel as size of main window changes.
	 */
	public void repaint() {
		super.repaint();
		if (this.getParent() == null) {
			return;
		}
		this.setPreferredSize(new Dimension((int)(this.getParent().getSize().getWidth() * 0.9), (int)(this.getPreferredSize().getHeight())));
	}
	
}