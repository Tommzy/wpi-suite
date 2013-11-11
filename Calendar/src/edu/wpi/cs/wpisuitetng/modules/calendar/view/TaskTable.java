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

public class TaskTable extends JPanel {

  public TaskTable() {
    super(new GridLayout(1, 0));

    JTable table = new JTable(new TaskTableModel());
    table.setPreferredScrollableViewportSize(new Dimension(500, 80));
    table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);

    // Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(table);

    // Add the scroll pane to this panel.
    add(scrollPane);
  }

  class TaskTableModel extends AbstractTableModel {
    private String[]   columnNames = {
                                       "Task", "Date", "Time"
                                   };
    private Object[][] data        = {
                                       {
                                           "GUI HW", "Nov 13, 2013", "09:00"
                                       },
                                       {
                                           "DB HW", "Nov 13, 2013", "10:00"
                                       },
                                       {
                                           "Pick a layout", "Nov 13, 2013",
                                           "12:00"
                                       },
                                       {
                                           "Decide on a DB structure",
                                           "Nov 13, 2013", "14:30"
                                       },
                                       {
                                           "Call PM", "Nov 13, 2013",
                                           "16:50"
                                       },
                                       {
                                           "Write blog", "Nov 13, 2013",
                                           "16:55"
                                       }
                                   };

    
    public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/editor for each
     * cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     */
    public Class getColumnClass(int c) {
      return getValueAt(0, c).getClass();
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame("Task Table");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Using Mig for JPanels to help with layout.
    JPanel panel = new JPanel(new MigLayout());

    // Table label
    JLabel eventTableLabel = new JLabel("Tasks");
    eventTableLabel.setFont(new Font("Arial", Font.BOLD, 16));

    // Create and set up the content pane for the table.
    TaskTable newContentPane = new TaskTable();
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