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
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import net.miginfocom.swing.MigLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class AddCommitmentPanel.
 */
@SuppressWarnings("serial")
public class AddCommitmentPanel extends JPanel {

  /** The btn submit. */
  JButton    btnSubmit;

  /** The name label. */
  JLabel     nameLabel;

  /** The name text field. */
  JTextField nameTextField;

  /** The end date label. */
  JLabel     startDateLabel, endDateLabel;

  /** The start time text field. */
  JTextField startDateTextField, startTimeTextField;

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

    startDateLabel = new JLabel("Time:");

    startDateTextField = new JTextField(10);

    startTimeTextField = new JTextField(4);

    locationLabel = new JLabel("Where:");
    locationTextField = new JTextField(10);

    descriptionLabel = new JLabel("Description:");

    descriptionTextArea = new JTextArea();
    descriptionTextArea.setPreferredSize(new Dimension(300, 300));

    inviteeLabel = new JLabel("Invitee:");

    inviteeTextArea = new JTextArea();
    inviteeTextArea.setPreferredSize(new Dimension(300, 300));

    btnSubmit = new JButton("Submit");

    contentPanel.add(nameLabel);
    contentPanel.add(nameTextField);
    contentPanel.add(startDateLabel);
    contentPanel.add(startDateTextField);
    contentPanel.add(startTimeTextField, "wrap");
    // This is not in commitments anymore, still here if added back
    // contentPanel.add(locatoinLabel);
    // contentPanel.add(locationTextField, "wrap");
    contentPanel.add(descriptionLabel);
    contentPanel.add(descriptionTextArea, "span 4");
    contentPanel.add(inviteeLabel);
    contentPanel.add(inviteeTextArea, "wrap, span 4");
    contentPanel.add(btnSubmit);
    btnSubmit.addActionListener(AddCommitmentPanelController.getInstance());
    AddCommitmentPanelController.getInstance().setBtnSubmit(btnSubmit);
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
    if(data.equals("startTime"))
      dateString = (this.startDateTextField + " " + this.startTimeTextField);
    else if(data.equals("endTime"))
      dateString = (this.startDateTextField + " " + this.startTimeTextField);

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
}
