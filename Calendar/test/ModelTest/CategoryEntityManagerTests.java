package ModelTest;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockData;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CategoryEntityManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import static org.junit.Assert.*;

import org.junit.Test;

public class CategoryEntityManagerTests {

	@Test
	public void assignUniqueIDTest() throws WPISuiteException {
		
		Category cat1 = new Category("Category 1", false, null);
		Category cat2 = new Category("Category 2", false, null);
		Category cat3 = new Category("Category 3", false, null);
		Category cat4 = new Category("Category 4", false, null);
		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
		catEntMan.assignUniqueID(cat1);
		fakeDB.save(cat1, testProject);
		catEntMan.assignUniqueID(cat2);
		fakeDB.save(cat2, testProject);
		catEntMan.assignUniqueID(cat3);
		fakeDB.save(cat3, testProject);
		catEntMan.assignUniqueID(cat4);
		fakeDB.save(cat4, testProject);

		fakeDB.save(admin);
		

		
		assertEquals(catEntMan.HighestId(), 4);

	}

	@Test
	public void saveTest1() throws WPISuiteException {
		//Set Up
		Category cat1 = new Category("Category 1", false, null);
		Category cat2 = new Category("Category 2", false, null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		//Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
		fakeDB.save(admin);
		//Tests
		catEntMan.save(cat1);
		assertEquals(catEntMan.HighestId(), 1);
		
		catEntMan.save(cat2);
		assertEquals(catEntMan.HighestId(), 2);

		
	}

	@Test
public void saveTest2() throws WPISuiteException {
	//Set Up
		Category cat1 = new Category("Category 1", false, null);
		Category cat2 = new Category("Category 2", false, null);

	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	
	Session sesh = new Session(admin, "01");
	
	//Project testProject = new Project("test", "1");
	
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	
	CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
	fakeDB.save(admin);
	//Tests
	catEntMan.save(sesh, cat1);
	assertEquals(catEntMan.HighestId(), 1);
	
	catEntMan.save(sesh, cat2);
	assertEquals(catEntMan.HighestId(), 2);
}
	
	@Test
	public void makeEntityTest() throws WPISuiteException {
		//Set Up
		Category cat1 = new Category("Category 1", false, null);

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
				fakeDB.save(admin);
				//Tests
				
				String catString = cat1.toJSON();
				catEntMan.makeEntity(sesh, catString);
				
				assertEquals(catEntMan.getEntity(sesh, "1")[0].getName(), "Category 1");
				
	}
	
	/*
	@Test
public void getAllTest() throws WPISuiteException {
	//Set Up
	Category cat1 = new Category("Category 1", null, "Category 1");
	Category cat2 = new Category("Category 2", null, "Category 2");

	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	
	
	
	Project testProject = new Project("test", "1");
	Session sesh = new Session(admin,testProject, "01");
	
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	
	CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
	fakeDB.save(admin);
	catEntMan.assignUniqueID(cat1);
	fakeDB.save(cat1, testProject);
	catEntMan.assignUniqueID(cat2);
	fakeDB.save(cat2, testProject);
	//Tests
	
	Category[] testcat = catEntMan.getAll(sesh);
	assertEquals(testcat.length, 2);
}
	*/
	@Test
	public void getEntityTest() throws WPISuiteException {
		//Set Up
		Category cat1 = new Category("Category 1", false, null);
		Category cat2 = new Category("Category 2", false, null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		
		
		
		Project testProject = new Project("test", "1");
		Session sesh = new Session(admin,testProject, "01");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
		fakeDB.save(admin);
		catEntMan.assignUniqueID(cat1);
		fakeDB.save(cat1, testProject);
		catEntMan.assignUniqueID(cat2);
		fakeDB.save(cat2, testProject);
		//Tests
		
		assertEquals(catEntMan.getEntity(sesh, "1")[0].getId(), 1);
	}
	
	@Test
	public void updateTest() throws WPISuiteException {
		//Set Up
		Category cat1 = new Category("Category 1", false, null);
		Category cat2 = new Category("Category 2", false, null);

		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		
		
		
		Project testProject = new Project("test", "1");
		Session sesh = new Session(admin,testProject, "01");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
		fakeDB.save(admin);
		catEntMan.assignUniqueID(cat1);
		fakeDB.save(cat1, testProject);
		catEntMan.assignUniqueID(cat2);
		fakeDB.save(cat2, testProject);
		//Tests
		Category cat3 = new Category("Category 3", false, null);
		cat3.setId(2);
		String stringcat = cat3.toJSON();
		
		System.out.print(stringcat);
		
		catEntMan.update(sesh, stringcat);
		assertEquals(catEntMan.getEntity(sesh, "2")[0].getName(),"Category 3");
	}

	@Test
	public void deleteEntityTest() throws WPISuiteException {
		//Set Up
		Category cat1 = new Category("Category 1", false, null);
		Category cat2 = new Category("Category 2", false, null);
		Category cat3 = new Category("Category 3", false, null);

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
				fakeDB.save(admin);
				catEntMan.assignUniqueID(cat1);
				fakeDB.save(cat1, testProject);
				catEntMan.assignUniqueID(cat2);
				fakeDB.save(cat2, testProject);
				catEntMan.assignUniqueID(cat3);
				fakeDB.save(cat3, testProject);
				//Tests
				catEntMan.deleteEntity(sesh, "1");
				catEntMan.getEntity(sesh, "1");
				
	}
	
	@Test
	public void deleteAllTest() throws WPISuiteException {
		//Set Up
		Category cat1 = new Category("Category 1", false, null);
		Category cat2 = new Category("Category 2", false, null);
		Category cat3 = new Category("Category 3", false, null);

				
				User admin = new User("admin", "admin", "1234", 27);
				admin.setRole(Role.ADMIN);
				
				
				
				Project testProject = new Project("test", "1");
				Session sesh = new Session(admin,testProject, "01");
				
				MockData fakeDB = new MockData(new HashSet<Object>());
				fakeDB = new MockData(new HashSet<Object>());
				
				CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
				fakeDB.save(admin);
				catEntMan.assignUniqueID(cat1);
				fakeDB.save(cat1, testProject);
				catEntMan.assignUniqueID(cat2);
				fakeDB.save(cat2, testProject);
				catEntMan.assignUniqueID(cat3);
				fakeDB.save(cat3, testProject);
				//Tests
				catEntMan.deleteAll(sesh);;
				assertNull(catEntMan.getEntity(sesh, "2"));		
	}

/*	@Test
public void ensureRoleTest() throws WPISuiteException {
	//Set Up
	
	User admin = new User("admin", "admin", "1234", 27);
	admin.setRole(Role.ADMIN);
	Session sesh = new Session(admin, "01");
	MockData fakeDB = new MockData(new HashSet<Object>());
	fakeDB = new MockData(new HashSet<Object>());
	CategoryEntityManager catEntMan = new CategoryEntityManager(fakeDB);
	fakeDB.save(admin);
	
	//Tests

	//How do I test a void function?
}*/
}