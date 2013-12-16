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

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class SchedulerPanel extends JPanel {

	/**
	 * Instantiates a new scheduler panel.
	 */
	public SchedulerPanel() {

		SchedulerList schedulerList = new SchedulerList(new MigLayout());
		SchedulerTable schedulerTable = new SchedulerTable(new MigLayout());

		schedulerList.setAlignmentX(LEFT_ALIGNMENT);
		add(schedulerList);
		add(schedulerTable);
	}
}
