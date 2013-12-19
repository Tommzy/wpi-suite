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

/*
 * TableSortDemo.java requires no other files.
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


import java.awt.Font;

/**
 * The Class for the table for filtering.
 */
public class FiltersTable extends JPanel {

  /**
   * Instantiates a new filters table.
   */
  public FiltersTable() {
    
    // Table label
    final JLabel filterTableLabel = new JLabel("Filters");
    filterTableLabel.setAlignmentX(CENTER_ALIGNMENT);
    filterTableLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
    final JTable table = new JTable(new FiltersTableModel());
    //table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    //table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);

    // Create the scroll pane and add the table to it.
    final JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
    add(filterTableLabel);
    // Add the scroll pane to this panel.
    add(scrollPane);
  }

  /**
   * The Class FiltersTableModel.
   */
  @SuppressWarnings("serial")
  class FiltersTableModel extends AbstractTableModel {
    
    /** The column names. */
    private String[]   columnNames = {"Filter", "Active?" };
    
    /** The data. */
    private Object[][] data        = 
    { 
      {"GUI", new Boolean(false), },
      {"DB", new Boolean(false), },
      {"Meetings", new Boolean(false), },
      {"High Priority", new Boolean(false), },
      {"Low Priority", new Boolean(false), }, 
    };

    /**
     * Gets the number of columns in a table.
     * @see javax.swing.table.TableModel#getColumnCount()
     * @return The number of columns in the table.
     */
    public int getColumnCount() {
      return columnNames.length;
    }

    /**
     * Gets the number of rows in a table.
     * @see javax.swing.table.TableModel#getRowCount()
     * @return The number or rows in the table.
     */
    public int getRowCount() {
      return data.length;
    }

    /**
     * Gets the name of a column as a string.
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     * @param col The column to check.
     * @return The name of the column as a string.
     */
    public String getColumnName(int col) {
      return columnNames[col];
    }

    /**
     * Gets the data located within a row and column (X, Y).
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     * @param row The row to check.
     * @param col The column to check.
     * @return Gives back the object located at X, Y in the table.
     */
    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    /**
     * JTable uses this method to determine the default renderer/editor for
     * each cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     * @param col Column to get the value from
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     * @return Gives back the class type of the column in the table.
     */
    public Class getColumnClass(int col) {
      return getValueAt(0, col).getClass();
    }


    /** 
     * Checks to see if the cell is editable.
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     * @param row The row to check.
     * @param col The column to check.
     * @return True if it's column 1 (the checkbox).
     */
    public boolean isCellEditable(int row, int col) {
      // Note that the data/cell address is constant,
      // no matter where the cell appears onscreen.
      if (col == 1) {
        return true;
      } else {
        return false;
      }
    }


    /** 
     * Sets the value of the given data to the cell.
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
     * @param value The new value received.
     * @param row The row the cell is in.
     * @param col The col the cell is in.
     */
    public void setValueAt(Object value, int row, int col) {

      data[row][col] = value;
      // Normally, one should call fireTableCellUpdated() when
      // a value is changed. However, doing so causes a problem
      // with TableSorter. 
      // The tableChanged() call on TableSorter that results from calling
      // fireTableCellUpdated() causes the indices to be regenerated
      // when they shouldn't be.
    }
  }
}
