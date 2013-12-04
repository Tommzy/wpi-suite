package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JLabel;

import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

/**
 * The Class DatePickerPanelHeaderLabel.
 */
public class DatePickerPanelHeaderLabel extends JLabel {
	
	/** The date controller. */
	protected DateController dateController;
	
	/**
	 * Instantiates a new date picker panel header label.
	 *
	 * @param date the date
	 */
	public DatePickerPanelHeaderLabel(DateController date) {
		dateController = date;
		setText(date.get(GregorianCalendar.YEAR) + " " 
				+ date.getCalendar().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()) + " " 
				 + date.get(GregorianCalendar.DATE));
		
	}
	
	/**
	 * Gets the date controller.
	 *
	 * @return the date controller
	 */
	public DateController getDateController() {
		return dateController;
	}
}
