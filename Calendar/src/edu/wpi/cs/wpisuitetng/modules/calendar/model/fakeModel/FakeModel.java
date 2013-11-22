package edu.wpi.cs.wpisuitetng.modules.calendar.model.fakeModel;

import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItemListModel;

public class FakeModel extends CalendarItemListModel{
	private FakeDate currentDate;
	private Calendar cal = GregorianCalendar.getInstance();
	private static FakeModel modelInstance = new FakeModel();
	
	public FakeModel() {
		currentDate = new FakeDate(cal.get(GregorianCalendar.YEAR), 
				cal.get(GregorianCalendar.MONTH), 
				cal.get(GregorianCalendar.DAY_OF_MONTH));
		
	
	}
	public FakeDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(FakeDate currentDate) {
		this.currentDate = currentDate;
	}
	
	public static FakeModel getInstance() {
		return modelInstance;
	}
}

