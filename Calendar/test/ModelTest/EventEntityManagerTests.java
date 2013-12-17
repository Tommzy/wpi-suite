package ModelTest;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockData;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.EventEntityManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import static org.junit.Assert.*;

import org.junit.Test;

public class EventEntityManagerTests {

	@Test
	public void assignUniqueIDTest() throws WPISuiteException {
		
		Event ev1 = new Event("Event 1", null, null, "no where", "Event 1",null);
		Event ev2 = new Event("Event 2", null, null, "no where", "Event 2",null);
		Event ev3 = new Event("Event 3", null, null, "no where", "Event 3",null);
		Event ev4 = new Event("Event 4", null, null, "no where", "Event 4",null);
		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		EventEntityManager evEntMan = new EventEntityManager(fakeDB);
		evEntMan.assignUniqueID(ev1);
		fakeDB.save(ev1, testProject);
		evEntMan.assignUniqueID(ev2);
		fakeDB.save(ev2, testProject);
		evEntMan.assignUniqueID(ev3);
		fakeDB.save(ev3, testProject);
		evEntMan.assignUniqueID(ev4);
		fakeDB.save(ev4, testProject);

		fakeDB.save(admin);
		

		
		assertEquals(evEntMan.HighestId(), 4);

	}

	@Test
	public void saveTest1() throws WPISuiteException {
		//Set Up
		Event ev1 = new Event("Event 1", null, null, "no where", "Event 1",null);
		Event ev2 = new Event("Event 2", null, null, "no where", "Event 2",null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		//Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		EventEntityManager evEntMan = new EventEntityManager(fakeDB);
		fakeDB.save(admin);
		//Tests
		evEntMan.save(ev1);
		assertEquals(evEntMan.HighestId(), 1);
		
		evEntMan.save(ev2);
		assertEquals(evEntMan.HighestId(), 2);

		
	}

	@Test
	public void saveTest2() throws WPISuiteException {
	//Set Up
	Event ev1 = new Event("Event 1", null, null, "no where", "Event 1",null);
	Event ev2 = new Event("Event 2", null, null, "no where", "Event 2",null);

	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	
	Session sesh = new Session(admin, "01");
	
	//Project testProject = new Project("test", "1");
	
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	
	EventEntityManager evEntMan = new EventEntityManager(fakeDB);
	fakeDB.save(admin);
	//Tests
	evEntMan.save(sesh, ev1);
	assertEquals(evEntMan.HighestId(), 1);
	
	evEntMan.save(sesh, ev2);
	assertEquals(evEntMan.HighestId(), 2);
}
	
	@Test
	public void makeEntityTest() throws WPISuiteException {
		//Set Up
				Event ev1 = new Event("Event 1", null, null, "no where", "Event 1",null);


				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				EventEntityManager evEntMan = new EventEntityManager(fakeDB);
				fakeDB.save(admin);
				//Tests
				
				String evString = ev1.toJSON();
				evEntMan.makeEntity(sesh, evString);
				
				assertEquals(evEntMan.getEntity(sesh, "1")[0].getName(), "Event 1");
				
	}
	
	@Test
public void getAllTest() throws WPISuiteException {
	//Set Up
	Event ev1 = new Event("Event 1", null, null, "no where", "Event 1");
	Event ev2 = new Event("Event 2", null, null, "no where", "Event 2");

	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	
	ev1.setUsername("admin");
	ev2.setUsername("admin");
	
	
	
	Project testProject = new Project("test", "1");
	Session sesh = new Session(admin,testProject, "01");
	
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	
	EventEntityManager evEntMan = new EventEntityManager(fakeDB);
	fakeDB.save(admin);
	evEntMan.assignUniqueID(ev1);
	fakeDB.save(ev1, testProject);
	evEntMan.assignUniqueID(ev2);
	fakeDB.save(ev2, testProject);
	//Tests
	
	Event[] testev = evEntMan.getAll(sesh);
	assertEquals(testev.length, 2);
}
	
	@Test
	public void getEntityTest() throws WPISuiteException {
		//Set Up
		Event ev1 = new Event("Event 1", null, null, "no where", "Event 1",null);
		Event ev2 = new Event("Event 2", null, null, "no where", "Event 2",null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		
		
		
		Project testProject = new Project("test", "1");
		Session sesh = new Session(admin,testProject, "01");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		EventEntityManager evEntMan = new EventEntityManager(fakeDB);
		fakeDB.save(admin);
		evEntMan.assignUniqueID(ev1);
		fakeDB.save(ev1, testProject);
		evEntMan.assignUniqueID(ev2);
		fakeDB.save(ev2, testProject);
		//Tests
		
		assertEquals(evEntMan.getEntity(sesh, "1")[0].getId(), 1);
	}
	
	@Test
	public void updateTest() throws WPISuiteException {
		//Set Up
		Event ev1 = new Event("Event 1", null, null, "no where", "Event 1",null);
		Event ev2 = new Event("Event 2", null, null, "no where", "Event 2",null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		
		
		
		Project testProject = new Project("test", "1");
		Session sesh = new Session(admin,testProject, "01");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		EventEntityManager evEntMan = new EventEntityManager(fakeDB);
		fakeDB.save(admin);
		evEntMan.assignUniqueID(ev1);
		fakeDB.save(ev1, testProject);
		evEntMan.assignUniqueID(ev2);
		fakeDB.save(ev2, testProject);
		//Tests
		Event ev3 = new Event("Event 3", null, null, "no where", "Event 3",null);
		ev3.setId(2);
		String stringev = ev3.toJSON();
		
		System.out.print(stringev);
		
		evEntMan.update(sesh, stringev);
		assertEquals(evEntMan.getEntity(sesh, "2")[0].getDescription(),"Event 3");
	}

	@Test
	public void deleteEntityTest() throws WPISuiteException {
		//Set Up
				Event ev1 = new Event("Event 1", null, null, "no where", "Event 1",null);
				Event ev2 = new Event("Event 2", null, null, "no where", "Event 2",null);
				Event ev3 = new Event("Event 3", null, null, "no where", "Event 3",null);

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				EventEntityManager evEntMan = new EventEntityManager(fakeDB);
				fakeDB.save(admin);
				evEntMan.assignUniqueID(ev1);
				fakeDB.save(ev1, testProject);
				evEntMan.assignUniqueID(ev2);
				fakeDB.save(ev2, testProject);
				evEntMan.assignUniqueID(ev3);
				fakeDB.save(ev3, testProject);
				//Tests
				evEntMan.deleteEntity(sesh, "1");
				evEntMan.getEntity(sesh, "1");
				
	}
	
	@Test
	public void deleteAllTest() throws WPISuiteException {
		//Set Up
				Event ev1 = new Event("Event 1", null, null, "no where", "Event 1",null);
				Event ev2 = new Event("Event 2", null, null, "no where", "Event 2",null);
				Event ev3 = new Event("Event 3", null, null, "no where", "Event 3",null);

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				EventEntityManager evEntMan = new EventEntityManager(fakeDB);
				fakeDB.save(admin);
				evEntMan.assignUniqueID(ev1);
				fakeDB.save(ev1, testProject);
				evEntMan.assignUniqueID(ev2);
				fakeDB.save(ev2, testProject);
				evEntMan.assignUniqueID(ev3);
				fakeDB.save(ev3, testProject);
				//Tests
				evEntMan.deleteAll(sesh);;
				assertEquals(evEntMan.Count(), 0);	
	}

	/*	
  	@Test
	public void ensureRoleTest() throws WPISuiteException {
	//Set Up
	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	Session sesh = new Session(admin, "01");
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	EventEntityManager evEntMan = new EventEntityManager(fakeDB);
	fakeDB.save(admin);
	
	//Tests

	//How do I test a void function?
}
*/
}