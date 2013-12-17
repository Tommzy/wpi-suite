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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.categories.CategoriesModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCategoryController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateCategoryController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCategoryPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CategoryFilter;
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

	/** The type of this commitment */
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
		final CategoriesModel model = CategoriesModel.getInstance();
		Collection<Category> categories = new CategoryFilter().getCategoryList();

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
				if (nameTextField.getText().equals("")) {
					nameErrMsg.setText("Name cannot be empty! ");
				}
				else if (nameTextField.getText().trim().equals("")) {
					nameErrMsg.setText("Name cannot be all spaces! ");
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
	    nameTextField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (nameTextField.getText().equals("")) {
					nameErrMsg.setText("Name cannot be empty! ");
				}
				else if (nameTextField.getText().trim().equals("")) {
					nameErrMsg.setText("Name cannot be all spaces! ");
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
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
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

		if (IDText.getText().equals("")) {
			btnUpdate.setVisible(false);
			btnSubmit.setVisible(true);
		}
		else {
			btnUpdate.setVisible(true);
			btnSubmit.setVisible(false);
		}

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((JButton)e.getSource()).addActionListener(new AddCategoryController(model, packInfo()));
				((JButton)e.getSource()).addActionListener(AddCategoryPanelController.getInstance());
				((JButton)e.getSource()).removeActionListener(this);
				((JButton)e.getSource()).doClick();
				disableAllButton();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((JButton)e.getSource()).addActionListener(new UpdateCategoryController(packInfo()));
				((JButton)e.getSource()).addActionListener(AddCategoryPanelController.getInstance());
				((JButton)e.getSource()).removeActionListener(this);
				((JButton)e.getSource()).doClick();
				disableAllButton();
				
			}
		});
		btnCancel.addActionListener(AddCategoryPanelController.getInstance());

		add(contentPanel);
	}

	/**
	 * @return
	 */
	private Category packInfo() {
		// TODO Auto-generated method stub
		int id;
		if (IDText.getText().equals("")) {
			id = -1;
		}
		else {
			id = Integer.parseInt(IDText.getText()); 
		}
		String name = nameTextField.getText();
		Color color = colorSelected;
		boolean personal = personalRadioButton.isSelected()? true : false;
		Category category = new Category (name, personal, color);
		category.setId(id);
		return category;
	}

	/**
	 * Disable all buttons. Used by controller when closing the tab. 
	 */
	public void disableAllButton() {
		btnSubmit.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnCancel.setEnabled(false);
	}


	private boolean checkContent() {
		if (nameErrMsg.getContentText().equals("") ) {
			return true;
		}
		else 
			return false;
	}
}
