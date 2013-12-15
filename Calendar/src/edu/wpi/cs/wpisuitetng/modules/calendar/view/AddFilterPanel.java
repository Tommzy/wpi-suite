/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Andrew Aveyard
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
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.ManageFiltersPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import net.miginfocom.swing.MigLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class AddFilterPanel.
 */
@SuppressWarnings("serial")
public class AddFilterPanel extends JPanel {

  /** The btn submit. */
  JButton    btnSubmit, btnUpdate, btnCancel;

  /** The name label. */
  JLabel     nameLabel;
  
  /** The error msg box for name. */
  JErrorMessageLabel nameErrMsg;

  /** The name text field. */
  JTextField nameTextField;
  
  /** The team categories label. */
  JLabel     teamCategoriesLabel;
  
  /** The personal categories label */
  JLabel personCategoriesLabel;
  
  /** ScrollPane Container for team categories */
  JScrollPane teamScroll;
  
  /** ScrollPane Container for personal categories */
  JScrollPane personalScroll;
  
  /** The description text area. */
  JTextArea  teamTextArea;
  
  /** The description text area. */
  JTextArea  personalTextArea;
  
  
  /** The Id field */
  JLabel IDText; 



  /**
   * Instantiates a new filter panel.
   * 
   * @param miglayout
   *          the MigLayout
   */
  public AddFilterPanel(MigLayout miglayout) {
    JPanel contentPanel = new JPanel(miglayout);
    nameLabel = new JLabel("Name:");

    nameTextField = new JTextField(10);
    
    nameErrMsg = new JErrorMessageLabel();
    

    teamCategoriesLabel = new JLabel("Team Categories");
    personCategoriesLabel = new JLabel("Personal Categories");
    
    teamTextArea = new JTextArea();
    personalTextArea = new JTextArea();

    teamScroll = new JScrollPane(teamTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    teamScroll.setPreferredSize(new Dimension(400, 100));
    
    personalScroll = new JScrollPane(personalTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    personalScroll.setPreferredSize(new Dimension(400, 100));
    
    btnSubmit = new JButton("Submit");
    btnUpdate = new JButton("Update");
    btnCancel = new JButton ("Cancel");
    
    JLabel IDText = new JLabel(); 
    
    // Set up properties and values
	nameTextField.setInputVerifier(new TextVerifier(nameErrMsg, btnSubmit));


    contentPanel.add(nameLabel);
    contentPanel.add(nameTextField, "span 3");
    contentPanel.add(nameErrMsg, "wrap");
    contentPanel.add(teamCategoriesLabel, "span 3");
    contentPanel.add(personCategoriesLabel, "wrap, span 4");    
    contentPanel.add(teamScroll, "span 3, width 150");
    contentPanel.add(personalScroll, "wrap, span 4, width 150");
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
    btnSubmit.addActionListener(ManageFiltersPanelController.getInstance());
    btnCancel.addActionListener(ManageFiltersPanelController.getInstance());
    btnUpdate.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			((JButton)e.getSource()).addActionListener(AddCommitmentPanelController.getInstance());
			((JButton)e.getSource()).removeActionListener(this);
			((JButton)e.getSource()).doClick();
		}
    	
    });
    this.add(contentPanel);
  }


  public void initiateFocus() {
	  nameTextField.requestFocusInWindow();
  }

  /**
   * Gets the new description.
   * 
   * @return the new description
   */
//  public String getNewDescription() {
//    return this.descriptionTextArea.getText();
//  }

  private String formatInt (int i) {
	  return i < 10? "0" + String.valueOf(i) : String.valueOf(i); 
  }

  private boolean checkContent() {
	  if (nameErrMsg.getContentText().equals("")) {
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
}
