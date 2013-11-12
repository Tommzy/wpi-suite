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

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.MainCalendarModel;

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
  
  /** The tglbtn period. */
  List<JToggleButton> tglbtnPeriod = new ArrayList<JToggleButton>();
  
  /** The calendar view. */
  JPanel              calendarView = new JPanel();

  /**
   * Create the calendar area within main panel.
   *
   * @param mainCalendarModel the main calendar model
   */
  public MainCalendarView(MainCalendarModel mainCalendarModel) {
    // Create toggle buttons for different calendar view
    final JPanel calendarViewSwitch = new JPanel();
    calendarViewSwitch.setLayout(new BoxLayout(calendarViewSwitch,
        BoxLayout.X_AXIS));
    for (CalendarTimePeriod value : CalendarTimePeriod.values()) {
      final JToggleButton tglbtn = new JToggleButton(value.name());
      tglbtnPeriod.add(tglbtn);
      tglbtn.addActionListener(new MainCalendarController(mainCalendarModel,
          this));
      calendarViewSwitch.add(tglbtn);
    }
    add(calendarViewSwitch);

    add(calendarView);

    initialize();
  }

  /**
   * Getter for tglbtnPeriod.
   *
   * @return tglbtnPeriod
   */
  public final List<JToggleButton> getToggleButtonPeriod() {
    return tglbtnPeriod;
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
    tglbtnPeriod.get(3).doClick();
  }

}
