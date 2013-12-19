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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.EventsModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the requirements
 * from the server.
 *
 * @version $Revision: 1.0 $
 * @author Hui Zheng & EJ & Jared
 */
public class GetEventController implements ActionListener {

	private GetEventRequestObserver observer;

	public GetEventController() {
		observer = new GetEventRequestObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("calendar/event", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		request.send(); // send the request
		}
	
	/**
	 * Sends an HTTP request to retrieve all requirements
	 */
	public void retrieveEvents() {
		final Request request = Network.getInstance().makeRequest("calendar/event", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is GetEventController.retrieveEvents() to update the EventsList" + "   "+ request.getBody());
		request.send(); // send the request
	}
	
	/**
	 * Add the given Events to the local model (they were received from the core).
	 * This method is called by the GetEventsRequestObserver
	 * 
	 * @param Events an array of Events received from the server
	 */
	public void receivedEvents(Event[] Events) {
//		System.out.println(Events.length);
		// Empty the local model to eliminate duplications
		EventsModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (Events != null) {
			
			// add the Events to the local model
			EventsModel.getInstance().addEvents(Events);
//			System.out.println(EventsModel.getInstance().getAllEvent().size());
		}
	}

	
	
	

}

