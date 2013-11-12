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

public abstract class Event extends CalendarItem {
	float endTime;
	
	/**Event constructor
	 * @param name
	 * @param date
	 * @param startTime
	 * @param endTime
	 */
	public Event(String name, Date date, float startTime,float endTime) {
		super(name, date, startTime);
		this.endTime = endTime;
		// TODO Auto-generated constructor stub
	}

}
