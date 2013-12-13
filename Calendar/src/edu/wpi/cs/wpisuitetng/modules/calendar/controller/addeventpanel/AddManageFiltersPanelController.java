package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;

public class AddManageFiltersPanelController implements ActionListener {

	JTabbedPane tabbedPane;


	public static AddManageFiltersPanelController instance;

	public AddManageFiltersPanelController( ) {
	}

	public static AddManageFiltersPanelController getInstance() {
		if (instance == null) {
			instance = new AddManageFiltersPanelController();
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
			((JButton)e.getSource()).setEnabled(false);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
					  tabbedPane.removeTabAt(AddEventPanelController.getInstance().getTabbedPane().getSelectedIndex());
				  }
				}, 1000);	
		}
	}

}
