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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.UpdateCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;


@SuppressWarnings("serial")
public class SchedulerPanel extends JPanel {
  SchedulerList schedulerList;
  boolean doubleClicked = false;
  JButton btnRefresh;
	/**
	 * Instantiates a new scheduler panel.
	 */
	public SchedulerPanel() {
	  btnRefresh = new JButton("Refresh");


		schedulerList = new SchedulerList(new MigLayout());
		SchedulerTable schedulerTable = new SchedulerTable(new MigLayout());

		schedulerList.setAlignmentX(LEFT_ALIGNMENT);
		btnRefresh.addActionListener(new ActionListener() {
      
    
		 @Override
     public void actionPerformed(ActionEvent e) {
		   schedulerList = new SchedulerList(new MigLayout());
		   Timer timer = new Timer();
	      timer.schedule(new TimerTask() {
	        @Override
	        public void run() {
	          if (!doubleClicked){
	            doubleClicked = true;
	            btnRefresh.doClick();
	          }
	        }
	      }, 1000);
       if (schedulerList.getParent() != null) {
         schedulerList.getParent().revalidate();
         schedulerList.getParent().repaint();
       }
		   
     }

   });
		
		add(btnRefresh);
		add(schedulerList);
		add(schedulerTable);
	}
}
