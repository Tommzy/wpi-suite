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

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

/**
 * Generate day calendar view. 
 * 
 */
@SuppressWarnings("serial")
public class CalendarDayView extends JPanel {
	CalendarDay cd;
	DateController date = MainCalendarController.getInstance().getDateController();
	private Component superComponent;
	
	/**
	 * Constructor
	 * Empty calendar
	 */
	public CalendarDayView(Component comp) {
		setLayout(new MigLayout("insets 0 0 0 0"));
		cd = new CalendarDay(1, date);
		cd.eventWidthMultiplier = 1;
		cd.initTimeLabels();
		cd.initHeader();
		cd.view.setPreferredSize(new Dimension(700, 450));
		cd.view.setMinimumSize(new Dimension(500, 450));
		add(cd, "width :100%:");
	
		
	}
	
	/**
	 * Constructor that consumes a list of DayEvents
	 * @param events A list of events to be added to calendar
	 */
	public CalendarDayView(ArrayList<DayEvent> events, Component comp) {
		setLayout(new MigLayout("insets 0 0 0 0"));
		cd = new CalendarDay(1, date);
		cd.eventWidthMultiplier = 1;
		cd.initTimeLabels();
		cd.initHeader();
		cd.view.setMinimumSize(new Dimension(500, 450));
		add(cd, "width :100%:");
		
		for (int i=0; i < events.size(); i++) {
			addEvent(events.get(i));
		}
		
	}

	/**
	 * 
	 * Constructor that consumes an array of DayEvents
	 * @param events An array of events to be added to calendar
	 */
	public CalendarDayView (DayEvent[] events, Component comp) {
		this.superComponent = comp;
		setLayout(new MigLayout("insets 0 0 0 0"));
		cd = new CalendarDay(1, date);
		cd.eventWidthMultiplier = 1;
		cd.initTimeLabels();
		cd.initHeader();
//		cd.view.setPreferredSize(new Dimension(500, 450));
		add(cd, "width :100%:");
		
		for (int i=0; i < events.length; i++) {
			addEvent(events[i]);
		}
		
		
	}
	
	/**
	 * Resize panel as size of main window changes.
	 */
	public void repaint() {
		super.repaint();
		if (superComponent == null) {
			return;
		}
		setPreferredSize(new Dimension((int)(superComponent.getSize().getWidth()*0.9), (int)(this.getPreferredSize().getHeight())));
	}
	
	/**
	 * Add an event to calendar
	 * @param event Event to be added.
	 */
	private void addEvent (DayEvent event) {
		cd.addEvent(event);
	}
	
	//Test the detailed view, adding some new events
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarDayView d = new CalendarDayView(frame);
		
		d.cd.addEvent(new DayEvent("Whoopsssssssssssssssssssssssssssss", new GregorianCalendar(2013, 5, 21, 10, 50, 0), new GregorianCalendar(2013, 5, 21, 12, 5, 0))); 
		d.cd.addEvent(new DayEvent("Innebandy", new GregorianCalendar(2013, 5, 21, 15, 50, 0), new GregorianCalendar(2013, 5, 21, 16, 5, 0))); 
		d.cd.addEvent(new DayEvent("Abcd", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.cd.addEvent(new DayEvent("Efgh", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.cd.addEvent(new DayEvent("Hey", new GregorianCalendar(2013, 5, 21, 8, 40, 0), new GregorianCalendar(2013, 5, 21, 9, 15, 0))); 
		
		
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(d);
		
		frame.pack();
		frame.setVisible(true);

	}
	
	
}