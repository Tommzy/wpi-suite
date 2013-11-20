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
public class AddCommitmentPanel extends JPanel{

	JButton btnSubmit;

	JLabel nameLabel;

	JTextField nameTextField;

	JLabel startDateLabel;

	JTextField startDateTextField, startTimeTextField;

	JLabel descriptionLabel;

	JTextArea descriptionTextArea;

	public AddCommitmentPanel(MigLayout miglayout) {
		JPanel contentPanel = new JPanel(miglayout);
		nameLabel = new JLabel("Name:");

		nameTextField = new JTextField(10);

		startDateLabel = new JLabel("Starts:");

		startDateTextField = new JTextField(10);

		startTimeTextField = new JTextField(4);

		descriptionLabel = new JLabel("Description:");

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setPreferredSize(new Dimension(300, 300));

		btnSubmit = new JButton("Submit");

		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);
		contentPanel.add(startDateLabel);
		contentPanel.add(startDateTextField);
		contentPanel.add(startTimeTextField);
		contentPanel.add(descriptionLabel);
		contentPanel.add(descriptionTextArea, "span 4");
		contentPanel.add(btnSubmit);

		this.add(contentPanel);
	}

	public String getTxtNewname(){
		if (this.nameTextField.getText().equals(""))
			return null;
		else
			return this.nameTextField.getText();
	}

	public GregorianCalendar getNewDate(String data) throws ParseException {
		String dateString = "";
		if (data.equals("startTime"))
			dateString = (this.startDateTextField + " " + this.startTimeTextField);
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

	public String getNewDescription(){
		return this.descriptionTextArea.getText();
	}
}
