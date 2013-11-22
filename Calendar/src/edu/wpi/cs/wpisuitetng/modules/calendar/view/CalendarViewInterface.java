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

/**
 * This interface is used by all 4 views of calendar. 
 * Each calendar view will have functions to jump back one unit, 
 * jump forward one unit, and jump to the current unit. 
 *
 */
public interface CalendarViewInterface {
	
	/**
	 * Bring calendar to the next unit (either day, week, month or year)
	 */
	public void next();
	
	/**
	 * Bring calendar to the previous unit (either day, week, month or year)
	 */
	public void previous();
	
	/**
	 * Bring calendar to the current unit (either day, week, month or year)
	 */
	public void today();
}
