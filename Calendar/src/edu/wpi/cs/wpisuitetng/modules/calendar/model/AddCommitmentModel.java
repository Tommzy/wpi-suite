package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;

public class AddCommitmentModel {
  JTabbedPane mainTab;
  public JTabbedPane getMainTab() {
    return mainTab;
  }

  public void setMainTab(JTabbedPane mainTab) {
    this.mainTab = mainTab;
  }

  public AddCommitmentModel(JTabbedPane tabbedPane) {
    this.mainTab = tabbedPane;
  }
  
  public void getAddEventModel() {
    mainTab.add(new AddEventPanel(new MigLayout(), mainTab));
    mainTab.setTitleAt(2, "New Commitment");
  }
}
