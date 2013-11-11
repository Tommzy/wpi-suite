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

public class EventTable extends JPanel {

  public EventTable() {
    super(new GridLayout(1, 0));

    JTable table = new JTable(new EventTableModel());
    table.setPreferredScrollableViewportSize(new Dimension(500, 80));
    table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);

    // Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(table);

    // Add the scroll pane to this panel.
    add(scrollPane);
  }

  class EventTableModel extends AbstractTableModel {
    private String[]   columnNames = {
                                       "Event", "Date", "Time", "Location"
                                   };
    private Object[][] data        = {
                                       {
                                           "GUI Meeting", "Nov 13, 2013",
                                           "08:00 - 10:00", "SL 402"
                                       },
                                       {
                                           "Team Meeting", "Nov 13, 2013",
                                           "11:00 - 11:30", "Anderson Labs A"
                                       },
                                       {
                                           "Lunch", "Nov 13, 2013",
                                           "12:00 - 13:00", "Campus Center"
                                       },
                                       {
                                           "DB Meeting", "Nov 13, 2013",
                                           "14:00 - 16:00", "Pumpkin Lounge"
                                       },
                                       {
                                           "Code Review", "Nov 13, 2013",
                                           "16:00 - 17:00", "SL 402"
                                       },
                                       {
                                         "Quittin' Time", "Nov 13, 2013",
                                         "17:00 - 17:15", "SL 402"
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
    JFrame frame = new JFrame("Event Table");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Using Mig for JPanels to help with layout.
    JPanel panel = new JPanel(new MigLayout());

    // Table label
    JLabel eventTableLabel = new JLabel("Events");
    eventTableLabel.setFont(new Font("Arial", Font.BOLD, 16));

    // Create and set up the content pane for the table.
    EventTable newContentPane = new EventTable();
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