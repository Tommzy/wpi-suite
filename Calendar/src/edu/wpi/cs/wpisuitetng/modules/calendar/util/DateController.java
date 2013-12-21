/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Hui Zheng - Team 3
 * Based on Source Code from CategoriesModels
 * V1.0
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Year view, month view, day view and week view are supposed to share 
 * the same "Date"
 * Once I switched the date in the month view to another date, and jump back 
 * to day view, day view should access this class (via MainCalendarController)
 * to retrieve the last updated date. 
 * @author Hongbo
 *
 */
public class DateController {
	private GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
	
	public DateController() {
		cal = (GregorianCalendar) cal.clone();
	}
	
	public DateController(int year, int month, int date) {
		cal = (GregorianCalendar) cal.clone();
		cal.set(year, month, date);
	}
	
	public DateController(Calendar cale) {
		cal.set(cale.get(Calendar.YEAR), cale.get(Calendar.MONTH), cale.get(Calendar.DATE));
	}
	
	public DateController(DateController date) {
		cal = (GregorianCalendar) cal.clone();
		cal.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
	}
	
	
	public int getYear() {
		return cal.get(GregorianCalendar.YEAR);
	}
	
	public int getMonth() {
		return cal.get(GregorianCalendar.MONTH);
	}
	
	public int getDayOfMonth() {
		return cal.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public int getDayOfWeek() {
		return cal.get(GregorianCalendar.DAY_OF_WEEK);
	}
	
	/**
	 * use the GregorianCalendar index to retrieve information of calendar
	 * usage: the following statement is 
	 * equivalent to dateController.getYear();
	 * dateController.get(GregorianCalendar.YEAR);
	 * @param index The constant of class GregorianCalendar
	 * @return 
	 */
	public int get(int index) {
		return cal.get(index);
	}
	
	public void set(int index, int value) {
		cal.set(index, value);
	}

	public DateController getPrecursorDate() {
		cal.add(GregorianCalendar.DATE, -1);
		DateController newDate = new DateController(this);
		cal.add(GregorianCalendar.DATE, 1);
		return newDate;
	}
	
	

	public void setToNextDate() {
		cal.add(GregorianCalendar.DATE, 1);
	}
	
	public void setToPreviousDate() {
		cal.add(GregorianCalendar.DATE, -1);
	}
	
	public void setToNextMonth() {
		cal.add(GregorianCalendar.MONTH, 1);
	}
	
	public void setToPreviousMonth() {
		cal.add(GregorianCalendar.MONTH, -1);
	}
	
	public void setToNextYear() {
		cal.add(GregorianCalendar.YEAR, 1);
	}
	
	public void setToPreviousYear() {
		cal.add(GregorianCalendar.YEAR, -1);
	}
	
	public void setToToday() {
		cal = (GregorianCalendar) GregorianCalendar.getInstance();
	}

	public DateController getNextDate() {
		cal.add(GregorianCalendar.DATE, +1);
		DateController newDate = new DateController(this);
		cal.add(GregorianCalendar.DATE, -1);
		return newDate;
	}

	 public DateController getFirstDayOfWeek() {
         int dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);
         cal.add(GregorianCalendar.DATE, - dayOfWeek + 1);
         DateController firstDayOfWeek = new DateController(cal);
         cal.add(GregorianCalendar.DATE, dayOfWeek - 1);
         return firstDayOfWeek;
 }
	
	public void setToFirstDayOfWeek() {
        int dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);
        cal.add(GregorianCalendar.DATE, - dayOfWeek + 1);
	}
	
	public String toString() {
		return getYear() + " " + getMonth() + " " + getDayOfMonth();
	}
	
	public DateController clone() {
		return new DateController(getYear(), getMonth(), getDayOfMonth());
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof DateController)) {
			return false;
		}
		
		if (((DateController)o).getYear() != getYear()) {
			return false;
		}
		
		if (((DateController)o).getMonth() != getMonth()) {
			return false;
		}
		
		if (((DateController)o).getDayOfMonth() != getDayOfMonth()) {
			return false;
		}
		
		return true;
	}
	
	public void setToNextWeek() {
		cal.add(GregorianCalendar.WEEK_OF_MONTH, 1);
	}
		
	public void setToPreviousWeek() {
			cal.add(GregorianCalendar.WEEK_OF_MONTH, -1);
	}
	
	public GregorianCalendar getCalendar() {
		return cal;
	}
}
