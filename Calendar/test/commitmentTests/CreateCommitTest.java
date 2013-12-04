package commitmentTests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;

public class CreateCommitTest {

	@Before
	public void DoSetUp() {
		
	}
	
	@Test
	public void TestAddCommitmentController() {
		Commitment commit1 = new Commitment("Test Commitment 1", new GregorianCalendar(), "Test Description 1");
		CommitmentsModel cm = CommitmentsModel.getInstance();
		AddCommitmentController acc = new AddCommitmentController(cm, null);
		acc.addCommitmentToDatabase(commit1);
		fail("F");
	}

}
