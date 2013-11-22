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
	private Calendar cal = GregorianCalendar.getInstance();
	
	public DateController() {
		cal = (Calendar) cal.clone();
		
		//System.out.println(cal);
	}
	
	public DateController(int year, int month, int date) {
		cal = (Calendar) cal.clone();
		
		cal.set(year, month, date);
//		System.out.println(cal);
	}
	
	public DateController(DateController date) {
		cal = (Calendar) cal.clone();
		cal.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
		
//		System.out.println(cal);
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
}
