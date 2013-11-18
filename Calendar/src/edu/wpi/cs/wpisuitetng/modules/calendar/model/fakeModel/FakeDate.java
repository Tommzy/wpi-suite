package edu.wpi.cs.wpisuitetng.modules.calendar.model.fakeModel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FakeDate {
	private int year, month, dayOfMonth;
	private Calendar cal = GregorianCalendar.getInstance();
	
	/**
	 * 
	 * @param year
	 * @param month an integer between 0 and 11
	 * @param dayOfMonth
	 */
	public FakeDate(int year, int month, int dayOfMonth) {
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
		cal.set(year, month, dayOfMonth);
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}
	
	public FakeDate getNextDate() {
		cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
		FakeDate fakeDate = new FakeDate(cal.get(GregorianCalendar.YEAR), cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DAY_OF_MONTH));
		cal.add(GregorianCalendar.DAY_OF_MONTH, -1);
		return fakeDate;
	}
	
	public FakeDate getPrecursorDate() {
		cal.add(GregorianCalendar.DAY_OF_MONTH, -1);
		FakeDate fakeDate = new FakeDate(cal.get(GregorianCalendar.YEAR), cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DAY_OF_MONTH));
		cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
		return fakeDate;
	}
	
}
