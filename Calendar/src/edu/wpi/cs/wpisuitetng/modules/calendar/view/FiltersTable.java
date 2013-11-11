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

import net.miginfocom.swing.MigLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class FiltersTable extends JPanel {
  private boolean DEBUG = false;

  public FiltersTable() {
    super(new GridLayout(1, 0));

    JTable table = new JTable(new FiltersTableModel());
    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);

    // Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(table);

    // Add the scroll pane to this panel.
    add(scrollPane);
  }

  class FiltersTableModel extends AbstractTableModel {
    private String[]   columnNames = {
                                       "Filter", "Active?"
                                   };
    private Object[][] data        = {
                                       {
                                           "GUI", new Boolean(false)
                                       }, {
                                           "DB", new Boolean(false)
                                       }, {
                                           "Meetings", new Boolean(false)
                                       }, {
                                           "High Priority", new Boolean(false)
                                       }, {
                                           "Low Priority", new Boolean(false)
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
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     */
    public Class getColumnClass(int c) {
      return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's editable.
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

    /*
     * Don't need to implement this method unless your table's data can change.
     */
    public void setValueAt(Object value, int row, int col) {
      if (DEBUG) {
        System.out.println("Setting value at " + row + "," + col + " to "
            + value + " (an instance of " + value.getClass() + ")");
      }

      data[row][col] = value;
      // Normally, one should call fireTableCellUpdated() when
      // a value is changed. However, doing so in this demo
      // causes a problem with TableSorter. The tableChanged()
      // call on TableSorter that results from calling
      // fireTableCellUpdated() causes the indices to be regenerated
      // when they shouldn't be. Ideally, TableSorter should be
      // given a more intelligent tableChanged() implementation,
      // and then the following line can be uncommented.
      // fireTableCellUpdated(row, col);

      if (DEBUG) {
        System.out.println("New value of data:");
      }
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame("Filter Table");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Using Mig for JPanels to help with layout.
    JPanel panel = new JPanel(new MigLayout());
    
    // Table label
    JLabel filterTableLabel = new JLabel("Filters");
    filterTableLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
    // Create and set up the content pane.
    FiltersTable newContentPane = new FiltersTable();
    panel.add(filterTableLabel, "center");
    panel.add(newContentPane, "dock south");
    newContentPane.setOpaque(true); // content panes must be opaque
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
