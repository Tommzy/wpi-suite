package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItemListModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class GetCalendarItemController implements ActionListener{
	
	private final CalendarItemListModel model;

	public GetCalendarItemController(CalendarItemListModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("postboard/postboardmessage", HttpMethod.GET); // GET == read
		request.addObserver(new GetCalendarItemRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
	}
	
	/**
	 * Add the given messages to the local model (they were received from the core).
	 * This method is called by the GetMessagesRequestObserver
	 * 
	 * @param messages an array of messages received from the server
	 */
	public void receivedMessages(CalendarItem[] messages) {
		// Empty the local model to eliminate duplications
		model.emptyModel();
		
		// Make sure the response was not null
		if (messages != null) {
			
			// add the messages to the local model
			model.addCalendarItems(messages);
		}
	}

}
