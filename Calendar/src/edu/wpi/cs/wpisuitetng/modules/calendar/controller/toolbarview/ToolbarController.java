package edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.AddEventModel;

public class ToolbarController implements ActionListener {
	AddEventModel addEventModel;
	
	public ToolbarController(AddEventModel addEventModel) {
		this.addEventModel = addEventModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		addEventModel.getAddEventModel();
		//JOptionPane.showMessageDialog(null, "HellO");
	}

}
