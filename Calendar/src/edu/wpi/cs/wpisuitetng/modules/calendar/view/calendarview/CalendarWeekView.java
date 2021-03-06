/*******************************************************************************
* Copyright (c) 2013 -- WPI Suite
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Team3
******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view.calendarview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CommitmentFilter;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DayEvent;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.EventFilter;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.Updatable;

/**
* Generate day calendar view.
*
*/
@SuppressWarnings("serial")
public class CalendarWeekView extends JPanel implements Updatable{
	
	/** The week. */
	CalendarDay[] week = new CalendarDay[8];
	
	/** The date. */
	DateController date = MainCalendarController.getInstance().getDateController().clone();
	
	/** The weekdays. */
	String[] weekdays = new DateFormatSymbols().getWeekdays();
	
	/** The header. */
	JLabel header = new JLabel("<HTML><div style='font-size:10'>&nbsp;<br />&nbsp;</div></HTML>");
	
	/** The today button. */
	private JButton previousButton = new JButton("<"), 
			nextButton = new JButton(">"), todayButton = new JButton("Today");
	
	/** The week panel. */
	JPanel weekPanel = new JPanel();
	
	/** The cmt list. */
	private Collection<Commitment> cmtList = new ArrayList<Commitment>();
	
	/** The event list */
	private Collection<Event> eventList = new ArrayList<Event>();
	
	/**
	 * Constructor
	 * Empty calendar.
	 */
	public CalendarWeekView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel btnPanel = new JPanel();
		btnPanel.add(previousButton);
		btnPanel.add(todayButton, "gapleft 10");
		btnPanel.add(nextButton, "gapleft 10");
		setupButtonListeners();

		weekPanel.setLayout(new BoxLayout(weekPanel, BoxLayout.X_AXIS));
		week[0] = new CalendarDay(null);
		week[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		week[0].add(header, BorderLayout.NORTH);
		weekPanel.add(week[0]);
		date.setToFirstDayOfWeek();
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date.clone());
			date.setToNextDate();
			week[i].initHeader();
			week[i].view.setPreferredSize(new Dimension(100, 450));
			weekPanel.add(week[i]);
		}
		
		add(btnPanel, "wrap");
		add(weekPanel, "wrap");
	}
	
	/**
	 * Constructor that consumes a list of DayEvents.
	 *
	 * @param events A list of events to be added to calendar
	 */
	public CalendarWeekView(List<Event> events) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel btnPanel = new JPanel();
		btnPanel.add(previousButton);
		btnPanel.add(todayButton, "gapleft 10");
		btnPanel.add(nextButton, "gapleft 10");
		setupButtonListeners();

		weekPanel.setLayout(new BoxLayout(weekPanel, BoxLayout.X_AXIS));
		week[0] = new CalendarDay(null);
		week[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		week[0].add(header, BorderLayout.NORTH);
		weekPanel.add(week[0]);
		date.setToFirstDayOfWeek();
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date.clone());
			date.setToNextDate();			
			week[i].initHeader();
			week[i].view.setPreferredSize(new Dimension(100, 450));
			weekPanel.add(week[i]);
		}
		
		for (int i=0; i < events.size(); i++) {
			addEvent(events.get(i), events.get(i).getStartTime().get(GregorianCalendar.DAY_OF_WEEK));
		}
		
		add(btnPanel, "wrap");
		add(weekPanel, "wrap");
	}

	/**
	 * Constructor that consumes an array of DayEvents.
	 *
	 * @param events An array of events to be added to calendar
	 */
	public CalendarWeekView (Event[] events) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel btnPanel = new JPanel();
		btnPanel.add(previousButton);
		btnPanel.add(todayButton, "gapleft 10");
		btnPanel.add(nextButton, "gapleft 10");
		setupButtonListeners();

		weekPanel.setLayout(new BoxLayout(weekPanel, BoxLayout.X_AXIS));
		week[0] = new CalendarDay(null);
		week[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		week[0].add(header, BorderLayout.NORTH);
		weekPanel.add(week[0]);
		date.setToFirstDayOfWeek();
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date.clone());
			date.setToNextDate();			
			week[i].initHeader();
			week[i].view.setPreferredSize(new Dimension(100, 450));
			weekPanel.add(week[i]);
		}
		
		for (int i=0; i < events.length; i++) {
			addEvent(events[i], events[i].getStartTime().get(GregorianCalendar.DAY_OF_WEEK));
		}
		
		add(btnPanel, "wrap");
		add(weekPanel, "wrap");	
		
	}
	
	/**
	 * Add an event to calendar.
	 *
	 * @param event Event to be added.
	 * @param dayOfWeek the day of week
	 */
	private void addEvent (Event event, int dayOfWeek) {
		week[dayOfWeek].addEvent(event);
	}
	
	/**
	 * Add an commitment to calendar.
	 *
	 * @param event Commitment to be added.
	 * @param dayOfWeek the day of week
	 */
	private void addCommitment (Commitment commitment, int dayOfWeek) {
		week[dayOfWeek].addCommitment(commitment);
	}
	
	/**
	 * Setup button listeners.
	 */
	private void setupButtonListeners() {
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousWeek();
			}
	
		});
		
		todayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentWeek();
			}	
		});	
		
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextWeek();
			}	
		});	
	}
	
	/**
	 * Previous week.
	 */
	private void previousWeek() {
		DateController date = MainCalendarController.getInstance().getDateController();
		date.setToPreviousWeek();
		MainCalendarController.getInstance().updateAll();
	}
	
	/**
	 * Current week.
	 */
	private void currentWeek() {
		DateController date = MainCalendarController.getInstance().getDateController();
		date.setToToday();
		MainCalendarController.getInstance().updateAll();
	}
	
	/**
	 * Next week.
	 */
	private void nextWeek() {
		DateController date = MainCalendarController.getInstance().getDateController();
		date.setToNextWeek();
		MainCalendarController.getInstance().updateAll();
	}
	
	/**
	 * Update week view.
	 */
	public void updateWeekView() {
		weekPanel.removeAll();
		weekPanel.setLayout(new BoxLayout(weekPanel, BoxLayout.X_AXIS));
		weekPanel.setPreferredSize(new Dimension(1200, 800));

		// Please do not change the dateController of MainCalendarController directly
		// use clone() if necessary
		date = MainCalendarController.getInstance().getDateController();
		DateController originalDate = date.clone();
		week[0] = new CalendarDay(date);
		week[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		week[0].add(header, BorderLayout.NORTH);
		weekPanel.add(week[0]);
		week[0].setPreferredSize(new Dimension(new Dimension((int) (this.getPreferredSize().getWidth() / 7), (int)(this.getPreferredSize().getHeight()))));
		date.setToFirstDayOfWeek();
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date.clone());
			date.setToNextDate();			
			week[i].initHeader();
			week[i].setPreferredSize(new Dimension(new Dimension((int) (this.getPreferredSize().getWidth() / 7), (int)(this.getPreferredSize().getHeight()))));	
			weekPanel.add(week[i]);
		}
		MainCalendarController.getInstance().setDateController(originalDate);
		parseEvent();
		parseCommitment();
		
		revalidate();
		repaint();
	}
	
	/**
	 * Parses the commitment.
	 */
	private void parseCommitment() {
		DateController dateController = MainCalendarController.getInstance().getDateController().clone();
		dateController.setToFirstDayOfWeek();
		GregorianCalendar calendarStart = new GregorianCalendar(dateController.getYear(), 
				dateController.getMonth(), dateController.getDayOfMonth(), 0, 0);
		
		dateController.setToNextWeek();
		
		GregorianCalendar calendarEnd = new GregorianCalendar(dateController.getYear(), 
				dateController.getMonth(), dateController.getDayOfMonth(), 0, 0);
		
		CommitmentFilter cmtFilter = new CommitmentFilter(calendarStart, calendarEnd);
		Collection<Commitment> cmtList = cmtFilter.getCommitmentList();
		this.cmtList  = cmtList;
		Iterator<Commitment> itr = cmtList.iterator();
		while (itr.hasNext()) {
			Commitment cmt = itr.next();
			addCommitment(cmt, cmt.getStartTime().get(GregorianCalendar.DAY_OF_WEEK));
		}
	}
	
	/**
	 * Parses the event.
	 */
	private void parseEvent() {
		DateController dateController = MainCalendarController.getInstance().getDateController().clone();
		dateController.setToFirstDayOfWeek();
		GregorianCalendar calendarStart = new GregorianCalendar(dateController.getYear(), 
				dateController.getMonth(), dateController.getDayOfMonth(), 0, 0);
		
		dateController.setToNextWeek();
		
		GregorianCalendar calendarEnd = new GregorianCalendar(dateController.getYear(), 
				dateController.getMonth(), dateController.getDayOfMonth(), 0, 0);
		
		EventFilter eventFilter = new EventFilter(calendarStart, calendarEnd);
		Collection<Event> eventList = eventFilter.getEventList();
		this.eventList  = eventList;
		Iterator<Event> itr = eventList.iterator();
		while (itr.hasNext()) {
			Event event = itr.next();
			addEvent(event, event.getStartTime().get(GregorianCalendar.DAY_OF_WEEK));
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
		for (int i = 1; i < week.length; i++) {
			week[i].setPreferredSize(new Dimension((int) (week[i].getParent().getPreferredSize().width / 7), (int) (week[i].getParent().getPreferredSize().getHeight())));
		}
	}
	
	//Test the detailed view, adding some new events
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarWeekView d = new CalendarWeekView();

		JScrollPane scroll = new JScrollPane(d);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(scroll);

		frame.pack();
		frame.setVisible(true);

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.calendar.view.Updatable#update()
	 */
	@Override
	public void update() {
		updateWeekView();
	}

	/**
	 * Gets the day view commitment list.
	 *
	 * @return the day view commitment list
	 */
	public Collection<Commitment> getDayViewCommitmentList() {
		return cmtList;
	}
	
	/**
	 * Gets the day view commitment list.
	 *
	 * @return the day view commitment list
	 */
	public Collection<Event> getDayViewEventList() {
		return eventList;
	}
		
	
}