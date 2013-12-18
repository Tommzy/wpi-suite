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
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings("serial")
public class SchedulerTable extends JPanel {
	// TODO: Add in the Date & Description (as well as DB stuff of course)
	/**
	 * Instantiates a new scheduler table.
	 */
	
	Invitation currentInvitation = new Invitation(" "," "," ");
	String keys[] = {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
	List<String> availability[] = null;
	private SchedulerTableModel stm = new SchedulerTableModel();
	String invitationName = "";
	String invitationDate = "";
	String invitationDescription = "";
	
	private MigLayout layout = new MigLayout();
	final JLabel schedulerTableLabel = new JLabel("Meeting Scheduler: " + invitationName);
	final JTable table = new JTable(stm);
	JButton btnSubmit = new JButton("Submit");
	final JLabel descriptionLabel = new JLabel("Description: ");
	final JLabel dateLabel = new JLabel("Date: ");
	
	public SchedulerTable(MigLayout miglayout) {
		layout = miglayout;
		refreshView();
	}
	
	public void setInvitation(Invitation invitation) {
		currentInvitation = invitation;
	}
	
	public void refreshData() {
		List<String> namesList = null;
		for (int i = 0; i < keys.length; ++i) {
			namesList.clear();
			String personsAvailable[] = currentInvitation.getAvailablity().get(keys[i]);
			namesList = Arrays.asList(personsAvailable[i].split(","));
			availability[i] = namesList;
		}
		
		invitationName = currentInvitation.getName();
		invitationDate = currentInvitation.getDate();
		invitationDescription = currentInvitation.getDescription();
		
		for (int j = 0; j < availability.length; ++j) {
			for (int k = 0; k < availability[j].size(); ++j) {
				stm.setValueAt(false, k, 2);
				if (availability[j].get(k) == currentInvitation.getCurrentUser()) {
					stm.setValueAt(true, k, 2);
					break;
				}
			}
		}
		
		refreshView();
	}
	
	public void refreshView() {
		this.removeAll();
		JPanel contentPanel = new JPanel(layout);
		
		schedulerTableLabel.setAlignmentX(TOP_ALIGNMENT);
		schedulerTableLabel.setFont(new Font("Arial", Font.BOLD, 16));

		table.setAutoCreateRowSorter(true);

		btnSubmit.setAlignmentX(BOTTOM_ALIGNMENT);

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: Fix the access of the nested class?  -Eric
				//        HashMap<String, String[]> availibility = SchedulerTableModel.this.packHashMap();
			}

		});
		
		contentPanel.add(schedulerTableLabel, "wrap");
		contentPanel.add(dateLabel);
		//TODO
		contentPanel.add(new JLabel(invitationDate), "wrap");
		contentPanel.add(descriptionLabel);
		//TODO
		contentPanel.add(new JLabel(invitationDescription), "wrap");
		contentPanel.add(table, "wrap");
		contentPanel.add(btnSubmit);
		
		this.add(contentPanel);
	}

	/**
	 * The Class SchedulerTableModel.
	 */
	class SchedulerTableModel extends AbstractTableModel {

		/** The column names. */
		private String[]   columnNames = {"Time", "Number Available", "Available?" };

		/** The data. */
		private Object[][] data        = 
			{ 
				{"8:00-9:00",new Integer(0), new Boolean(false), },
				{"9:00-10:00",new Integer(0), new Boolean(false), },
				{"10:00-11:00",new Integer(0), new Boolean(false), },
				{"11:00-12:00",new Integer(0), new Boolean(false), },
				{"12:00-13:00",new Integer(0), new Boolean(false), },
				{"13:00-14:00",new Integer(0), new Boolean(false), },
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
		@SuppressWarnings({ "unchecked", "rawtypes" })
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

		public HashMap<String, String[]> packHashMap(){
			HashMap<String, String[]> availibility = new HashMap<String, String[]>();

			for(int i=8; i<17; i++){
				// See if the user checked the box
				boolean isChecked = (Boolean) getValueAt(i-8, 2);
				if(isChecked){
					availibility.put(Integer.toString(i), new String[0]);
				}
			}

			return availibility;
		}
	}
}
