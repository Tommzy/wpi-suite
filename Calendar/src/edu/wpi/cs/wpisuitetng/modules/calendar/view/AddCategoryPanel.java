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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCategoryPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
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
	
	/** Color Picker label */
	JLabel colorLabel;
	
	/** Color Picker panel */
	JLabel[] colorDisplays;
	
	/** Color array */
	Color[] color = {Color.WHITE, Color.CYAN, Color.ORANGE, Color.YELLOW, Color.MAGENTA, Color.PINK};
	
	/** ID Label */
	JLabel IDText;
	
	/**
	 * Constructor
	 */
	public AddCategoryPanel() {
		this.setLayout(new MigLayout());
		
		nameLabel = new JLabel("Name: ");
		nameTextField = new JTextField(10);
		
		colorLabel = new JLabel("Color: ");
		colorDisplays = new JLabel[color.length];
		for (int i = 0; i < color.length; i++) {
			colorDisplays[i] = new JLabel("                  ");
			colorDisplays[i].setOpaque(true);
			colorDisplays[i].setBackground(color[i]);
		}
		
		IDText = new JLabel();
		
		btnCancel = new JButton("Cancel");
		btnSubmit = new JButton("Submit");
		btnUpdate = new JButton("Update");
		
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
					System.out.println("triggered: ");
					((JLabel)e.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
					System.out.println("Colored Black...");
					for (int i = 0; i < color.length; i++) {
						if (! ((JLabel)e.getSource()).getBackground().equals(color[i])) {
							colorDisplays[i].setBorder(null);
							System.out.println("Colored back... " + i);
						}
						else {
							System.out.println(i);
						}
						colorDisplays[i].revalidate();
						colorDisplays[i].repaint();
					}
				}
			});
		}
		
		add(nameLabel);
		add(nameTextField, "wrap, span ");
		add(colorLabel);
		for (int i = 0; i < color.length; i++) {
			if (i == color.length - 1) {
				add(colorDisplays[i], "wrap");
			}
			else {
				add(colorDisplays[i]);
			}
		}
		add(btnSubmit, "cell 1 2");
		add(btnUpdate);
		add(btnCancel);
		
		
	}
	
}
