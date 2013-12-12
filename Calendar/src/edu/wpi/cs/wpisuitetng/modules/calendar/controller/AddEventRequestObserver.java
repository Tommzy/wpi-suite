
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

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request
 * to the server to add a event.
 * @version $Revision: 1.0 $
 * @author Hui Zheng & EJ & Jared
 */
public class AddEventRequestObserver implements RequestObserver {

	private final AddEventController controller;
	
	public AddEventRequestObserver(AddEventController controller) {
		this.controller = controller;
	}
	public Event testItem;

	
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
		
		//Parse the calendar item out of the response body
		final Event item = Event.fromJSON(response.getBody());
		//Pass the messaged back to the controller
		//Needs to put Event back into the system
		//TODO
		controller.addEventToModel(item);
		MainCalendarController.getInstance().updateAll();
		System.out.print("From AddEventObserver." + response.getBody());
		
	} 
	
	public Event testReturn(){
		return testItem;
	}

	@Override
	public void responseError(IRequest iReq) {
		System.err.println(iReq.getBody());
		System.err.println(iReq.getResponse().getStatusMessage());
		System.err.println("The request to add a event errored.");	
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to add a eventt failed.");
	}
}
