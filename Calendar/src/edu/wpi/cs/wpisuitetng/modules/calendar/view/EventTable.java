/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Team 3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.Updatable;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * The Class for the table of events.
 */
@SuppressWarnings("serial")
public class EventTable extends JPanel implements Updatable {

  /** The data in this table. */
  Object[][] data;



  /**
   * Instantiates a new event table.
   */
  public EventTable() {
    super(new GridLayout(1, 0));

    MainCalendarController.getInstance().addToUpdateList(this);
    MainCalendarController.getInstance().setEventTable(this);
    MainCalendarController.getInstance().getYearView().today();
    update();
  }



  /**
   * Setup the table.
   */
  private void setupTable() {
    // remove all the old things from it
    removeAll();
    // Table label
    final JLabel eventTableLabel = new JLabel("Events");
    eventTableLabel.setAlignmentX(CENTER_ALIGNMENT);
    eventTableLabel.setFont(new Font("Arial", Font.BOLD, 16));
    final JTable table = new JTable(new EventTableModel(data));
    table.setAutoCreateRowSorter(true);

    // Create the scroll pane and add the table to it.
    final JScrollPane scrollPane = new JScrollPane(table,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    add(eventTableLabel);
    // Add the scroll pane to this panel.
    add(scrollPane);
  }

  /**
   * The Class EventTableModel.
   */
  class EventTableModel extends AbstractTableModel {

    /** The column names. */
    private String[] columnNames = { "Event", "Start", "End", "Location" };


    /**
     * Instantiates a new task table model.
     * 
     * @param data2
     *          the data to display in the table
     */
    public EventTableModel(Object[][] data2) {
      data = data2;
    }



    /**
     * Gives the number of columns in the table.
     * 
     * @return The number of columns in the table.
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
      return columnNames.length;
    }



    /**
     * Gives the number of rows total in the table.
     * 
     * @return The number of rows in the table.
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
      return data.length;
    }



    /**
     * Gets the name of the column as a string.
     * 
     * @param col
     *          The column name to check.
     * @return A string that names the column.
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int col) {
      return columnNames[col];
    }



    /**
     * Get the value of a cell at a row and column.
     * 
     * @param row
     *          The row to check.
     * @param col
     *          The column to check.
     * @return The data located at row, col.
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int row, int col) {
      return data[row][col];
    }



    /**
     * JTable uses this method to determine the default renderer/editor for each
     * cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     * 
     * @param col
     *          The column to check
     * @return The class of the data at 0, col.
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    public Class getColumnClass(int col) {
      return getValueAt(0, col).getClass();
    }
  }



  /** Update the table view with the new data.
   * @see edu.wpi.cs.wpisuitetng.modules.calendar.util.Updatable#update()
   */
  @Override
  public final void update() {
    // grab the local data
    CalendarTimePeriod timePeriod = MainCalendarController.getInstance()
        .getSelectedCalendarView();
    Collection<Event> eventList = null;
    // Set the table to the appropriate time frame
    switch (timePeriod) {
      case Month:
        eventList = MainCalendarController.getInstance().getMonthView()
            .getMonthViewEventList();
        break;
      case Day:
        eventList = MainCalendarController.getInstance().getDayView()
            .getDayViewEventList();
        break;
      case Week:
        eventList = MainCalendarController.getInstance().getWeekView()
            .getDayViewEventList();
      case Year:
        System.out.println("year view event table");
        MainCalendarController.getInstance().getYearView().updateTables();
        eventList = MainCalendarController.getInstance().getYearView()
            .getEventList();
        System.out.println("size = " + eventList.size());
        break;
      default:
        break;
    }

    if(eventList == null) {
      data = new Object[0][0];
      setupTable();
      return;
    }
    
    // Put in the data and display it
    int length = eventList.size();
    Iterator<Event> itr = eventList.iterator();
    data = new Object[length][4];
    for(int i = 0; i < length; i++) {
      Event event = itr.next();
      data[i][0] = event.getName();
      data[i][3] = event.getLocation();
      GregorianCalendar calStart = event.getStartTime();
      int yearStart = calStart.get(Calendar.YEAR);
      int monthStart = calStart.get(Calendar.MONTH);
      int dayStart = calStart.get(Calendar.DATE);
      int hourStart = calStart.get(Calendar.HOUR_OF_DAY);
      int minuteStart = calStart.get(Calendar.MINUTE);
      data[i][1] = "" + (monthStart + 1) + "/" + dayStart + "/" + yearStart
          + " " + hourStart + ":"
          + (minuteStart < 10 ? "0" + minuteStart : minuteStart);
      GregorianCalendar calEnd = event.getStartTime();
      int yearEnd = calStart.get(Calendar.YEAR);
      int monthEnd = calStart.get(Calendar.MONTH);
      int dayEnd = calStart.get(Calendar.DATE);
      int hourEnd = calStart.get(Calendar.HOUR_OF_DAY);
      int minuteEnd = calStart.get(Calendar.MINUTE);
      data[i][2] = "" + (monthEnd + 1) + "/" + dayEnd + "/" + yearEnd + " "
          + hourEnd + ":" + (minuteEnd < 10 ? "0" + minuteEnd : minuteEnd);

    }

    setupTable();
  }
}