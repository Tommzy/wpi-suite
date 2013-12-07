package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;

public class AddCommitmentPanelController implements ActionListener {

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

	public static AddCommitmentPanelController instance;

	public AddCommitmentPanelController( ) {
	}

	public static AddCommitmentPanelController getInstance() {
		if (instance == null) {
			instance = new AddCommitmentPanelController();
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
		MainCalendarController.getInstance().updateAll();
		if (e.getSource().getClass().equals(JButton.class)) {
			tabbedPane.removeTabAt(AddEventPanelController.getInstance().getTabbedPane().getSelectedIndex());
		}

//		if (e.getSource() == btnSubmit) {
//			tabbedPane.removeTabAt(AddEventPanelController.getInstance().getTabbedPane().getSelectedIndex());
//		}
//		else if (e.getSource() == btnCancel) {
//			System.out.println(AddEventPanelController.getInstance().getTabbedPane().getSelectedIndex());
//			tabbedPane.removeTabAt(AddEventPanelController.getInstance().getTabbedPane().getSelectedIndex());
//		}

	}

}
