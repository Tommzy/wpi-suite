/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.MaskFormatter;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import org.jdesktop.swingx.JXDatePicker;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class AddEventPanel extends JPanel {
	JButton btnSubmit;

	JLabel nameLabel;

	JTextField nameTextField;
	
	JErrorMessageLabel nameErrMsg;

	JLabel startDateLabel, endDateLabel;

	JXDatePicker startDatePicker, endDatePicker;

	JFormattedTextField startTimeTextField, endTimeTextField;
	
	JErrorMessageLabel startDateTimeErrMsg, endDateTimeErrMsg;

	JLabel locatoinLabel;

	JTextField locationTextField;

	JLabel descriptionLabel;

	JTextArea descriptionTextArea;

	JLabel inviteeLabel;

	JTextArea inviteeTextArea;

	JCheckBox allDayEventCheckBox;
	
	public AddEventPanel(MigLayout miglayout, JTabbedPane tabbedPane) {
		// Set up panel
		JPanel contentPanel = new JPanel(miglayout);
		JPanel rightPanel = new JPanel();
		contentPanel.setSize(this.getWidth() / 2, this.getHeight());
		rightPanel.setSize(this.getWidth() / 2, this.getHeight());
		
		// Initiate fields. 
		nameLabel = new JLabel("Name:");

		nameTextField = new JTextField(10);
		
		nameErrMsg = new JErrorMessageLabel("Name can not be empty! ");
		
		startDateLabel = new JLabel("Starts:");

		startDatePicker = new JXDatePicker();
		startDatePicker.setFormats(new SimpleDateFormat("MM/dd/YYYY"));
		startDatePicker.setDate(new GregorianCalendar(MainCalendarController.getInstance().getDateController().getYear(), 
				MainCalendarController.getInstance().getDateController().getMonth(), 
				MainCalendarController.getInstance().getDateController().getDayOfMonth()).getTime());
		
		startDateTimeErrMsg = new JErrorMessageLabel();

		endDateLabel = new JLabel("Ends:");

		endDatePicker = new JXDatePicker();
		endDatePicker.setFormats(new SimpleDateFormat("MM/dd/YYYY"));
		endDatePicker.setDate(new GregorianCalendar(MainCalendarController.getInstance().getDateController().getYear(), 
				MainCalendarController.getInstance().getDateController().getMonth(), 
				MainCalendarController.getInstance().getDateController().getDayOfMonth()).getTime());
		
		endDateTimeErrMsg = new JErrorMessageLabel();
		
		// Initiate time fields. Add input verifiers and listener
		try {
			startTimeTextField = new JFormattedTextField(new MaskFormatter("##:##"));
			endTimeTextField = new JFormattedTextField(new MaskFormatter("##:##"));
			
		} catch (ParseException pe) {
			System.out.println("time formatter is bad: " + pe.getMessage());
		}
	

			
		locatoinLabel = new JLabel("Where:");

		locationTextField = new JTextField(10);

		descriptionLabel = new JLabel("Description:");

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setPreferredSize(new Dimension(400, 100));
		
		inviteeLabel = new JLabel("Invitee:");

		inviteeTextArea = new JTextArea();
		inviteeTextArea.setPreferredSize(new Dimension(400, 100));
		allDayEventCheckBox = new JCheckBox("All Day Event?");

		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		AddEventPanelController.getInstance().setTabbedPane(tabbedPane);
		AddEventPanelController.getInstance().setBtnSubmit(btnSubmit);
		btnSubmit.addActionListener(AddEventPanelController.getInstance());
		
		// Set up properties
		nameTextField.setInputVerifier(new TextVerifier(nameErrMsg, btnSubmit));
		
		startTimeTextField.setColumns(3);
		startTimeTextField.setInputVerifier(new TimeVerifier(startDateTimeErrMsg, btnSubmit));
		endTimeTextField.setColumns(3);
		endTimeTextField.setInputVerifier(new TimeVerifier(endDateTimeErrMsg, btnSubmit));
		
		// add listener first, then set value so that listener can be triggered. 
		startTimeTextField.addPropertyChangeListener("value", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String startTime = startTimeTextField.getText();
				String[] startHourMin = startTime.split(":");
				if (Integer.parseInt(startHourMin[0]) != 23) {
					String endTime = formatInt((Integer.parseInt(startHourMin[0]) + 1)) + ":" + startHourMin[1];
					endTimeTextField.setValue(endTime);
				}
				else {
					endTimeTextField.setValue("23:59");
				}
			}
			
		});
		startTimeTextField.setValue(getCurrentTime());
		
		endTimeTextField.addPropertyChangeListener("value", new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				Date startDate = startDatePicker.getDate();
				Date endDate = endDatePicker.getDate(); 
				if (startDate.equals(endDate)) {
					String startTime = (String) startTimeTextField.getValue();
					String endTime = (String) endTimeTextField.getValue();
					String[] startHourMin = startTime.split(":");
					String[] endHourMin = endTime.split(":");
					if (Integer.parseInt(startHourMin[0]) > Integer.parseInt(endHourMin[0])) {
						endDateTimeErrMsg.setText("End time can not be ahead of start Time!");
						endTimeTextField.requestFocus();
						btnSubmit.setEnabled(checkContent());
					}
					else if (Integer.parseInt(startHourMin[0]) == Integer.parseInt(endHourMin[0])) {
						if (Integer.parseInt(startHourMin[1]) > Integer.parseInt(endHourMin[1])) {
							endDateTimeErrMsg.setText("End time can not be ahead of start Time!");
							endTimeTextField.requestFocus();
							btnSubmit.setEnabled(checkContent());
						}
						else {
							endDateTimeErrMsg.setText("");
							btnSubmit.setEnabled(checkContent());
						}
					}
					else {
						endDateTimeErrMsg.setText("");
						btnSubmit.setEnabled(checkContent());
					}
				}
			}
		});
		
		// Add to panel
		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);
		contentPanel.add(nameErrMsg, "wrap");
		contentPanel.add(startDateLabel);
		contentPanel.add(startDatePicker);
		contentPanel.add(startTimeTextField);
		contentPanel.add(startDateTimeErrMsg, "wrap");
		contentPanel.add(endDateLabel);
		contentPanel.add(endDatePicker);
		contentPanel.add(endTimeTextField);
		contentPanel.add(endDateTimeErrMsg, "wrap");
		contentPanel.add(locatoinLabel);
		contentPanel.add(locationTextField, "wrap");
		contentPanel.add(descriptionLabel);
		contentPanel.add(descriptionTextArea, "wrap, span 4");
		contentPanel.add(inviteeLabel);
		contentPanel.add(inviteeTextArea, "wrap, span 4");
		contentPanel.add(allDayEventCheckBox);
		contentPanel.add(btnSubmit);
		this.add(contentPanel);
		
		this.add(rightPanel);
	}

	public String getTxtNewname() {
		if (this.nameTextField.getText().equals(""))
			return null;
		else
			return this.nameTextField.getText();
	}

//	public GregorianCalendar getNewDate(String data) throws ParseException {
//		String dateString = "";
//		if (data.equals("startTime"))
//			dateString = (this.startDateTextField + " " + this.startTimeTextField);
//		else if (data.equals("endTime"))
//			dateString = (this.startDateTextField + " " + this.startTimeTextField);
//
//		try {
//			GregorianCalendar date;
//			// Date example ("12/31/13 20:35")
//			date = new SimpleDateFormat("mm/dd/yy HH:mm").parse(dateString);
//			return date;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//
//			// throw e.printStackTrace();
//			return null;
//		}
//		// The function returns Null if the try breaks
//	}

	public String getNewLocation() {
		return this.locationTextField.getText();
	}

	public String getNewDescription() {
		return this.descriptionTextArea.getText();
	}
	
	public String getCurrentTime() {
		String hour = formatInt(GregorianCalendar.getInstance().get(GregorianCalendar.HOUR_OF_DAY));
		String minute = formatInt(GregorianCalendar.getInstance().get(GregorianCalendar.MINUTE));
		return hour + ":" + minute;
	}
	
	private String formatInt (int i) {
		return i < 10? "0" + String.valueOf(i) : String.valueOf(i); 
	}
	
	private boolean checkContent() {
		if (nameErrMsg.getContentText().equals("") && startDateTimeErrMsg.getContentText().equals("") && endDateTimeErrMsg.getContentText().equals("")) {
			return true;
		}
		else 
			return false;
	}
	
	private class TextVerifier extends InputVerifier {
		JLabel errMsg; 
		JButton btnSubmit;
		
		public TextVerifier(JComponent errMsg, JButton btnSubmit) {
			this.errMsg = (JLabel) errMsg;
			this.btnSubmit = btnSubmit;
		}
		
		@Override
		public boolean verify(JComponent input) {
			JTextField tf = (JTextField) input;
			if (tf.getText().trim().equals("")) {
				errMsg.setText("Invalid Name! ");
				btnSubmit.setEnabled(checkContent());
			}
			else {
				errMsg.setText("");
				btnSubmit.setEnabled(checkContent());
			}
			return (! tf.getText().trim().equals(""));
		}
	}
	
	private class TimeVerifier extends InputVerifier {
		JLabel errMsg; 
		JButton btnSubmit;
		
		public TimeVerifier(JComponent errMsg, JButton btnSubmit) {
			this.errMsg = (JLabel) errMsg;
			this.btnSubmit = btnSubmit;
		}
		
		@Override
		public boolean verify(JComponent input) {
			JTextField tf = (JTextField) input;
			String content[] = tf.getText().split(":");
			if ((! content[0].trim().equals("")) && (! content[1].trim().equals(""))) {
				if (Integer.parseInt(content[0].trim()) > 23 || Integer.parseInt(content[1].trim()) > 59) {
					errMsg.setText("Invalid Time! ");
					btnSubmit.setEnabled(checkContent());
					return false;
				}
				else {
					content[0] = formatInt(Integer.parseInt(content[0].trim()));
					content[1] = formatInt(Integer.parseInt(content[1].trim()));
					tf.setText(content[0] + ":" + content[1]);
					btnSubmit.setEnabled(checkContent());
					return true;
				}
			}
			else {
				errMsg.setText("Invalid Time! ");
				btnSubmit.setEnabled(checkContent());
				return false;
			}
		}
	}	
}
