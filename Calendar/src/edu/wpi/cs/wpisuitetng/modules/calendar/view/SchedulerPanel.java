package edu.wpi.cs.wpisuitetng.modules.calendar.view;

/*
 * TableSortDemo.java requires no other files.
 */

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.awt.Font;


@SuppressWarnings("serial")
public class SchedulerPanel extends JPanel {

  /**
   * Instantiates a new scheduler panel.
   */
  public SchedulerPanel() {
  
	  SchedulerTable schedulerTable = new SchedulerTable();
	  SchedulerList schedulerList = new SchedulerList();
	  schedulerList.setAlignmentX(LEFT_ALIGNMENT);
	  add(schedulerList);
	  add(schedulerTable);
//    // Table label
//    final JLabel schedulerTableLabel = new JLabel("Meeting Scheduler");
//    schedulerTableLabel.setAlignmentX(CENTER_ALIGNMENT);
//    schedulerTableLabel.setFont(new Font("Arial", Font.BOLD, 16));
//    
//    final JTable table = new JTable(new SchedulerPanelModel());
//    //table.setPreferredScrollableViewportSize(new Dimension(500, 70));
//    //table.setFillsViewportHeight(true);
//    table.setAutoCreateRowSorter(true);
//
//    // Create the scroll pane and add the table to it.
//    final JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//    
//    add(schedulerTableLabel);
//    // Add the scroll pane to this panel.
//    add(scrollPane);
  }
}
