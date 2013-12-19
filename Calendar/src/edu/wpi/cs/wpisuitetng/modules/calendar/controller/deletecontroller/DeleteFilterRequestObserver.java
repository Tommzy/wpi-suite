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
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.getcontroller.GetFilterController;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer is called when a response is delete from a request
 * to the server to delete a Filter.
 * @version $Revision: 1.0 $
 * @author Jared
 */
public class DeleteFilterRequestObserver implements RequestObserver{
	
	public DeleteFilterController controller;
	public GetFilterController getController;

	
	
	public DeleteFilterRequestObserver(DeleteFilterController controller) {
		
		this.controller = controller;
		getController = new GetFilterController();
	}

	
	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		//Filter[] items = Filter.fromJsonArray(iReq.getResponse().getBody());
		
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		
	
		System.out.println("After delete: Success! Here is DeleteFilterRequestController in the JSON way"+ "   " + result);
		
		//TODO
		//Update the view
		getController.retrieveFilters();
		MainCalendarController.getInstance().updateAll();
		
	}
	
	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
	//	fail(iReq, null);
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		System.err.println("After delete: The request to delete Filters Errored. " + result);
	}


	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
//		Filter[] errorFilter = { new Filter("Error", null, "Error") };
//		controller.receivedFilters(errorFilter);
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());

		System.err.println("After delete: The request to delete Filters failed.  " + result);
		
	}
}
