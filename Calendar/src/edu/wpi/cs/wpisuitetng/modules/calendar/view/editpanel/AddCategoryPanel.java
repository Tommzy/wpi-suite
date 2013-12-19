/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCategoryController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCategoryPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller.UpdateCategoryController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.CategoriesModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CategoryFilter;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.JErrorMessageLabel;
import net.miginfocom.swing.MigLayout;

/**
 * Create a new page for users to add category
 * @author Yuchen Zhang
 *
 */
@SuppressWarnings("serial")
public class AddCategoryPanel extends JPanel{
	/** Submit Update Cancel button */
	JButton btnSubmit, btnUpdate, btnCancel, btnDelete;

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
	Color[] color = {Color.WHITE, Color.ORANGE, Color.YELLOW, Color.PINK, 
			new Color(227, 92, 92), new Color(227, 92, 213), new Color(184, 92, 227), new Color(98, 92, 227),
			new Color(92, 184, 227), new Color(92, 227, 165), new Color(98, 227, 92), new Color(225, 227, 92), 
			new Color(227, 155, 92), new Color(227, 130, 92)};
	
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
	
	/** category array */
	Category[] categories;
	
	/** HashMap for color and id */
	HashMap<Integer, Integer> colorId;

	/**
	 * Constructor
	 */
	public AddCategoryPanel() {
		JPanel contentPanel = new JPanel(new MigLayout());
		final CategoriesModel model = CategoriesModel.getInstance();
		categories = new CategoryFilter().getCategoryArray();
		System.out.println(categories.length);
		colorId = new HashMap<Integer, Integer>();

		nameLabel = new JLabel("Name: ");
		nameTextField = new JTextField(10);
		nameErrMsg = new JErrorMessageLabel();

		colorLabel = new JLabel("Color: ");
		colorDisplays = new JLabel[color.length];
		for (int i = 0; i < color.length; i++) {
			colorDisplays[i] = new JLabel("");
			colorDisplays[i].setOpaque(true);
			colorDisplays[i].setBackground(color[i]);
			colorDisplays[i].setHorizontalAlignment(JLabel.CENTER);
			colorDisplays[i].setPreferredSize(new Dimension(120, 20));
		}

		IDText = new JLabel();

		btnCancel = new JButton("Cancel");
		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);

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
					if (! ((JLabel)e.getSource()).getText().trim().equals("")) {
						nameTextField.setText(((JLabel)e.getSource()).getText());
						btnUpdate.setVisible(true);
						btnDelete.setVisible(true);
						btnSubmit.setVisible(false);
						btnDelete.setEnabled(true);
						IDText.setText(String.valueOf(colorId.get((int)((JLabel)e.getSource()).getBackground().getRGB())));
					}
					else {
						nameTextField.setText(((JLabel)e.getSource()).getText());
						nameErrMsg.setText("");
						btnUpdate.setVisible(false);
						btnDelete.setVisible(false);
						btnDelete.setEnabled(false);
						btnSubmit.setVisible(true);
					}
					if (btnUpdate.getParent() != null ) {
						btnUpdate.getParent().revalidate();
						btnUpdate.getParent().repaint();
					}
				}
			});
		}

		typeLabel = new JLabel("Type");
		ButtonGroup radioButtonGroup = new ButtonGroup() ;
		personalRadioButton = new JRadioButton("Personal Category");
		teamRadioButton = new JRadioButton("Team Category");
		radioButtonGroup.add(personalRadioButton);
		radioButtonGroup.add(teamRadioButton);
		
		personalRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int j = 0; j < colorDisplays.length; j++) {
					for (int i = 0; i < categories.length; i++) {
						if (categories[i].isPersonal()) {
							if (colorDisplays[j].getBackground().getRGB() == categories[i].getColor().getRGB()) {
								colorDisplays[j].setText(categories[i].getName());
								colorId.put(categories[i].getColor().getRGB(), categories[i].getId());
								colorDisplays[j].revalidate();
								colorDisplays[j].repaint();
								break;
							}
							colorDisplays[j].setText("");
							colorDisplays[j].revalidate();
							colorDisplays[j].repaint();
						}
					}
				}
				for (int i = 0; i < colorDisplays.length; i++) {
					colorDisplays[i].setBorder(null);
				}
				nameTextField.setText("");
				nameErrMsg.setText("");
				btnSubmit.setVisible(true);
				btnUpdate.setVisible(true);
				btnDelete.setVisible(true);
				if (personalRadioButton.getParent() != null) {
					personalRadioButton.getParent().revalidate();
					personalRadioButton.getParent().repaint();
				}
			}
		});
		teamRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				colorId.clear();
				for (int j = 0; j < colorDisplays.length; j++) {
					for (int i = 0; i < categories.length; i++) {
						if (! categories[i].isPersonal()) {
							if (colorDisplays[j].getBackground().getRGB() == categories[i].getColor().getRGB()) {
								colorDisplays[j].setText(categories[i].getName());
								colorId.put(categories[i].getColor().getRGB(), categories[i].getId());
								colorDisplays[j].revalidate();
								colorDisplays[j].repaint();
								break;
							}
							colorDisplays[j].setText("");
							colorDisplays[j].revalidate();
							colorDisplays[j].repaint();
						}
					}
				}
				for (int i = 0; i < colorDisplays.length; i++) {
					colorDisplays[i].setBorder(null);
				}
				nameTextField.setText("");
				nameErrMsg.setText("");
				btnSubmit.setVisible(true);
				btnUpdate.setVisible(true);
				btnDelete.setVisible(true);
				if (teamRadioButton.getParent() != null) {
					teamRadioButton.getParent().revalidate();
					teamRadioButton.getParent().repaint();
				}
			}
		});
		teamRadioButton.doClick();;

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
				btnDelete.setEnabled(checkContent());
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
				btnDelete.setEnabled(checkContent());
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
			if ((i == color.length - 1) || (i % 5 == 4)) {
				contentPanel.add(colorDisplays[i], "wrap");
			}
			else if (i % 5 == 0) {
				contentPanel.add(colorDisplays[i], "cell 1 " + String.valueOf(1 + i / 5));
			}
			else {
				contentPanel.add(colorDisplays[i]);
			}
		}
		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);
		contentPanel.add(nameErrMsg, "wrap, span");		
		contentPanel.add(btnSubmit, "cell 1 5");
		contentPanel.add(btnUpdate);
		contentPanel.add(btnCancel);
		contentPanel.add(btnDelete, "gapleft 5%");

		if (IDText.getText().equals("")) {
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);
			btnSubmit.setVisible(true);
		}
		else {
			btnUpdate.setVisible(true);
			btnDelete.setVisible(true);
			btnSubmit.setVisible(false);
		}

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((JButton)e.getSource()).addActionListener(new AddCategoryController(model, packInfo()));
				((JButton)e.getSource()).addActionListener(AddCategoryPanelController.getInstance());
				((JButton)e.getSource()).removeActionListener(this);
				((JButton)e.getSource()).doClick();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((JButton)e.getSource()).addActionListener(new UpdateCategoryController(packInfo()));
				((JButton)e.getSource()).addActionListener(AddCategoryPanelController.getInstance());
				((JButton)e.getSource()).removeActionListener(this);
				((JButton)e.getSource()).doClick();
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Deleting... " + IDText.getText());
//				((JButton)e.getSource()).addActionListener(new DeleteCategoryController(Integer.parseInt(IDText.getText())));
				Category cate = packInfo();
				cate.setActive(false);
				((JButton)e.getSource()).addActionListener(new UpdateCategoryController(cate));
				((JButton)e.getSource()).addActionListener(AddCategoryPanelController.getInstance());
				((JButton)e.getSource()).removeActionListener(this);
				((JButton)e.getSource()).doClick();
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
