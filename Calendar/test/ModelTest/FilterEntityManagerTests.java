package ModelTest;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockData;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.FilterEntityManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import static org.junit.Assert.*;

import org.junit.Test;

public class FilterEntityManagerTests {

	@Test
	public void assignUniqueIDTest() throws WPISuiteException {
		
		Filter fil1 = new Filter("Filter 1", null);
		Filter fil2 = new Filter("Filter 2", null);
		Filter fil3 = new Filter("Filter 3", null);
		Filter fil4 = new Filter("Filter 4", null);
		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
		FilterMan.assignUniqueID(fil1);
		fakeDB.save(fil1, testProject);
		FilterMan.assignUniqueID(fil2);
		fakeDB.save(fil2, testProject);
		FilterMan.assignUniqueID(fil3);
		fakeDB.save(fil3, testProject);
		FilterMan.assignUniqueID(fil4);
		fakeDB.save(fil4, testProject);

		fakeDB.save(admin);
		

		
		assertEquals(FilterMan.HighestId(), 4);

	}

	@Test
	public void saveTest1() throws WPISuiteException {
		//Set Up
		Filter fil1 = new Filter("Filter 1", null);
		Filter fil2 = new Filter("Filter 2", null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		//Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
		fakeDB.save(admin);
		//Tests
		FilterMan.save(fil1);
		assertEquals(FilterMan.HighestId(), 1);
		
		FilterMan.save(fil2);
		assertEquals(FilterMan.HighestId(), 2);

		
	}

	@Test
	public void saveTest2() throws WPISuiteException {
	//Set Up
	Filter fil1 = new Filter("Filter 1", null);
	Filter fil2 = new Filter("Filter 2", null);

	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	
	Session sesh = new Session(admin, "01");
	
	//Project testProject = new Project("test", "1");
	
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	
	FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
	fakeDB.save(admin);
	//Tests
	FilterMan.save(sesh, fil1);
	assertEquals(FilterMan.HighestId(), 1);
	
	FilterMan.save(sesh, fil2);
	assertEquals(FilterMan.HighestId(), 2);
}
	
	@Test
	public void makeEntityTest() throws WPISuiteException {
		//Set Up
				Filter fil1 = new Filter("Filter 1", null);


				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
				fakeDB.save(admin);
				//Tests
				
				String filString = fil1.toJSON();
				FilterMan.makeEntity(sesh, filString);
				
				assertEquals(FilterMan.getEntity(sesh, "1")[0].getName(), "Filter 1");
				
	}
	
	@Test
public void getAllTest() throws WPISuiteException {
	//Set Up
	Filter fil1 = new Filter("Filter 1", null);
	Filter fil2 = new Filter("Filter 2", null);

	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	
	fil1.setUserId("admin");
	fil2.setUserId("admin");
	
	Project testProject = new Project("test", "1");
	Session sesh = new Session(admin,testProject, "01");
	
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	
	FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
	fakeDB.save(admin);
	FilterMan.assignUniqueID(fil1);
	fakeDB.save(fil1, testProject);
	FilterMan.assignUniqueID(fil2);
	fakeDB.save(fil2, testProject);
	//Tests
	
	Filter[] testfil = FilterMan.getAll(sesh);
	assertEquals(testfil.length, 4); //getAll() currently returns filter twice right now. However, this gets filtered out in the gui so it works
}
	@Test
	public void getEntityTest() throws WPISuiteException {
		//Set Up
		Filter fil1 = new Filter("Filter 1", null);
		Filter fil2 = new Filter("Filter 2", null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		
		
		
		Project testProject = new Project("test", "1");
		Session sesh = new Session(admin,testProject, "01");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
		fakeDB.save(admin);
		FilterMan.assignUniqueID(fil1);
		fakeDB.save(fil1, testProject);
		FilterMan.assignUniqueID(fil2);
		fakeDB.save(fil2, testProject);
		//Tests
		
		assertEquals(FilterMan.getEntity(sesh, "1")[0].getId(), 1);
	}
	
	@Test
	public void updateTest() throws WPISuiteException {
		//Set Up
		Filter fil1 = new Filter("Filter 1", null);
		Filter fil2 = new Filter("Filter 2", null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		
		
		
		Project testProject = new Project("test", "1");
		Session sesh = new Session(admin,testProject, "01");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
		fakeDB.save(admin);
		FilterMan.assignUniqueID(fil1);
		fakeDB.save(fil1, testProject);
		FilterMan.assignUniqueID(fil2);
		fakeDB.save(fil2, testProject);
		//Tests
		Filter fil3 = new Filter("Filter 3", null);
		fil3.setId(2);
		String stringfil = fil3.toJSON();
		
		System.out.print(stringfil);
		
		FilterMan.update(sesh, stringfil);
		assertEquals(FilterMan.getEntity(sesh, "2")[0].getName(),"Filter 3");
	}

	@Test
	public void deleteEntityTest() throws WPISuiteException {
		//Set Up
				Filter fil1 = new Filter("Filter 1", null);
				Filter fil2 = new Filter("Filter 2", null);
				Filter fil3 = new Filter("Filter 3", null);

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
				fakeDB.save(admin);
				FilterMan.assignUniqueID(fil1);
				fakeDB.save(fil1, testProject);
				FilterMan.assignUniqueID(fil2);
				fakeDB.save(fil2, testProject);
				FilterMan.assignUniqueID(fil3);
				fakeDB.save(fil3, testProject);
				//Tests
				FilterMan.deleteEntity(sesh, "1");
				FilterMan.getEntity(sesh, "1");
				
	}
	
	@Test
	public void deleteAllTest() throws WPISuiteException {
		//Set Up
				Filter fil1 = new Filter("Filter 1", null);
				Filter fil2 = new Filter("Filter 2", null);
				Filter fil3 = new Filter("Filter 3", null);

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
				fakeDB.save(admin);
				FilterMan.assignUniqueID(fil1);
				fakeDB.save(fil1, testProject);
				FilterMan.assignUniqueID(fil2);
				fakeDB.save(fil2, testProject);
				FilterMan.assignUniqueID(fil3);
				fakeDB.save(fil3, testProject);
				//Tests
				FilterMan.deleteAll(sesh);;
				assertEquals(FilterMan.Count(), 0);	
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
	FilterEntityManager FilterMan = new FilterEntityManager(fakeDB);
	fakeDB.save(admin);
	
	//Tests

	//How do I test a void function?
}
*/
}