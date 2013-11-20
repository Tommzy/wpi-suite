package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItemListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddCalendarItemController implements ActionListener{

	private final CalendarItemListModel model;
	private final AddEventPanel viewEvent;
	private final AddCommitmentPanel viewCommitment;
	
	/**
	 * Construct an AddMessageController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddCalendarItemController(CalendarItemListModel model, AddEventPanel viewEvent, AddCommitmentPanel viewCommitment) {
		this.model = model;
		this.viewEvent = viewEvent;
		this.viewCommitment = viewCommitment;
	}

	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the text that was entered
		String name = viewEvent.getTxtNewname();
		GregorianCalendar startTime = null;
		try {
			startTime = viewEvent.getNewDate("startTime");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		GregorianCalendar endTime = null;
		try {
			endTime = viewEvent.getNewDate("endTime");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String location = viewEvent.getNewLocation();
		String description = viewEvent.getNewDescription();
		
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		
		//don't want to make a new event if there is no name
		if ((name.length() > 0) & (endTime != null)) {
<<<<<<< HEAD
			Event sentEvent = new Event(name, startTime, endTime, location, description);
			// Add the message to the model
			model.addCalendarItem(sentEvent);//
		}else if((name.length() > 0) & (endTime == null)){
			Commitment sentCommitment = new Commitment(name, startTime,location, description);
=======
			Event sentEvent = new Event(name, startTime, endTime, description);
			// Add the message to the model
			model.addCalendarItem(sentEvent);//
		}else if((name.length() > 0) & (endTime == null)){
			Commitment sentCommitment = new Commitment(name, startTime, description);
>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3
			// Add the message to the model
			model.addCalendarItem(sentCommitment);
		}
		
		// Send a request to the core to save this message
		if ((name.length() > 0) & (endTime != null)) {
<<<<<<< HEAD
			Event sentEvent = new Event(name, startTime, endTime, location, description);
			final Request request = Network.getInstance().makeRequest("postboard/postboardmessage", HttpMethod.PUT); // PUT == create
			request.setBody(new Event(name, startTime, endTime, location, description).toJSON()); // put the new message in the body of the request
			request.addObserver(new AddCalendarItemRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}else if((name.length() > 0) & (endTime == null)){
			Commitment sentCommitment = new Commitment(name, startTime,location, description);
			// Add the message to the model
			final Request request = Network.getInstance().makeRequest("postboard/postboardmessage", HttpMethod.PUT); // PUT == create
			request.setBody(new Event(name, startTime, endTime, location, description).toJSON()); // put the new message in the body of the request
=======
			Event sentEvent = new Event(name, startTime, endTime, description);
			final Request request = Network.getInstance().makeRequest("calendar/addcalendaritem", HttpMethod.PUT); // PUT == create
			request.setBody(sentEvent.toJSON()); // put the new message in the body of the request
			request.addObserver(new AddCalendarItemRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}else if((name.length() > 0) & (endTime == null)){
			Commitment sentCommitment = new Commitment(name, startTime, description);
			// Add the message to the model
			final Request request = Network.getInstance().makeRequest("calendar/addcalendaritem", HttpMethod.PUT); // PUT == create
			request.setBody(sentCommitment.toJSON()); // put the new message in the body of the request
>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3
			request.addObserver(new AddCalendarItemRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}

	}
<<<<<<< HEAD
	
	public void actionAddCommitment(ActionEvent commitment) {
		// Get the text that was entered
		String name = viewCommitment.getTxtNewname();
		Date startTime = viewCommitment.getNewDate("startTime");
		String location = viewCommitment.getNewLocation();
		String description = viewCommitment.getNewDescription();
		
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		
		//don't want to make a new event if there is no name
		if (name.length() > 0) {
			Commitment sentCommitment = new Commitment(name, startTime, location, description);
			// Add the message to the model
			model.addCalendarItem(sentCommitment);
		}
	}
=======
>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3


	/** ATTENTION AT THIS PART
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addCalendarItemToModel(CalendarItem item) {
		model.addCalendarItem(item);
	}
<<<<<<< HEAD

}
=======
}
>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3
