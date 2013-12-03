package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JLabel;

import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

public class DatePickerPanelHeaderLabel extends JLabel {
	protected DateController dateController;
	
	public DatePickerPanelHeaderLabel(DateController date) {
		dateController = date;
		setText(date.get(GregorianCalendar.YEAR) + " " 
				+ date.getCalendar().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()) + " " 
				 + date.get(GregorianCalendar.DATE));
		
	}
	
	public DateController getDateController() {
		return dateController;
	}
}
