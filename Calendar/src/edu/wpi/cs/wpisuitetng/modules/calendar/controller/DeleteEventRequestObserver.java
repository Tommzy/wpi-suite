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
 * to the server to delete a Event.
 * @version $Revision: 1.0 $
 * @author Hui Zheng & Andrew Paon & Mark & Chris turner & Jared
 */
public class DeleteEventRequestObserver implements RequestObserver{
	
	public DeleteEventController controller;
	public GetEventController getController;

	
	
	public DeleteEventRequestObserver(DeleteEventController controller) {
		
		this.controller = controller;
		getController = new GetEventController();
	}

	
	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		//Event[] items = Event.fromJsonArray(iReq.getResponse().getBody());
		
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		
	
		System.out.println("After delete: Success! Here is DeleteEventRequestController in the JSON way"+ "   " + result);
		
		//TODO
		//Update the view
		getController.retrieveEvents();
		MainCalendarController.getInstance().updateAll();
		
	}
	
	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
	//	fail(iReq, null);
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		System.err.println("After delete: The request to delete Events Errored. " + result);
	}


	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
//		Event[] errorEvent = { new Event("Error", null, "Error") };
//		controller.receivedEvents(errorEvent);
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());

		System.err.println("After delete: The request to delete Events failed.  " + result);
		
	}
}
