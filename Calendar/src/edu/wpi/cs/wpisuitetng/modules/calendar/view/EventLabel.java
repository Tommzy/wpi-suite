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
 * @author Yuchen Zhang
 *
 */
public class EventLabel extends JLabel {
	String originalContent;
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
	
	public String getOriginalContent () {
		return originalContent;
	}
	
	/**
	 * 
	 */
//	@Override
//	public void paint(Graphics g) {
//		super.repaint();
//
//		if ((! this.getText().equals(null)) && (this.getSize().width != 0)) {
//			String currentLabel = this.getText();
//			// Split up string to pull content from html tags
//			String[] content = currentLabel.split("<br />");
//			content[0] = content[0].split(">")[content[0].split(">").length - 1];
//			content[content.length - 1] = content[content.length - 1].split("<")[0];
//			// Trim string line by line 
//			for (int i = 0; i < content.length; i++) {
//				content [i] = ellipsize(content[i], this.getSize().width);
//			}
//			
//			// Add html tags into the new label strings
//			String newText = "<HTML><div style='text-align:center'>";
//			for (int i = 0; i < content.length; i++) {
//				newText += content[i] + "<br />";
//			}
//			newText += "</div></HTML>";
//			System.out.println(newText);
//			this.setText(newText);
//			super.paint(g);
//		}
//	}
	
	/**
	 * Calculate text width
	 * @param str a string whose width to be calculated 
	 * @return width of the string
	 */
	private static int textWidth(String str) {
	    return (int) (str.length() /*- str.replaceAll(NON_THIN, "").length() / 2*/);
	}
	
	/**
	 * Chop off text if it exceeds label length
	 * @param text
	 * @param max
	 * @return
	 */
	public static String ellipsize(String text, int max) {

	    if (textWidth(text) <= max)
	        return text;

	    // Start by chopping off at the word before max
	    // This is an over-approximation due to thin-characters...
	    int end = text.lastIndexOf(' ', max - 3);

	    // Just one long word. Chop it off.
	    if (end == -1)
	        return text.substring(0, max - 3) + ".";

	    // Step forward as long as textWidth allows.
	    int newEnd = end;
	    do {
	        end = newEnd;
	        newEnd = text.indexOf(' ', end + 1);

	        // No more spaces.
	        if (newEnd == -1)
	            newEnd = text.length();

	    } while (textWidth(text.substring(0, newEnd) + "...") < max);

	    return text.substring(0, end) + "...";
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
