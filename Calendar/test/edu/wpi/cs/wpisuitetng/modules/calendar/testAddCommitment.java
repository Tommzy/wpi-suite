package edu.wpi.cs.wpisuitetng.modules.calendar;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import net.miginfocom.swing.MigLayout;

import org.junit.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CommitmentListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

public class testAddCommitment {
	
	private CommitmentListModel model = new CommitmentListModel().getInstance();
	private AddCommitmentPanel viewCommitment = new AddCommitmentPanel(new MigLayout()); 
	Commitment testCommit1 ;
	AddCommitmentController controller;
	
	@Before
	public void setup(){
		controller = new AddCommitmentController(model, viewCommitment);
//		testCommit1 = new Commitment("First test",new GregorianCalendar(1992,8,19,23,4),"Success ><!");
		controller.addTestToDatabase();	
	}
	
	@Test
	public void test1() {
//		Network.initNetwork(new MockNetwork());
//		Network.getInstance().setDefaultNetworkConfiguration(
//				new NetworkConfiguration("http://wpisuitetng"));
		assertNotNull(controller.testReturn());
	}

}
