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
import java.util.List;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.CategoriesModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class CategoriesModelTests {

	
	
	@Test
	public void addCategoryTest() {
		//Set Up
		
		Category testCom1 = new Category("Test Category", true, null);
		Category testCom2 = new Category("Copy Stuff", true, null);
		
		CategoriesModel model = CategoriesModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addCategory(testCom1);
		model.addCategory(testCom2);
		
		List<Category> comList = model.getAllCategory();
		
		assertEquals(comList.size(), 2);
		
	}
	
	@Test
	public void removeCategoryTest() {
		//Set Up
		
		Category testCom1 = new Category("Test Category", true, null);
		testCom1.setId(1);
		Category testCom2 = new Category("Copy Stuff", true, null);
		testCom2.setId(2);
		
		CategoriesModel model = CategoriesModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addCategory(testCom1);
		model.addCategory(testCom2);
		
		model.removeCategory(2);
		
		List<Category> comList = model.getAllCategory();
		
		assertEquals(comList.size(), 1);
		
	}
	
	@Test
	public void emptyModelTest() {
		//Set Up
		
				Category testCom1 = new Category("Test Category", true, null);
				Category testCom2 = new Category("Copy Stuff", true, null);
				
				CategoriesModel model = CategoriesModel.getInstance();

				
				//Tests
				model.addCategory(testCom1);
				model.addCategory(testCom2);
				
				List<Category> comList = model.getAllCategory();
				model.emptyModel();
				assertEquals(comList.size(), 0);
				
	}
	
	
	
	@Test
	public void getSizeTests() {
		//Set Up
		
		Category testCom1 = new Category("Test Category", true, null);
		Category testCom2 = new Category("Copy Stuff", true, null);
		
		CategoriesModel model = CategoriesModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addCategory(testCom1);
		model.addCategory(testCom2);
		

		assertEquals(model.getSize(), 2);
		
	}
	
	@Test
	public void getCategoryTest() {
		//Set Up
		
		Category testCom1 = new Category("Test Category", true,null);
		testCom1.setId(1);
		Category testCom2 = new Category("Copy Stuff", true,null);
		testCom2.setId(2);
		
		CategoriesModel model = CategoriesModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addCategory(testCom1);
		model.addCategory(testCom2);
		
		assertEquals(model.getCategory(2), testCom2);
		
	}

}
