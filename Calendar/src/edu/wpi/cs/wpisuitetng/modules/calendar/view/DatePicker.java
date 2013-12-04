package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

public class DatePicker extends JPanel {
	protected DateController dateController = new DateController();
	protected DateController superDateController = null;
	protected DateController dateIte;
	protected Collection<DatePickerLabel> dateList = new ArrayList<DatePickerLabel>();
	protected JLabel headerLabel = null;
	protected JFormattedTextField fieldToChange;
	
	/**
	 * 
	 * @param month the month that will be displayed on
	 * the date picker. An integer between 0 and 11
	 */
	public DatePicker(int year, int month, int date, JFormattedTextField field) {
		this.fieldToChange = field;
		setOpaque(true);
		setLayout(new MigLayout("insets 0 0 0 0"));
		DateController monthStartDate = new DateController(year, month, 1);
		int dayOfWeek = monthStartDate.getDayOfWeek();
		dateIte = monthStartDate.clone();
		dateIte.getCalendar().add(GregorianCalendar.DATE, -dayOfWeek + 1);
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 7; j ++) {
				DatePickerLabel label = new DatePickerLabel(dateIte.clone());
				dateList.add(label);
				label.setText(formatInt(dateIte.getDayOfMonth()));
				label.setOpaque(true);
				if (dateIte.getMonth() != month) {
					label.disable();
				}
				label.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DatePickerLabel label = (DatePickerLabel) e.getSource();
						if (!label.isDisable()) {
							dateController.set(GregorianCalendar.YEAR, label.getDate().get(GregorianCalendar.YEAR));
							dateController.set(GregorianCalendar.MONTH, label.getDate().get(GregorianCalendar.MONTH));
							dateController.set(GregorianCalendar.DATE, label.getDate().get(GregorianCalendar.DATE));
							if (fieldToChange != null) {
								fieldToChange.setValue(formatInt(dateController.getMonth() + 1) + "/" + formatInt(dateController.getDayOfMonth()) + "/" + dateController.getYear());
							}
							updateDatePicker();
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				if (j != 6) {
					add(label, "width :14%:, align center");
				} else {
					add(label, "wrap, width :14%:, align center");
				}
				dateIte.setToNextDate();
			}
		}
		dateController.set(GregorianCalendar.YEAR, year);
		dateController.set(GregorianCalendar.MONTH, month);
		dateController.set(GregorianCalendar.DATE, date);
		updateDatePicker();
	}
	
	private String formatInt (int i) {
		return i < 10? "0" + String.valueOf(i) : String.valueOf(i); 
	}

	protected void updateDatePicker() {
		Iterator<DatePickerLabel> itr = dateList.iterator();
		while (itr.hasNext()) {
			DatePickerLabel label = itr.next();
			if (dateController.equals(label.getDate())) {
				label.setBackground( new Color(236,252,144));
			} else {
				label.setBackground(Color.white);
			}
		}
		superDateController = dateController.clone();
		updateHeaderLabel();
	}
	
	/*
	 * if this date picker is using inside a date picker panel,
	 * and you want to update the text of header label,
	 * you should add the header label to this date picker class
	 * and call this update header label method
	 */
	public void updateHeaderLabel() {
		if (headerLabel != null) {
			headerLabel.setText(dateController.get(GregorianCalendar.YEAR) + " " 
					+ dateController.getCalendar().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()));
		}
	}
	
	public void setHeaderLabel(JLabel label) {
		headerLabel = label;
	}
	
	public void setSuperDateController(DateController date) {
		superDateController = date;
	}
	
}
