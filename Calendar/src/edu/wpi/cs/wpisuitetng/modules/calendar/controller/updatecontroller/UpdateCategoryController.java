

/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jared Wormley
 ******************************************************************************/



package edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.CategoriesModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdatecategoryController.
 */
public class UpdateCategoryController implements ActionListener{
	
	/** The instance. */
	private static UpdateCategoryController instance;
	
	/** The observer. */
	private UpdateCategoryRequestObserver observer;
	
	/** The updated category. */
	private static Category updatedCategory;
	
	/**
	 * Instantiates a new update category controller.
	 *
	 * @param updatedcategory the updated category
	 */
	public UpdateCategoryController(Category updatedcategory) {
		observer = new UpdateCategoryRequestObserver(this);
		this.updatedCategory = updatedcategory;
	}
	
	/**
	 * Gets the single instance of UpdatecategoryController.
	 *
	 * @return single instance of UpdatecategoryController
	 */
	public static UpdateCategoryController getInstance()
	{
		if(instance == null)
		{
			instance = new UpdateCategoryController(updatedCategory);
		}
		
		return instance;
	}
	
	/**
	 * Gets the updated category.
	 *
	 * @return the updated category
	 */
	public Category getUpdatedCategory(){
		return UpdateCategoryController.getInstance().updatedCategory;
	}

	/**
	 * Update Category.
	 *
	 * @param newcategory the new Category
	 */
	public void updateCategory(Category newcategory) 
	{
		Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.POST); // POST == update
		request.setBody(newcategory.toJSON()); // put the new category in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// get category
		UpdateCategoryController.getInstance()
		.updateCategory(UpdateCategoryController.getInstance()
		.getUpdatedCategory());
//		Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.POST); // POST == update
//		request.setBody(UpdateCategoryController.getInstance()
//				.getUpdatedCategory().toJSON()); // put the new category in the body of the request
//		request.addObserver(observer); // add an observer to process the response
//		request.send(); 
		
	}
	
	/**
	 * Update sucess.
	 *
	 * @param newComm the new category
	 * @return true, if successful
	 */
	public boolean updateSucess(Category newComm){
		return UpdateCategoryController.getInstance().getUpdatedCategory().equals(newComm);
	}
}

