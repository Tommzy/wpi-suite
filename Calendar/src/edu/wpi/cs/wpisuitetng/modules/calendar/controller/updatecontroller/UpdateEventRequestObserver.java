

/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: E.J. Murphy, Andrew Paon, Eric Wilcox
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications
 * about UpdateEventRequest information as the UpdateEventRequest is constructed.
 */
public class UpdateEventRequestObserver implements RequestObserver {
	
	/** The controller. */
	private final UpdateEventController controller;
	
	/**
	 * This method is called when information about an UpdateEventRequest
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param controller the controller
	 */
	public UpdateEventRequestObserver(UpdateEventController controller) {
		this.controller = controller;
	}
	
	
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
		
		// Parse the Event out of the response body
		final Event event = Event.fromJSON(response.getBody());	
		UpdateEventController.getInstance().updateSucess(event);
		MainCalendarController.getInstance().updateAll();
		System.out.println("Success! Here is GetEventRequestController in the JSON way"+ "   " + iReq.getResponse().getBody());
	}
	

	@Override
	public void responseError(IRequest iReq) {
		System.err.println(iReq.getResponse().getStatusMessage());
		System.err.println("The request to update a Event failed.");
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to update a Event failed.");
	}

}
