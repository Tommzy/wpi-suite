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

import java.awt.Color;
import java.util.GregorianCalendar;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class CategoryTests {

	@Test
	public void getAndSetTests() {
		//Set Up
		
		Category testcat = new Category("Test Category", false, null);
		Project testProj = new Project(null, null);
		User testUser = new User(null, null, null, 0);
		GregorianCalendar testCal1 = new GregorianCalendar(2013, 12, 13, 12, 24, 0);
		GregorianCalendar testCal2 = new GregorianCalendar(2013, 12, 13, 16, 30, 0);
		
		//Tests
		// Name
		testcat.setName("I changed the name");
		assertEquals(testcat.getName(), "I changed the name");
		// ID
		testcat.setId(2);
		assertEquals(testcat.getId(), 2);
		// Project
		testcat.setProject(testProj);
		assertSame(testcat.getProject(), testProj);
		// Permission
		testcat.setPermission(Permission.WRITE, testUser);
		assertEquals(testcat.getPermission(testUser), Permission.WRITE);
		// Color
		testcat.setColor(new Color(255,0,0));
		assertEquals(testcat.getColor(), new Color(255,0,0));
		// isPersonal
		testcat.setIsPersonal(true);
		assertTrue(testcat.isPersonal());
		

		
	}
	
	@Test
	public void CopyTest() {
		//Set Up
		
		Category testcat1 = new Category("Test Category", false, null);
		Category testcat2 = new Category("Test Category2", false, null);
		
		//Tests
	
		testcat1.copy(testcat2);
		
		assertEquals(testcat1.getId(), testcat2.getId());
		assertEquals(testcat1.getName(), testcat2.getName());
		assertEquals(testcat1.getProject(), testcat2.getProject());
		assertEquals(testcat1.getColor(), testcat2.getColor());
		assertEquals(testcat1.getUserId(), testcat2.getUserId());
		assertEquals(testcat1.isPersonal(), testcat2.isPersonal());
		assertEquals(testcat1.isActive(), testcat2.isActive());
		
		
	}
	
	@Test
	public void JSONTests() {
		//Set Up
		
		Category testcat1 = new Category("Test Category", false, null);
		//Tests
		
		String catString = testcat1.toJSON();
		System.out.print(catString  + "\n");
		Category testcat2 = Category.fromJSON(catString);
		System.out.print(testcat2.toJSON() + "\n");
		
		assertEquals(testcat1.getId(), testcat2.getId());
		assertEquals(testcat1.getName(), testcat2.getName());
		assertEquals(testcat1.getProject(), testcat2.getProject());
		assertEquals(testcat1.getColor(), testcat2.getColor());
		assertEquals(testcat1.getUserId(), testcat2.getUserId());
		assertEquals(testcat1.isPersonal(), testcat2.isPersonal());
		assertEquals(testcat1.isActive(), testcat2.isActive());
		
	}
	

}
