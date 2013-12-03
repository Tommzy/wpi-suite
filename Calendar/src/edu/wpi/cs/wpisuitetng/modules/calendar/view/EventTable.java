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

import java.awt.Font;
/**
 * The Class for the table of events.
 */
@SuppressWarnings("serial")
public class EventTable extends JPanel {

  /**
   * Instantiates a new event table.
   */
  public EventTable() {
    
    // Table label
    final JLabel eventTableLabel = new JLabel("Events");
    eventTableLabel.setAlignmentX(CENTER_ALIGNMENT);
    eventTableLabel.setFont(new Font("Arial", Font.BOLD, 16));
    final JTable table = new JTable(new EventTableModel());
    //table.setPreferredScrollableViewportSize(new Dimension(500, 80));
    //table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);

    // Create the scroll pane and add the table to it.
    final JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    add(eventTableLabel);
    // Add the scroll pane to this panel.
    add(scrollPane);
  }

  /**
   * The Class EventTableModel.
   */
  class EventTableModel extends AbstractTableModel {
    
    /** The column names. */
    private String[]   columnNames = {"Event", "Date", "Time", "Location" };
    
    /** The event data. */
    private Object[][] eventData   = 
    {
      {"GUI Meeting", "Nov 13, 2013",                                         
        "08:00 - 10:00", "SL 402", },
      {"Team Meeting", "Nov 13, 2013",
        "11:00 - 11:30", "Anderson Labs A", },
      {"Lunch", "Nov 13, 2013",
        "12:00 - 13:00", "Campus Center", },
      {"DB Meeting", "Nov 13, 2013",
        "14:00 - 16:00", "Pumpkin Lounge", },
      {"Code Review", "Nov 13, 2013",
        "16:00 - 17:00", "SL 402", },
      {"Quittin' Time", "Nov 13, 2013",
        "17:00 - 17:15", "SL 402", }
    };

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
      return eventData.length;
    }

    /**
     * Gets the name of the column as a string.
     *
     * @param col The column name to check.
     * @return A string that names the column.
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int col) {
      return columnNames[col];
    }

    /**
     * Get the value of a cell at a row and column.
     *
     * @param row The row to check.
     * @param col The column to check.
     * @return The data located at row, col.
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int row, int col) {
      return eventData[row][col];
    }

    /**
     * JTable uses this method to determine the default renderer/editor for each
     * cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     *
     * @param col The column to check
     * @return The class of the data at 0, col.
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    public Class getColumnClass(int col) {
      return getValueAt(0, col).getClass();
    }
  }
}