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

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer is called when a response is delete from a request
 * to the server to delete a category.
 * @version $Revision: 1.0 $
 * @author Jared
 */
public class DeleteCategoryRequestObserver implements RequestObserver{
	
	public DeleteCategoryController controller;
	public GetCategoryController getController;

	
	
	public DeleteCategoryRequestObserver(DeleteCategoryController controller) {
		
		this.controller = controller;
		getController = new GetCategoryController();
	}

	
	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		//category[] items = category.fromJsonArray(iReq.getResponse().getBody());
		
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		
	
		System.out.println("After delete: Success! Here is DeleteCategoryRequestController in the JSON way"+ "   " + result);
		
		//TODO
		//Update the view
		getController.retrieveCategories();
		MainCalendarController.getInstance().updateAll();
		
	}
	
	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
	//	fail(iReq, null);
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		System.err.println("After delete: The request to delete categories Errored. " + result);
	}


	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
//		Category[] errorCategories = { new Category("Error", null) };
//		controller.receivedCategories(errorCategory);
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());

		System.err.println("After delete: The request to delete categories failed.  " + result);
		
	}
}

