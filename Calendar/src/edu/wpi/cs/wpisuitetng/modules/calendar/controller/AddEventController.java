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

	private final EventsModel model;
	private final Event eventToBeAdded;
	 
	//Event testCommit1 = new Event("First test",new GregorianCalendar(1992,8,19,23,4),"Success ><!");
	 
	/**
	 * Construct an AddEventController for the given model, view pair
	 * @param model the model containing the messages
	 * @param viewEvent the view where the user enters new messages
	 */
	public AddEventController(EventsModel model, Event eventToBeAdded) {
		this.model = model;
		this.eventToBeAdded = eventToBeAdded;
	}
  
	AddEventRequestObserver observer = new AddEventRequestObserver(this);
	
//	public void addTestToDatabase(){
//		final Request request = Network.getInstance().makeRequest("calendar/Event", HttpMethod.PUT); // PUT == create
//		request.setBody(testCommit1.toJSON()); // put the new message in the body of the request
//		request.addObserver(observer); // add an observer to process the response
//		request.send();
//	}
	
	public Event testReturn(){
		return observer.testReturn();
	}
	
	 
	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//addTestToDatabase();
////		System.out.println("this is event!-s---->" + event.getActionCommand().toString());
//		// Get the text that was entered
//		String name = (String) eventDetails.get("name");
//		GregorianCalendar startTime = (GregorianCalendar) eventDetails.get("startDateTime");
//		GregorianCalendar endTime = (GregorianCalendar) eventDetails.get("startDateTime");
//		String description = (String) eventDetails.get("desc");
//		String invitee = (String) eventDetails.get("invitee");
//		 
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		
		
		// Send a request to the core to save this message
//		if(name.length() > 0){
//			Event sentEvent = new Event(name, startTime, description);
//			// Add the message to the model
//			final Request request = Network.getInstance().makeRequest("calendar/event", HttpMethod.PUT); // PUT == create
//			request.setBody(sentEvent.toJSON()); // put the new message in the body of the request
//			request.addObserver(new AddEventRequestObserver(this)); // add an observer to process the response
//			request.send(); // send the request
//			System.out.println("from AddEventController." + request.getBody());
//		}
		
		final Request request = Network.getInstance().makeRequest("calendar/event", HttpMethod.PUT); // PUT == create
		request.setBody(eventToBeAdded.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddEventRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request

	}


	/** ATTENTION AT THIS PART
	 * When the new Event is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addEventToModel(Event item) {
		model.addEvent(item);
	}
}


