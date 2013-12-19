/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.deletecontroller;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.getcontroller.GetCategoryController;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer is called when a response is delete from a request
 * to the server to delete a category.
 * @version $Revision: 1.0 $
 * @author Jared
 */
public class DeleteCategoryRequestObserver implements RequestObserver{

	/** The controller that instantiated this observer*/
	public DeleteCategoryController controller;

	/** The get controller*/
	public GetCategoryController getController;

	/**
	 * Constructor - set the controller of this observer.
	 * @param controller the controller to reference
	 */
	public DeleteCategoryRequestObserver(DeleteCategoryController controller) {
		this.controller = controller;
		getController = new GetCategoryController();
	}

	/**
	 * Parse the messages out of the response body and pass them to the controller
	 * @param iReq the request of the response
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		System.out.println("After delete: Success! Here is DeleteCategoryRequestController in the JSON way"+ "   " + result);
		//Update the view
		getController.retrieveCategories();
		MainCalendarController.getInstance().updateAll();
		
	}
	
	/**
	 * The response had an error
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		System.err.println("After delete: The request to delete categories Errored. " + result);
	}

	/**
	 * The fail exception.
	 * @param iReq the request
	 * @param exception the exception
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		System.err.println("After delete: The request to delete categories failed.  " + result);
		
	}
}
