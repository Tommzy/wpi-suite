/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.categories.CategoriesModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


/**
 * This controller responds when the user clicks the Submit button by
 * adding the contents of the category text fields to the model as a new
 * category.
 * @version $Revision: 1.0 $
 * @author Hui Zheng
 */
public class AddCategoryController implements ActionListener{

	/** Reference to the CategoriesModel */
	private final CategoriesModel model;
	
	/** The new category to add */
	private final Category categoryToBeAdded;
	
	/** The observer associated with this controller */
	AddCategoryRequestObserver observer = new AddCategoryRequestObserver(this);
	
	/**
	 * Construct an AddCategoryController for the given model
	 * @param model the model containing the messages
	 * @param categoryToBeAdded the category to add
	 */
	public AddCategoryController(CategoriesModel model, Category categoryToBeAdded) {
		this.model = model;
		this.categoryToBeAdded = categoryToBeAdded;
	}
	
	/**
	 * Make and send the request for adding the category to the server.
	 * @param category the category to add to the database
	 */
	public void addCategoryToDatabase(Category category){
		final Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.PUT); // PUT == create
		request.setBody(category.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddCategoryRequestObserver(this)); // add an observer to process the response
		request.send();
	}
	
	/**
	 * This method is called when the user clicks the Submit button
	 * @param event The event for the action
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.PUT); // PUT == create
		request.setBody(categoryToBeAdded.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddCategoryRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		System.out.println("from AddCategoryController." + request.getBody());
	}


	/**
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param item the category to add to the local model
	 */
	public void addCategoryToModel(Category item) {
		model.addCategory(item);
	}
}
