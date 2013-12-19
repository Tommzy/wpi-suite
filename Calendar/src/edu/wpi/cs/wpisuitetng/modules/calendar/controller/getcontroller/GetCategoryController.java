/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.getcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.CategoriesModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the categories
 * from the server.
 *
 * @version $Revision: 1.0 $
 * @author Jared
 */
public class GetCategoryController implements ActionListener {

	private GetCategoryRequestObserver observer;

	public GetCategoryController() {
		observer = new GetCategoryRequestObserver(this);;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is GetCategoryController.actionPerformed" + "   "+ request.getBody());
		request.send(); // send the request
		}
	
	/**
	 * Sends an HTTP request to retrieve all requirements
	 */
	public void retrieveCategories() {
		final Request request = Network.getInstance().makeRequest("calendar/category", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is GetCategoryController.retrieveCategories() to update the categoriesList" + "   "+ request.getBody());
		request.send(); // send the request
	}
	
	/**
	 * Add the given categories to the local model (they were received from the core).
	 * This method is called by the GetCategoriesRequestObserver
	 * 
	 * @param categories an array of categories received from the server
	 */
	public void receivedCategories(Category[] categories) {
//		System.out.println(categories.length);
		// Empty the local model to eliminate duplications
		CategoriesModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (categories != null) {
			
			// add the categories to the local model
			CategoriesModel.getInstance().addCategories(categories);
//			System.out.println(CategoriesModel.getInstance().getAllCategory().size());
		}
	}

	
	
	

}

