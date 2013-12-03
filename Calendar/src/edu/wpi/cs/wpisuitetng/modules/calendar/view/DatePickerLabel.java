package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.JLabel;

import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

public class DatePickerLabel extends JLabel {
	private DateController dateController;
	private boolean isDisable = false;
	
	public DatePickerLabel(DateController date) {
		dateController = date;
	}
	
	public DateController getDate() {
		return dateController;
	}
	
	public void disable() {
		super.disable();
		isDisable = true;
	}
	
	public boolean isDisable() {
		return isDisable;
	}
}
