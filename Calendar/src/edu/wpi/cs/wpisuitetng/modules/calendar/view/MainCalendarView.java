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

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.MainCalendarModel;

/**
 * This Panel sets the calendar area within main view of Calendar module.
 * It contains a sub panel CalendarView and 
 * buttons to switch among day-display, week-display, month-display and so on. 
 * 
 * @author Yuchen Zhang
 *
 */
@SuppressWarnings("serial")
public class MainCalendarView extends JPanel {
	ArrayList<JToggleButton> tglbtnPeriod = new ArrayList<JToggleButton>();
	
	/**
	 * Create the calendar area within main panel.
	 */
	public MainCalendarView(MainCalendarModel mainCalendarModel) {
//		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		setLayout(null);
		this.setSize(getMaximumSize());
		for (CalendarTimePeriod value : CalendarTimePeriod.values()) {
			JToggleButton tglbtn = new JToggleButton(value.name());
			tglbtnPeriod.add(tglbtn);
			tglbtn.addActionListener(new MainCalendarController(mainCalendarModel, this));
			add(tglbtn);
		}
	}
	
	/**
	 * Getter for tglbtnPeriod
	 * @return tglbtnPeriod
	 */
	public ArrayList<JToggleButton> getToggleButtonPeriod() {
		return tglbtnPeriod;
	}

}
