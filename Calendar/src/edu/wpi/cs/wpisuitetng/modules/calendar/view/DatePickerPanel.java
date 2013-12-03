package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

public class DatePickerPanel extends JPanel {
	protected DatePicker datePicker;
	protected DatePickerPanelHeaderLabel headerLabel;
	protected DateController dateController;
	JFormattedTextField outputField;
	
	public DatePickerPanel(JFormattedTextField field) {
		this.outputField = field;
		dateController = new DateController();
		updateDatePickerPanel();
	}
	
	public DatePickerPanel(int year, int month, int date, JFormattedTextField field) {
		this.outputField = field;
		dateController = new DateController(year, month, date);
		updateDatePickerPanel();
	}

	protected void updateDatePickerPanel() {
		removeAll();
		int year = dateController.getYear();
		int month = dateController.getMonth();
		int date = dateController.getDayOfMonth();
		datePicker = new DatePicker(year, month, date, outputField);
		datePicker.setPreferredSize(new Dimension (this.getPreferredSize().width, this.getPreferredSize().height));
		headerLabel = new DatePickerPanelHeaderLabel(new DateController(year, month, date));
		datePicker.setHeaderLabel(headerLabel);
		datePicker.setSuperDateController(dateController);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
		setLayout(new MigLayout("insets 1 1 1 1"));
		
		JButton backButton = new JButton(" < ");
		backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dateController.setToPreviousMonth();
				updateDatePickerPanel();
			}
			
		});
		JButton nextButton = new JButton(" > ");
		nextButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dateController.setToNextMonth();
				updateDatePickerPanel();
			}
			
		});
		
		add(backButton);
		add(headerLabel, "cell 0 0");
		add(nextButton, "cell 0 0, wrap");
		add(datePicker, "align center");
		revalidate();
		repaint();
		//System.out.println(dateController);
	}
	
	public DateController getSelectedDate() {
		return dateController;
	}
	
}
