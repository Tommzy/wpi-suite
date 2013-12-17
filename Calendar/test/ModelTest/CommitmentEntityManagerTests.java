package ModelTest;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockData;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CommitmentEntityManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import static org.junit.Assert.*;

import org.junit.Test;

public class CommitmentEntityManagerTests {

	@Test
	public void assignUniqueIDTest() throws WPISuiteException {
		
		Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
		Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");
		Commitment com3 = new Commitment("Commitment 3", null, "Commitment 3");
		Commitment com4 = new Commitment("Commitment 4", null, "Commitment 4");
		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
		comEntMan.assignUniqueID(com1);
		fakeDB.save(com1, testProject);
		comEntMan.assignUniqueID(com2);
		fakeDB.save(com2, testProject);
		comEntMan.assignUniqueID(com3);
		fakeDB.save(com3, testProject);
		comEntMan.assignUniqueID(com4);
		fakeDB.save(com4, testProject);

		fakeDB.save(admin);
		

		
		assertEquals(comEntMan.HighestId(), 4);

	}

	@Test
	public void saveTest1() throws WPISuiteException {
		//Set Up
		Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
		Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		//Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
		fakeDB.save(admin);
		//Tests
		comEntMan.save(com1);
		assertEquals(comEntMan.HighestId(), 1);
		
		comEntMan.save(com2);
		assertEquals(comEntMan.HighestId(), 2);

		
	}

	@Test
	public void saveTest2() throws WPISuiteException {
	//Set Up
	Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
	Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");

	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	
	Session sesh = new Session(admin, "01");
	
	//Project testProject = new Project("test", "1");
	
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	
	CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
	fakeDB.save(admin);
	//Tests
	comEntMan.save(sesh, com1);
	assertEquals(comEntMan.HighestId(), 1);
	
	comEntMan.save(sesh, com2);
	assertEquals(comEntMan.HighestId(), 2);
}
	
	@Test
	public void makeEntityTest() throws WPISuiteException {
		//Set Up
				Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");


				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
				fakeDB.save(admin);
				//Tests
				
				String comString = com1.toJSON();
				comEntMan.makeEntity(sesh, comString);
				
				assertEquals(comEntMan.getEntity(sesh, "1")[0].getName(), "Commitment 1");
				
	}
	
	
	@Test
	public void getAllTest() throws WPISuiteException {
	//Set Up
	Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
	com1.setTeamCommitment(false);
	Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");
	com1.setTeamCommitment(true);

	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	
	com1.setUsername("admin");
	com1.setUsername("admin");
	
	Project testProject = new Project("test", "1");
	Session sesh = new Session(admin,testProject, "01");
	
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	
	CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
	fakeDB.save(admin);
	comEntMan.assignUniqueID(com1);
	fakeDB.save(com1, testProject);
	comEntMan.assignUniqueID(com2);
	fakeDB.save(com2, testProject);
	//Tests
	
	Commitment[] testcom = comEntMan.getAll(sesh);
	assertEquals(testcom.length, 2);
}
	
	@Test
	public void getEntityTest() throws WPISuiteException {
		//Set Up
		Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
		Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		
		
		
		Project testProject = new Project("test", "1");
		Session sesh = new Session(admin,testProject, "01");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
		fakeDB.save(admin);
		comEntMan.assignUniqueID(com1);
		fakeDB.save(com1, testProject);
		comEntMan.assignUniqueID(com2);
		fakeDB.save(com2, testProject);
		//Tests
		
		assertEquals(comEntMan.getEntity(sesh, "1")[0].getId(), 1);
	}
	
	@Test
	public void updateTest() throws WPISuiteException {
		//Set Up
		Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
		Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		
		
		
		Project testProject = new Project("test", "1");
		Session sesh = new Session(admin,testProject, "01");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
		fakeDB.save(admin);
		comEntMan.assignUniqueID(com1);
		fakeDB.save(com1, testProject);
		comEntMan.assignUniqueID(com2);
		fakeDB.save(com2, testProject);
		//Tests
		Commitment com3 = new Commitment("Commitment 3", null, "Commitment 3");
		com3.setId(2);
		String stringCom = com3.toJSON();
		
		System.out.print(stringCom);
		
		comEntMan.update(sesh, stringCom);
		assertEquals(comEntMan.getEntity(sesh, "2")[0].getDescription(),"Commitment 3");
	}

	@Test
	public void deleteEntityTest() throws WPISuiteException {
		//Set Up
				Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
				Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");
				Commitment com3 = new Commitment("Commitment 3", null, "Commitment 3");

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
				fakeDB.save(admin);
				comEntMan.assignUniqueID(com1);
				fakeDB.save(com1, testProject);
				comEntMan.assignUniqueID(com2);
				fakeDB.save(com2, testProject);
				comEntMan.assignUniqueID(com3);
				fakeDB.save(com3, testProject);
				//Tests
				comEntMan.deleteEntity(sesh, "1");
				comEntMan.getEntity(sesh, "1");
				
	}
	
	@Test
	public void deleteAllTest() throws WPISuiteException {
		//Set Up
				Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
				Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");
				Commitment com3 = new Commitment("Commitment 3", null, "Commitment 3");

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
				fakeDB.save(admin);
				comEntMan.assignUniqueID(com1);
				fakeDB.save(com1, testProject);
				comEntMan.assignUniqueID(com2);
				fakeDB.save(com2, testProject);
				comEntMan.assignUniqueID(com3);
				fakeDB.save(com3, testProject);
				//Tests
				comEntMan.deleteAll(sesh);;
				assertEquals(comEntMan.Count(), 0);		
	}

/*	@Test
public void ensureRoleTest() throws WPISuiteException {
	//Set Up
	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	Session sesh = new Session(admin, "01");
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
	fakeDB.save(admin);
	
	//Tests

	//How do I test a void function?
}*/
}