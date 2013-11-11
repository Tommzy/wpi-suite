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

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class AddEventPanel extends JPanel{
	JButton btnSubmit;
	
	JLabel nameLabel;
	
	JTextField nameTextField;
	
	JLabel startDateLabel, endDateLabel;
	
	JTextField startDateTextField, startTimeTextField,
		endDateTextField, endTimeTextField;

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
}
