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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CommitmentFilter;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

/**
 * Generate day calendar view. 
 * 
 */
@SuppressWarnings("serial")
public class CalendarDayView extends JPanel implements Updatable{
	CalendarDay[] day = new CalendarDay[2];
	JLabel header = new JLabel("<HTML><div>&nbsp;<br />&nbsp;</div></HTML>");
	DateController date = MainCalendarController.getInstance().getDateController();
	private JButton previousButton = new JButton("<"), 
			nextButton = new JButton(">"), todayButton = new JButton("Today");
	JPanel dayPanel = new JPanel();
	
	// a commitment list copy 
	// commitment table will retrieve this cmtList and display accordingly
	// if MainCalendarController says it is on the Day view
	private Collection<Commitment> cmtList = new ArrayList<Commitment>();
	
	/**
	 * Constructor
	 * Empty calendar
	 */
	public CalendarDayView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel btnPanel = new JPanel();
		btnPanel.add(previousButton);
		btnPanel.add(todayButton, "gapleft 10");
		btnPanel.add(nextButton, "gapleft 10");
		setupButtonListeners();

		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.lightGray));
		day[0] = new CalendarDay(date);
		day[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.lightGray));
		day[0].add(header, BorderLayout.NORTH);
		dayPanel.add(day[0]);

		
		day[1] = new CalendarDay(date);
		day[1].initHeader();
		day[1].view.setPreferredSize(new Dimension(1200, 450));
		dayPanel.add(day[1]);
		
		
		add(btnPanel, "wrap");
		add(dayPanel, "wrap");
			
	}
	
	/**
	 * Constructor that consumes a list of DayEvents
	 * @param events A list of events to be added to calendar
	 */
	public CalendarDayView(ArrayList<DayEvent> events) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel btnPanel = new JPanel();
		btnPanel.add(previousButton);
		btnPanel.add(todayButton, "gapleft 10");
		btnPanel.add(nextButton, "gapleft 10");
		setupButtonListeners();

		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.lightGray));
		day[0] = new CalendarDay(date);
		day[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.lightGray));
		day[0].add(header, BorderLayout.NORTH);
		dayPanel.add(day[0]);

		
		day[1] = new CalendarDay(date);
		day[1].initHeader();
		day[1].view.setPreferredSize(new Dimension(1200, 450));
		dayPanel.add(day[1]);
		
		
		add(btnPanel, "wrap");
		add(dayPanel, "wrap");
		
		for (int i=0; i < events.size(); i++) {
			addEvent(events.get(i));
		}
		
	}

	/**
	 * 
	 * Constructor that consumes an array of DayEvents
	 * @param events An array of events to be added to calendar
	 */
	public CalendarDayView (DayEvent[] events) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel btnPanel = new JPanel();
		btnPanel.add(previousButton);
		btnPanel.add(todayButton, "gapleft 10");
		btnPanel.add(nextButton, "gapleft 10");
		setupButtonListeners();

		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.lightGray));
		day[0] = new CalendarDay(date);
		day[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.lightGray));
		day[0].add(header, BorderLayout.NORTH);
		dayPanel.add(day[0]);

		
		day[1] = new CalendarDay(date);
		day[1].initHeader();
		day[1].view.setPreferredSize(new Dimension(1200, 450));
		dayPanel.add(day[1]);
		
		
		add(btnPanel, "wrap");
		add(dayPanel, "wrap");
		
		for (int i=0; i < events.length; i++) {
			addEvent(events[i]);
		}
		
		
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
	
	/**
	 * Add an event to calendar
	 * @param event Event to be added.
	 */
	private void addEvent (DayEvent event) {
		day[1].addEvent(event);
	}
	
	private void setupButtonListeners() {
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousDay();
			}	
		});
		
		todayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDay();
			}	
		});	
		
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextDay();
			}	
		});	
	}
	
	private void previousDay() {
		DateController date = MainCalendarController.getInstance().getDateController();
		date.setToPreviousDate();
		MainCalendarController.getInstance().updateAll();
	}
	
	private void currentDay() {
		DateController date = MainCalendarController.getInstance().getDateController();
		date.setToToday();
		MainCalendarController.getInstance().updateAll();
	}
	
	private void nextDay() {
		DateController date = MainCalendarController.getInstance().getDateController();
		date.setToNextDate();
		MainCalendarController.getInstance().updateAll();
	}
	
	public void updateDayView() {
		dayPanel.removeAll();
		DateController date = MainCalendarController.getInstance().getDateController();
		
		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.X_AXIS));
		dayPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.lightGray));
		day[0] = new CalendarDay(date);
		day[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		day[0].add(header, BorderLayout.NORTH);
		day[0].view.setPreferredSize(new Dimension(75,450));
		dayPanel.add(day[0]);
		
		day[1] = new CalendarDay(date);
		day[1].initHeader();
		day[1].view.setPreferredSize(new Dimension(1200,450));
		dayPanel.add(day[1], "width :100%:");
		parseCommitment();
		revalidate();
		repaint();
	}
	
	public Collection<Commitment> getDayViewCommitmentList() {
		return cmtList;
	}
	
	public void parseCommitment() {
		DateController dateController = MainCalendarController.getInstance().getDateController().clone();
		
		GregorianCalendar calendarStart = new GregorianCalendar(dateController.getYear(), 
				dateController.getMonth(), dateController.getDayOfMonth(), 0, 0);
		
		dateController.setToNextDate();
		
		GregorianCalendar calendarEnd = new GregorianCalendar(dateController.getYear(), 
				dateController.getMonth(), dateController.getDayOfMonth(), 0, 0);
		
		CommitmentFilter cmtFilter = new CommitmentFilter(calendarStart, calendarEnd);
		Collection<Commitment> cmtList = cmtFilter.getCommitmentList();
		this.cmtList = cmtList;
		Iterator<Commitment> itr = cmtList.iterator();
		while (itr.hasNext()) {
			Commitment cmt = itr.next();
			GregorianCalendar eventStartTime = cmt.getStartTime();
			GregorianCalendar eventEndTime = (GregorianCalendar) cmt.getStartTime().clone();
			eventEndTime.add(Calendar.HOUR, 1);
			DayEvent dayEvent = new DayEvent(cmt.getName(), eventStartTime, eventEndTime);
			addEvent(dayEvent);
			//System.out.println(cmt.getName() + " " + eventStartTime.get(GregorianCalendar.HOUR_OF_DAY) + " " + eventEndTime.get(GregorianCalendar.HOUR_OF_DAY));
		}
	}
	
	//Test the detailed view, adding some new events
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarDayView d = new CalendarDayView();
		
		d.day[1].addEvent(new DayEvent("Whoopsssssssssssssssssssssssssssss", new GregorianCalendar(2013, 5, 21, 10, 50, 0), new GregorianCalendar(2013, 5, 21, 12, 5, 0))); 
		d.day[1].addEvent(new DayEvent("Innebandy", new GregorianCalendar(2013, 5, 21, 15, 50, 0), new GregorianCalendar(2013, 5, 21, 16, 5, 0))); 
		d.day[1].addEvent(new DayEvent("Abcd", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.day[1].addEvent(new DayEvent("Efgh", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.day[1].addEvent(new DayEvent("Hey", new GregorianCalendar(2013, 5, 21, 8, 40, 0), new GregorianCalendar(2013, 5, 21, 9, 15, 0))); 
		
		
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(d);
		
		frame.pack();
		frame.setVisible(true);

	}

	@Override
	public void update() {
		updateDayView();
	}
	
}