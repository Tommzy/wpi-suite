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
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.DayEvent;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview.MonthView;

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

  /*
  //for test display use
	DayEvent[] sampleEvent = {
			new DayEvent("Whoops", new GregorianCalendar(2013, 5, 16, 20, 50, 0), new GregorianCalendar(2013, 5, 16, 21, 5, 0)), 
			new DayEvent("Innebandy", new GregorianCalendar(2013, 5, 16, 15, 50, 0), new GregorianCalendar(2013, 5, 16, 16, 5, 0)), 
			new DayEvent("Abcd", new GregorianCalendar(2013, 5, 16, 15, 55, 0), new GregorianCalendar(2013, 5, 16, 16, 15, 0)), 
			new DayEvent("Efgh", new GregorianCalendar(2013, 5, 16, 15, 56, 0), new GregorianCalendar(2013, 5, 16, 16, 16, 0)),
			new DayEvent("Hey", new GregorianCalendar(2013, 5, 18, 8, 50, 0), new GregorianCalendar(2013, 5, 18, 10, 5, 0)) };
			*/
  /**
   * Create the calendar area within main panel.
   *
   * @param mainCalendarModel the main calendar model
   */
  public MainCalendarView() {
    // Create toggle buttons for different calendar view
    final JPanel calendarViewSwitch = new JPanel();
    calendarViewSwitch.setLayout(new BoxLayout(calendarViewSwitch,
        BoxLayout.X_AXIS));
    for (CalendarTimePeriod value : CalendarTimePeriod.values()) {
    	UIManager.put("ToggleButton.font",new Font("Times New Roman",Font.BOLD,12));
    	final JToggleButton toggleBtn = new JToggleButton(value.name());
      toggleButtonPeriod.add(toggleBtn);
      toggleBtn.addActionListener(MainCalendarController.getInstance());
      calendarViewSwitch.add(toggleBtn);
    }
    add(calendarViewSwitch);
    
    MainCalendarController mainCalendarController = MainCalendarController.getInstance();
    //I got rid of this -Mark
    //mainCalendarController.setModel(mainCalendarModel);
    CalendarWeekView weekView = new CalendarWeekView();
    MonthView monthView = new MonthView();
    CalendarDayView dayView = new CalendarDayView();
 
    mainCalendarController.setView(this);
    mainCalendarController.setWeekView(weekView);
    mainCalendarController.setMonthView(monthView);
    mainCalendarController.setDayView(dayView);
    
    mainCalendarController.addToUpdateList(weekView);
    mainCalendarController.addToUpdateList(monthView);
    mainCalendarController.addToUpdateList(dayView);
    
    mainCalendarController.setYearView(new CalendarYearView());
    
   
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
