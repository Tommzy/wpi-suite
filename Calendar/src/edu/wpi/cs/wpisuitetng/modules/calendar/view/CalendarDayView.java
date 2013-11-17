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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;

/**
 *
 * @author Yuchen Zhang
 */
public class CalendarDayView extends JPanel {
	JPanel view = new JPanel(new GridBagLayout());
	JScrollPane scroll = new JScrollPane(view,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ArrayList<EventCard> eventCards = new ArrayList<EventCard>();
	HashMap<EventCard, GridBagConstraints> eventConstraint = new HashMap<EventCard, GridBagConstraints>();
	final int minimalInterval = 2;
	int currentMaxWidth = 1;
	Calendar date = GregorianCalendar.getInstance();
	String[] weekdays = new DateFormatSymbols().getWeekdays();

	public CalendarDayView() {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new BorderLayout());
		scroll.setMinimumSize(new Dimension(100, 500));
		initTimeLabels();
		JLabel header = new JLabel(format(date.get(GregorianCalendar.MONTH) + 1) + "-" + format(date.get(GregorianCalendar.DATE)) + "-" + date.get(GregorianCalendar.YEAR) + " " + weekdays[date.get(GregorianCalendar.DAY_OF_WEEK)]);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		add(header, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}
	
	public CalendarDayView(ArrayList<DayEvent> events) {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new BorderLayout());
		//scroll.setMinimumSize(new Dimension(100, 500));
		initTimeLabels();
		
		JLabel header = new JLabel(format(date.get(GregorianCalendar.MONTH) + 1) + "-" + format(date.get(GregorianCalendar.DATE)) + "-" + date.get(GregorianCalendar.YEAR) + " " + weekdays[date.get(GregorianCalendar.DAY_OF_WEEK)]);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		add(header, BorderLayout.NORTH);
		
		for (int i=0; i < events.size(); i++) {
			addEvent(events.get(i));
		}

		add(scroll, BorderLayout.CENTER);
	}

	public CalendarDayView(DayEvent[] events) {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new BorderLayout());
		//scroll.setMinimumSize(new Dimension(100, 500));
		initTimeLabels();

		JLabel header = new JLabel(format(date.get(GregorianCalendar.MONTH) + 1) + "-" + format(date.get(GregorianCalendar.DATE)) + "-" + date.get(GregorianCalendar.YEAR) + " " + weekdays[date.get(GregorianCalendar.DAY_OF_WEEK)]);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		add(header, BorderLayout.NORTH);
		
		for (int i=0; i < events.length; i++) {
			addEvent(events[i]);
		}

		add(scroll, BorderLayout.CENTER);
	}

	/**
	 * Initialize the time labels shown to the right.
	 * Every even hour is displayed, and a event have a resolution of 30 min
	 * in this implementation
	 */
	private void initTimeLabels() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;

		for (int i = 0; i < 24*(60/minimalInterval); i++) {
			//Even hours
			c.gridy = i;
			if (i % (60/minimalInterval) == 0) {
				JLabel timeLabel = new JLabel(format(i / (60/minimalInterval)) + ":00  ");
				timeLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));
				view.add(timeLabel, c);
			} 
			//Minutes
			else {
				//Add an empty box, making the minutes take place as well
				Box box = Box.createVerticalBox();
				box.add(Box.createVerticalStrut(1));
				box.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));
				view.add(box, c);
			}
		}
	}

	public void addEvent(DayEvent event) {
		ArrayList<EventCard> conflict = new ArrayList<EventCard>();
		int newGridX = 0;
		boolean hasOverlap = false;
		//
		String label = "";
		if (event.getTimeSpan() < 30) {
			label = "<HTML><p>" + event.getEventName() + "</p></HTML>";
		}
		if (event.getTimeSpan() >= 30 ) {
			label = "<HTML><p style='text-align:center'>" + event.getEventName() + "<br />" + 
					format(event.getStartTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
					format(event.getStartTime().get(GregorianCalendar.MINUTE)) + " - " +
					format(event.getEndTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
					format(event.getEndTime().get(GregorianCalendar.MINUTE)) + "</p></HTML>";
		}
		final JLabel newEvent = new JLabel(); 
		newEvent.setText(label);
		newEvent.setVerticalAlignment(SwingConstants.TOP);
		newEvent.setHorizontalAlignment(SwingConstants.CENTER);
		newEvent.setOpaque(true);   //Make the label show it's background
		newEvent.setBackground(new Color(200, 240, 200));
		newEvent.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
		newEvent.setPreferredSize(new Dimension (100, newEvent.getMinimumSize().height));

		int labelSize = event.getTimeSpan() / minimalInterval; // 1 min = 1/minimalInterval px height

		for (EventCard eventCard : eventCards) {
			if (event.isActiveDuringTimeStamp(eventCard.event.getStartTime())
					|| event.isActiveDuringTimeStamp(eventCard.event.getEndTime())) {
				conflict.add(eventCard); //Which events conflict with the new one
				newGridX = newGridX < eventConstraint.get(eventCard).gridx? eventConstraint.get(eventCard).gridx : newGridX;
				currentMaxWidth = currentMaxWidth < newGridX + 1? newGridX + 1: currentMaxWidth;
				hasOverlap = true;
			}
		}
		
		// Resize event width
//		for (EventCard eventCard : eventCards) {
//			if (hasOverlap && (!conflict.contains(eventCard)) ) {
//				eventConstraint.get(eventCard).gridwidth++;
//				view.remove(eventCard.eventLabel);
//				view.add(eventCard.eventLabel, eventConstraint.get(eventCard));
//			}
//		}
		
		final GridBagConstraints cLocal = new GridBagConstraints();
		//Determine where to put the new event
		cLocal.gridy = (60/minimalInterval) * event.getStartTime().get(GregorianCalendar.HOUR_OF_DAY) + event.getStartTime().get(GregorianCalendar.MINUTE) / minimalInterval;
		cLocal.gridheight = labelSize;  
		cLocal.fill = GridBagConstraints.BOTH;
		cLocal.gridx = newGridX + 1;
		//cLocal.gridwidth = currentMaxWidth - newGridX;
		cLocal.gridwidth = 1;
		cLocal.weightx = 1; 
		cLocal.weighty = 1;
		SwingUtilities.invokeLater(new Runnable() {
			//Just add the new event at the correct position
			public void run() {
				view.add(newEvent, cLocal);
			}
		});
		//Keep track of the new event, and revalidate/repaint the view
		EventCard eventCard = new EventCard(event, newEvent);
		eventCards.add(eventCard);
		eventConstraint.put(eventCard, cLocal);
		revalidate();
		repaint();
	}

	/**
	 * Simple innner class for connecting events with it's corresponding
	 * visual representation
	 */
	private class EventCard {

		private DayEvent event;
		private JLabel eventLabel;

		public EventCard(DayEvent event, JLabel eventLabel) {
			this.event = event;
			this.eventLabel = eventLabel;
		}
	}
	
	private String format(int i) {
		if (i <= 9) {
			return "0" + i;
		}
		else
			return Integer.toString(i);
	}
	
	private enum weekday {
		Sunday,
		Monday,
		Tuesday,
		Wednesday,
		Thursday,
		Friday,
		Saturday
	}

	//Test the detailed view, adding some new events
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarDayView d = new CalendarDayView();
		d.addEvent(new DayEvent("Whoops", new GregorianCalendar(2013, 5, 21, 20, 50, 0), new GregorianCalendar(2013, 5, 21, 22, 5, 0))); 
		d.addEvent(new DayEvent("Innebandy", new GregorianCalendar(2013, 5, 21, 15, 50, 0), new GregorianCalendar(2013, 5, 21, 16, 5, 0))); 
		d.addEvent(new DayEvent("Abcd", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.addEvent(new DayEvent("Efgh", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.addEvent(new DayEvent("Hey", new GregorianCalendar(2013, 5, 21, 8, 50, 0), new GregorianCalendar(2013, 5, 21, 9, 21, 0))); 
		
		JScrollPane scroll = new JScrollPane(d);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(scroll);
		
		frame.pack();
		frame.setVisible(true);

	}
}