package edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.AddEventModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;

public class ToolbarController implements ActionListener {
	
	private static ToolbarController instance;
	private JButton addEventButton;
	
	public ToolbarController() {
		
	}
	
	public JButton getAddEventButton() {
		return addEventButton;
	}

	public void setAddEventButton(JButton addEventButton) {
		this.addEventButton = addEventButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		System.out.println(addEventButton);
		if (e.getSource() == addEventButton) {
			if (AddEventPanelController.getInstance().getTabbedPane().getTabCount() == 1) {
				AddEventPanelController.getInstance().getTabbedPane().add(new AddCommitmentPanel(new MigLayout()));
				AddEventPanelController.getInstance().getTabbedPane().setTitleAt(1, "Add Event");
				JButton closeButton = new JButton("x");
				closeButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						AddEventPanelController.getInstance().getBtnSubmit().doClick();
						
					}
					
				});
			
				AddEventPanelController.getInstance().getTabbedPane().setSelectedIndex(1);
				
			}
		}
		
	}

	public static ToolbarController getInstance() {
		if (instance == null) {
			instance = new ToolbarController();
		}
		
		return instance;
	}
}
