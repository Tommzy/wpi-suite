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
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;


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
	 * @param commitment It's type is CalendarItem
	 */
	public void addCalendarItem(Commitment commitment){
		//
		//this.itemsList.add(commitment);
	}
}
