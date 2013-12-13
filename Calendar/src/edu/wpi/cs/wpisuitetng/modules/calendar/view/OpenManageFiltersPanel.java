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
import java.util.HashMap;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateCommitmentRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import net.miginfocom.swing.MigLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class AddManageFiltersPanel.
 */
@SuppressWarnings("serial")
public class OpenManageFiltersPanel extends JPanel {

  /** The btn submit. */
  JButton    btnSubmit, btnUpdate, btnCancel;

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
  
  /** The help content for date and time */
  JLabel dateHelpText, timeHelpText;
  
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
  
  /** ScroolPane Container for description */
  JScrollPane descriptionScroll;
  
  /** The description text area. */
  JTextArea  descriptionTextArea;
  
  /** The invitee label. */
  JLabel     inviteeLabel;
  
  /** ScroolPane Container for invitee */
  JScrollPane inviteeScroll;
  
  /** The invitee text area. */
  JTextArea  inviteeTextArea;
  
  /** The Id field */
  JLabel IDText; 



  /**
   * Instantiates the Manage Filters panel.
   * 
   * @param miglayout
   *          the MigLayout
   */
  public OpenManageFiltersPanel(MigLayout miglayout) {
    JPanel contentPanel = new JPanel(miglayout);
    nameLabel = new JLabel("Name:");

    nameTextField = new JTextField(10);
    
    nameErrMsg = new JErrorMessageLabel();

    startDateLabel = new JLabel("Time:");

    try {
		startDateTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		startTimeTextField = new JFormattedTextField(new MaskFormatter("##:##"));		
	} catch (ParseException pe) {
		System.out.println("Date / time formatter is bad: " + pe.getMessage());
	}
    
    startDateTimeErrMsg = new JErrorMessageLabel();
    
    dateHelpText = new JLabel ("<HTML><font color='gray'>MM/DD/YYYY</font></HTML>");
    
    timeHelpText = new JLabel ("<HTML><font color='gray'>24-HR</font></HTML>");

    locationLabel = new JLabel("Where:");
    locationTextField = new JTextField(10);

    descriptionLabel = new JLabel("Description:");
    
    descriptionTextArea = new JTextArea();
    descriptionScroll = new JScrollPane(descriptionTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    descriptionScroll.setPreferredSize(new Dimension(400, 100));
    
    inviteeLabel = new JLabel("Invitee:");

    inviteeTextArea = new JTextArea();
    inviteeScroll = new JScrollPane(inviteeTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    inviteeScroll.setPreferredSize(new Dimension(400, 100));
    final CommitmentsModel model = CommitmentsModel.getInstance();

    btnSubmit = new JButton("Submit");
    btnUpdate = new JButton("Update");
    btnSubmit.setEnabled(false);
    btnCancel = new JButton ("Cancel");
    
    IDText = new JLabel(); 
    
    // Set up properties and values
	nameTextField.setInputVerifier(new TextVerifier(nameErrMsg, btnSubmit));

    startDateTextField.setColumns(8);
	startDateTextField.setInputVerifier(new DateVerifier(startDateTimeErrMsg, btnSubmit));
	startDatePicker = new DatePickerPanel(startDateTextField);
	startTimeTextField.setColumns(4);
	startTimeTextField.setInputVerifier(new TimeVerifier(startDateTimeErrMsg, btnSubmit));
	startDateTextField.addPropertyChangeListener("value", new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String content[] = ((String)startDateTextField.getValue()).split("/");
			startDatePicker.setSelectedDate(new DateController(Integer.parseInt(content[2]), Integer.parseInt(content[0]) - 1, Integer.parseInt(content[1])));
			
		}
		
	});
	startDateTextField.setValue(formatInt(MainCalendarController.getInstance().getDateController().getMonth() + 1) + "/" +
			formatInt(MainCalendarController.getInstance().getDateController().getDayOfMonth()) + "/" +
			formatInt(MainCalendarController.getInstance().getDateController().getYear()));
	startTimeTextField.setValue(getCurrentTime());

    contentPanel.add(nameLabel);
    contentPanel.add(nameTextField, "span 3");
    contentPanel.add(nameErrMsg, "wrap");
    contentPanel.add(startDateLabel);
    contentPanel.add(startDateTextField);
    contentPanel.add(startTimeTextField);
    contentPanel.add(startDateTimeErrMsg, "wrap, span");
    contentPanel.add(dateHelpText, "cell 1 2");
    contentPanel.add(timeHelpText, "cell 2 2, wrap");
    contentPanel.add(startDatePicker, "cell 1 3, wrap, span");
    contentPanel.add(descriptionLabel);
    contentPanel.add(descriptionScroll, "wrap, span 4");
    contentPanel.add(inviteeLabel);
    contentPanel.add(inviteeScroll, "wrap, span 4");
    contentPanel.add(btnSubmit, "cell 1 6");
    contentPanel.add(btnUpdate, "cell 2 6");
    contentPanel.add(btnCancel, "cell 3 6");
    
    // Set up button listenter and properties. 
    if (IDText.getText().equals("")) {
    	btnUpdate.setVisible(false);
    	btnSubmit.setVisible(true);
    }
    else {
    	btnUpdate.setVisible(true);
    	btnSubmit.setVisible(false);
    }
    btnCancel.addActionListener(AddCommitmentPanelController.getInstance());
    btnSubmit.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			((JButton)e.getSource()).addActionListener(new AddCommitmentController(model , packInfo()));
			((JButton)e.getSource()).addActionListener(AddCommitmentPanelController.getInstance());
			((JButton)e.getSource()).removeActionListener(this);
			((JButton)e.getSource()).doClick();
		}
    	
    });
    btnUpdate.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			((JButton)e.getSource()).addActionListener(new UpdateCommitmentController(packInfo()));
			((JButton)e.getSource()).addActionListener(AddCommitmentPanelController.getInstance());
			System.out.println(packInfo().getId());
			((JButton)e.getSource()).removeActionListener(this);
			((JButton)e.getSource()).doClick();
		}
    	
    });
    this.add(contentPanel);
  }



  private Commitment packInfo() {
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
	  // Description
	  String desc = descriptionTextArea.getText();
	  // Invitee
	  String invitee = inviteeTextArea.getText();
	  Commitment commitment = new Commitment(name, startDateTime, desc);
	  commitment.setId(id);
	  return commitment;
  }

  /**
   * Gets the new location.
   * 
   * @return the new location
   */
  public String getNewLocation() {
    return this.locationTextField.getText();
  }

  public void initiateFocus() {
	  nameTextField.requestFocusInWindow();
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
  
  public void populateCommitment (Commitment commitment) {
	  IDText.setText(String.valueOf(commitment.getId()));
	  nameTextField.setText(commitment.getName());
	  descriptionTextArea.setText(commitment.getDescription());
	  // TODO add this back when invitee getter is set up. 
//	  inviteeTextArea.setText(commitment.getInvitee());
	  GregorianCalendar startDateTime = commitment.getStartTime();
	  startDateTextField.setValue(formatInt(startDateTime.get(GregorianCalendar.MONTH) + 1) + "/" + formatInt(startDateTime.get(GregorianCalendar.DAY_OF_MONTH)) + "/" + startDateTime.get(GregorianCalendar.YEAR));
	  startTimeTextField.setValue(formatInt(startDateTime.get(GregorianCalendar.HOUR_OF_DAY)) + ":" + formatInt(startDateTime.get(GregorianCalendar.MINUTE)));
	  startDatePicker.setSelectedDate(new DateController(startDateTime));
	  if (IDText.getText().equals("")) {
		  btnUpdate.setVisible(false);
		  btnSubmit.setVisible(true);
	  }
	  else {
		  btnUpdate.setVisible(true);
		  btnSubmit.setVisible(false);
	  }
	  
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
				btnUpdate.setEnabled(checkContent());
			}
			else if (tf.getText().trim().equals("")) {
				errMsg.setText("Invalid Name! ");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
			}
			else {
				errMsg.setText("");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
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
					btnUpdate.setEnabled(checkContent());
					return false;
				}
				else {
					content[0] = formatInt(Integer.parseInt(content[0].trim()));
					content[1] = formatInt(Integer.parseInt(content[1].trim()));
					tf.setText(content[0] + ":" + content[1]);
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
							btnUpdate.setEnabled(checkContent());
							return false;
						}
						else {
							content[0] = formatInt(Integer.parseInt(content[0].trim()));
							content[1] = formatInt(Integer.parseInt(content[1].trim()));
							tf.setText(content[0] + "/" + content[1] + "/" + content[2]);
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