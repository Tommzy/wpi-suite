package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetInvitationController;
import edu.wpi.cs.wpisuitetng.modules.calendar.invitation.InvitationModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SchedulerList extends JPanel {

	public SchedulerList(MigLayout miglayout) {
		JPanel contentPanel = new JPanel(miglayout);

		JList<String> schedulerList = new JList<String>();
		schedulerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		schedulerList.setMinimumSize(new Dimension(150, 100));
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		GetInvitationController getController = new GetInvitationController();
		try{
		  getController.actionPerformed(null);
		}
		catch (Exception e) {
		  
		}
		List<Invitation> invites = InvitationModel.getInstance().getAllInvitation();
		
		
		System.out.println("All invitations: \n");
		for(int i=0; i<invites.size(); i++){
			//listModel.addElement(invites.get(i).getName());
			System.out.println(invites.get(i).getName());
		}
		
		
		
		
		schedulerList = new JList<String>(listModel);

		JScrollPane listScroller = new JScrollPane(schedulerList);
		listScroller.setPreferredSize(new Dimension(150, 100));


		contentPanel.add(listScroller);

		this.add(contentPanel);

	}

}
