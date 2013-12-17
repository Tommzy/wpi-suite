package ModelTest;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class EventTests {

	@Test
	public void getAndSetTests() {
		//Set Up
		
		Event testev = new Event("Test Event", null, null,"no where", "Test Event",null);
		Project testProj = new Project(null, null);
		User testUser = new User(null, null, null, 0);
		GregorianCalendar testCal1 = new GregorianCalendar(2013, 12, 13, 12, 24, 0);
		GregorianCalendar testCal2 = new GregorianCalendar(2013, 12, 13, 16, 30, 0);
		
		//Tests
		// Description
		testev.setDescription("I changed the description");
		assertEquals(testev.getDescription(), "I changed the description");
		// Name
		testev.setName("I changed the name");
		assertEquals(testev.getName(), "I changed the name");
		// ID
		testev.setId(2);
		assertEquals(testev.getId(), 2);
		// TeamEvent
		testev.setTeamEvent(false);
		assertEquals(testev.isTeamEvent(), false);
		// Project
		testev.setProject(testProj);
		assertSame(testev.getProject(), testProj);
		// StartTime
		testev.setStartTime(testCal1);
		assertSame(testev.getStartTime(), testCal1);
		// Permission
		testev.setPermission(Permission.WRITE, testUser);
		assertEquals(testev.getPermission(testUser), Permission.WRITE);
		// Username
		testev.setUsername("Chuck");
		assertEquals(testev.getUsername(), "Chuck");
		//EndTime
		testev.setEndTime(testCal2);
		assertEquals(testev.getEndTime(), testCal2);
		
		//getTimeSpan
		assertEquals(testev.getTimeSpan(), 246);
		
	}
	
	@Test
	public void CopyTest() {
		//Set Up
		
		Event testev = new Event("Test Event", null, null,"no where", "Test Event",null);
		Event copyev = new Event("Copy Stuff", null, null,"no where", "More CopyStuff",null);
		Event copyDontWork = new Event("Test Event", null, null,"no where", "Test Event",null);
		
		//Tests
		
		System.out.print(testev.toJSON() + "\n");
		System.out.print(copyev.toJSON() + "\n");
		testev.copy(copyev);
		System.out.print(testev.toJSON() + "\n");
		assertTrue(testev.equals(copyev));
		//assertEquals(testev, copyDontWork);
		
	}
	
	@Test
	public void isTimeStampActiveDuringTest() {
		//Set Up
		
		Event testev = new Event("Test Event", null, null,"no where", "Test Event",null);

		GregorianCalendar cal1 = new GregorianCalendar(2013, 12, 13, 16, 30);
		GregorianCalendar cal11 = new GregorianCalendar(2013, 12, 13, 17, 30);
		GregorianCalendar cal2 = new GregorianCalendar(2013, 12, 13, 18, 45);
		GregorianCalendar cal3 = new GregorianCalendar(2013, 12, 13, 16, 45);
		
		//Tests
		
		testev.setStartTime(cal1);
		testev.setEndTime(cal11);
		assertTrue(testev.isActiveDuringTimeStamp(cal3));
		assertFalse(testev.isActiveDuringTimeStamp(cal2));
		
	}
	
	@Test
	public void JSONTests() {
		//Set Up
		
		Event testev1 = new Event("Test Event", null, null,"no where","Test Event",null);
		//Tests
		
		String evString = testev1.toJSON();
		System.out.print(evString  + "\n");
		Event testev2 = Event.fromJSON(evString);
		System.out.print(testev2.toJSON() + "\n");
		
		//assertTrue(testev2.equals(testev1));
		assertEquals(testev2, testev1);
		
	}
	

}
