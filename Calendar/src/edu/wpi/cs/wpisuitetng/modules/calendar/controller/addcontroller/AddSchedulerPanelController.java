package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class AddSchedulerPanelController implements ActionListener {

	JTabbedPane tabbedPane;
	JButton btnSubmit;
	JButton btnCancel;

	/**
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}

	/**
	 * @param btnCancel the btnCancel to set
	 */
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public static AddSchedulerPanelController instance;

	public AddSchedulerPanelController() {
	}

	public static AddSchedulerPanelController getInstance() {
		if (instance == null) {
			instance = new AddSchedulerPanelController();
		}

		return instance;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().getClass().equals(JButton.class)) {
			// Close the tab a second later for calendar view to refresh. Avoid showing a flash to users when the calendar refreshes. 
			tabbedPane.removeTabAt(AddSchedulerPanelController.getInstance().getTabbedPane().getSelectedIndex());
		}
	}

}
