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
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
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

	/** The date label. */
	JLabel     dateLabel;

	/** The start time text field. */
	JFormattedTextField dateTextField;

	/** The help content for date and time */
	JLabel dateHelpText;

	/** The start date picker */ 
	DatePickerPanel datePicker;

	/** The error msg box for date and time. */
	JErrorMessageLabel	dateErrMsg; 

	JLabel IDText;


	/**
	 * Instantiates a new commitment panel.
	 * 
	 * @param miglayout
	 *          the miglayout
	 */
	public AddSchedulerPanel(MigLayout miglayout) {
		JPanel contentPanel = new JPanel(miglayout);
		nameLabel = new JLabel("Name:");

		nameTextField = new JTextField(10);

		nameErrMsg = new JErrorMessageLabel();

		dateLabel = new JLabel("Date:");

		try {
			dateTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));	
		} catch (ParseException pe) {
			System.out.println("Date format is bad: " + pe.getMessage());
		}

		dateErrMsg = new JErrorMessageLabel();

		dateHelpText = new JLabel ("<HTML><font color='gray'>MM/DD/YYYY</font></HTML>");

		//final CommitmentsModel model = CommitmentsModel.getInstance();

		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnCancel = new JButton ("Cancel");


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

		contentPanel.add(btnSubmit, "cell 1 8");
		contentPanel.add(btnCancel);


		btnCancel.addActionListener(AddCommitmentPanelController.getInstance());
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disableAllButton();
			}
		});
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//((JButton)e.getSource()).addActionListener(new AddCommitmentController(model , packInfo()));
				((JButton)e.getSource()).addActionListener(AddCommitmentPanelController.getInstance());
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

	//	private Scheduler packInfo() {
	//		// ID 
	//		int id;
	//		// Name
	//		String name = nameTextField.getText();
	//		// Start date time
	//		GregorianCalendar startDateTime = new GregorianCalendar();
	//		try {
	//			Date tempDate = new SimpleDateFormat("MM/dd/yyyy").parse((String)dateTextField.getValue());
	//			startDateTime.setTime(tempDate);
	//		} catch (ParseException e) {
	//			System.out.println("Cannot parse date! ");
	//			e.printStackTrace();
	//		}
	//		Schedule scheduler = new Schedule(name, dateTime);
	//
	//		return schedule;
	//	}

	private String formatInt (int i) {
		return i < 10? "0" + String.valueOf(i) : String.valueOf(i); 
	}

	/**
	 * Initiate focus on name field. 
	 */
	public void initiateFocus() {
		nameTextField.requestFocusInWindow();
	}


	private boolean checkContent() {
		if (nameErrMsg.getContentText().equals("") && dateErrMsg.getContentText().equals("") ) {
			return true;
		}
		else 
			return false;
	}
}
