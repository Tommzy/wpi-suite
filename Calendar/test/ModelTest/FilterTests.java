/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 * V1.0
 ******************************************************************************/
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
		// Project
		testfil.setProject(testProj);
		assertSame(testfil.getProject(), testProj);
		// Permission
		testfil.setPermission(Permission.WRITE, testUser);
		assertEquals(testfil.getPermission(testUser), Permission.WRITE);
		// Categories
		testfil.setCategories(null);
		assertNull(testfil.getCategories());
		// User ID
		testfil.setUserId("Jimbo");
		assertEquals(testfil.getUserId(), "Jimbo");
		// Activeness
		testfil.setActiveness(true);
		assertTrue(testfil.isActive());
		
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
		assertEquals(testfil1.isActive(), testfil2.isActive());
		assertEquals(testfil1.getUserId(), testfil2.getUserId());
		assertEquals(testfil1.getCategories(), testfil2.getCategories());
		
		
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
