package edu.wpi.cs.wpisuitetng.modules.calendar.view;

/*
 * TableSortDemo.java requires no other files.
 */

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

// TODO: Auto-generated Javadoc
/**
 * The Class TaskTable.
 */
@SuppressWarnings("serial")
public class TaskTable extends JPanel {

  /**
   * Instantiates a new task table.
   */
  public TaskTable() {
    super(new GridLayout(1, 0));

    // Table label
    final JLabel taskTableLabel = new JLabel("Tasks");
    taskTableLabel.setAlignmentX(CENTER_ALIGNMENT);
    taskTableLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
    final JTable table = new JTable(new TaskTableModel());
    //table.setPreferredScrollableViewportSize(new Dimension(500, 80));
    //table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);

    // Create the scroll pane and add the table to it.
    final JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
    add(taskTableLabel);
    // Add the scroll pane to this panel.
    add(scrollPane);
  }

  /**
   * The Class TaskTableModel.
   */
  @SuppressWarnings("serial")
  class TaskTableModel extends AbstractTableModel {
    
    /** The column names. */
    private String[]   columnNames = 
    {
      "Task", "Date", "Time",
    };
    
    /** The data. */
    private Object[][] data        = 
    {
      {
        "GUI HW", "Nov 13, 2013", "09:00",
      },
      {
        "DB HW", "Nov 13, 2013", "10:00",
      },
      {
        "Pick a layout", "Nov 13, 2013",
        "12:00",
      },
      {
        "Decide on a DB structure",
        "Nov 13, 2013", "14:30",
      },
      {
        "Call PM", "Nov 13, 2013",
        "16:50",
      },
      {
        "Write blog", "Nov 13, 2013",
        "16:55",
      },
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
      return data.length;
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
      return data[row][col];
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
}