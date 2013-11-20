package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItemListModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class GetCalendarItemController implements ActionListener {


	private GetCalendarItemRequestObserver observer;
	private static GetCalendarItemController instance;
		
	
	/**
	 * Constructs the controller given a RequirementModel
	 */
	private GetCalendarItemController() {
		
		observer = new GetCalendarItemRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the GetRequirementController or creates one if it does not
	 * exist. */
	public static GetCalendarItemController getInstance()
	{
		if(instance == null)
		{
			instance = new GetCalendarItemController();
		}
		
		return instance;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {


		// Send a request to the core to save this requirement
		final Request request = Network.getInstance().makeRequest(
				"Calendar/CalendarItem", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the
										// response
		request.send(); // send the request

	}

	/**
	 * Add the given messages to the local model (they were received from the
	 * core). This method is called by the GetMessagesRequestObserver
	 * 
	 * @param messages
	 *            an array of messages received from the server
	 */
	public void receivedMessages(CalendarItem[] items) {
		// Empty the local model to eliminate duplications
		CalendarItemListModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (items != null) {
			
			// add the requirements to the local model
			CalendarItemListModel.getInstance().addCalendarItems(items);
		}
	}
	
	

}
