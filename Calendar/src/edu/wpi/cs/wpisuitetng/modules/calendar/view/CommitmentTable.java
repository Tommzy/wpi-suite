package edu.wpi.cs.wpisuitetng.modules.calendar.view;

/*
 * TableSortDemo.java requires no other files.
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.master.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.FakeCommitmentModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview.MonthView;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

/**
 * The Class CommitmentTable.
 * 
 * Used for tracking commitments in a table on the right-hand side of the screen
 * for a user/project.
 */
@SuppressWarnings("serial")
public class CommitmentTable extends JPanel {
	Object[][] data;

	/**
	 * Instantiates a new task table.
	 */
	public CommitmentTable() {
		super(new GridLayout(1, 0));
		update();

	}

	public void setupTable() {
		removeAll();
		
		// Table label
		final JLabel commitmentTableLabel = new JLabel("Commitments");
		commitmentTableLabel.setAlignmentX(CENTER_ALIGNMENT);
		commitmentTableLabel.setFont(new Font("Arial", Font.BOLD, 16));

		JTable table = new JTable(new TaskTableModel(data));
		// table.setPreferredScrollableViewportSize(new Dimension(500, 80));
		// table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		// Create the scroll pane and add the table to it.
		final JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(commitmentTableLabel);
		// Add the scroll pane to this panel.
		
		add(scrollPane);
		revalidate();
		repaint();
		
	}

	public void update() {

		CalendarTimePeriod timePeriod = MainCalendarController.getInstance()
				.getSelectedCalendarView();
		Collection<Commitment> cmtList = null;
		switch (timePeriod) {
		case Month:
			cmtList = MainCalendarController.getInstance().getMonthView()
					.getMonthViewPanel().getMonthCommitmentList();
			break;
		default:
			break;
		}

		if (cmtList == null) {
			data = new Object[0][0];
			setupTable();
			return;
		}
		int length = cmtList.size();
		Iterator<Commitment> itr = cmtList.iterator();
		System.out.println(length);
		data = new Object[length][3];
		for (int i = 0; i < length; i++) {
			Commitment cmt = itr.next();
			data[i][0] = cmt.getName();
			Calendar cal = cmt.getStartTime();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DATE);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);
			data[i][2] = "" + hour + ":" + minute;
			data[i][1] = "" + (month + 1) + "/" + day;

		}
		
		setupTable();
	}

	/**
	 * The Class TaskTableModel.
	 */
	@SuppressWarnings("serial")
	class TaskTableModel extends AbstractTableModel {

		/** The column names. */
		private String[] columnNames = { "Commitment", "Date", "Time", };

		/** The data. */
		private Object[][] data = {
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

		public TaskTableModel(Object[][] data2) {
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
		 *            The column name to check.
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
		 *            The row to check.
		 * @param col
		 *            The column to check.
		 * @return The data located at row, col.
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		/**
		 * JTable uses this method to determine the default renderer/editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 * 
		 * @param col
		 *            The column to check
		 * @return The class of the data at 0, col.
		 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
		 */
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}

	}
}