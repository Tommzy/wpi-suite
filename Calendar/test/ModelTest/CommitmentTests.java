package ModelTest;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class CommitmentTests {

	@Test
	public void getAndSetTests() {
		//Set Up
		
		Commitment testCom = new Commitment("Test Commitment", null, "Test Commitment",null);
		Project testProj = new Project(null, null);
		User testUser = new User(null, null, null, 0);
		GregorianCalendar testCal1 = new GregorianCalendar();
		
		//Tests
		// Description
		testCom.setDescription("I changed the description");
		assertEquals(testCom.getDescription(), "I changed the description");
		// Name
		testCom.setName("I changed the name");
		assertEquals(testCom.getName(), "I changed the name");
		// ID
		testCom.setId(2);
		assertEquals(testCom.getId(), 2);
		// TeamCommitment
		testCom.setTeamCommitment(false);
		assertEquals(testCom.isTeamCommitment(), false);
		// Project
		testCom.setProject(testProj);
		assertSame(testCom.getProject(), testProj);
		// StartTime
		testCom.setStartTime(testCal1);
		assertSame(testCom.getStartTime(), testCal1);
		// Permission
		testCom.setPermission(Permission.WRITE, testUser);
		assertEquals(testCom.getPermission(testUser), Permission.WRITE);
		// Username
		testCom.setUsername("Chuck");
		assertEquals(testCom.getUsername(), "Chuck");
		// Status
		testCom.setStatus(2);
		assertEquals(testCom.getStatus(), 2);
		
	}
	
	@Test
	public void CopyTest() {
		//Set Up
		
		Commitment testCom = new Commitment("Test Commitment", null, "Test Commitment",null);
		Commitment copyCom = new Commitment("Copy Stuff", null ,"More CopyStuff",null);
		
		//Tests
		
		testCom.copy(copyCom);

		
		assertEquals(testCom.getId(), copyCom.getId());
		assertEquals(testCom.getName(), copyCom.getName());
		assertEquals(testCom.getProject(), copyCom.getProject());
		assertEquals(testCom.getStartTime(), copyCom.getStartTime());
		assertEquals(testCom.getUsername(), copyCom.getUsername());
		assertEquals(testCom.getDescription(), copyCom.getDescription());
		
	}
	
	@Test
	public void isTimeStampActiveDuringTest() {
		//Set Up
		
		Commitment testCom = new Commitment("Test Commitment", null, "Test Commitment",null);

		GregorianCalendar cal1 = new GregorianCalendar(2013, 12, 13, 16, 30);
		GregorianCalendar cal2 = new GregorianCalendar(2013, 12, 13, 18, 45);
		GregorianCalendar cal3 = new GregorianCalendar(2013, 12, 13, 16, 45);
		
		//Tests
		
		testCom.setStartTime(cal1);
		assertTrue(testCom.isActiveDuringTimeStamp(cal3));
		assertFalse(testCom.isActiveDuringTimeStamp(cal2));
		
	}
	
	@Test
	public void JSONTests() {
		//Set Up
		
		Commitment testCom = new Commitment("Test Commitment", null, "Test Commitment",null);
		//Tests
		
		String comString = testCom.toJSON();
		System.out.print(comString  + "\n");
		Commitment copyCom = Commitment.fromJSON(comString);

		
		assertEquals(testCom.getId(), copyCom.getId());
		assertEquals(testCom.getName(), copyCom.getName());
		assertEquals(testCom.getProject(), copyCom.getProject());
		assertEquals(testCom.getStartTime(), copyCom.getStartTime());
		assertEquals(testCom.getUsername(), copyCom.getUsername());
		assertEquals(testCom.getDescription(), copyCom.getDescription());
		
	}
	

}
