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
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import net.miginfocom.swing.MigLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class AddCommitmentPanel.
 */
@SuppressWarnings("serial")
public class AddCommitmentPanel extends JPanel {

  /** The btn submit. */
  JButton    btnSubmit, btnCancel;

  /** The name label. */
  JLabel     nameLabel;
  
  /** The error msg box for name. */
  JErrorMessageLabel nameErrMsg;

  /** The name text field. */
  JTextField nameTextField;

  /** The date label. */
  JLabel     startDateLabel;

  /** The start time text field. */
  JFormattedTextField startDateTextField, startTimeTextField;
  
  /** The start date picker */ 
  DatePickerPanel startDatePicker;
  
  /** The error msg box for date and time. */
  JErrorMessageLabel	startDateTimeErrMsg; 

  /** The location label. */
  JLabel     locationLabel;

  /** The location text field. */
  JTextField locationTextField;

  /** The description label. */
  JLabel     descriptionLabel;

  /** The description text area. */
  JTextArea  descriptionTextArea;

  /** The invitee label. */
  JLabel     inviteeLabel;

  /** The invitee text area. */
  JTextArea  inviteeTextArea;



  /**
   * Instantiates a new adds the commitment panel.
   * 
   * @param miglayout
   *          the miglayout
   */
  public AddCommitmentPanel(MigLayout miglayout) {
    JPanel contentPanel = new JPanel(miglayout);
    nameLabel = new JLabel("Name:");

    nameTextField = new JTextField(10);
    
    nameErrMsg = new JErrorMessageLabel("Name can not be empty! ");

    startDateLabel = new JLabel("Time:");

    try {
		startDateTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		startTimeTextField = new JFormattedTextField(new MaskFormatter("##:##"));		
	} catch (ParseException pe) {
		System.out.println("Date / time formatter is bad: " + pe.getMessage());
	}
    
    startDateTimeErrMsg = new JErrorMessageLabel();

    locationLabel = new JLabel("Where:");
    locationTextField = new JTextField(10);

    descriptionLabel = new JLabel("Description:");

    descriptionTextArea = new JTextArea();
    descriptionTextArea.setPreferredSize(new Dimension(300, 300));

    inviteeLabel = new JLabel("Invitee:");

    inviteeTextArea = new JTextArea();
    inviteeTextArea.setPreferredSize(new Dimension(300, 300));
    
    CommitmentsModel model = null;

    btnSubmit = new JButton("Submit");
    btnCancel = new JButton ("Cancel");
    
    // Set up properties and values
	nameTextField.setInputVerifier(new TextVerifier(nameErrMsg, btnSubmit));

    startDateTextField.setColumns(8);
	startDateTextField.setInputVerifier(new DateVerifier(startDateTimeErrMsg, btnSubmit));
	startDateTextField.setValue(formatInt(MainCalendarController.getInstance().getDateController().getMonth() + 1) + "/" +
			formatInt(MainCalendarController.getInstance().getDateController().getDayOfMonth()) + "/" +
			formatInt(MainCalendarController.getInstance().getDateController().getYear()));
	startDatePicker = new DatePickerPanel(startDateTextField);
	startTimeTextField.setColumns(4);
	startTimeTextField.setInputVerifier(new TimeVerifier(startDateTimeErrMsg, btnSubmit));
	
	startTimeTextField.setValue(getCurrentTime());

    contentPanel.add(nameLabel);
    contentPanel.add(nameTextField);
    contentPanel.add(nameErrMsg, "wrap");
    contentPanel.add(startDateLabel);
    contentPanel.add(startDateTextField);
    contentPanel.add(startTimeTextField);
    contentPanel.add(startDateTimeErrMsg, "wrap, span");
    contentPanel.add(startDatePicker, "cell 1 2, wrap, span");
    // This is not in commitments anymore, still here if added back
    // contentPanel.add(locationLabel);
    // contentPanel.add(locationTextField, "wrap");
    contentPanel.add(descriptionLabel);
    contentPanel.add(descriptionTextArea, "span 4");
    contentPanel.add(inviteeLabel);
    contentPanel.add(inviteeTextArea, "wrap, span 4");
    contentPanel.add(btnSubmit);
    contentPanel.add(btnCancel);
    btnSubmit.addActionListener(AddCommitmentPanelController.getInstance());
    btnCancel.addActionListener(AddCommitmentPanelController.getInstance());
    btnSubmit.addActionListener(new AddCommitmentController(model,this));
    AddCommitmentPanelController.getInstance().setBtnSubmit(btnSubmit);
    AddCommitmentPanelController.getInstance().setBtnCancel(btnCancel);
    this.add(contentPanel);
  }



  /**
   * Gets the txt newname.
   * 
   * @return the txt newname
   */
  public String getTxtNewname() {
    if(this.nameTextField.getText().equals(""))
      return null;
    else
      return this.nameTextField.getText();
  }



  /**
   * Gets the new date.
   * 
   * @param data
   *          the data
   * @return the new date
   */
  public GregorianCalendar getNewDate(String data) {
    String dateString = "";
    if(data.equals("startTime")){
      dateString = (this.startDateTextField.getValue() + " " + this.startTimeTextField.getValue());
      System.out.println("Get start time success! " + dateString);
    }
    else if(data.equals("endTime")){
    	
    	dateString = (this.startDateTextField.getValue() + " " + this.startTimeTextField.getValue());
    	System.out.println("Get end time success! ");
    }
      

    try {
      Date date;
      // Date example ("12/31/13 20:35")
      date = new SimpleDateFormat("mm/dd/yy HH:mm").parse(dateString);
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(date);
      return cal;
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // The function returns Null if the try breaks
//    GregorianCalendar cal = new GregorianCalendar(1992,8,19,23,4);
    return null;
  }



  /**
   * Gets the new location.
   * 
   * @return the new location
   */
  public String getNewLocation() {
    return this.locationTextField.getText();
  }



  /**
   * Gets the new description.
   * 
   * @return the new description
   */
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
		if (nameErrMsg.getContentText().equals("") && startDateTimeErrMsg.getContentText().equals("") ) {
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
		JLabel errMsg; 
		JButton btnSubmit;
		
		public DateVerifier(JComponent errMsg, JButton btnSubmit) {
			this.errMsg = (JLabel) errMsg;
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
}
