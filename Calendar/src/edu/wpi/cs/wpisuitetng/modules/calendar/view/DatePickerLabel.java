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

import javax.swing.JLabel;

import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

public class DatePickerLabel extends JLabel {
	private DateController dateController;
	private boolean isDisable = false;
	
	public DatePickerLabel(DateController date) {
		dateController = date;
	}
	
	public DateController getDate() {
		return dateController;
	}
	
	public void disable() {
		super.disable();
		isDisable = true;
	}
	
	public boolean isDisable() {
		return isDisable;
	}
}
