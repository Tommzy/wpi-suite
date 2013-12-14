package edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCategoryPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddManageFiltersPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCategoryPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
//import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddManageFiltersPanel;

public class ToolbarController implements ActionListener {

   private static ToolbarController instance;
   private JButton addEventButton;
   private JButton addCommitmentButton;
   private JButton manageFiltersButton;
   private JButton manageCategoryButton;

   public ToolbarController() {

   }

   /**
    * @param manageCategoryButton the manageCategoryButton to set
    */
   public void setManageCategoryButton(JButton manageCategoryButton) {
	   this.manageCategoryButton = manageCategoryButton;
   }

  public JButton getAddEventButton() {
    return addEventButton;
  }

  public JButton getAddCommitmentButton() {
    return addCommitmentButton;
  }

  public void setAddEventButton(JButton addEventButton) {
    this.addEventButton = addEventButton;
  }

  public void setAddCommitmentButton(JButton addCommitmentButton) {
    this.addCommitmentButton = addCommitmentButton;
  }
  
  public JButton getManageFiltersButton() {
		return manageFiltersButton;
  }
  
  public JButton getManageCategoryButton() {
	  return manageCategoryButton;
  }

  public void setManageFiltersButton(JButton manageFiltersButton) {
		this.manageFiltersButton = manageFiltersButton;
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
	      AddCommitmentPanel commitmentPanel = new AddCommitmentPanel(new MigLayout());
	      AddCommitmentPanelController.getInstance().getTabbedPane().add(commitmentPanel);
	      AddCommitmentPanelController.getInstance().getTabbedPane().setTitleAt(AddCommitmentPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Commitment");
	      AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddCommitmentPanelController.getInstance().getTabbedPane().getTabCount() - 1);
	      commitmentPanel.initiateFocus();
      }
    if (e.getSource() == manageFiltersButton) {
//    	AddManageFiltersPanel ManageFiltersPanel = new AddManageFiltersPanel(new MigLayout());
//    	AddManageFiltersPanelController.getInstance().getTabbedPane().add(ManageFiltersPanel);
//    	AddManageFiltersPanelController.getInstance().getTabbedPane().setTitleAt(AddManageFiltersPanel.getInstance().getTabbedPane().getTabCount() - 1, "Manage Filters");
//    	AddManageFiltersPanelController.getInstance().getTabbedPane().setSelectedIndex(AddManageFiltersPanel.getInstance().getTabbedPane().getTabCount() - 1);
//	    ManageFiltersPanel.initiateFocus();  
    }
    if (e.getSource() == manageCategoryButton) {
    	AddCategoryPanel categoryPanel = new AddCategoryPanel();
    	AddCategoryPanelController.getInstance().getTabbedPane().add(categoryPanel);
    	AddCategoryPanelController.getInstance().getTabbedPane().setTitleAt(AddCategoryPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Manage Category");
    	AddCategoryPanelController.getInstance().getTabbedPane().setSelectedIndex(AddCategoryPanelController.getInstance().getTabbedPane().getTabCount() - 1);
    }

  }

  public static ToolbarController getInstance() {
    if (instance == null) {
      instance = new ToolbarController();
    }

    return instance;
  }
}
