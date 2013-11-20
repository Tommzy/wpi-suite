package edu.wpi.cs.wpisuitetng.modules.calendar;

import net.miginfocom.swing.MigLayout;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CommitmentListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;

public class Main {
	private CommitmentListModel model = new CommitmentListModel().getInstance();
	private AddCommitmentPanel viewCommitment = new AddCommitmentPanel(new MigLayout()); 
	Commitment testCommit1 ;
	AddCommitmentController controller;

	public Main(){
		controller = new AddCommitmentController(model, viewCommitment);
//		testCommit1 = new Commitment("First test",new GregorianCalendar(1992,8,19,23,4),"Success ><!");
		controller.addTestToDatabase();	
		System.out.println("H");
	}

	public static void main(String[] arg) {
		new Main();
	}
}
