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


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddInvitationController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddSchedulerPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.invitation.InvitationModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

// TODO: Auto-generated Javadoc
/**
 * The Class AddCommitmentPanel.
 */
@SuppressWarnings("serial")
public class AddSchedulerPanel extends JPanel {

	/** The btn submit. */
	JButton    btnSubmit, btnCancel;

	/** The name label. */
	JLabel     nameLabel;

	/** The error msg box for name. */
	JErrorMessageLabel nameErrMsg;

	/** The name text field. */
	JTextField nameTextField;

	/** The description label. */
	JLabel descriptionLabel;

	/** The description text area. */
	JTextArea descriptionTextArea;

	/** ScrollPane Container for description */
	JScrollPane descriptionScroll;

	/** The date label. */
	JLabel     dateLabel;

	/** The start time text field. */
	JFormattedTextField dateTextField;

	/** The help content for date and time. */
	JLabel dateHelpText;

	/** The start date picker. */ 
	DatePickerPanel datePicker;

	/** The error msg box for date and time. */
	JErrorMessageLabel	dateErrMsg; 

	/** The ID text. */
	JLabel IDText;

	/** The model. */
	final InvitationModel model = InvitationModel.getInstance();



	/**
	 * Instantiates a new commitment panel.
	 * 
	 * @param miglayout
	 *          the miglayout
	 */
	public AddSchedulerPanel(MigLayout miglayout) {
		JPanel contentPanel = new JPanel(miglayout);

		// Name
		nameLabel = new JLabel("Name:");
		nameTextField = new JTextField(10);

		nameErrMsg = new JErrorMessageLabel();

		// Desc
		descriptionLabel = new JLabel("Description:");
		descriptionTextArea = new JTextArea();

		// Wrap on word (not char)
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);

		descriptionScroll = new JScrollPane(descriptionTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		descriptionScroll.setPreferredSize(new Dimension(200, 100));

		// Date
		dateLabel = new JLabel("Date:");

		try {
			dateTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));	
		} catch (ParseException pe) {
			System.out.println("Date format is bad: " + pe.getMessage());
		}

		dateErrMsg = new JErrorMessageLabel();

		dateHelpText = new JLabel ("<HTML><font color='gray'>MM/DD/YYYY</font></HTML>");

		// Buttons
		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnCancel = new JButton ("Cancel");


		IDText = new JLabel();


		// Set up properties and values
		//nameTextField.setInputVerifier(new TextVerifier(nameErrMsg, btnSubmit));

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
				if (nameTextField.getText().trim().equals("")) {
					nameErrMsg.setText("Name cannot be empty or all spaces! ");
				}
				else {
					nameErrMsg.setText("");
				}
				btnSubmit.setEnabled(checkContent());
				if (nameTextField.getParent() != null) {
					nameTextField.getParent().revalidate();
					nameTextField.getParent().repaint();
				}
			}
		});

		dateTextField.setColumns(8);
		//dateTextField.setInputVerifier(new DateVerifier(dateErrMsg, btnSubmit));
		datePicker = new DatePickerPanel(dateTextField);
		dateTextField.addPropertyChangeListener("value", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String content[] = ((String)dateTextField.getValue()).split("/");
				datePicker.setSelectedDate(new DateController(Integer.parseInt(content[2]), Integer.parseInt(content[0]) - 1, Integer.parseInt(content[1])));

			}

		});
		dateTextField.setValue(formatInt(MainCalendarController.getInstance().getDateController().getMonth() + 1) + "/" +
				formatInt(MainCalendarController.getInstance().getDateController().getDayOfMonth()) + "/" +
				formatInt(MainCalendarController.getInstance().getDateController().getYear()));

		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);
		contentPanel.add(nameErrMsg, "cell 3 0, wrap");
		contentPanel.add(dateLabel);
		contentPanel.add(dateTextField);
		contentPanel.add(dateHelpText, "cell 1 2");
		contentPanel.add(datePicker, "cell 1 3, wrap, span");
		contentPanel.add(descriptionLabel);
		contentPanel.add(descriptionScroll, "wrap");
		contentPanel.add(btnSubmit, "cell 1 8");
		contentPanel.add(btnCancel);

		// Cancel
		btnCancel.addActionListener(AddSchedulerPanelController.getInstance());
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disableAllButton();
			}
		});

		// Submit
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((JButton)e.getSource()).addActionListener(new AddInvitationController(model , packInfo()));
				((JButton)e.getSource()).addActionListener(AddSchedulerPanelController.getInstance());
				((JButton)e.getSource()).removeActionListener(this);
				((JButton)e.getSource()).doClick();
				disableAllButton();
			}

		});

		this.add(contentPanel);
	}

	/**
	 * Disable all buttons. Used by controller when closing the tab. 
	 */
	public void disableAllButton() {
		btnSubmit.setEnabled(false);
		btnCancel.setEnabled(false);
	}

	/**
	 * Format int.
	 *
	 * @param i the i
	 * @return the string
	 */
	private String formatInt (int i) {
		return i < 10? "0" + String.valueOf(i) : String.valueOf(i); 
	}

	/**
	 * Initiate focus on name field. 
	 */
	public void initiateFocus() {
		nameTextField.requestFocusInWindow();
	}


	/**
	 * Check content.
	 *
	 * @return true, if successful
	 */
	private boolean checkContent() {
		if (nameErrMsg.getContentText().equals("") && dateErrMsg.getContentText().equals("") ) {
			return true;
		}
		else 
			return false;
	}


	/**
	 * Pack info.
	 *
	 * @return the invitation
	 */
	private Invitation packInfo() {
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
			Date tempDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateTextField.getValue().toString());
			startDateTime.setTime(tempDate);
		} catch (ParseException e) {
			System.out.println("Cannot parse date! ");
			e.printStackTrace();
		}

		// Description
		String desc = descriptionTextArea.getText();
		// Pack into a commitment
		Invitation invite = new Invitation(name, startDateTime.getTime().toString(), desc);


		invite.setId(id);

		return invite;
	}
}
