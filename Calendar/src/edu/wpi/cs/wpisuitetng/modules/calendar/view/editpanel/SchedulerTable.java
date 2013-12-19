/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/


package edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel;

/*
 */

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddInvitationController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddSchedulerPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller.UpdateInvitationController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.network.Network;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings("serial")
public class SchedulerTable extends JPanel {
	// TODO: Add in the Date & Description (as well as DB stuff of course)
	/**
	 * Instantiates a new scheduler table.
	 */
	String username;
	Invitation currentInvitation;
	String keys[] = {"8", "9", "10", "11", "12", "13", "14", "15", "16"};
	private SchedulerTableModel stm = new SchedulerTableModel();
	
	private MigLayout layout = new MigLayout();
	
	JLabel schedulerTableLabel;
	JTable table = new JTable(stm);
	JButton btnSubmit = new JButton("Submit");
	JButton btnCancel = new JButton("Cancel");
	JLabel descriptionLabel = new JLabel("Description: ");
	JLabel dateLabel = new JLabel("Date: ");
	
	public SchedulerTable(MigLayout miglayout, Invitation inv) {
		this.currentInvitation = inv;
		layout = miglayout;
		// get my username
		try {
			String cookie = Network.getInstance().getDefaultNetworkConfiguration().getRequestHeaders().get("cookie").get(0);
			cookie = cookie.substring(9);
			username = cookie.split("=")[0];
			System.out.println(username);
		} catch (Exception e) {}
		
		refreshView();
		
	}
	
	public void setInvitation(Invitation invitation) {
		currentInvitation = invitation;
	}
	
//	public void refreshData() {
//		List<String> namesList = new ArrayList<String>();
//		
//		for (int i = 0; i < currentInvitation.getAvailablity().keySet().size(); i++) {
//			namesList.clear();
//			String personsAvailable[] = currentInvitation.getAvailablity().get(keys[i]);
//			if (personsAvailable[i].contains(",")) {
//				namesList = Arrays.asList(personsAvailable[i].split(","));
//			}
//			availability.add(namesList);
//		}
//		
//		for (int j = 0; j < availability.size(); j++) {
//			stm.setValueAt(availability.get(j).size(), j, 1);
//			stm.setValueAt(false, j, 2);
//			for (int k = 0; k < availability.get(j).size(); k++) {
//				if (availability.get(j).get(k).equals(username)) {
//					stm.setValueAt(true, j, 2);
//					break;
//				}
//			}
//			
//		}
//		
//	}
	
	public void refreshView() {
		this.removeAll();
		JPanel contentPanel = new JPanel(layout);
		
		schedulerTableLabel = new JLabel ("Meeting Scheduler: " + currentInvitation.getName());
		descriptionLabel = new JLabel ("Description: " + currentInvitation.getDescription());
		dateLabel = new JLabel ("Date: " + currentInvitation.getDate());

		table.setAutoCreateRowSorter(true);

		btnSubmit.setAlignmentX(BOTTOM_ALIGNMENT);

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSubmit.addActionListener(new UpdateInvitationController(packInfo()));
				btnSubmit.addActionListener(AddSchedulerPanelController.getInstance());
				btnSubmit.removeActionListener(this);
				btnSubmit.doClick();
			}

		});
		
		btnCancel.addActionListener(AddSchedulerPanelController.getInstance());
		populateInvitation();

		contentPanel.add(schedulerTableLabel, "wrap, span");
		contentPanel.add(dateLabel, "wrap, span");
		contentPanel.add(descriptionLabel, "wrap, span");
		contentPanel.add(table, "wrap, span");
		contentPanel.add(btnSubmit);
		contentPanel.add(btnCancel, "gapleft 5%");
		
		this.add(contentPanel);
	}
	
	/**
	 * Pack everything in an invitation
	 * @return packed invitation
	 */
	public Invitation packInfo(){
		HashMap<String, String> map = currentInvitation.getAvailablity();
		for(int i = 0; i < keys.length; i++){
			// See if the user checked the box
			
			boolean isChecked = (Boolean) table.getValueAt(i, 2);
			if(! isChecked){
				String attendee = map.get(keys[i]);
				if (attendee.contains(username)) {
					String[] attendeeSplit = attendee.split("," + username + ",");
					if (attendeeSplit.length == 0) {
						attendee = ",";
					}
					else if (attendeeSplit.length == 1 ) {
						if (attendeeSplit[0].charAt(0) == ",".charAt(0)) {
							attendee = attendeeSplit[0] + ",";
						}
						else if (attendeeSplit[0].charAt(attendeeSplit[0].length() - 1) == ",".charAt(0)) {
							attendee = "," + attendeeSplit[0];
						}
					}
					else {
						attendee = attendeeSplit[0] + "," + attendeeSplit[1];
					}
					
					map.remove(keys[i]);
					map.put(keys[i], attendee);
				}
			}
			else {
				String attendee = map.get(keys[i]);
				if(! attendee.contains("," + username + ",")) {
					attendee = attendee + username + ",";
					map.remove(keys[i]);
					map.put(keys[i], attendee);
				}
			}
		}
		currentInvitation.setAvailablity(map);
		return currentInvitation;
	}
	
	/**
	 * populate the currentInvitation to table
	 */
	public void populateInvitation () {
		HashMap<String, String> map = currentInvitation.getAvailablity();
		for(int i = 0; i < keys.length; i++){
			// See if the user checked the box
			String attendee = map.get(keys[i]);
			int count = 0;
		    for (int j=0; j < attendee.length(); j++)
		    {
		        if (attendee.charAt(j) == ",".charAt(0))
		        {
		             count++;
		        }
		    }
			table.setValueAt(new Integer(count - 1), i, 1);
			if (attendee.contains("," + username + ",")) {
				table.setValueAt(new Boolean(true), i, 2);
			}
		}
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
				{"8:00-9:00",new Integer(0), new Boolean(false) },
				{"9:00-10:00",new Integer(0), new Boolean(false) },
				{"10:00-11:00",new Integer(0), new Boolean(false) },
				{"11:00-12:00",new Integer(0), new Boolean(false) },
				{"12:00-13:00",new Integer(0), new Boolean(false) },
				{"13:00-14:00",new Integer(0), new Boolean(false) },
				{"14:00-15:00",new Integer(0), new Boolean(false) },
				{"15:00-16:00",new Integer(0), new Boolean(false) },
				{"16:00-17:00",new Integer(0), new Boolean(false) },
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
	}
}
