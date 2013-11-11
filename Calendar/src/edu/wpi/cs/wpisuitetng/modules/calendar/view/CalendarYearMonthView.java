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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;

// TODO: Auto-generated Javadoc
/**
 * Calendar class for displaying year & month.
 */
@SuppressWarnings("serial")
public class CalendarYearMonthView extends JXMonthView implements
    ActionListener, KeyListener {

  /** The Constant START_END_DAY. */
  public static final Color START_END_DAY = new Color(47, 150, 9);
  
  /** The Constant SELECTION. */
  public static final Color SELECTION     = new Color(236, 252, 144);
  
  /** The Constant UNSELECTABLE. */
  public static final Color UNSELECTABLE  = Color.red;

  /**
   * Constructor for the iteration calendar.
   *
   */
  public CalendarYearMonthView() {
    buildLayout();
  }

  /**
   * Builds the layout.
   */
  private void buildLayout() {
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
   * 
   * @param e
   *          ActionEvent
   * 
   * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO
  }

  /**
   * Method keyTyped.
   * 
   * @param e
   *          KeyEvent
   * @see java.awt.event.KeyListener#keyTyped(KeyEvent)
   */
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO
  }

  /**
   * Method keyPressed.
   * 
   * @param e
   *          KeyEvent
   * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
   */
  @Override
  public void keyPressed(KeyEvent e) {
    // TODO
  }

  /**
   * Method keyReleased.
   * 
   * @param e
   *          KeyEvent
   * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
   */
  @Override
  public void keyReleased(KeyEvent e) {
    // TODO
  }

}
