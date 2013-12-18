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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.events.EventsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the Event text fields to the model as a new
 * requirement.
 * @version $Revision: 1.0 $
 * @author E.J. Murphy
 */
public class AddEventController implements ActionListener{

	/** Reference to the Events model */
	private final EventsModel model;
	
	/** The new event to add */
	private final Event eventToBeAdded;

	/** The observer associated with this controller */
	AddEventRequestObserver observer = new AddEventRequestObserver(this);
	
	/**
	 * Construct an AddEventController for the given model
	 * @param model the model containing the messages
	 * @param eventToBeAdded the event to add to the model
	 */
	public AddEventController(EventsModel model, Event eventToBeAdded) {
		this.model = model;
		this.eventToBeAdded = eventToBeAdded;
	}
	
	/**
	 * This method is called when the user clicks the Submit button
	 * @param event The event for the action
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Send a request to the core to save this message
		// Add the message to the model
		final Request request = Network.getInstance().makeRequest("calendar/event", HttpMethod.PUT); // PUT == create
		request.setBody(eventToBeAdded.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddEventRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
	}


	/**
	 * When the new Event is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addEventToModel(Event item) {
		model.addEvent(item);
	}
}
