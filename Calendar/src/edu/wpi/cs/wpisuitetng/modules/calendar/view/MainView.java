/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Yuchen Zhang
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

// TODO: Auto-generated Javadoc
/**
 * This Panel sets the main view framework when users go to Calendar module. 
 * It contains several sub panels. 
 * 
 * @author Yuchen Zhang
 *
 */
@SuppressWarnings("serial")
public class MainView extends JPanel {
	
	/** The add event tab panel. */
	AddEventTabPanel addEventTabPanel;
	
	/** The add commit tab panel. */
	JTabbedPane addCommitTabPanel;
	/**
	 * Create the main panel.
	 */
	public MainView() {
		addEventTabPanel = new AddEventTabPanel();
		add(addEventTabPanel);
		//addCommitTabPanel = new AddCommitTabPanel;
		MainCalendarController.getInstance().setMainView(this);
		
//		JPanel calendarPanel = new MainCalendarView(new MainCalendarModel());
//		calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
//		add(calendarPanel);
	}
	
	/**
	 * Gets the main tab pane.
	 *
	 * @return the main tab pane
	 */
	public AddEventTabPanel getMainTabPane() {
		return addEventTabPanel;
	}
}
