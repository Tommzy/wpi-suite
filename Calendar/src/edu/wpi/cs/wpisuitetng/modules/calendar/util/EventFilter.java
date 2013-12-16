/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetEventController;
import edu.wpi.cs.wpisuitetng.modules.calendar.events.EventsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;

/**
 * This class is used to filter a mist of commitments and return those that begin within a specified time slot
 *
 */
public class EventFilter {
	private GregorianCalendar startTime, endTime;
	private Collection<Event> eventList = new ArrayList<Event>();
	/**
	 * Constructor for commitment filter
	 * 
	 * @param startTime 
	 * @param endTime
	 */
	public EventFilter(GregorianCalendar startTime, GregorianCalendar endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Used to create a collection of commitments that begin within the start and end time
	 * 
	 * @return The collection of commitments within the time frame
	 */
	public Collection<Event> getEventList() {
		try {
			JButton button = new JButton("");
			button.addActionListener(new GetEventController());
			button.doClick();
			
			Collection<Event> list = EventsModel.getInstance().getAllEvent();
			Iterator<Event> itr = list.iterator();

			while (itr.hasNext()) {
				Event event = itr.next();
				if (event.getStartTime().after(startTime) && event.getStartTime().before(endTime)) {
					eventList.add(event);
				}
			}
//			System.out.println("SUCCESS PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
			return eventList;
		} catch (NullPointerException e) {
			System.out.println("event filter null pointer exception");
		}
		
		System.out.println("FAIL PRINT OUT cmtlist in the eventFileter.getEventList");
		return new ArrayList<Event> ();
	}
	
	/**
	 * Filter commitments based on an existing category 
	 * @param categeory Key word category
	 * @return List of filtered commitments
	 */
	public Collection<Event> filterOnCategory(/*Category categeory*/) {
		return eventList;
	}
	
	/**
	 * Filter commitments based on a key word string
	 * @param s Key word string
	 * @return List of filtered commitments
	 */
	public Collection<Event> filterOnString (String s) {
		Iterator<Event> itr = eventList.iterator();
		while (itr.hasNext()) {
			Event event = itr.next();
			if ((! event.getName().contains(s)) && (! event.getDescription().contains(s)) && (! event.getLocation().contains(s))) {
				eventList.remove(event);
			}
		}
		return eventList;
	}
}
