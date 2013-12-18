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


import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;


/**
 * This controller coordinates retrieving all of the Commitment
 * from the server.
 *
 * @version $Revision: 1.0 $
 * @author Hui Zheng & EJ & Jared
 */
public class GetEventRequestObserver implements RequestObserver{

	public GetEventController controller;
	
	
	public GetEventRequestObserver(GetEventController controller) {
		this.controller = controller;
	}

	
	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		Event[] items = Event.fromJsonArray(iReq.getResponse().getBody());
		//TODO
		//put this back in
	
		System.out.println("Success! Here is GetEventRequestController in the JSON way"+ "   " + iReq.getResponse().getBody());
		controller.receivedEvents(items);

		
	}
	
	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
	//	fail(iReq, null);
		System.err.println("The request to get Events Errored. " + iReq.toString());
	}


	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
//		Event[] errorEvent = { new Event("Error", null, "Error") };
//		controller.receivedEvents(errorEvent);
		System.err.println("The request to get Events failed.");
		
	}


}

