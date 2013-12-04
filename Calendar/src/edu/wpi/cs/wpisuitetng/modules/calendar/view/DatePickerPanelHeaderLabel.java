package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JLabel;

import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

public class DatePickerPanelHeaderLabel extends JLabel {
	protected DateController dateController;
	
	public DatePickerPanelHeaderLabel(DateController date) {
		dateController = date;
		setPreferredSize(new Dimension(100, 20));
		setHorizontalAlignment(CENTER);
		setOpaque(true);
		setBackground(new Color(138, 173, 209));
		setText(date.get(GregorianCalendar.YEAR) + " " 
				+ date.getCalendar().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()) );
		
	}
	
	public DateController getDateController() {
		return dateController;
	}
}
