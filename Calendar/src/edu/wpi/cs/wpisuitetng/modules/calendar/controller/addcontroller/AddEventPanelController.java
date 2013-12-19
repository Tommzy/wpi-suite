package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class AddEventPanelController implements ActionListener {

	JTabbedPane tabbedPane;
	JButton btnSubmit;
	JButton btnCancel;
	
	public static AddEventPanelController instance;


	
	public static AddEventPanelController getInstance() {
		if (instance == null) {
			instance = new AddEventPanelController();
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
			final Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					tabbedPane.removeTabAt(AddEventPanelController.getInstance().getTabbedPane().getSelectedIndex());
				}
			}, 1000);

		}
	}

}
