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

public abstract class CalenderItem {
	String name;
	Date date;
	float startTime;
	float duration;
	
	/**constructure
	 * @param name
	 * @param date
	 * @param startTime
	 */
	public CalenderItem(String name, Date date, float startTime){
		this.name = name;
		this.date = date;
		this.startTime = startTime;
	}

	
	/**return the duration of a calendar item.
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private float computeDuration(float startTime, float endTime) {
		// Assume 
		return (endTime-startTime);
	}
	
	

}
