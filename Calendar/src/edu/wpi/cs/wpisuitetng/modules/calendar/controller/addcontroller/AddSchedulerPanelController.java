/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

/**This controller hold the view of add category view
 * @author Yuchen Zhang
 * @version v1.0
 */
public class AddSchedulerPanelController implements ActionListener {

	JTabbedPane tabbedPane;
	JButton btnSubmit;
	JButton btnCancel;

	/**
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}

	/**
	 * @param btnCancel the btnCancel to set
	 */
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public static AddSchedulerPanelController instance = null;



	/**getter of AddSchedulerPanelController
	 * @return AddSchedulerPanelController the instance of AddSchedulerPanelController
	 */
	public static AddSchedulerPanelController getInstance() {
		if (instance == null) {
			instance = new AddSchedulerPanelController();
		}

		return instance;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().getClass().equals(JButton.class)) {
			// Close the tab a second later for calendar view to refresh. Avoid showing a flash to users when the calendar refreshes. 
			tabbedPane.removeTabAt(AddSchedulerPanelController.getInstance().getTabbedPane().getSelectedIndex());
		}
	}

}
