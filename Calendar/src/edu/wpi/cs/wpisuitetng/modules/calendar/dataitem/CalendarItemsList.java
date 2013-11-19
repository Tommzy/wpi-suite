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

import java.util.ArrayList;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;


/**Abstruct class of calendar items list
 * @author Tommzy
 *
 */
public abstract class CalendarItemsList {
	ArrayList<CalendarItem> itemsList; 
	
	public CalendarItemsList(){
		this.itemsList = new ArrayList<CalendarItem>();
	}
	
	/**Add calendar item to the item list
	 * @param calendarItem It's type is CalendarItem
	 */
	public void addCalendarItem(CalendarItem calendarItem){
		//
		this.itemsList.add(calendarItem);
	}
}
