package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.util.ArrayList;

public class MonthName {
	private ArrayList<String> monthName = new ArrayList<String>();
	private ArrayList<String> monthNameAbbr = new ArrayList<String>();
	
	public static final int JANUARY = 0;
	public static final int FEBRUARY = 1;
	public static final int MARCH = 2;
	public static final int APRIL = 3;
	public static final int MAY = 4;
	public static final int JUNE = 5;
	public static final int JULY = 6;
	public static final int AUGUST = 7;
	public static final int SEPTEMBER = 8;
	public static final int OCTOBER = 9;
	public static final int NOVEMBER = 10;
	public static final int DECEMBER = 11;
	
	public MonthName() {
		monthName.add("January");
		monthName.add("February");
		monthName.add("March");
		monthName.add("April");
		monthName.add("May");
		monthName.add("June");
		monthName.add("July");
		monthName.add("August");
		monthName.add("September");
		monthName.add("October");
		monthName.add("November");
		monthName.add("December");
		
		monthNameAbbr.add("Jan");
		monthNameAbbr.add("Feb");
		monthNameAbbr.add("Mar");
		monthNameAbbr.add("Apr");
		monthNameAbbr.add("May");
		monthNameAbbr.add("Jun");
		monthNameAbbr.add("Jul");
		monthNameAbbr.add("Aug");
		monthNameAbbr.add("Sep");
		monthNameAbbr.add("Oct");
		monthNameAbbr.add("Nov");
		monthNameAbbr.add("Dec");
	}
	
	public String getMonthName(int index) {
		return monthName.get(index);
	}
	
	public String getMonthNameAbbr(int index) {
		return monthNameAbbr.get(index);
	}
}
