/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 * V1.0
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.getcontroller.GetInvitationController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.InvitationModel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SchedulerList extends JPanel {
	JComboBox<Invitation> schedulerCB;
	String username;
	Invitation currentSelection;
	SchedulerTable schedulerTable;
	MigLayout layout;
	
	public SchedulerList(MigLayout miglayout) {
		// mig layout for this panel that we will add our things to
		layout = miglayout;
		schedulerTable = new SchedulerTable(new MigLayout(), new Invitation("", "", ""));
		GetInvitationController getController = new GetInvitationController();
		schedulerCB = new JComboBox<Invitation>( InvitationModel.getInstance().getInvitationArray());
		try{
			getController.actionPerformed(null);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		updateView();
		try {
			schedulerCB.setSelectedIndex(0);
		} catch (Exception e) {
			System.out.println(e);

		}

	}
	
	public void updateView() {
		this.removeAll();
		JPanel contentPanel = new JPanel(layout);
		

		// List for displaying in the GUI


		// try to get the invitations in the DB currently
		
		// get the local list model
//		List<Invitation> invites = InvitationModel.getInstance().getAllInvitation();
//
//		// list that we will display in the JList
//		DefaultListModel<String> listModel = new DefaultListModel<String>();
//		for(int i=0; i<invites.size(); i++){
//			// grab the names of the invites
//			listModel.addElement(invites.get(i).getName().toString());
//			System.out.println(invites.get(i).getName().toString());
//
//		}
		//listModel.add
		//		for(int i = 0; i < listModel.size(); i++){
		//		  System.out.println(listModel.get(i));
		//		}

		System.out.println("adding");
		// put in the names of the invites into the JList to display

//		System.out.println(InvitationModel.getInstance().getInvitationArray().length);
		schedulerCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentSelection = (Invitation) schedulerCB.getSelectedItem();
				schedulerTable = new SchedulerTable(new MigLayout(), currentSelection);
				updateView();
				
			}
		});
		

		// Add the scrollbar to the content panel
		contentPanel.add(schedulerCB);
		contentPanel.add(schedulerTable);

		// add this panel to the super
		this.add(contentPanel);
		if(getParent() != null){
			getParent().revalidate();
			getParent().repaint();
		}
	}

}
