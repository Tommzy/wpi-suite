package edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddSchedulerPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddManageFiltersPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddSchedulerPanel;
//import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddManageFiltersPanel;

public class ToolbarController implements ActionListener {

	private static ToolbarController instance;
	private JButton addEventButton;
	private JButton addCommitmentButton;
	private JButton addSchedulerButton;
	private JButton manageFiltersButton;


	public ToolbarController() {

	}

	public JButton getAddEventButton() {
		return addEventButton;
	}

	public JButton getAddCommitmentButton() {
		return addCommitmentButton;
	}

	public JButton getAddSchedulerButton() {
		return addSchedulerButton;
	}

	public void setAddEventButton(JButton addEventButton) {
		this.addEventButton = addEventButton;
	}

	public void setAddCommitmentButton(JButton addCommitmentButton) {
		this.addCommitmentButton = addCommitmentButton;
	}

	public void setAddSchedulerButton(JButton addSchedulerButton) {
		this.addSchedulerButton = addSchedulerButton;
	}

	public JButton getManageFiltersButton() {
		return manageFiltersButton;
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
			AddCommitmentPanel CommitmentPanel = new AddCommitmentPanel(new MigLayout());
			AddCommitmentPanelController.getInstance().getTabbedPane().add(CommitmentPanel);
			AddCommitmentPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Commitment");
			AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
			CommitmentPanel.initiateFocus();
		}

		if (e.getSource() == addSchedulerButton) {
			AddSchedulerPanel newSchedulerPanel = new AddSchedulerPanel(new MigLayout());
			AddSchedulerPanelController.getInstance().getTabbedPane().add(newSchedulerPanel);
			AddSchedulerPanelController.getInstance().getTabbedPane().setTitleAt(AddSchedulerPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Scheduler");
			AddSchedulerPanelController.getInstance().getTabbedPane().setSelectedIndex(AddSchedulerPanelController.getInstance().getTabbedPane().getTabCount() - 1);
			newSchedulerPanel.initiateFocus();
		}

		if (e.getSource() == manageFiltersButton) {
			//	AddManageFiltersPanel ManageFiltersPanel = new AddManageFiltersPanel(new MigLayout());
			//	AddManageFiltersPanelController.getInstance().getTabbedPane().add(ManageFiltersPanel);
			//	AddManageFiltersPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Manage Filters");
			//	AddManageFiltersPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
			//   ManageFiltersPanel.initiateFocus();  
		}

	}

	public static ToolbarController getInstance() {
		if (instance == null) {
			instance = new ToolbarController();
		}

		return instance;
	}
}
