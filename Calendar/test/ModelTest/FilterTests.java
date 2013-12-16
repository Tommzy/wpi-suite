package ModelTest;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class FilterTests {

	@Test
	public void getAndSetTests() {
		//Set Up
		
		Filter testfil = new Filter("Test Filter", null);
		Project testProj = new Project(null, null);
		User testUser = new User(null, null, null, 0);
		GregorianCalendar testCal1 = new GregorianCalendar(2013, 12, 13, 12, 24, 0);
		GregorianCalendar testCal2 = new GregorianCalendar(2013, 12, 13, 16, 30, 0);
		
		//Tests
		// Name
		testfil.setName("I changed the name");
		assertEquals(testfil.getName(), "I changed the name");
		// ID
		testfil.setId(2);
		assertEquals(testfil.getId(), 2);
		// TeamFilter
		// Project
		testfil.setProject(testProj);
		assertSame(testfil.getProject(), testProj);
		// StartTime
		// Permission
		testfil.setPermission(Permission.WRITE, testUser);
		assertEquals(testfil.getPermission(testUser), Permission.WRITE);
		
	}
	
	@Test
	public void CopyTest() {
		//Set Up
		
		Filter testfil1 = new Filter("Test Filter", null);
		Filter testfil2 = new Filter("Copy Stuff", null);
		
		//Tests
		
		
		testfil1.copy(testfil2);
		

		assertEquals(testfil1.getId(), testfil2.getId());
		assertEquals(testfil1.getName(), testfil2.getName());
		assertEquals(testfil1.getProject(), testfil2.getProject());
		
		
	}
	
	@Test
	public void JSONTests() {
		//Set Up
		
		Filter testfil1 = new Filter("Test Filter", null);
		//Tests
		
		String filString = testfil1.toJSON();
		System.out.print(filString  + "\n");
		Filter testfil2 = Filter.fromJSON(filString);
		System.out.print(testfil2.toJSON() + "\n");
		
		assertEquals(testfil1.getId(), testfil2.getId());
		assertEquals(testfil1.getName(), testfil2.getName());
		assertEquals(testfil1.getProject(), testfil2.getProject());
		
	}
	

}
