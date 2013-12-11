

/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:Team 3
 ******************************************************************************/



package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the Event text fields to the model as a new
 * Event.
 * @version $Revision: 1.0 $
 * @author E.J. Murphy, Andrew Paon, Eric Wilcox, Hui Zheng
 */

public class UpdateEventController implements ActionListener{
	
	/** The instance. */
	private static UpdateEventController instance;
	
	/** The observer. */
	private UpdateEventRequestObserver observer;
	
	/** The updated Event. */
	private static Event updatedEvent;
	
	/**
	 * Instantiates a new update Event controller.
	 *
	 * @param updatedEvent the updated Event
	 */
	public UpdateEventController(Event updatedEvent) {
		observer = new UpdateEventRequestObserver(this);
		this.updatedEvent = updatedEvent;
	}
	
	/**
	 * Gets the single instance of UpdateEventController.
	 *
	 * @return single instance of UpdateEventController
	 */
	public static UpdateEventController getInstance()
	{
		if(instance == null)
		{
			instance = new UpdateEventController(updatedEvent);
		}
		
		return instance;
	}
	
	/**
	 * Gets the updated Event.
	 *
	 * @return the updated Event
	 */
	public Event getUpdatedEvent(){
		return UpdateEventController.getInstance().updatedEvent;
	}

	/**
	 * Update Event.
	 *
	 * @param newEvent the new Event
	 */
	public void updateEvent(Event newEvent) 
	{
		Request request = Network.getInstance().makeRequest("calendar/event", HttpMethod.POST); // POST == update
		request.setBody(newEvent.toJSON()); // put the new Event in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// get Event
		UpdateEventController.getInstance()
		.updateEvent(UpdateEventController.getInstance()
		.getUpdatedEvent());
		
	}
	
	/**
	 * Update sucess.
	 *
	 * @param newComm the new Event
	 * @return true, if successful
	 */
	public boolean updateSucess(Event newComm){
		return UpdateEventController.getInstance().getUpdatedEvent().equals(newComm);
	}
}

