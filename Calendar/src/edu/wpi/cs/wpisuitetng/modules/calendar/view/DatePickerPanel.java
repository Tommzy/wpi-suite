package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

public class DatePickerPanel extends JPanel {
	protected DatePicker datePicker;
	protected DatePickerPanelHeaderLabel headerLabel;
	protected DateController dateController;
	public DatePickerPanel(int year, int month, int date) {
		
		dateController = new DateController(year, month, date);
		updateDatePickerPanel();
	}

	protected void updateDatePickerPanel() {
		removeAll();
		int year = dateController.getYear();
		int month = dateController.getMonth();
		int date = dateController.getDayOfMonth();
		System.out.println(year + " " + month + " " + date);
		datePicker = new DatePicker(year, month, date);
		headerLabel = new DatePickerPanelHeaderLabel(new DateController(year, month, date));
		datePicker.setHeaderLabel(headerLabel);
		datePicker.setSuperDateController(dateController);
		setLayout(new MigLayout("insets 0 0 0 0"));
		add(headerLabel, "wrap");
		JButton backButton = new JButton("<");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dateController.setToPreviousMonth();
				updateDatePickerPanel();
			}
			
		});
		JButton nextButton = new JButton(">");
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dateController.setToNextMonth();
				updateDatePickerPanel();
			}
			
		});
		
		add(backButton, "cell 0 0, height 9%:10%:11%, width 9%:10%:11%");
		add(nextButton, "cell 1 0, wrap, height 9%:10%:11%, width 9%:10%:11%");
		add(datePicker);
		revalidate();
		repaint();
		//System.out.println(dateController);
	}
	
	public DateController getSelectedDate() {
		return dateController;
	}
	
}
