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

package edu.wpi.cs.wpisuitetng.modules.calendar.view.calendarview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller.UpdateCommitmentListener;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller.UpdateEventListener;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CategoryFilter;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.EventLabel;

/**
 * Generate day calendar view. 
 * 
 */
@SuppressWarnings("serial")
public class CalendarDay extends JPanel {
	JPanel view = new JPanel(new GridBagLayout());
	
	List<CalendarCard> calendarCards = new ArrayList<CalendarCard>();
	private Map<CalendarCard, GridBagConstraints> eventConstraint = new HashMap<CalendarCard, GridBagConstraints>();
	private final int minimalInterval = 2;
	private int currentMaxWidth = 1;
	String[] weekdays = new DateFormatSymbols().getWeekdays();
	DateController dateController;
	private final int COMMITMENT_TIME_SPAN = 60;
	
	/**
	 * Constructor
	 * Create view of a calendar day 
	 */
	public CalendarDay(DateController date) {
		dateController = date;
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
		setLayout(new BorderLayout());
		initGridBox();
		add(view, BorderLayout.CENTER);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				printDate();
			}
		});
	}
	
	private void printDate() {
		System.out.println("Calendar Day " + dateController + "clicked");
	}
	
	/**
	 * Initialize box of calendar panel.
	 * An event has a resolution of 2 min in this implementation.
	 */
	private void initGridBox() {
		GridBagConstraints constrain = new GridBagConstraints();
		constrain.gridx = 0;
		constrain.weightx = 0;
		constrain.weighty = 1;
		constrain.fill = GridBagConstraints.BOTH;
		constrain.anchor = GridBagConstraints.NORTH;

		for (int i = 0; i < 25*(60/minimalInterval); i++) {
			//Draw box
			constrain.gridy = i;
			Box box = Box.createVerticalBox();
			box.add(Box.createVerticalStrut(1));
			view.add(box, constrain);
			constrain.gridx = 1;
			constrain.weightx = 1;
			view.add(box, constrain);
		}
	}
	
	/**
	 * Initialize the time labels shown to the left.
	 * Every hour is displayed. 
	 */
	protected void initTimeLabels() {
		GridBagConstraints constrain = new GridBagConstraints();
		constrain.gridx = 0;
		constrain.weightx = 0;
		constrain.weighty = 1;
		constrain.fill = GridBagConstraints.BOTH;
		constrain.anchor = GridBagConstraints.NORTH;
		System.out.print(Color.lightGray.getRed() + " " + Color.lightGray.getGreen() + " " + Color.lightGray.getBlue());
		
		for (int i = 0; i < 25*(60/minimalInterval); i++) {
			//Even hours
			constrain.gridy = i;
			if (i == 0) {
				JLabel timeLabel = new JLabel("midnight");
				timeLabel.setFont(timeLabel.getFont().deriveFont(10f));
				constrain.gridheight = 15;
				view.add(timeLabel, constrain);
			}
			else if ((i % (60/minimalInterval) == 0) && (i / (60/minimalInterval) != 24)) {
				JLabel timeLabel = new JLabel(format(i / (60/minimalInterval)) + ":00  ");
				timeLabel.setFont(timeLabel.getFont().deriveFont(10f));
				constrain.gridheight = 15;
				view.add(timeLabel, constrain);
			} 
			else {
				Box box = Box.createVerticalBox();
				box.add(Box.createVerticalStrut(1));
				box.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.lightGray));
				constrain.gridheight = 1;
				view.add(box, constrain);
			}
		}
	}
	
	/**
	 * Initialize the header shown on the top.
	 */
	protected void initHeader() {
		JLabel header = new JLabel("<HTML><div style='text-align:center; font-size:10'>" + 
					format(dateController.getMonth() + 1) + "-" + 
					format(dateController.getDayOfMonth()) + "-" + 
					dateController.getYear() + "<br />" + 
					weekdays[dateController.getDayOfWeek()] + "</div></HTML>");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		add(header, BorderLayout.NORTH);
	}
	
	/**
	 * Add an event to calendar
	 * @param event Event to be added
	 */
	public void addEvent(Event event) {
		List<CalendarCard> conflict = new ArrayList<CalendarCard>();
		int newGridX = 0;
		boolean hasOverlap = false; 
		String eventName = event.getName();
		
		// Construct label of the event
		String label = formatLabel(event);
		
		// Set up JLabel to display the event
		EventLabel newEvent = new EventLabel(label); 
		newEvent.setFont(newEvent.getFont().deriveFont(10f));
		newEvent.setVerticalAlignment(SwingConstants.TOP);
		newEvent.setHorizontalAlignment(SwingConstants.CENTER);
		newEvent.setOpaque(true);   //Make the label show it's background
		if (event.getCategoryID() != -1) {
			Category[] cats = new CategoryFilter().getCategoryAllArray();
			for(int i = 0; i < cats.length; i++) {
				if (cats[i].getId() == event.getCategoryID()) {
					newEvent.setBackground(cats[i].getColor());
				}
			}
		}
		else {
			newEvent.setBackground(null);
		}
		newEvent.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
		newEvent.setToolTipText(formatToolTip(event));
		newEvent.addMouseListener(new UpdateEventListener(event));
		
		int labelSpan = event.getTimeSpan() < 20? 20 : event.getTimeSpan();
		int labelSize = labelSpan / minimalInterval; // 1 min = 1/minimalInterval px height

		for (CalendarCard card : calendarCards) {
			if (event.isActiveDuringTimeStamp(card.getStartTime())
					|| event.isActiveDuringTimeStamp(card.getEndTime())) {
				conflict.add(card); //Which events conflict with the new one
				newGridX = newGridX < eventConstraint.get(card).gridx? eventConstraint.get(card).gridx : newGridX;
				currentMaxWidth = currentMaxWidth < newGridX + 1? newGridX + 1: currentMaxWidth;
				hasOverlap = true;
			}
		}
		
		// Set up JLabel constraint
		final GridBagConstraints cLocal = new GridBagConstraints();
		//Determine where to put the new event
		cLocal.gridy = (60/minimalInterval) * event.getStartTime().get(GregorianCalendar.HOUR_OF_DAY) + event.getStartTime().get(GregorianCalendar.MINUTE) / minimalInterval;
		cLocal.gridheight = labelSize >= (25*60/minimalInterval - cLocal.gridy)? (25*60/minimalInterval - cLocal.gridy) : labelSize;  
		cLocal.fill = GridBagConstraints.BOTH;
		cLocal.gridx = newGridX + 1;
		//cLocal.gridwidth = currentMaxWidth - newGridX;
		cLocal.gridwidth = 1;
		cLocal.weightx = 1; 
		cLocal.weighty = 1;
		view.add(newEvent, cLocal);
		//Keep track of the new event, and revalidate/repaint the view
		CalendarCard eventCard = new EventCard(event, newEvent);
		calendarCards.add(eventCard);
		eventConstraint.put(eventCard, cLocal);
		revalidate();
		repaint();
	}

	/**
	 * Add an event to calendar
	 * @param event Event to be added
	 */
	public void addCommitment(Commitment commitment) {
		List<CalendarCard> conflict = new ArrayList<CalendarCard>();
		int newGridX = 0;
		boolean hasOverlap = false; 
		String eventName = commitment.getName();
		
		// Construct label of the commitment
		String label = "";	
		label = formatLabel(commitment);
		
		// Set up JLabel to display the event
		EventLabel newCommitment = new EventLabel(label);
		newCommitment.setFont(newCommitment.getFont().deriveFont(10f));
		newCommitment.setVerticalAlignment(SwingConstants.TOP);
		newCommitment.setHorizontalAlignment(SwingConstants.CENTER);
		newCommitment.setOpaque(true);   //Make the label show it's background
		if (commitment.getCategoryID() != -1) {
			Category[] cats = new CategoryFilter().getCategoryAllArray();
			for(int i = 0; i < cats.length; i++) {
				if (cats[i].getId() == commitment.getCategoryID()) {
					newCommitment.setBackground(cats[i].getColor());
				}
			}
		}
		else {
			newCommitment.setBackground(null);
		}
		newCommitment.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
		newCommitment.setToolTipText(formatToolTip(commitment));
		newCommitment.addMouseListener(new UpdateCommitmentListener(commitment));
		
		int labelSpan = COMMITMENT_TIME_SPAN; 
		int labelSize = labelSpan / minimalInterval; // 1 min = 1/minimalInterval px height

		for (CalendarCard card : calendarCards) {
			if (commitment.isActiveDuringTimeStamp(card.getStartTime())
					|| commitment.isActiveDuringTimeStamp(card.getEndTime())) {
				conflict.add(card); //Which events conflict with the new one
				newGridX = newGridX < eventConstraint.get(card).gridx? eventConstraint.get(card).gridx : newGridX;
				currentMaxWidth = currentMaxWidth < newGridX + 1? newGridX + 1: currentMaxWidth;
				hasOverlap = true;
			}
		}
		
		// Set up JLabel constraint
		final GridBagConstraints cLocal = new GridBagConstraints();
		//Determine where to put the new event
		cLocal.gridy = (60/minimalInterval) * commitment.getStartTime().get(GregorianCalendar.HOUR_OF_DAY) + commitment.getStartTime().get(GregorianCalendar.MINUTE) / minimalInterval;
		cLocal.gridheight = labelSize;  
		cLocal.fill = GridBagConstraints.BOTH;
		cLocal.gridx = newGridX + 1;
		//cLocal.gridwidth = currentMaxWidth - newGridX;
		cLocal.gridwidth = 1;
		cLocal.weightx = 1; 
		cLocal.weighty = 1;
		view.add(newCommitment, cLocal);

		//Keep track of the new event, and revalidate/repaint the view
		CalendarCard eventCard = new CommitmentCard(commitment, newCommitment);
		calendarCards.add(eventCard);
		eventConstraint.put(eventCard, cLocal);
		
		revalidate();
		repaint();
	}

	/**
	 * Create label text for an event based on event's length
	 * @param event Event that needs a label text
	 * @return Label for that event
	 */
	private String formatLabel(Event event) {
		String label = "";
		if (event.getTimeSpan() < 30) {
			label = "<HTML><div style='text-align:center'>" + event.getName() + "</div></HTML>";
		}
		if (event.getTimeSpan() >= 30 ) {
			label = "<HTML><font color=black><p style='text-align:center'>" + event.getName() + "<br />" + 
					format(event.getStartTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
					format(event.getStartTime().get(GregorianCalendar.MINUTE)) + " - " +
					format(event.getEndTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
					format(event.getEndTime().get(GregorianCalendar.MINUTE)) + "</p></font></HTML>";
		}
		return label;
	}
	
	/**
	 * Create label text for an event based on event's length
	 * @param event Event that needs a label text
	 * @return Label for that event
	 */
	private String formatLabel(Commitment commitment) {
		String label = "";
		if (commitment.getStatus() == 2) {
			label = "<HTML><font color=gray><p style='text-align:center'>" + commitment.getName() + "<br />" + 
					format(commitment.getStartTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
					format(commitment.getStartTime().get(GregorianCalendar.MINUTE)) + "</p></font></HTML>";
		}
		else {
			label = "<HTML><font color=black><p style='text-align:center'>" + commitment.getName() + "<br />" + 
					format(commitment.getStartTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
					format(commitment.getStartTime().get(GregorianCalendar.MINUTE)) + "</p></font></HTML>";
		}
		return label;
	}

	/**
	 * Create tooltip text for an event 
	 * @param event Event that needs a label text
	 * @return Label for that event
	 */
	private String formatToolTip(Event event) {
		String label = "<HTML><div style='text-align:center'>" + event.getName() + "<br />" + 
				format(event.getStartTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
				format(event.getStartTime().get(GregorianCalendar.MINUTE)) + " - " +
				format(event.getEndTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
				format(event.getEndTime().get(GregorianCalendar.MINUTE)) + "</div></HTML>";
		return label;
	}
	
	/**
	 * Create tooltip text for a commitment
	 * @param commitment Commitment that needs a label text
	 * @return Label for that event
	 */
	private String formatToolTip(Commitment commitment) {
		
		String label = "<HTML><div style='text-align:center'>" + commitment.getName() + "<br />" + 
				format(commitment.getStartTime().get(GregorianCalendar.HOUR_OF_DAY)) + ":" +
				format(commitment.getStartTime().get(GregorianCalendar.MINUTE)) + "</div></HTML>";
		
		return label;
	}
	
	/**
	 * Simple inner class for connecting events/commitments with it's corresponding
	 * visual representation
	 */
	private interface CalendarCard {
		
		 GregorianCalendar getStartTime();
		
		 GregorianCalendar getEndTime();
		
	}

	/**
	 * calendar card for event
	 * @author Yuchen
	 *
	 */
	private class EventCard implements CalendarCard {
		private Event event;
		private JLabel label;
		
		public EventCard(Event event, JLabel eventLabel) {
			this.event = event;
			label = eventLabel;
		}

		@Override
		public GregorianCalendar getStartTime() {
			return event.getStartTime();
		}

		@Override
		public GregorianCalendar getEndTime() {
			return event.getEndTime();
		}
	}
	
	/**
	 * calendar card for commitment
	 * @author Yuchen
	 *
	 */
	private class CommitmentCard implements CalendarCard { 
		private Commitment commitment;
		private JLabel label;
		
		public CommitmentCard(Commitment commitment, JLabel commitmentLabel) {
			this.commitment = commitment;
			label = commitmentLabel;
		}

		@Override
		public GregorianCalendar getStartTime() {
			return commitment.getStartTime();
		}

		@Override
		public GregorianCalendar getEndTime() {
			GregorianCalendar endTime = (GregorianCalendar) commitment.getStartTime().clone();
			endTime.add(GregorianCalendar.HOUR, 1);
			return endTime;
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
		else{
			return Integer.toString(i);
		}
	}
	
	/**
	 * Override function of paint(Graphics g)
	 */
	@Override
	public void paint(Graphics g) {
		Component[] components = view.getComponents();
		resizeAllLabel();
		int preferredWidth = this.getSize().width / currentMaxWidth;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EventLabel) {
				if ((! ((EventLabel)components[i]).getText().equals(null)) && (((EventLabel)components[i]).getSize().width != 0)) {
					
					String currentLabel = ((EventLabel)components[i]).getOriginalContent();
					// Split up string to pull content from html tags
					String[] content = currentLabel.split("<br />");
					content[0] = content[0].split("'>")[content[0].split("'>").length - 1];
					content[content.length - 1] = content[content.length - 1].split("</")[0];
					// Trim string line by line 
					for (int j = 0; j < content.length; j++) {
						content [j] = ellipsize(content[j], ((EventLabel)components[i]).getSize().width);
					}
					
					// Add html tags into the new label strings
					String color = ((EventLabel)components[i]).getText().split("<font color=")[1].split(">")[0];
					String newText = "<HTML><font color=" + color + "><p style='text-align:center'>";
					for (int j = 0; j < content.length; j++) {
						if (j != content.length - 1) {
							newText += content[j] + "<br />";
						}
						else {
							newText += content[j];
						}
					}
					newText += "</p></font></HTML>";
					((EventLabel)components[i]).setText(newText);
					
				}
			}
		}
		super.paint(g);
		
	}
	
	/**
	 * resize all lables
	 */
	private void resizeAllLabel() {
		Component[] components = view.getComponents();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EventLabel) {
				components[i].setPreferredSize(new Dimension(view.getSize().width / currentMaxWidth, components[i].getSize().height));
			}
		}
	}

	/**
	 * Calculate text width
	 * @param str a string whose width to be calculated 
	 * @return width of the string
	 */
	private static int textWidth(String str) {
	    return (int) (str.length() * 6.5 /*- str.replaceAll(NON_THIN, "").length() / 2*/);
	}
	
	/**
	 * Chop off text if it exceeds label length
	 * @param text
	 * @param max
	 * @return product string that got chopped off
	 */
	public static String ellipsize(String text, int max) {

	    if (textWidth(text) <= max){
	        return text;
	    }
	    // Start by chopping off at the word before max
	    // This is an over-approximation due to thin-characters...
	    int end = text.lastIndexOf(' ', (int) (max / 6.5 - 3));

	    // Just one long word. Chop it off.
	    if (end == -1) {
	    	if ((int) (max / 6.5 - 3) < 0){
	    		return text.substring(0, 0) + "...";
	    	}
	    	else {
	    		return text.substring(0, (int) (max / 6.5 - 3)) + "...";
	    	}
	    }

	    // Step forward as long as textWidth allows.
	    int newEnd = end;
	    do {
	        end = newEnd;
	        newEnd = text.indexOf(' ', end + 1);

	        // No more spaces.
	        if (newEnd == -1){
	            newEnd = text.length();
	        }
	    } while (textWidth(text.substring(0, newEnd) + "...") < max);

	    return text.substring(0, end) + "...";
	}
	
	
}