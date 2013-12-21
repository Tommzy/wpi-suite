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


import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;


// TODO: Auto-generated Javadoc
/**
 * This controller coordinates retrieving all of the Filter
 * from the server.
 *
 * @version $Revision: 1.0 $
 * @author Jared Wormley
 */
public class GetFilterRequestObserver implements RequestObserver{

	/** The controller. */
	final GetFilterController controller;


	/**
	 * This method is called when information about an GetFilterRequest
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param controller the controller
	 */
	public GetFilterRequestObserver(GetFilterController controller) {
		this.controller = controller;
	}


	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		final Filter[] items = Filter.fromJsonArray(iReq.getResponse().getBody());
		//TODO
		//put this back in

		System.out.println("Success! Here is GetFilterRequestController in the JSON way"+ "   " + iReq.getResponse().getBody());
		controller.receivedFilters(items);


	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		//	fail(iReq, null);
		System.err.println("The request to get Filters Errored. " + iReq.toString());

	}


	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
		//		Filter[] errorFilter = { new Filter("Error", null, "Error") };
		//		controller.receivedFilters(errorFilter);
		System.err.println("The request to get Filters failed.");

	}


}

