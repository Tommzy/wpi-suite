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

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
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
public class CalendarWeekView extends JPanel implements Updatable{
	
	/** The week. */
	CalendarDay[] week = new CalendarDay[8];
	
	/** The date. */
	DateController date = MainCalendarController.getInstance().getDateController();
	
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
		date = setFirstDayOfWeek(date);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date);
			date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek() + i - 1);
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
	public CalendarWeekView(ArrayList<DayEvent> events) {
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
		date = setFirstDayOfWeek(date);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date);
			date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek() + i - 1);
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
	public CalendarWeekView (DayEvent[] events) {
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
		date = setFirstDayOfWeek(date);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date);
			date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek() + i - 1);
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
	private void addEvent (DayEvent event, int dayOfWeek) {
		week[dayOfWeek].addEvent(event);
	}
	
	/**
	 * Set first day of week.
	 *
	 * @param dc the dc
	 * @return the date controller
	 */
	private DateController setFirstDayOfWeek (DateController dc) {
		DateController temp = new DateController(dc.getYear(), dc.getMonth(), dc.getDayOfMonth());
		temp.set(Calendar.DAY_OF_WEEK, dc.getFirstDayOfWeek());
		return temp;
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
		// Please do not change the dateController of MainCalendarController directly
		// use clone() if necessary
		date = MainCalendarController.getInstance().getDateController();
		DateController originalDate = date.clone();
		week[0] = new CalendarDay(date);
		week[0].initTimeLabels();
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		week[0].add(header, BorderLayout.NORTH);
		weekPanel.add(week[0]);
		for (int i = 1; i < weekdays.length; i++) {
			week[i] = new CalendarDay(date);
			date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek() + i - 1);
			week[i].initHeader();
			week[i].view.setPreferredSize(new Dimension(100, 450));	
			weekPanel.add(week[i]);
		}
		MainCalendarController.getInstance().setDateController(originalDate);
		parseCommitment();
		
		revalidate();
		repaint();
	}
	
	/**
	 * Parses the commitment.
	 */
	private void parseCommitment() {
		DateController dateController = MainCalendarController.getInstance().getDateController().clone();
		
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
			GregorianCalendar eventStartTime = cmt.getStartTime();
			GregorianCalendar eventEndTime = (GregorianCalendar) cmt.getStartTime().clone();
			eventEndTime.add(Calendar.HOUR, 1);
			DayEvent dayEvent = new DayEvent(cmt.getName(), eventStartTime, eventEndTime);
			addEvent(dayEvent, eventStartTime.get(GregorianCalendar.DAY_OF_WEEK));
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
		
	
}