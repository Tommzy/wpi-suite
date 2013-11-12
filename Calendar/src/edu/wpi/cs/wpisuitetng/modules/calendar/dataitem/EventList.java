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
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.dataitem;

public class EventList extends CalendarItemsList{
	
	/**
	 * constructor
	 */
	EventList(){
		super();
	}
	
	/**Add an event to event list
	 * @param event
	 */
	public void addEvents(Event event){
		this.addCalendarItem(event);
	}
	
	

}
