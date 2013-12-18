package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
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
  JComboBox<Invitation> schedulerCB;
  
	public SchedulerList(MigLayout miglayout) {
		// mig layout for this panel that we will add our things to
	  JPanel contentPanel = new JPanel(miglayout);

	  // List for displaying in the GUI

		
		// try to get the invitations in the DB currently
		GetInvitationController getController = new GetInvitationController();
		try{
		  getController.actionPerformed(null);
		}
		catch (Exception e) {
		  
		}
		// get the local list model
		List<Invitation> invites = InvitationModel.getInstance().getAllInvitation();
		
		// list that we will display in the JList
    DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(int i=0; i<invites.size(); i++){
		  // grab the names of the invites
		  listModel.addElement(invites.get(i).getName().toString());
      System.out.println(invites.get(i).getName().toString());
      
		}
		//listModel.add
//		for(int i = 0; i < listModel.size(); i++){
//		  System.out.println(listModel.get(i));
//		}
		
		System.out.println("adding");
		// put in the names of the invites into the JList to display
		schedulerCB = new JComboBox<Invitation>( InvitationModel.getInstance().getInvitationArray());
		System.out.println(InvitationModel.getInstance().getInvitationArray().length);


		// Add the scrollbar to the content panel
		contentPanel.add(schedulerCB);

		// add this panel to the super
		this.add(contentPanel);
		if(getParent() != null){
  		getParent().revalidate();
      getParent().repaint();
		}
	}

}
