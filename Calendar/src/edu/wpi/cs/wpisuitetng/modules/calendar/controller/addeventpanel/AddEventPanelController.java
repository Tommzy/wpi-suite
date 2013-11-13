package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class AddEventPanelController implements ActionListener {

	JTabbedPane tabbedPane;
	JButton btnSubmit;
	
	public AddEventPanelController(JTabbedPane tabbedPane, JButton button) {
		this.tabbedPane = tabbedPane;
		btnSubmit = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSubmit) {
			tabbedPane.removeTabAt(1);
		}

	}

}
