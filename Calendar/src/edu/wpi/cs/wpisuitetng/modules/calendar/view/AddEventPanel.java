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
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
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

	JLabel startDateLabel, endDateLabel;

	JXDatePicker startDatePicker;

	JTextField startTimeTextField, endDateTextField,
			endTimeTextField;

	JLabel locatoinLabel;

	JTextField locationTextField;

	JLabel descriptionLabel;

	JTextArea descriptionTextArea;

	JLabel inviteeLabel;

	JTextArea inviteeTextArea;

	JCheckBox allDayEventCheckBox;
	
	public AddEventPanel(MigLayout miglayout, JTabbedPane tabbedPane) {
		JPanel contentPanel = new JPanel(miglayout);
		JPanel rightPanel = new JPanel();
		contentPanel.setSize(this.getWidth() / 2, this.getHeight());
		rightPanel.setSize(this.getWidth() / 2, this.getHeight());
		nameLabel = new JLabel("Name:");

		nameTextField = new JTextField(10);

		startDateLabel = new JLabel("Starts:");

		startDatePicker = new JXDatePicker();
		startDatePicker.setDate(new GregorianCalendar(MainCalendarController.getInstance().getDateController().getYear(), 
				MainCalendarController.getInstance().getDateController().getMonth(), 
				MainCalendarController.getInstance().getDateController().getDayOfMonth()).getTime());
		startDatePicker.setInputVerifier(null);

		startTimeTextField = new JTextField(4);

		endDateLabel = new JLabel("Ends:");

		endDateTextField = new JTextField(10);

		endTimeTextField = new JTextField(4);
	

			
		locatoinLabel = new JLabel("Where:");

		locationTextField = new JTextField(10);

		descriptionLabel = new JLabel("Description:");

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setPreferredSize(new Dimension(300, 200));
		
		inviteeLabel = new JLabel("Invitee:");

		inviteeTextArea = new JTextArea();
		inviteeTextArea.setPreferredSize(new Dimension(300, 200));
		allDayEventCheckBox = new JCheckBox("All Day Event?");

		btnSubmit = new JButton("Submit");
		AddEventPanelController.getInstance().setTabbedPane(tabbedPane);
		AddEventPanelController.getInstance().setBtnSubmit(btnSubmit);
		btnSubmit.addActionListener(AddEventPanelController.getInstance());
	
		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField, "wrap");
		contentPanel.add(startDateLabel);
		contentPanel.add(startDatePicker);
		contentPanel.add(startTimeTextField, "wrap");
		contentPanel.add(endDateLabel);
		contentPanel.add(endDateTextField);
		contentPanel.add(endTimeTextField, "wrap");
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
}
