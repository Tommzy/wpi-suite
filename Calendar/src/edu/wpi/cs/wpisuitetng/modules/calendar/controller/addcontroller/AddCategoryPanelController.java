/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

/**This controller hold the view of add category view
 * @author HF
 * @version v1.0
 */
public class AddCategoryPanelController implements ActionListener {

	JTabbedPane tabbedPane;

	public static AddCategoryPanelController instance;



	/**Return the instance of the singleton AddCategoryPanelController
	 * @return AddCategoryPanelController a instance of AddCategoryPanelController
	 */
	public static AddCategoryPanelController getInstance() {
		if (instance == null) {
			instance = new AddCategoryPanelController();
		}

		return instance;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().getClass().equals(JButton.class)) {
			// Close the tab a second later for calendar view to refresh. Avoid showing a flash to users when the calendar refreshes. 
			final Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					tabbedPane.removeTabAt(AddCategoryPanelController.getInstance().getTabbedPane().getSelectedIndex());
				}
			}, 1000);
			
		}

	}

}
