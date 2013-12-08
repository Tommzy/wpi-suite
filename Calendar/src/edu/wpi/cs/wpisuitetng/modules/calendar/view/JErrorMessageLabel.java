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

// TODO: Auto-generated Javadoc
/**
 * The Class JErrorMessageLabel.
 *
 * @author Yuchen Zhang
 */
public class JErrorMessageLabel extends JLabel {

	/**
	 * Instantiates a new j error message label.
	 */
	public JErrorMessageLabel() {
	}

	/**
	 * Instantiates a new j error message label.
	 *
	 * @param text the text
	 */
	public JErrorMessageLabel(String text) {
		super("<html><font color='red'>" + text + "</font></html>");
	}

	/**
	 * Instantiates a new j error message label.
	 *
	 * @param image the image
	 */
	public JErrorMessageLabel(Icon image) {
		super(image);
	}

	/**
	 * Instantiates a new j error message label.
	 *
	 * @param text the text
	 * @param horizontalAlignment the horizontal alignment
	 */
	public JErrorMessageLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
	}

	/**
	 * Instantiates a new j error message label.
	 *
	 * @param image the image
	 * @param horizontalAlignment the horizontal alignment
	 */
	public JErrorMessageLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
	}

	/**
	 * Instantiates a new j error message label.
	 *
	 * @param text the text
	 * @param icon the icon
	 * @param horizontalAlignment the horizontal alignment
	 */
	public JErrorMessageLabel(String text, Icon icon, int horizontalAlignment) {
		super(("<html><font color='red'>" + text + "</font></html>"), icon, horizontalAlignment);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JLabel#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		super.setText("<html><font color='red'>" + text + "</font></html>");
	}
	
	/**
	 * Gets the content text.
	 *
	 * @return the content text
	 */
	public String getContentText() {
		String all = super.getText();
		String content = all.split("<font color='red'>")[1].split("</font>")[0];
		return content;
	}

}
