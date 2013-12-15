package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class SchedulerList extends JPanel {
	
	public SchedulerList() {
		JList<String> schedulerList = new JList<String>();
		schedulerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane listScroller = new JScrollPane(schedulerList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		Dimension dimension = new Dimension();
		dimension.setSize(400, 400);
		schedulerList.setMinimumSize(dimension);
		
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Scheduler 1");
		listModel.addElement("Scheduler 2");
		listModel.addElement("Scheduler 3");
		listModel.addElement("Scheduler 4");
		listModel.addElement("Scheduler 5");
		listModel.addElement("Scheduler 6");

		schedulerList = new JList(listModel);
		

		add(schedulerList);
		
	}

}
