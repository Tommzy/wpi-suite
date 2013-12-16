package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SchedulerList extends JPanel {
	
	public SchedulerList(MigLayout miglayout) {
    JPanel contentPanel = new JPanel(miglayout);

		JList<String> schedulerList = new JList<String>();
		schedulerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    schedulerList.setMinimumSize(new Dimension(150, 100));

    DefaultListModel<String> listModel = new DefaultListModel<String>();
    listModel.addElement("Invitation 1");
    listModel.addElement("Invitation 2");
    listModel.addElement("Invitation 3");
    listModel.addElement("Invitation 4");
    listModel.addElement("Invitation 5");
    listModel.addElement("Invitation 6");
    listModel.addElement("Invitation X");
    schedulerList = new JList<String>(listModel);
    
		JScrollPane listScroller = new JScrollPane(schedulerList);
		listScroller.setPreferredSize(new Dimension(150, 100));


		contentPanel.add(listScroller);
		
		this.add(contentPanel);
		
	}

}
