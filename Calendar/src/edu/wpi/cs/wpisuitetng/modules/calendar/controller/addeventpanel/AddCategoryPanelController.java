package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class AddCategoryPanelController implements ActionListener {

	JTabbedPane tabbedPane;

	public static AddCategoryPanelController instance;

	public AddCategoryPanelController( ) {
	}

	public static AddCategoryPanelController getInstance() {
		if (instance == null) {
			instance = new AddCategoryPanelController();
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
					tabbedPane.removeTabAt(AddCategoryPanelController.getInstance().getTabbedPane().getSelectedIndex());
				}
			}, 1000);
			
		}

	}

}
