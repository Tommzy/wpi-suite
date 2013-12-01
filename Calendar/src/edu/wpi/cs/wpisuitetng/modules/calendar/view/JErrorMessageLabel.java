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

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * @author Yuchen Zhang
 *
 */
public class JErrorMessageLabel extends JLabel {

	/**
	 * 
	 */
	public JErrorMessageLabel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 */
	public JErrorMessageLabel(String text) {
		super("<html><font color='red'>" + text + "</font></html>");
	}

	/**
	 * @param image
	 */
	public JErrorMessageLabel(Icon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param horizontalAlignment
	 */
	public JErrorMessageLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 * @param horizontalAlignment
	 */
	public JErrorMessageLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param icon
	 * @param horizontalAlignment
	 */
	public JErrorMessageLabel(String text, Icon icon, int horizontalAlignment) {
		super(("<html><font color='red'>" + text + "</font></html>"), icon, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setText(String text) {
		super.setText("<html><font color='red'>" + text + "</font></html>");
	}
	
	public String getContentText() {
		String all = super.getText();
		String content = all.split("<font color='red'>")[1].split("</font>")[0];
		return content;
	}

}
