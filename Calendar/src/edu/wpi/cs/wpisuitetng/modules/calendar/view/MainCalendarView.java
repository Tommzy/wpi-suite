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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItemListModel;

// TODO: Auto-generated Javadoc
/**
 * This Panel sets the calendar area within main view of Calendar module. It
 * contains a sub panel CalendarView and buttons to switch among day-view,
 * week-view, month-view and year-view.
 * 
 * @author Yuchen Zhang
 * 
 */
@SuppressWarnings("serial")
public class MainCalendarView extends JPanel {

  /** The toggle button period. */
  List<JToggleButton> toggleButtonPeriod = new ArrayList<JToggleButton>();
  
  /** The calendar view. */
  JPanel  calendarView = new JPanel();
  
  /** The scroll area which contains the calendar view. */
  JScrollPane calendarScroll = new JScrollPane(calendarView);

  /**
   * Create the calendar area within main panel.
   *
   * @param mainCalendarModel the main calendar model
   */
  public MainCalendarView(CalendarItemListModel mainCalendarModel) {
    // Create toggle buttons for different calendar view
    final JPanel calendarViewSwitch = new JPanel();
    calendarViewSwitch.setLayout(new BoxLayout(calendarViewSwitch,
        BoxLayout.X_AXIS));
    for (CalendarTimePeriod value : CalendarTimePeriod.values()) {
    	UIManager.put("ToggleButton.font",new Font("Times New Roman",Font.BOLD,12));
    	final JToggleButton toggleBtn = new JToggleButton(value.name());
      toggleButtonPeriod.add(toggleBtn);
      toggleBtn.addActionListener(new MainCalendarController(mainCalendarModel,
          this));
      calendarViewSwitch.add(toggleBtn);
    }
    add(calendarViewSwitch);
    
    calendarScroll.setPreferredSize(new Dimension (1000, 300));
 
	add(calendarScroll);
	
    initialize();
  }

  /**
   * Getter for tglbtnPeriod.
   *
   * @return tglbtnPeriod
   */
  public final List<JToggleButton> getToggleButtonPeriod() {
    return toggleButtonPeriod;
  }

  /**
   * Gets the calendar view.
   *
   * @return the calendar view
   */
  public final JPanel getCalendarView() {
    return calendarView;
  }

  /**
   * Initialize.
   */
  private void initialize() {
	  toggleButtonPeriod.get(3).doClick();
  }

}
