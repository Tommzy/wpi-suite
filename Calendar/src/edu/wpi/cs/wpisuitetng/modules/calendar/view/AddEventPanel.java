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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class AddEventPanel extends JPanel {
	JButton btnSubmit, btnCancel;

	JLabel nameLabel;

	JTextField nameTextField;
	
	JErrorMessageLabel nameErrMsg;

	JLabel startDateLabel, endDateLabel;

	JFormattedTextField startDateTextField, endDateTextField;
	
	DatePickerPanel startDatePicker, endDatePicker;

	JFormattedTextField startTimeTextField, endTimeTextField;
	
	JErrorMessageLabel startDateTimeErrMsg;

	JLabel locationLabel;

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
		
		startDateTimeErrMsg = new JErrorMessageLabel();

		endDateLabel = new JLabel("Ends:");

		startDateTimeErrMsg = new JErrorMessageLabel();
		
		// Initiate time fields. Add input verifiers and listener
		try {
			startDateTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
			startTimeTextField = new JFormattedTextField(new MaskFormatter("##:##"));
			endDateTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
			endTimeTextField = new JFormattedTextField(new MaskFormatter("##:##"));
			
		} catch (ParseException pe) {
			System.out.println("Date / time formatter is bad: " + pe.getMessage());
		}
	

			
		locationLabel = new JLabel("Where:");

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
		btnCancel = new JButton("Cancel");
		AddEventPanelController.getInstance().setTabbedPane(tabbedPane);
		AddEventPanelController.getInstance().setBtnSubmit(btnSubmit);
		AddEventPanelController.getInstance().setBtnCancel(btnCancel);
		btnSubmit.addActionListener(AddEventPanelController.getInstance());
		btnCancel.addActionListener(AddEventPanelController.getInstance());
		
		// Set up properties
		nameTextField.setInputVerifier(new TextVerifier(nameErrMsg, btnSubmit));
		
		
		
		startDateTextField.setColumns(8);
		startDateTextField.setInputVerifier(new DateVerifier(startDateTimeErrMsg, btnSubmit));
		startDatePicker = new DatePickerPanel(startDateTextField);
		startTimeTextField.setColumns(4);
		startTimeTextField.setInputVerifier(new TimeVerifier(startDateTimeErrMsg, btnSubmit));
		endDateTextField.setColumns(8);
		endDateTextField.setInputVerifier(new DateVerifier(startDateTimeErrMsg, btnSubmit));
		endDatePicker = new DatePickerPanel(endDateTextField);
		endTimeTextField.setColumns(4);
		endTimeTextField.setInputVerifier(new TimeVerifier(startDateTimeErrMsg, btnSubmit));
		
		// add listener first, then set value so that listener can be triggered. 
		startDateTextField.addPropertyChangeListener("value", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String content[] = ((String)startDateTextField.getValue()).split("/");
				startDatePicker.setSelectedDate(new DateController(Integer.parseInt(content[2]), Integer.parseInt(content[0]) - 1, Integer.parseInt(content[1])));
				
			}
			
		});
		
		startDateTextField.addPropertyChangeListener("value", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String startDate = (String) startDateTextField.getValue();
				endDateTextField.setValue(startDate);
			}
			
		});
		startDateTextField.setValue(formatInt(MainCalendarController.getInstance().getDateController().getMonth() + 1) + "/" +
				formatInt(MainCalendarController.getInstance().getDateController().getDayOfMonth()) + "/" +
				formatInt(MainCalendarController.getInstance().getDateController().getYear()));
		
		startTimeTextField.addPropertyChangeListener("value", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String startTime = (String) startTimeTextField.getValue();
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
		
		endDateTextField.addPropertyChangeListener("value", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String content[] = ((String)endDateTextField.getValue()).split("/");
				endDatePicker.setSelectedDate(new DateController(Integer.parseInt(content[2]), Integer.parseInt(content[0]) - 1, Integer.parseInt(content[1])));
				
			}
			
		});
		
		endDateTextField.addPropertyChangeListener("value", new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String startDate = (String) startDateTextField.getValue();
				String endDate = (String) endDateTextField.getValue(); 
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				try {
					Date start = df.parse(startDate);
					Date end = df.parse(endDate);
					if (start.after(end)) {
						startDateTimeErrMsg.setText("End date can not be ahead of start date!");
						endDateTextField.requestFocus();
						btnSubmit.setEnabled(checkContent());
					}
					else if (start.equals(end)) {
						String startTime = (String) startTimeTextField.getValue();
						String endTime = (String) endTimeTextField.getValue();
						String[] startHourMin = startTime.split(":");
						String[] endHourMin = endTime.split(":");
						if (Integer.parseInt(startHourMin[0]) > Integer.parseInt(endHourMin[0])) {
							startDateTimeErrMsg.setText("End time can not be ahead of start time!");
							endTimeTextField.requestFocus();
							btnSubmit.setEnabled(checkContent());
						}
						else if (Integer.parseInt(startHourMin[0]) == Integer.parseInt(endHourMin[0])) {
							if (Integer.parseInt(startHourMin[1]) > Integer.parseInt(endHourMin[1])) {
								startDateTimeErrMsg.setText("End time can not be ahead of start time!");
								endTimeTextField.requestFocus();
								btnSubmit.setEnabled(checkContent());
							}
							else {
								if (startDateTimeErrMsg.getContentText().equals("End time can not be ahead of start time!"))
									startDateTimeErrMsg.setText("");
								btnSubmit.setEnabled(checkContent());
							}
						}
						else {
							if (startDateTimeErrMsg.getContentText().equals("End time can not be ahead of start time!"))
								startDateTimeErrMsg.setText("");
							btnSubmit.setEnabled(checkContent());
						}
					}
					else {
						if (startDateTimeErrMsg.getContentText().equals("End date can not be ahead of start date!"))
							startDateTimeErrMsg.setText("");
						btnSubmit.setEnabled(checkContent());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		endTimeTextField.addPropertyChangeListener("value", new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				System.out.println("wtf");
				String startDate = (String) startDateTextField.getValue();
				String endDate = (String) endDateTextField.getValue(); 
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				try {
					Date start = df.parse(startDate);
					Date end = df.parse(endDate);
					if (start.after(end)) {
						startDateTimeErrMsg.setText("End date can not be ahead of start date!");
						endDateTextField.requestFocus();
						btnSubmit.setEnabled(checkContent());
					}
					else if (start.equals(end)) {
						String startTime = (String) startTimeTextField.getValue();
						String endTime = (String) endTimeTextField.getValue();
						String[] startHourMin = startTime.split(":");
						String[] endHourMin = endTime.split(":");
						if (Integer.parseInt(startHourMin[0]) > Integer.parseInt(endHourMin[0])) {
							startDateTimeErrMsg.setText("End time can not be ahead of start time!");
							endTimeTextField.requestFocus();
							btnSubmit.setEnabled(checkContent());
						}
						else if (Integer.parseInt(startHourMin[0]) == Integer.parseInt(endHourMin[0])) {
							if (Integer.parseInt(startHourMin[1]) > Integer.parseInt(endHourMin[1])) {
								startDateTimeErrMsg.setText("End time can not be ahead of start time!");
								endTimeTextField.requestFocus();
								btnSubmit.setEnabled(checkContent());
							}
							else {
								if (startDateTimeErrMsg.getContentText().equals("End time can not be ahead of start time!"))
									startDateTimeErrMsg.setText("");
								btnSubmit.setEnabled(checkContent());
							}
						}
						else {
							if (startDateTimeErrMsg.getContentText().equals("End time can not be ahead of start time!"))
								startDateTimeErrMsg.setText("");
							btnSubmit.setEnabled(checkContent());
						}
					}
					else {
						if (startDateTimeErrMsg.getContentText().equals("End date can not be ahead of start date!"))
							startDateTimeErrMsg.setText("");
						btnSubmit.setEnabled(checkContent());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});	
		
		// Add to panel
		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField, "span 2");
		contentPanel.add(nameErrMsg, "wrap, span");
		contentPanel.add(startDateLabel);
		contentPanel.add(startDateTextField);
		contentPanel.add(startTimeTextField);
		contentPanel.add(endDateLabel, "gap 5%");
		contentPanel.add(endDateTextField);
		contentPanel.add(endTimeTextField);
		contentPanel.add(startDatePicker, "cell 1 3, span 3");
		contentPanel.add(endDatePicker, "wrap, span");
		contentPanel.add(startDateTimeErrMsg, "cell 1 4, wrap, span");
		contentPanel.add(locationLabel);
		contentPanel.add(locationTextField, "wrap, span 2");
		contentPanel.add(descriptionLabel);
		contentPanel.add(descriptionTextArea, "wrap, span ");
		contentPanel.add(inviteeLabel);
		contentPanel.add(inviteeTextArea, "wrap, span ");
		contentPanel.add(allDayEventCheckBox, "wrap, span");
		contentPanel.add(btnSubmit);
		contentPanel.add(btnCancel);
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
		if (nameErrMsg.getContentText().equals("") && startDateTimeErrMsg.getContentText().equals("") && startDateTimeErrMsg.getContentText().equals("")) {
			return true;
		}
		else 
			return false;
	}
	
	private class TextVerifier extends InputVerifier {
		JErrorMessageLabel errMsg; 
		JButton btnSubmit;
		
		public TextVerifier(JComponent errMsg, JButton btnSubmit) {
			this.errMsg = (JErrorMessageLabel) errMsg;
			this.btnSubmit = btnSubmit;
		}
		
		@Override
		public boolean verify(JComponent input) {
			JTextField tf = (JTextField) input;
			if (tf.getText().equals("")) {
				errMsg.setText("Name can not be empty! ");
				btnSubmit.setEnabled(checkContent());
			}
			else if (tf.getText().trim().equals("")) {
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
		JErrorMessageLabel errMsg; 
		JButton btnSubmit;
		
		public TimeVerifier(JComponent errMsg, JButton btnSubmit) {
			this.errMsg = (JErrorMessageLabel) errMsg;
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
					if (errMsg.getContentText().equals("Invalid Time! ")) 
						errMsg.setText("");
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
	
	private class DateVerifier extends InputVerifier {
		JErrorMessageLabel errMsg; 
		JButton btnSubmit;
		
		public DateVerifier(JComponent errMsg, JButton btnSubmit) {
			this.errMsg = (JErrorMessageLabel) errMsg;
			this.btnSubmit = btnSubmit;
		}

		@Override
		public boolean verify(JComponent input) {
			JTextField tf = (JTextField) input;
			ArrayList<Integer> month31day = new ArrayList<Integer>();
			month31day.add(1);
			month31day.add(3);
			month31day.add(5);
			month31day.add(7);
			month31day.add(8);
			month31day.add(10);
			month31day.add(12);
			final ArrayList<Integer> month30day = new ArrayList<Integer>();
			month30day.add(4);
			month30day.add(6);
			month30day.add(9);
			month30day.add(11);
			
			String[] content = tf.getText().split("/");
			if ((! content[0].trim().equals("")) && (! content[1].trim().equals("")) && (! content[2].trim().equals(""))) {
				if (! content[2].trim().contains(" ")) {
					if (month31day.contains(Integer.parseInt(content[0].trim())) ) {
						if (Integer.parseInt(content[1].trim()) > 31) {
							errMsg.setText("Invalid Date! ");
							btnSubmit.setEnabled(checkContent());
							return false;
						}
						else {
							content[0] = formatInt(Integer.parseInt(content[0].trim()));
							content[1] = formatInt(Integer.parseInt(content[1].trim()));
							tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
							if (errMsg.getContentText().equals("Invalid Date! ")) 
								errMsg.setText("");
							btnSubmit.setEnabled(checkContent());
							return true;
						}
					}
					else if (month30day.contains(Integer.parseInt(content[0].trim())) ) {
						if (Integer.parseInt(content[1].trim()) > 30) {
							errMsg.setText("Invalid Date! ");
							btnSubmit.setEnabled(checkContent());
							return false;
						}
						else {
							content[0] = formatInt(Integer.parseInt(content[0].trim()));
							content[1] = formatInt(Integer.parseInt(content[1].trim()));
							tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
							if (errMsg.getContentText().equals("Invalid Date! ")) 
								errMsg.setText("");
							btnSubmit.setEnabled(checkContent());
							return true;
						}
					}
					else if (Integer.parseInt(content[0].trim()) == 2 ) {
						if (Integer.parseInt(content[2].trim()) % 4 != 0) {
							if (Integer.parseInt(content[1].trim()) > 28) {
								errMsg.setText("Invalid Date! ");
								btnSubmit.setEnabled(checkContent());
								return false;
							}
							else {
								content[0] = formatInt(Integer.parseInt(content[0].trim()));
								content[1] = formatInt(Integer.parseInt(content[1].trim()));
								tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
								if (errMsg.getContentText().equals("Invalid Date! ")) 
									errMsg.setText("");
								btnSubmit.setEnabled(checkContent());
								return true;
							}
						}
						else if (Integer.parseInt(content[2].trim()) % 400 != 0 ) {
							if (Integer.parseInt(content[1].trim()) > 29) {
								errMsg.setText("Invalid Date! ");
								btnSubmit.setEnabled(checkContent());
								return false;
							}
							else {
								content[0] = formatInt(Integer.parseInt(content[0].trim()));
								content[1] = formatInt(Integer.parseInt(content[1].trim()));
								tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
								if (errMsg.getContentText().equals("Invalid Date! ")) 
									errMsg.setText("");
								btnSubmit.setEnabled(checkContent());
								return true;
							}
						}
						else {
							if (Integer.parseInt(content[1].trim()) > 28) {
								errMsg.setText("Invalid Date! ");
								btnSubmit.setEnabled(checkContent());
								return false;
							}
							else {
								content[0] = formatInt(Integer.parseInt(content[0].trim()));
								content[1] = formatInt(Integer.parseInt(content[1].trim()));
								tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
								if (errMsg.getContentText().equals("Invalid Date! ")) 
									errMsg.setText("");
								btnSubmit.setEnabled(checkContent());
								return true;
							}
						}
					}
					else {
						errMsg.setText("Invalid Date! ");
						btnSubmit.setEnabled(checkContent());
						return false;
					}
				}
				else {
					errMsg.setText("Invalid Date! ");
					btnSubmit.setEnabled(checkContent());
					return false;
				}
			}
			else {
				errMsg.setText("Invalid Date! ");
				btnSubmit.setEnabled(checkContent());
				return false;
			}
		}
	}

	public void initiateFocus() {
		// TODO Auto-generated method stub
		nameTextField.requestFocusInWindow();
	}
}
