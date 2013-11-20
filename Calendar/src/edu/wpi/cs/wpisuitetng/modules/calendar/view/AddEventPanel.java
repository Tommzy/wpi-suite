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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class AddEventPanel extends JPanel {
	JButton btnSubmit;

	JLabel nameLabel;

	JTextField nameTextField;

	JLabel startDateLabel, endDateLabel;

	JTextField startDateTextField, startTimeTextField, endDateTextField,
			endTimeTextField;

	JLabel locatoinLabel;

	JTextField locationTextField;

	JLabel descriptionLabel;

	JTextArea descriptionTextArea;

	JLabel inviteeLabel;

	JTextArea inviteeTextArea;

	JCheckBox allDayEventCheckBox;

	public AddEventPanel(MigLayout miglayout) {
		JPanel contentPanel = new JPanel(miglayout);
		nameLabel = new JLabel("Name:");

		nameTextField = new JTextField(10);

		startDateLabel = new JLabel("Starts:");

		startDateTextField = new JTextField(10);

		startTimeTextField = new JTextField(4);

		endDateLabel = new JLabel("Ends:");

		endDateTextField = new JTextField(10);

		endTimeTextField = new JTextField(4);

		locatoinLabel = new JLabel("Where:");

		locationTextField = new JTextField(10);

		descriptionLabel = new JLabel("Description:");

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setPreferredSize(new Dimension(300, 300));

		inviteeLabel = new JLabel("Invitee:");

		inviteeTextArea = new JTextArea();
		inviteeTextArea.setPreferredSize(new Dimension(300, 300));
		allDayEventCheckBox = new JCheckBox("All Day Event?");

		btnSubmit = new JButton("Submit");

		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);
		contentPanel.add(startDateLabel);
		contentPanel.add(startDateTextField);
		contentPanel.add(startTimeTextField);
		contentPanel.add(endDateLabel);
		contentPanel.add(endDateTextField);
		contentPanel.add(endTimeTextField);
		contentPanel.add(locatoinLabel);
		contentPanel.add(locationTextField, "wrap");
		contentPanel.add(descriptionLabel);
		contentPanel.add(descriptionTextArea, "span 4");
		contentPanel.add(inviteeLabel);
		contentPanel.add(inviteeTextArea, "wrap, span 4");
		contentPanel.add(allDayEventCheckBox);
		contentPanel.add(btnSubmit);

		this.add(contentPanel);
	}

	public String getTxtNewname() {
		if (this.nameTextField.getText().equals(""))
			return null;
		else
			return this.nameTextField.getText();
	}

	public GregorianCalendar getNewDate(String data) throws ParseException {
		String dateString = "";
		if (data.equals("startTime"))
			dateString = (this.startDateTextField + " " + this.startTimeTextField);
		else if (data.equals("endTime"))
			dateString = (this.endDateTextField + " " + this.endTimeTextField);

		try {
			GregorianCalendar date = new GregorianCalendar();
			// Date example ("12/31/13 20:35")
			date.setTime(new SimpleDateFormat("mm/dd/yy HH:mm").parse(dateString));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block

			// throw e.printStackTrace();
			return null;
		}
		// The function returns Null if the try breaks
	}

	public String getNewLocation() {
		return this.locationTextField.getText();
	}

	public String getNewDescription() {
		return this.descriptionTextArea.getText();
	}
}
