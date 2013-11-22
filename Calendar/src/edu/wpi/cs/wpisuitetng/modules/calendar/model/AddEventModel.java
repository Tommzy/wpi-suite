package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;

public class AddEventModel {
	JTabbedPane mainTab;
	public AddEventModel(JTabbedPane tabbedPane) {
		this.mainTab = tabbedPane;
	}
	
	public void getAddEventModel() {
		mainTab.add(new AddEventPanel(new MigLayout(), mainTab));
		mainTab.setTitleAt(1, "New Event");
	}
}
