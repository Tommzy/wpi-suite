/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Hui Zheng - Team 3
 * Based on Source Code from CategoriesModels
 * V1.0
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


import edu.wpi.cs.wpisuitetng.modules.calendar.controller.getcontroller.GetCategoryController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.CategoriesModel;

/**
 * This class is used to filter a mist of commitments and return those that begin within a specified time slot
 *
 */
public class CategoryFilter {
	private int type = -1;
	private Collection<Category> categoryList = new ArrayList<Category>();
	/**
	 * Constructor for category filter
	 */
	public CategoryFilter() {
		this(-1);
	}
	
	/**
	 * Constructor for category filter
	 * @param type Either 0 or 1; 1 stands for personal only or 0 stands for team only.
	 */
	public CategoryFilter(int type) {
		this.type = type;
	}
	
	/**
	 * Used to create a collection of category that begin within the start and end time
	 * 
	 * @return The array of category within the time frame
	 */
	public Category[] getCategoryArray() {
		try {
			Category teamMeeting = new Category("Team Meeting", true, Color.CYAN);
			teamMeeting.setId(1);
			teamMeeting.setIsPersonal(false);
			Category personalMeeting = new Category("Personal Meeting", true, Color.CYAN);
			teamMeeting.setId(2);
			teamMeeting.setIsPersonal(true);
			Category[] categoryArray;
			//new GetCategoryController().actionPerformed(null);
			GetCategoryController getController = new GetCategoryController();
			getController.retrieveCategories();
			Collection<Category> categoryList = CategoriesModel.getInstance().getAllCategory();
			Collection<Category> list = new ArrayList<Category>();
			if (type == 0) {
				for (Category cat : categoryList) {
					if ((! cat.isPersonal()) && (cat.isActive())) {
						list.add(cat);
					}
				}
				list.add(teamMeeting);
			}
			else if (type == 1) {
				for (Category cat : categoryList) {
					if ((cat.isPersonal()) && (cat.isActive())) {
						list.add(cat);
					}
				}
				list.add(personalMeeting);
			}
			else if (type == -1) {
				for (Category cat : categoryList) {
					if (cat.isActive()) {
						list.add(cat);
					}
				}
				list.add(personalMeeting);
				list.add(teamMeeting);
			}
			categoryArray = list.toArray(new Category[list.size()]);
			System.out.println(categoryArray.length);
			return categoryArray;
		} catch (NullPointerException e) {
			System.out.println("commitment filter null pointer exception");
			System.out.println(e);

		}
		
		System.out.println("FAIL PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
		return new Category[0];
	}
	
	/**
	 * Used to create a collection of category that begin within the start and end time
	 * 
	 * @return The array of category within the time frame
	 */
	public Category[] getCategoryAllArray() {
		try {
			Category teamMeeting = new Category("Team Meeting", true, Color.CYAN);
			teamMeeting.setId(1);
			teamMeeting.setIsPersonal(false);
			Category personalMeeting = new Category("Personal Meeting", true, Color.CYAN);
			teamMeeting.setId(2);
			teamMeeting.setIsPersonal(true);
			Category[] categoryArray;
			//new GetCategoryController().actionPerformed(null);
			GetCategoryController getController = new GetCategoryController();
			getController.retrieveCategories();
			Collection<Category> categoryList = CategoriesModel.getInstance().getAllCategory();
			Collection<Category> list = new ArrayList<Category>();
			if (type == 0) {
				for (Category cat : categoryList) {
					if (! cat.isPersonal()) {
						list.add(cat);
					}
				}
				list.add(teamMeeting);
			}
			else if (type == 1) {
				for (Category cat : categoryList) {
					if (cat.isPersonal()) {
						list.add(cat);
					}
				}
				list.add(personalMeeting);
			}
			else if (type == -1) {
				for (Category cat : categoryList) {
					list.add(cat);
				}
				list.add(personalMeeting);
				list.add(teamMeeting);
			}
			categoryArray = list.toArray(new Category[list.size()]);
			System.out.println(categoryArray.length);
			return categoryArray;
		} catch (NullPointerException e) {
			System.out.println("commitment filter null pointer exception");
			System.out.println(e);

		}
		
		System.out.println("FAIL PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
		return new Category[0];
	}
	
	/**
	 * Used to create a collection of commitments that begin within the start and end time
	 * 
	 * @return The collection of commitments within the time frame
	 */
	public Collection<Category> getCategoryList() {
		try {
//			JButton button = new JButton("");
//			button.addActionListener(new GetCategoryController());
//			button.doClick();
			new GetCategoryController().actionPerformed(null);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Collection<Category> list = CategoriesModel.getInstance().getAllCategory();
					System.out.println(list.size());
					Iterator<Category> itr = list.iterator();

					while (itr.hasNext()) {
						Category category = itr.next();
						categoryList.add(category);
					}
				}
			}, 0);
			
			return categoryList;
		} catch (NullPointerException e) {
			System.out.println("commitment filter null pointer exception");
			System.out.println(e);

		}
		
		System.out.println("FAIL PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
		return new ArrayList<Category> ();
	}
	
}
