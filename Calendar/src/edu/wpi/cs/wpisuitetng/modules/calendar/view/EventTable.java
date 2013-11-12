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



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
/**
 * The Class for the table of events.
 */
public class EventTable extends JPanel {

  /**
   * Instantiates a new event table.
   */
  public EventTable() {
    super(new GridLayout(1, 0));

    final JTable table = new JTable(new EventTableModel());
    table.setPreferredScrollableViewportSize(new Dimension(500, 80));
    table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);

    // Create the scroll pane and add the table to it.
    final JScrollPane scrollPane = new JScrollPane(table);

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
        "17:00 - 17:15", "SL 402", },
    };

    /**
     * Gives the number of columns in the table.
     * @see javax.swing.table.TableModel#getColumnCount()
     * @return The number of columns in the table.
     */
    public int getColumnCount() {
      return columnNames.length;
    }

    /**
     * Gives the number of rows total in the table.
     * @see javax.swing.table.TableModel#getRowCount()
     * @return The number of rows in the table.
     */
    public int getRowCount() {
      return eventData.length;
    }

    /**
     * Gets the name of the column as a string.
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     * @param col The column name to check.
     * @return A string that names the column.
     */
    public String getColumnName(int col) {
      return columnNames[col];
    }

    /**
     * Get the value of a cell at a row and column.
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     * @param row The row to check.
     * @param col The column to check.
     * @return The data located at row, col.
     */
    public Object getValueAt(int row, int col) {
      return eventData[row][col];
    }

    /**
     * JTable uses this method to determine the default renderer/editor for each
     * cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     *
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     * @param col The column to check
     * @return The class of the data at 0, col.
     */
    public Class getColumnClass(int col) {
      return getValueAt(0, col).getClass();
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    // Create and set up the window.
    final JFrame frame = new JFrame("Event Table");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Using Mig for JPanels to help with layout.
    final JPanel panel = new JPanel(new MigLayout());

    // Table label
    final JLabel eventTableLabel = new JLabel("Events");
    eventTableLabel.setFont(new Font("Arial", Font.BOLD, 16));

    // Create and set up the content pane for the table.
    final EventTable newContentPane = new EventTable();
    newContentPane.setOpaque(true); // content panes must be opaque
    // Add in the title, make the next add go down to the next row
    panel.add(eventTableLabel, "align center");
    // Add in the table to the panel.
    panel.add(newContentPane, "dock south");
    // Add in the panel to the frame.
    frame.setContentPane(panel);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}