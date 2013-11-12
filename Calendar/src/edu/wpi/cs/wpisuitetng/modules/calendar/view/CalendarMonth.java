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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.border.Border;

import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;


/**
 *
 */
@SuppressWarnings("serial")
public class CalendarMonth extends JXMonthView implements ActionListener, KeyListener {

	public static final Color START_END_DAY = new Color(47, 150, 9);
	public static final Color SELECTION = new Color(236,252,144);
	public static final Color UNSELECTABLE = Color.red;

	/**
	 * Constructor for the iteration calendar.
	 * @param parent IterationPanel
	 * @param vm ViewMode
	 * @param displayIteration Iteration
	 */
	public CalendarMonth(boolean isSingleMonth)
	{
		if (isSingleMonth)
		{
			buildMonthLayout();
		}
		else
		{
			buildYearLayout();
		}
	}
	
	/**
	 * Builds the layout
	 */
	private void buildMonthLayout()
	{
		Dimension preferredSize = new Dimension();
		Font font = this.getFont();
		preferredSize.width = 500;
		
		this.setPreferredColumnCount(1);
		this.setPreferredRowCount(1);
		this.setBoxPaddingX((this.getBoxPaddingX() * 8));
		this.setBoxPaddingY((this.getBoxPaddingY() * 8));
		this.setFont(font.deriveFont(24f));
		this.setPreferredSize(preferredSize);
		this.setSelectionBackground(SELECTION);
		this.setFlaggedDayForeground(START_END_DAY);
		this.setSelectionMode(SelectionMode.SINGLE_SELECTION);
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.addActionListener(this);
		
		this.addKeyListener(this);
	}
	
	private void buildYearLayout()
	{
		this.setPreferredColumnCount(4);
		this.setPreferredRowCount(3);
		this.setSelectionBackground(SELECTION);
		this.setFlaggedDayForeground(START_END_DAY);
		this.setSelectionMode(SelectionMode.SINGLE_SELECTION);
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.addActionListener(this);
				
		this.addKeyListener(this);
	}

	/**
	 * Method actionPerformed.
	 * @param e ActionEvent
	
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent) */
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO 	
	}

	/**
	 * Method keyTyped.
	 * @param e KeyEvent
	 * @see java.awt.event.KeyListener#keyTyped(KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		//TODO
	}

	/**
	 * Method keyPressed.
	 * @param e KeyEvent
	 * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		//TODO
	}

	/**
	 * Method keyReleased.
	 * @param e KeyEvent
	 * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		//TODO
	}

}
