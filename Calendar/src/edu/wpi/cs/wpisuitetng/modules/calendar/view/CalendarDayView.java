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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	final int minimalInterval = 2;

	public CalendarDayView() {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new BorderLayout());
		scroll.setMinimumSize(new Dimension(100, 500));
		initTimeLabels();

		add(scroll, BorderLayout.CENTER);
	}

	public CalendarDayView(ArrayList<DayEvent> events) {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new BorderLayout());
		scroll.setMinimumSize(new Dimension(100, 500));
		initTimeLabels();

		for (int i=0; i < events.size(); i++) {
			addEvent(events.get(i));
		}

		add(scroll, BorderLayout.CENTER);
	}

	public CalendarDayView(DayEvent[] events) {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new BorderLayout());
		scroll.setMinimumSize(new Dimension(100, 500));
		initTimeLabels();

		System.out.println("length: " + events.length);
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
				JLabel timeLabel = new JLabel((i / (60/minimalInterval) <= 9 ? "0" + i / (60/minimalInterval) : i / (60/minimalInterval)) + ":00  ");
				timeLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));
				view.add(timeLabel, c);
			} 
			//Half hours
			else {
				//Add an empty box, making the half hours take place as well
				Box box = Box.createVerticalBox();
				box.add(Box.createVerticalStrut(1));
				box.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));
				view.add(box, c);
			}
		}
	}

	public void addEvent(DayEvent event) {
		//Events overlapping with the new event is put into an array, and will
		//later be removed
		ArrayList<EventCard> toRemove = new ArrayList<EventCard>();
		//
		final JLabel newEvent = new JLabel(event.getEventName()); 
		// final JLabel newEvent = new JLabel(event.getEndTime().toString());
		newEvent.setVerticalAlignment(SwingConstants.TOP);
		newEvent.setHorizontalAlignment(SwingConstants.CENTER);
		newEvent.setOpaque(true);   //Make the label show it's background
		newEvent.setBackground(new Color(200, 240, 200));
		newEvent.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.lightGray));

		int labelSize = event.getTimeSpan() / minimalInterval; // 1 min = 1/2 px height

		for (EventCard eventCard : eventCards) {
			if (event.isActiveDuringTimeStamp(eventCard.event.getStartTime())
					|| event.isActiveDuringTimeStamp(eventCard.event.getEndTime())) {
				toRemove.add(eventCard); //Which events conflict with the new one

			}
		}
//		for (final EventCard eventCard : toRemove) {
//			eventCards.remove(eventCard);
//			SwingUtilities.invokeLater(new Runnable() {
//				//Remove the conflicting events
//				public void run() {
//					view.remove(eventCard.eventLabel);
//				}
//			});
//		}
		final GridBagConstraints cLocal = new GridBagConstraints();
		//Determine where to put the new event
		cLocal.gridy = (60/minimalInterval) * event.getStartTime().get(event.getStartTime().HOUR_OF_DAY) + event.getStartTime().get(event.getStartTime().MINUTE) / minimalInterval;
		cLocal.gridheight = labelSize;  cLocal.fill = GridBagConstraints.BOTH;
		cLocal.gridx = 2; cLocal.weightx = 1; cLocal.weighty = 1;
		SwingUtilities.invokeLater(new Runnable() {
			//Just add the new event at the correct position
			public void run() {
				view.add(newEvent, cLocal);
			}
		});
		//Keep track of the new event, and revalidate/repaint the view
		eventCards.add(new EventCard(event, newEvent));
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

	//Test the detailed view, adding some new events
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarDayView d = new CalendarDayView();
		d.addEvent(new DayEvent("Innebandy", new GregorianCalendar(2013, 5, 21, 15, 50, 0), new GregorianCalendar(2013, 5, 21, 16, 05, 0))); 
		
		JScrollPane scroll = new JScrollPane(d);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(scroll);
		
		frame.pack();
		frame.setVisible(true);

	}
}