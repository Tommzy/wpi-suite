/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    team3 
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.JToolBar;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.MainCalendarModel;



/**
 * @author Hongbo
 *
 */
@SuppressWarnings("serial")
public class ToolbarView extends JToolBar {
	/** The panel containing toolbar buttons */
	private final ToolbarPanel toolbarPanel;
	/**
	 * Construct this view and all components in it.
	 * @param boardModel 
	 */
	public ToolbarView() {
		
		// Prevent this toolbar from being moved
		setFloatable(false);
		
		// Add the panel containing the toolbar buttons
		toolbarPanel = new ToolbarPanel();
		add(toolbarPanel);
	}
	
}
