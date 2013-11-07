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

import edu.wpi.cs.wpisuitetng.modules.calendar.model.MainCalendarModel;

import java.awt.FlowLayout;

/**
 * This Panel sets the main view framework when users go to Calendar module. 
 * It contains several sub panels. 
 * 
 * @author Yuchen Zhang
 *
 */
@SuppressWarnings("serial")
public class MainView extends JPanel {

	/**
	 * Create the main panel.
	 */
	public MainView() {
		JPanel calendarPanel = new MainCalendarView(new MainCalendarModel());
		calendarPanel.setLayout(new FlowLayout());
		add(calendarPanel);
		this.setSize(getMaximumSize());
	}

}
