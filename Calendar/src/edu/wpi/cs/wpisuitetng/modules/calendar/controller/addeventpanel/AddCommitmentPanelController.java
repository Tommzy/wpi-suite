package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class AddCommitmentPanelController implements ActionListener {

	JTabbedPane tabbedPane;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().getClass().equals(JButton.class)) {
			// Close the tab a second later for calendar view to refresh. Avoid showing a flash to users when the calendar refreshes. 
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					tabbedPane.removeTabAt(AddCommitmentPanelController.getInstance().getTabbedPane().getSelectedIndex());
				}
			}, 1000);
			
		}

	}

}
