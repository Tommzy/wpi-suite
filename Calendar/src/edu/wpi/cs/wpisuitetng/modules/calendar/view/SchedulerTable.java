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
 */

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import net.miginfocom.swing.MigLayout;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddInvitationController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddSchedulerPanelController;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class SchedulerTable extends JPanel {

  // TODO: Add in the Date & Description (as well as DB stuff of course)
  /**
   * Instantiates a new scheduler table.
   */
  public SchedulerTable(MigLayout miglayout) {
    JPanel contentPanel = new JPanel(miglayout);
	
    // Table label
    final JLabel schedulerTableLabel = new JLabel("Meeting Scheduler");
    schedulerTableLabel.setAlignmentX(TOP_ALIGNMENT);
    schedulerTableLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
    final JTable table = new JTable(new SchedulerTableModel());
    table.setAutoCreateRowSorter(true);
    
    JButton btnSubmit = new JButton("Submit");
    btnSubmit.setAlignmentX(BOTTOM_ALIGNMENT);
    
    contentPanel.add(schedulerTableLabel, "wrap");
    contentPanel.add(table, "wrap");
    contentPanel.add(btnSubmit);
    
    btnSubmit.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

	});
    
    this.add(contentPanel);
  }

  /**
   * The Class SchedulerTableModel.
   */
  class SchedulerTableModel extends AbstractTableModel {
    
    /** The column names. */
    //private String[]   columnNames = {"Time", "Available?" };
    private String[]   columnNames = {"Time", "Number Available", "Available?" };
    
    /** The data. */
    private Object[][] data        = 
    { 
      {"8:00-9:00",new Integer(0), new Boolean(false), },
      {"9:00-10:00",new Integer(0), new Boolean(false), },
      {"10:00-11:00",new Integer(0), new Boolean(false), },
      {"11:00-12:00",new Integer(0), new Boolean(false), },
      {"12:00-13:00",new Integer(0), new Boolean(false), },
      {"14:00-15:00",new Integer(0), new Boolean(false), },
      {"15:00-16:00",new Integer(0), new Boolean(false), },
      {"16:00-17:00",new Integer(0), new Boolean(false), },
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
      if (col == 2) {
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
