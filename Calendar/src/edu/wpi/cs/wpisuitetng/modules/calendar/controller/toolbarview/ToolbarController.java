package edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.ManageFiltersPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.ManageFiltersPanel;

public class ToolbarController implements ActionListener {

   private static ToolbarController instance;
   private JButton addEventButton;
   private JButton addCommitmentButton;
   private JButton ManageFiltersButton;


  public ToolbarController() {

  }

  public JButton getAddEventButton() {
    return addEventButton;
  }

  public JButton getAddCommitmentButton() {
    return addCommitmentButton;
  }
  
  public JButton getManageFiltersButton() {
	return ManageFiltersButton;
  }

  public void setManageFiltersButton(JButton manageFiltersButton) {
	ManageFiltersButton = manageFiltersButton;
  }

  public void setAddEventButton(JButton addEventButton) {
    this.addEventButton = addEventButton;
  }

  public void setAddCommitmentButton(JButton addCommitmentButton) {
    this.addCommitmentButton = addCommitmentButton;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addEventButton) {
    	AddEventPanel newEventPanel = new AddEventPanel(new MigLayout());
        AddEventPanelController.getInstance().getTabbedPane().add(newEventPanel);
        AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Event");
        

        AddEventPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
        newEventPanel.initiateFocus();
    }

    if (e.getSource() == addCommitmentButton) {
      JTabbedPane pane = AddCommitmentPanelController.getInstance().getTabbedPane();
      AddCommitmentPanel newCommitmentPanel = new AddCommitmentPanel(new MigLayout());
        AddCommitmentPanelController.getInstance().getTabbedPane().add(newCommitmentPanel);
        AddCommitmentPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Commitment");

        AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
        newCommitmentPanel.initiateFocus();
      }
    
    if (e.getSource() == ManageFiltersButton) {
        JTabbedPane pane = ManageFiltersPanelController.getInstance().getTabbedPane();
        ManageFiltersPanel ManageFiltersPanel = new ManageFiltersPanel(new MigLayout());
        ManageFiltersPanelController.getInstance().getTabbedPane().add(ManageFiltersPanel);
        ManageFiltersPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Manage Filters");

          AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
          ManageFiltersPanel.initiateFocus();
        }
  }

  public static ToolbarController getInstance() {
    if (instance == null) {
      instance = new ToolbarController();
    }

    return instance;
  }


}
