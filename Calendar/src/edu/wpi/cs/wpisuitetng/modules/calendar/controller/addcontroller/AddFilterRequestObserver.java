
/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request
 * to the server to add a filter.
 * @version $Revision: 1.0 $
 * @author Hui Zheng
 */
public class AddFilterRequestObserver implements RequestObserver {

	/** The controller that instantiated this observer*/
	private final AddFilterController controller;

	/**
	 * Constructor - set the controller of this observer.
	 * @param controller the controller to reference
	 */
	public AddFilterRequestObserver(AddFilterController controller) {
		this.controller = controller;
	}

	/**
	 * The response is successful.
	 * @param iReq the request of the response
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
		
		//Parse the calendar item out of the response body
		final Filter item = Filter.fromJSON(response.getBody());
		//Pass the messaged back to the controller
		//Needs to put filter back into the system
		controller.addFilterToModel(item);
		MainCalendarController.getInstance().updateAll();
		System.out.print("From AddFilterObserver." + response.getBody());
	} 

	/**
	 * The response had an error
	 * @param iReq the request of the response
	 */
	@Override
	public void responseError(IRequest iReq) {
		System.err.println(iReq.getBody());
		System.err.println(iReq.getResponse().getStatusMessage());
		System.err.println("The request to add a filter errored.");	
	}

	/**
	 * The fail exception.
	 * @param iReq the request
	 * @param exception the exception
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to add a filter failed.");
	}
}
