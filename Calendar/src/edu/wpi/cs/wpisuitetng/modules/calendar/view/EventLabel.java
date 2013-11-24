/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Graphics;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;

/**
 * This class is an extension of JLabel that is used to
 * display event / commitment on calendar day/week (potentially month) view. 
 * @author Yuchen Zhang
 *
 */
@SuppressWarnings("serial")
public class EventLabel extends JLabel {
	private String originalContent;
	/**
	 * @param originalContent the originalContent to set
	 */
	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	/**
	 * 
	 */
	public EventLabel() {
	}

	/**
	 * @param text
	 */
	public EventLabel(String text) {
		super(text);
		originalContent = text;
	}

	/**
	 * @param image
	 */
	public EventLabel(Icon image) {
		super(image);
	}

	/**
	 * @param text
	 * @param horizontalAlignment
	 */
	public EventLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
	}

	/**
	 * @param image
	 * @param horizontalAlignment
	 */
	public EventLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
	}

	/**
	 * @param text
	 * @param icon
	 * @param horizontalAlignment
	 */
	public EventLabel(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
	}
	
	/**
	 * getter for originalContent
	 * @return originalContent
	 */
	public String getOriginalContent () {
		return originalContent;
	}
	
	//Test the detailed view, adding some new events
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		EventLabel label = new EventLabel("aaewfihuiuhetrewhfiniufnewfrew yurehghriuehtiuhewiuhiurwehtiuwehghrewnciuewqrhtehgiuhregwmieurwnwivciu");
		frame.add(label);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);

	}
	

}
