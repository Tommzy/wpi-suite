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

package edu.wpi.cs.wpisuitetng.modules.calendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.ToolbarView;

public class testCalendar {
	
	private static void testGUI() {
		JFrame cal = new JFrame();
		cal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel calPane =  new JPanel(new MigLayout());
		ToolbarView toolbar = new ToolbarView();
		MainView calendar = new MainView();
		calendar.setSize(cal.getMaximumSize());
		calPane.add(toolbar, "wrap");
		calPane.add(calendar);
		
		cal.add(calPane);
		
		cal.pack();
		cal.setVisible(true);
	}
	
	public static void main(String[] args) {
		testGUI();
	}
}
