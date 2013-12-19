/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view.util;

import java.awt.Color;
import java.awt.Dimension;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JLabel;

import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;

/**
 * As the header label of date picker need to 
 * be consistent with date picker, this class
 * is a header label that stores a date controller
 * @author Hongbo
 *
 */
public class DatePickerPanelHeaderLabel extends JLabel {
	protected DateController dateController;
	
	public DatePickerPanelHeaderLabel(DateController date) {
		dateController = date;
		setPreferredSize(new Dimension(100, 20));
		setHorizontalAlignment(CENTER);
		setOpaque(true);
		setBackground(new Color(138, 173, 209));
		setText(date.get(GregorianCalendar.YEAR) + " " 
				+ date.getCalendar().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()) );
		
	}
	
	public DateController getDateController() {
		return dateController;
	}
}
