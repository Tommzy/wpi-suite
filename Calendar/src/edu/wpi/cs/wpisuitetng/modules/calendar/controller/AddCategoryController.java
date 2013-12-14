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
import java.util.GregorianCalendar;
import java.util.HashMap;

import edu.wpi.cs.wpisuitetng.modules.calendar.categories.CategoriesModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
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

	private final CategoriesModel model;
	private final Category categoryToBeAdded;
	 
	//Category testCommit1 = new Category("First test",new GregorianCalendar(1992,8,19,23,4),"Success ><!");
	 
	/**
	 * Construct an AddCategoryController for the given model, view pair
	 * @param model the model containing the messages
	 * @param viewCategory the view where the user enters new messages
	 */
	public AddCategoryController(CategoriesModel model, Category categoryToBeAdded) {
		this.model = model;
		this.categoryToBeAdded = categoryToBeAdded;
	}
  
	AddCategoryRequestObserver observer = new AddCategoryRequestObserver(this);
	
//	public void addTestToDatabase(){
//		final Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.PUT); // PUT == create
//		request.setBody(testCommit1.toJSON()); // put the new message in the body of the request
//		request.addObserver(observer); // add an observer to process the response
//		request.send();
//	}
	
	public void addCategoryToDatabase(Category category){
		final Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.PUT); // PUT == create
		request.setBody(category.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddCategoryRequestObserver(this)); // add an observer to process the response
		request.send();
	}
	
	public Category testReturn(){
		return observer.testReturn();
	}
	
	 
	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//addTestToDatabase();
//		System.out.println("this is event!----->" + event.getActionCommand().toString());
		// Get the text that was entered
//		String name =  categoryToBeAdded.getName();
//		GregorianCalendar startTime = categoryToBeAdded.getStartTime();
//		String description = (String) categoryToBeAdded.getDescription();
//		String invitee = (String) categoryToBeAdded.getInvitee();
		 
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		
//		//don't want to make a new event if there is no name
//		if(name.length() > 0){
//			Category sentCategory = new Category(name, startTime, description);
//			// Add the message to the model
//			model.addCategory(sentCategory);
//		}
		
		// Send a request to the core to save this message
		// Add the message to the model
		final Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.PUT); // PUT == create
		request.setBody(categoryToBeAdded.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddCategoryRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		System.out.println("from AddCategoryController." + request.getBody());


	}


	/** ATTENTION AT THIS PART
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addCategoryToModel(Category item) {
		model.addCategory(item);
	}
}


