package ModelTest;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class CommitmentsModelTests {

	
	
	@Test
	public void addCommitmentTest() {
		//Set Up
		
		Commitment testCom1 = new Commitment("Test Commitment", null, "Test Commitment");
		Commitment testCom2 = new Commitment("Copy Stuff", null ,"More CopyStuff");
		
		CommitmentsModel model = CommitmentsModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addCommitment(testCom1);
		model.addCommitment(testCom2);
		
		List<Commitment> comList = model.getAllCommitment();
		
		assertEquals(comList.size(), 2);
		
	}
	
	@Test
	public void removeCommitmentTest() {
		//Set Up
		
		Commitment testCom1 = new Commitment("Test Commitment", null, "Test Commitment");
		testCom1.setId(1);
		Commitment testCom2 = new Commitment("Copy Stuff", null ,"More CopyStuff");
		testCom2.setId(2);
		
		CommitmentsModel model = CommitmentsModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addCommitment(testCom1);
		model.addCommitment(testCom2);
		
		model.removeCommitment(2);
		
		List<Commitment> comList = model.getAllCommitment();
		
		assertEquals(comList.size(), 1);
		
	}
	
	@Test
	public void emptyModelTest() {
		//Set Up
		
				Commitment testCom1 = new Commitment("Test Commitment", null, "Test Commitment");
				Commitment testCom2 = new Commitment("Copy Stuff", null ,"More CopyStuff");
				
				CommitmentsModel model = CommitmentsModel.getInstance();

				
				//Tests
				model.addCommitment(testCom1);
				model.addCommitment(testCom2);
				
				List<Commitment> comList = model.getAllCommitment();
				model.emptyModel();
				assertEquals(comList.size(), 0);
				
	}
	
	
	
	@Test
	public void getSizeTests() {
		//Set Up
		
		Commitment testCom1 = new Commitment("Test Commitment", null, "Test Commitment");
		Commitment testCom2 = new Commitment("Copy Stuff", null ,"More CopyStuff");
		
		CommitmentsModel model = CommitmentsModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addCommitment(testCom1);
		model.addCommitment(testCom2);
		

		assertEquals(model.getSize(), 2);
		
	}
	
	@Test
	public void getCommitmentTest() {
		//Set Up
		
		Commitment testCom1 = new Commitment("Test Commitment", null, "Test Commitment");
		testCom1.setId(1);
		Commitment testCom2 = new Commitment("Copy Stuff", null ,"More CopyStuff");
		testCom2.setId(2);
		
		CommitmentsModel model = CommitmentsModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addCommitment(testCom1);
		model.addCommitment(testCom2);
		
		assertEquals(model.getCommitment(2), testCom2);
		
	}

}
