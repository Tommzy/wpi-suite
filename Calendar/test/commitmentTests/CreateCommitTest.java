package commitmentTests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockNetwork;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

public class CreateCommitTest {

	@Before
	public void DoSetUp() {
		Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
		new NetworkConfiguration("http://wpisuitetng"));
	}
	
	/*@Test
	public void TestAddCommitmentController() {
		Commitment commit1 = new Commitment("Test Commitment 1", new GregorianCalendar(), "Test Description 1");
		CommitmentsModel cm = CommitmentsModel.getInstance();
		AddCommitmentController acc = new AddCommitmentController(cm, null);
		acc.addCommitmentToDatabase(commit1);
		
		GetCommitmentController con= new GetCommitmentController();
		con.retrieveCommitments();
		
		CommitmentsModel comMod = CommitmentsModel.getInstance();
		assertTrue(acc.getAdded());
		//assertEquals(comMod.getSize(), 1);
		
		
		//fail("F");
	}
	*/

}
