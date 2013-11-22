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
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicLabelUI;

import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;

/**
 * Generate day calendar view. 
 * 
 */
@SuppressWarnings("serial")
public class CalendarDay extends JPanel {
	JPanel view = new JPanel(new GridBagLayout());
//	JScrollPane scroll = new JScrollPane(view,
//			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	ArrayList<EventCard> eventCards = new ArrayList<EventCard>();
	private HashMap<EventCard, GridBagConstraints> eventConstraint = new HashMap<EventCard, GridBagConstraints>();
	private final int minimalInterval = 2;
	private int currentMaxWidth = 1;
	int eventWidthMultiplier = 1;
	String[] weekdays = new DateFormatSymbols().getWeekdays();
	private final static String NON_THIN = "[^iIl1\\.,']";
	Calendar date;
	
	/**
	 * Constructor
	 * Create view of a calendar day
	 */
	public CalendarDay(int eventWidthMultiplier, Calendar date) {
		this.eventWidthMultiplier = eventWidthMultiplier;
		this.date = date;
		GridBagConstraints c = new GridBagConstraints();
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
		setLayout(new BorderLayout());
		initGridBox();
		revalidate();
		repaint();
		add(view, BorderLayout.CENTER);
	}
	
	/**
	 * Initialize box of calendar panel.
	 * An event has a resolution of 2 min in this implementation.
	 */
	private void initGridBox() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;

		for (int i = 0; i < 24*(60/minimalInterval); i++) {
			//Draw box
			c.gridy = i;
			Box box = Box.createVerticalBox();
			box.add(Box.createVerticalStrut(1));
			view.add(box, c);
		}
	}
	
	/**
	 * Initialize the time labels shown to the right.
	 * Every hour is displayed. 
	 */
	protected void initTimeLabels() {
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
			else {
				Box box = Box.createVerticalBox();
				box.add(Box.createVerticalStrut(1));
				box.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));
				view.add(box, c);
			}
		}
	}
	
	/**
	 * Initialize the header shown on the top.
	 */
	protected void initHeader() {
		JLabel header = new JLabel("<HTML><div style='text-align:center'>" + 
					format(date.get(GregorianCalendar.MONTH) + 1) + "-" + 
					format(date.get(GregorianCalendar.DATE)) + "-" + 
					date.get(GregorianCalendar.YEAR) + "<br />" + 
					weekdays[date.get(GregorianCalendar.DAY_OF_WEEK)] + "</div></HTML>");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		add(header, BorderLayout.NORTH);
	}
	
	/**
	 * Add an event to calendar
	 * @param event Event to be added
	 */
	public void addEvent(DayEvent event) {
		ArrayList<EventCard> conflict = new ArrayList<EventCard>();
		int newGridX = 0;
		boolean hasOverlap = false; 
		String eventName = event.getEventName();
		
		// Set up JLabel to display the event
		final JLabel newEvent = new JLabel(); 
		newEvent.setVerticalAlignment(SwingConstants.TOP);
		newEvent.setHorizontalAlignment(SwingConstants.CENTER);
		newEvent.setOpaque(true);   //Make the label show it's background
		newEvent.setBackground(new Color(200, 240, 200));
		newEvent.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
//		newEvent.setPreferredSize(new Dimension (200 / eventWidthMultiplier, newEvent.getMinimumSize().height));
		newEvent.setMaximumSize(new Dimension (100 / eventWidthMultiplier / currentMaxWidth, newEvent.getMinimumSize().height));
//		newEvent.setText(ellipsize(label, newEvent.getSize().width));
		
		// Construct label of the event
		String label = "";
		if (event.getTimeSpan() < 30) {
			label = "<HTML><p>" + ellipsize(event.getEventName(), newEvent.getMaximumSize().width) + "</p></HTML>";
		}
		if (event.getTimeSpan() >= 30 ) {
			label = "<HTML><p style='text-align:center'>" + event.getEventName() + "<br />" + 
					format(event.getStartTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
					format(event.getStartTime().get(GregorianCalendar.MINUTE)) + " - " +
					format(event.getEndTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
					format(event.getEndTime().get(GregorianCalendar.MINUTE)) + "</p></HTML>";
		}
		
		newEvent.setText(label);
		
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
		
		// Set up JLabel constraint
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
	 * Simple inner class for connecting events with it's corresponding
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
	
	/**
	 * Format number to String. 
	 * If number is less than 10, then display a "0" ahead of the number. 
	 * @param i The number to be formatted
	 * @return Formatted number in string format. 
	 */
	private String format(int i) {
		if (i <= 9) {
			return "0" + i;
		}
		else
			return Integer.toString(i);
	}
	
	/**
	 * Calculate text width
	 * @param str a string whose width to be calculated 
	 * @return width of the string
	 */
	private static int textWidth(String str) {
	    return (int) (str.length() /*- str.replaceAll(NON_THIN, "").length() / 2*/);
	}
	
	/**
	 * Chop off text if it exceeds label length
	 * @param text
	 * @param max
	 * @return
	 */
	public static String ellipsize(String text, int max) {

	    if (textWidth(text) <= max)
	        return text;

	    // Start by chopping off at the word before max
	    // This is an over-approximation due to thin-characters...
	    int end = text.lastIndexOf(' ', max - 1);

	    // Just one long word. Chop it off.
	    if (end == -1)
	        return text.substring(0, max-1) + ".";

	    // Step forward as long as textWidth allows.
	    int newEnd = end;
	    do {
	        end = newEnd;
	        newEnd = text.indexOf(' ', end + 1);

	        // No more spaces.
	        if (newEnd == -1)
	            newEnd = text.length();

	    } while (textWidth(text.substring(0, newEnd) + "...") < max);

	    return text.substring(0, end) + "...";
	}
	
	//Test the detailed view, adding some new events
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarDay d = new CalendarDay(1, GregorianCalendar.getInstance());
		d.addEvent(new DayEvent("Whoops", new GregorianCalendar(2013, 5, 21, 20, 50, 0), new GregorianCalendar(2013, 5, 21, 22, 5, 0))); 
		d.addEvent(new DayEvent("Innebandy", new GregorianCalendar(2013, 5, 21, 15, 50, 0), new GregorianCalendar(2013, 5, 21, 16, 5, 0))); 
		d.addEvent(new DayEvent("Abcd", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.addEvent(new DayEvent("Efgh", new GregorianCalendar(2013, 5, 21, 15, 55, 0), new GregorianCalendar(2013, 5, 21, 16, 15, 0))); 
		d.addEvent(new DayEvent("Hey", new GregorianCalendar(2013, 5, 21, 8, 50, 0), new GregorianCalendar(2013, 5, 21, 10, 21, 0))); 
		
		JScrollPane scroll = new JScrollPane(d);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(scroll);
		
		frame.pack();
		frame.setVisible(true);

	}
}