package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;

public class AddCommitmentPanelController implements ActionListener {

	JTabbedPane tabbedPane;
//	JButton btnSubmit;
//	JButton btnUpdate;
//	JButton btnCancel;

//	/**
//	 * @return the btnCancel
//	 */
//	public JButton getBtnCancel() {
//		return btnCancel;
//	}
//
//	/**
//	 * @param btnCancel the btnCancel to set
//	 */
//	public void setBtnCancel(JButton btnCancel) {
//		this.btnCancel = btnCancel;
//	}

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

//	public JButton getBtnSubmit() {
//		return btnSubmit;
//	}
//
//	public void setBtnSubmit(JButton btnSubmit) {
//		this.btnSubmit = btnSubmit;
//	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().getClass().equals(JButton.class)) {
			((JButton)e.getSource()).setEnabled(false);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
					  tabbedPane.removeTabAt(AddEventPanelController.getInstance().getTabbedPane().getSelectedIndex());
				  }
				}, 1000);
			
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
