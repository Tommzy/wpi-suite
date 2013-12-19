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


import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;


/**
 * This controller coordinates retrieving all of the category
 * from the server.
 *
 * @version $Revision: 1.0 $
 * @author Jared
 */
public class GetCategoryRequestObserver implements RequestObserver{

	public GetCategoryController controller;
	
	
	public GetCategoryRequestObserver(GetCategoryController controller) {
		this.controller = controller;
	}

	
	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		Category[] items = Category.fromJsonArray(iReq.getResponse().getBody());
		//TODO
		//put this back in
	
		System.out.println("Success! Here is GetCategoryRequestController in the JSON way"+ "   " + iReq.getResponse().getBody());
		controller.receivedCategories(items);

		
	}
	
	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
	//	fail(iReq, null);
		System.err.println("The request to get categories Errored. " + iReq.toString());
	}


	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
//		category[] errorCategory = { new Category("Error", null, "Error") };
//		controller.receivedCategories(errorCategory);
		System.err.println("The request to get categories failed.");
		
	}


}

