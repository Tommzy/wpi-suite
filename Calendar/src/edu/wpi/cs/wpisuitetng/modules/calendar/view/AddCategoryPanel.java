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

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

/**
 * Create a new page for users to add category
 * @author Yuchen Zhang
 *
 */
@SuppressWarnings("serial")
public class AddCategoryPanel extends JPanel{
	/** Submit Update Cancel button */
	JButton btnSubmit, btnUpdate, btnCancel;

	/** Name label */
	JLabel nameLabel;

	/** Name text field */
	JTextField nameTextField;
	
	/** Name Err Msg */
	JErrorMessageLabel nameErrMsg;

	/** Color Picker label */
	JLabel colorLabel;

	/** Color Picker panel */
	JLabel[] colorDisplays;

	/** Color array */
	Color[] color = {Color.WHITE, Color.CYAN, Color.ORANGE, Color.YELLOW, Color.MAGENTA, Color.PINK};

	/** ID Label */
	JLabel IDText;

	/** The type of this category */
	JLabel typeLabel;

	/** Team radio button */
	JRadioButton teamRadioButton;

	/** Personal radio button */
	JRadioButton personalRadioButton;
	
	/** Selected Color */
	private Color colorSelected;

	/**
	 * Constructor
	 */
	public AddCategoryPanel() {
		JPanel contentPanel = new JPanel(new MigLayout());

		nameLabel = new JLabel("Name: ");
		nameTextField = new JTextField(10);
		nameErrMsg = new JErrorMessageLabel();

		colorLabel = new JLabel("Color: ");
		colorDisplays = new JLabel[color.length];
		for (int i = 0; i < color.length; i++) {
			colorDisplays[i] = new JLabel("                         ");
			colorDisplays[i].setOpaque(true);
			colorDisplays[i].setBackground(color[i]);
		}

		IDText = new JLabel();

		btnCancel = new JButton("Cancel");
		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		
		for (int i = 0; i < color.length; i++ ) {
			colorDisplays[i].addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					((JLabel)e.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
					colorSelected = ((JLabel)e.getSource()).getBackground();
					for (int i = 0; i < color.length; i++) {
						if (! ((JLabel)e.getSource()).getBackground().equals(color[i])) {
							colorDisplays[i].setBorder(null);
						}
						colorDisplays[i].revalidate();
						colorDisplays[i].repaint();
					}
				}
			});
		}
		
		typeLabel = new JLabel("Type");
		ButtonGroup radioButtonGroup = new ButtonGroup() ;
		personalRadioButton = new JRadioButton("Personal Category");
		teamRadioButton = new JRadioButton("Team Category");
		teamRadioButton.setSelected(true);
		radioButtonGroup.add(personalRadioButton);
		radioButtonGroup.add(teamRadioButton);
		
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
				btnUpdate.setEnabled(checkContent());
				if (nameTextField.getParent() != null) {
					nameTextField.getParent().revalidate();
					nameTextField.getParent().repaint();
				}
			}
		});

		contentPanel.add(typeLabel);
		contentPanel.add(personalRadioButton, "span 2");
		contentPanel.add(teamRadioButton, "wrap, span 2");
		contentPanel.add(colorLabel);
		for (int i = 0; i < color.length; i++) {
			if (i == color.length - 1) {
				contentPanel.add(colorDisplays[i], "wrap");
			}
			else {
				contentPanel.add(colorDisplays[i]);
			}
		}
		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField, "span 2");
		contentPanel.add(nameErrMsg, "wrap, span");		
		contentPanel.add(btnSubmit, "cell 1 3");
		contentPanel.add(btnUpdate);
		contentPanel.add(btnCancel);

		add(contentPanel);
	}
	
	private boolean checkContent() {
		  if (nameErrMsg.getContentText().equals("") ) {
			  return true;
		  }
		  else 
			  return false;
	  }
}
