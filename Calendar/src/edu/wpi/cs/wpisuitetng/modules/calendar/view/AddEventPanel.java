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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddEventController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateEventController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.events.EventsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class AddEventPanel extends JPanel {
	/** The btn submit, update, and cancel. */
	JButton btnSubmit, btnUpdate, btnCancel;
	
	/** The name label. */
	JLabel nameLabel;

	/** The name text field. */
	JTextField nameTextField;
	
	/** The error msg box for name. */
	JErrorMessageLabel nameErrMsg;

	/** The date label. */
	JLabel startDateLabel, endDateLabel;

	/** The date text field */
	JFormattedTextField startDateTextField, endDateTextField;
	
	/** The date picker */ 
	DatePickerPanel startDatePicker, endDatePicker;

	/** The time text field. */
	JFormattedTextField startTimeTextField, endTimeTextField;
	
	/** The help content for date and time */
	JLabel startDateHelpText, startTimeHelpText, endDateHelpText, endTimeHelpText;

	/** The category label */
	JLabel categoryLabel;

	/** The category drop down list */
	//JComboBox<Category> categoryComboBox;

	/** The type of this commitment */
	JLabel typeLabel;

	/** Team radio button */
	JRadioButton teamRadioButton;

	/** Personal radio button */
	JRadioButton personalRadioButton;

	/** The error msg box for date and time. */
	JErrorMessageLabel dateTimeErrMsg;

	/** The location label */
	JLabel locationLabel;

	/** The location text field */
	JTextField locationTextField;

	/** The description label */
	JLabel descriptionLabel;

	/** The description text area */
	JTextArea descriptionTextArea;
	
	/** ScroolPane Container for description */
	JScrollPane descriptionScroll;
	
	/** the invitee label */
	JLabel inviteeLabel;

	/** The invitee text area */
	JTextArea inviteeTextArea;
	
	/** ScroolPane Container for invitee */
	JScrollPane inviteeScroll;

//	JCheckBox allDayEventCheckBox;
	
	/** The IDText label */
	JLabel IDText;
	
	public AddEventPanel(MigLayout miglayout) {
		// Set up panel
		JPanel contentPanel = new JPanel(miglayout);
//		JPanel rightPanel = new JPanel();
		contentPanel.setSize(this.getWidth() / 2, this.getHeight());
//		rightPanel.setSize(this.getWidth() / 2, this.getHeight());
		// Initiate fields. 
		nameLabel = new JLabel("Name:");

		nameTextField = new JTextField(10);
		
		nameErrMsg = new JErrorMessageLabel();
		
		startDateLabel = new JLabel("Starts:");
		
		dateTimeErrMsg = new JErrorMessageLabel();

		endDateLabel = new JLabel("Ends:");

		dateTimeErrMsg = new JErrorMessageLabel();
		
		startDateHelpText = new JLabel ("<HTML><font color='gray'>MM/DD/YYYY</font></HTML>");
	    
	    startTimeHelpText = new JLabel ("<HTML><font color='gray'>24-HR</font></HTML>");
	    
	    endDateHelpText = new JLabel ("<HTML><font color='gray'>MM/DD/YYYY</font></HTML>");
	    
	    endTimeHelpText = new JLabel ("<HTML><font color='gray'>24-HR</font></HTML>");
		
		// Initiate time fields. Add input verifiers and listener
		try {
			startDateTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
			startTimeTextField = new JFormattedTextField(new MaskFormatter("##:##"));
			endDateTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
			endTimeTextField = new JFormattedTextField(new MaskFormatter("##:##"));
			
		} catch (ParseException pe) {
			System.out.println("Date / time formatter is bad: " + pe.getMessage());
		}
	
		typeLabel = new JLabel("Type");
		ButtonGroup radioButtonGroup = new ButtonGroup() ;
		personalRadioButton = new JRadioButton("Personal Commitment");
		teamRadioButton = new JRadioButton("Team Commitment");
		teamRadioButton.setSelected(true);
		radioButtonGroup.add(personalRadioButton);
		radioButtonGroup.add(teamRadioButton);
	    
	    categoryLabel = new JLabel("Category: ");
	    
//	    categoryComboBox = new JComboBox<Category>();
			
		locationLabel = new JLabel("Where:");

		locationTextField = new JTextField(10);

		descriptionLabel = new JLabel("Description:");

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
	    descriptionTextArea.setWrapStyleWord(true);
	    descriptionScroll = new JScrollPane(descriptionTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    descriptionScroll.setPreferredSize(new Dimension(400, 100));
	    
	    
		inviteeLabel = new JLabel("Invitee:");
		
		inviteeTextArea = new JTextArea();
		inviteeTextArea.setLineWrap(true);
	    inviteeTextArea.setWrapStyleWord(true);
	    inviteeScroll = new JScrollPane(inviteeTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    inviteeScroll.setPreferredSize(new Dimension(400, 100));
//		allDayEventCheckBox = new JCheckBox("All Day Event?");
		final EventsModel model = EventsModel.getInstance();
		
		btnSubmit = new JButton("Submit");
		btnUpdate = new JButton("Update");
		btnSubmit.setEnabled(false);
		btnCancel = new JButton("Cancel");
//		AddEventPanelController.getInstance().setTabbedPane(tabbedPane);
//		AddEventPanelController.getInstance().setBtnSubmit(btnSubmit);
//		AddEventPanelController.getInstance().setBtnCancel(btnCancel);
		
	    IDText = new JLabel(); 
		
		// Set up properties
	    nameTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			
			public void warn() {
				if (nameTextField.getText().equals("")) {
					nameErrMsg.setText("Name cannot be empty! ");
				}
				else if (nameTextField.getText().trim().equals("")) {
					nameErrMsg.setText("Name cannot be all spaces! ");
				}
				else {
					nameErrMsg.setText("");
				}
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
				if (nameTextField.getParent() != null) {
					nameTextField.getParent().revalidate();
					nameTextField.getParent().repaint();
				}
			}
		});
	    nameTextField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (nameTextField.getText().equals("")) {
					nameErrMsg.setText("Name cannot be empty! ");
				}
				else if (nameTextField.getText().trim().equals("")) {
					nameErrMsg.setText("Name cannot be all spaces! ");
				}
				else {
					nameErrMsg.setText("");
				}
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
				if (nameTextField.getParent() != null) {
					nameTextField.getParent().revalidate();
					nameTextField.getParent().repaint();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		startDateTextField.setColumns(8);
		startDateTextField.setInputVerifier(new DateVerifier(dateTimeErrMsg, btnSubmit));
		startDatePicker = new DatePickerPanel(startDateTextField);
		startTimeTextField.setColumns(4);
		startTimeTextField.setInputVerifier(new TimeVerifier(dateTimeErrMsg, btnSubmit));
		endDateTextField.setColumns(8);
		endDateTextField.setInputVerifier(new DateVerifier(dateTimeErrMsg, btnSubmit));
		endDatePicker = new DatePickerPanel(endDateTextField);
		endTimeTextField.setColumns(4);
		endTimeTextField.setInputVerifier(new TimeVerifier(dateTimeErrMsg, btnSubmit));
		
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
		
		endDateTextField.addPropertyChangeListener("value", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String content[] = ((String)endDateTextField.getValue()).split("/");
				endDatePicker.setSelectedDate(new DateController(Integer.parseInt(content[2]), Integer.parseInt(content[0]) - 1, Integer.parseInt(content[1])));
				
			}
			
		});
		
		// Set default value of date and time
		startDateTextField.setValue(formatInt(MainCalendarController.getInstance().getDateController().getMonth() + 1) + "/" +
				formatInt(MainCalendarController.getInstance().getDateController().getDayOfMonth()) + "/" +
				formatInt(MainCalendarController.getInstance().getDateController().getYear()));
		startTimeTextField.setValue(getCurrentTime());

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
						dateTimeErrMsg.setText("End date can not be ahead of start date!");
						endDateTextField.requestFocus();
						btnSubmit.setEnabled(checkContent());
						btnUpdate.setEnabled(checkContent());
					}
					else if (start.equals(end)) {
						String startTime = (String) startTimeTextField.getValue();
						String endTime = (String) endTimeTextField.getValue();
						String[] startHourMin = startTime.split(":");
						String[] endHourMin = endTime.split(":");
						if (Integer.parseInt(startHourMin[0]) > Integer.parseInt(endHourMin[0])) {
							dateTimeErrMsg.setText("End time can not be ahead of start time!");
							endTimeTextField.requestFocus();
							btnSubmit.setEnabled(checkContent());
							btnUpdate.setEnabled(checkContent());
						}
						else if (Integer.parseInt(startHourMin[0]) == Integer.parseInt(endHourMin[0])) {
							if (Integer.parseInt(startHourMin[1]) > Integer.parseInt(endHourMin[1])) {
								dateTimeErrMsg.setText("End time can not be ahead of start time!");
								endTimeTextField.requestFocus();
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
							}
							else {
								if (dateTimeErrMsg.getContentText().equals("End time can not be ahead of start time!"))
									dateTimeErrMsg.setText("");
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
							}
						}
						else {
							if (dateTimeErrMsg.getContentText().equals("End time can not be ahead of start time!"))
								dateTimeErrMsg.setText("");
							btnSubmit.setEnabled(checkContent());
							btnUpdate.setEnabled(checkContent());
						}
					}
					else {
						if (dateTimeErrMsg.getContentText().equals("End date can not be ahead of start date!"))
							dateTimeErrMsg.setText("");
						btnSubmit.setEnabled(checkContent());
						btnUpdate.setEnabled(checkContent());
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
				String startDate = (String) startDateTextField.getValue();
				String endDate = (String) endDateTextField.getValue(); 
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				try {
					Date start = df.parse(startDate);
					Date end = df.parse(endDate);
					if (start.after(end)) {
						dateTimeErrMsg.setText("End date can not be ahead of start date!");
						endDateTextField.requestFocus();
						btnSubmit.setEnabled(checkContent());
						btnUpdate.setEnabled(checkContent());
					}
					else if (start.equals(end)) {
						String startTime = (String) startTimeTextField.getValue();
						String endTime = (String) endTimeTextField.getValue();
						String[] startHourMin = startTime.split(":");
						String[] endHourMin = endTime.split(":");
						if (Integer.parseInt(startHourMin[0]) > Integer.parseInt(endHourMin[0])) {
							dateTimeErrMsg.setText("End time can not be ahead of start time!");
							endTimeTextField.requestFocus();
							btnSubmit.setEnabled(checkContent());
							btnUpdate.setEnabled(checkContent());
						}
						else if (Integer.parseInt(startHourMin[0]) == Integer.parseInt(endHourMin[0])) {
							if (Integer.parseInt(startHourMin[1]) > Integer.parseInt(endHourMin[1])) {
								dateTimeErrMsg.setText("End time can not be ahead of start time!");
								endTimeTextField.requestFocus();
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
							}
							else {
								if (dateTimeErrMsg.getContentText().equals("End time can not be ahead of start time!"))
									dateTimeErrMsg.setText("");
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
							}
						}
						else {
							if (dateTimeErrMsg.getContentText().equals("End time can not be ahead of start time!"))
								dateTimeErrMsg.setText("");
							btnSubmit.setEnabled(checkContent());
							btnUpdate.setEnabled(checkContent());
						}
					}
					else {
						if (dateTimeErrMsg.getContentText().equals("End date can not be ahead of start date!"))
							dateTimeErrMsg.setText("");
						btnSubmit.setEnabled(checkContent());
						btnUpdate.setEnabled(checkContent());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});	
		
	    btnCancel.addActionListener(AddEventPanelController.getInstance());
	    btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disableAllButton();
			}
	    	
	    });
	    btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((JButton)e.getSource()).addActionListener(new AddEventController(model , packInfo()));
				((JButton)e.getSource()).addActionListener(AddEventPanelController.getInstance());
				((JButton)e.getSource()).removeActionListener(this);
				((JButton)e.getSource()).doClick();
				disableAllButton();
			}
	    	
	    });
	    btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((JButton)e.getSource()).addActionListener(new UpdateEventController(packInfo()));
				((JButton)e.getSource()).addActionListener(AddEventPanelController.getInstance());
				((JButton)e.getSource()).removeActionListener(this);
				((JButton)e.getSource()).doClick();
				disableAllButton();
			}
	    	
	    });
	    
	    if (IDText.getText().equals("")) {
	    	btnUpdate.setVisible(false);
	    	btnSubmit.setVisible(true);
	    }
	    else {
	    	btnUpdate.setVisible(true);
	    	btnSubmit.setVisible(false);
	    }
		
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
		contentPanel.add(startDateHelpText, "cell 1 3");
		contentPanel.add(startTimeHelpText);
		contentPanel.add(endDateHelpText, "cell 4 3");
		contentPanel.add(endTimeHelpText, "wrap");
		contentPanel.add(startDatePicker, "cell 1 4, span 3");
		contentPanel.add(endDatePicker, "wrap, span");
		contentPanel.add(dateTimeErrMsg, "cell 1 5, wrap, span");
		contentPanel.add(locationLabel);
		contentPanel.add(locationTextField, "wrap, span 2");
	    contentPanel.add(typeLabel);
	    contentPanel.add(personalRadioButton, "span 2");
	    contentPanel.add(teamRadioButton, "wrap");
	    contentPanel.add(categoryLabel, "wrap");
//	    contentPanel.add(categoryComboBox, "wrap");
		contentPanel.add(descriptionLabel);
		contentPanel.add(descriptionScroll, "wrap, span ");
		contentPanel.add(inviteeLabel);
		contentPanel.add(inviteeScroll, "wrap, span ");
//		contentPanel.add(allDayEventCheckBox, "wrap, span");
		contentPanel.add(btnSubmit, "cell 1 11");
		contentPanel.add(btnUpdate);
		contentPanel.add(btnCancel);
		this.add(contentPanel);
		
//		this.add(rightPanel);
	}

	/**
	 * Disable all buttons. Used by controller when closing the tab. 
	 */
	public void disableAllButton() {
		btnSubmit.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnCancel.setEnabled(false);
	}

	public String getTxtNewname() {
		if (this.nameTextField.getText().equals(""))
			return null;
		else
			return this.nameTextField.getText();
	}
	
	private Event packInfo() {
		// ID 
		int id;
		if (IDText.getText().equals("")) {
			id = -1;
		}
		else {
			id = Integer.parseInt(IDText.getText()); 
		}
		// Name
		String name = nameTextField.getText();
		// Start date time
		GregorianCalendar startDateTime = new GregorianCalendar();
		try {
			Date tempDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(startDateTextField.getValue() + " " + startTimeTextField.getValue());
			startDateTime.setTime(tempDate);
		} catch (ParseException e) {
			System.out.println("Cannot parse date! ");
			e.printStackTrace();
		}
		// End date time
		GregorianCalendar endDateTime = new GregorianCalendar();
		try {
			Date tempDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(endDateTextField.getValue() + " " + endTimeTextField.getValue());
			endDateTime.setTime(tempDate);
		} catch (ParseException e) {
			System.out.println("Cannot parse date! ");
			e.printStackTrace();
		}
		// Location
		String location = locationTextField.getText();
		// Description
		String desc = descriptionTextArea.getText();
		// Invitee
		String invitee = inviteeTextArea.getText();
		Event event = new Event(name, startDateTime, endDateTime, location, desc);
		event.setTeamEvent(teamRadioButton.isSelected());
		if (teamRadioButton.isSelected()) {
			System.out.println("team radio button selected");
		}
		if (personalRadioButton.isSelected()) {
			System.out.println("personal radio button selected");
		}
		if (event.isTeamEvent()) {
			System.out.println("team event");
		} else {
			System.out.println("personal event");
		}
		event.setId(id);
		return event;
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
		if (nameErrMsg.getContentText().equals("") && dateTimeErrMsg.getContentText().equals("")) {
			return true;
		}
		else 
			return false;
	}
	
	public void initiateFocus() {
		nameTextField.requestFocusInWindow();
	}
	
	public void populateEvent (Event event) {
		if (event.isTeamEvent()) {
			System.out.println("team");
		} else {
			System.out.println("personal");
		}
		IDText.setText(String.valueOf(event.getId()));
		nameTextField.setText(event.getName());
		descriptionTextArea.setText(event.getDescription());
		locationTextField.setText(event.getLocation());
		// TODO add this back when invitee getter is set up. 
		//		  inviteeTextArea.setText(commitment.getInvitee());
		GregorianCalendar startDateTime = event.getStartTime();
		startDateTextField.setValue(formatInt(startDateTime.get(GregorianCalendar.MONTH) + 1) + "/" + formatInt(startDateTime.get(GregorianCalendar.DAY_OF_MONTH)) + "/" + startDateTime.get(GregorianCalendar.YEAR));
		startTimeTextField.setValue(formatInt(startDateTime.get(GregorianCalendar.HOUR_OF_DAY)) + ":" + formatInt(startDateTime.get(GregorianCalendar.MINUTE)));
		startDatePicker.setSelectedDate(new DateController(startDateTime));
		GregorianCalendar endDateTime = event.getEndTime();
		endDateTextField.setValue(formatInt(endDateTime.get(GregorianCalendar.MONTH) + 1) + "/" + formatInt(endDateTime.get(GregorianCalendar.DAY_OF_MONTH)) + "/" + endDateTime.get(GregorianCalendar.YEAR));
		endTimeTextField.setValue(formatInt(endDateTime.get(GregorianCalendar.HOUR_OF_DAY)) + ":" + formatInt(endDateTime.get(GregorianCalendar.MINUTE)));
		endDatePicker.setSelectedDate(new DateController(endDateTime));
		if (event.isTeamEvent()) {
			teamRadioButton.doClick();
		} else {
			personalRadioButton.doClick();
		}
		if (IDText.getText().equals("")) {
			btnUpdate.setVisible(false);
			btnSubmit.setVisible(true);
		}
		else {
			btnUpdate.setVisible(true);
			btnSubmit.setVisible(false);
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
					btnUpdate.setEnabled(checkContent());
					return false;
				}
				else {
					content[0] = formatInt(Integer.parseInt(content[0].trim()));
					content[1] = formatInt(Integer.parseInt(content[1].trim()));
					tf.setText(content[0] + ":" + content[1]);
					if (errMsg.getContentText().equals("Invalid Time! ")) 
						errMsg.setText("");
					btnSubmit.setEnabled(checkContent());
					btnUpdate.setEnabled(checkContent());
					return true;
				}
			}
			else {
				errMsg.setText("Invalid Time! ");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
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
							btnUpdate.setEnabled(checkContent());
							return false;
						}
						else {
							content[0] = formatInt(Integer.parseInt(content[0].trim()));
							content[1] = formatInt(Integer.parseInt(content[1].trim()));
							tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
							if (errMsg.getContentText().equals("Invalid Date! ")) 
								errMsg.setText("");
							btnSubmit.setEnabled(checkContent());
							btnUpdate.setEnabled(checkContent());
							return true;
						}
					}
					else if (month30day.contains(Integer.parseInt(content[0].trim())) ) {
						if (Integer.parseInt(content[1].trim()) > 30) {
							errMsg.setText("Invalid Date! ");
							btnSubmit.setEnabled(checkContent());
							btnUpdate.setEnabled(checkContent());
							return false;
						}
						else {
							content[0] = formatInt(Integer.parseInt(content[0].trim()));
							content[1] = formatInt(Integer.parseInt(content[1].trim()));
							tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
							if (errMsg.getContentText().equals("Invalid Date! ")) 
								errMsg.setText("");
							btnSubmit.setEnabled(checkContent());
							btnUpdate.setEnabled(checkContent());
							return true;
						}
					}
					else if (Integer.parseInt(content[0].trim()) == 2 ) {
						if (Integer.parseInt(content[2].trim()) % 4 != 0) {
							if (Integer.parseInt(content[1].trim()) > 28) {
								errMsg.setText("Invalid Date! ");
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
								return false;
							}
							else {
								content[0] = formatInt(Integer.parseInt(content[0].trim()));
								content[1] = formatInt(Integer.parseInt(content[1].trim()));
								tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
								if (errMsg.getContentText().equals("Invalid Date! ")) 
									errMsg.setText("");
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
								return true;
							}
						}
						else if (Integer.parseInt(content[2].trim()) % 400 != 0 ) {
							if (Integer.parseInt(content[1].trim()) > 29) {
								errMsg.setText("Invalid Date! ");
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
								return false;
							}
							else {
								content[0] = formatInt(Integer.parseInt(content[0].trim()));
								content[1] = formatInt(Integer.parseInt(content[1].trim()));
								tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
								if (errMsg.getContentText().equals("Invalid Date! ")) 
									errMsg.setText("");
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
								return true;
							}
						}
						else {
							if (Integer.parseInt(content[1].trim()) > 28) {
								errMsg.setText("Invalid Date! ");
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
								return false;
							}
							else {
								content[0] = formatInt(Integer.parseInt(content[0].trim()));
								content[1] = formatInt(Integer.parseInt(content[1].trim()));
								tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
								if (errMsg.getContentText().equals("Invalid Date! ")) 
									errMsg.setText("");
								btnSubmit.setEnabled(checkContent());
								btnUpdate.setEnabled(checkContent());
								return true;
							}
						}
					}
					else {
						errMsg.setText("Invalid Date! ");
						btnSubmit.setEnabled(checkContent());
						btnUpdate.setEnabled(checkContent());
						return false;
					}
				}
				else {
					errMsg.setText("Invalid Date! ");
					btnSubmit.setEnabled(checkContent());
					btnUpdate.setEnabled(checkContent());
					return false;
				}
			}
			else {
				errMsg.setText("Invalid Date! ");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
				return false;
			}
		}
	}
}
