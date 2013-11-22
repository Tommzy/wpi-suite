package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class GetCommitmentController implements ActionListener {


	private GetCommitmentRequestObserver observer;
	private static GetCommitmentController instance;
		
	
	/**
	 * Constructs the controller given a RequirementModel
	 */
	private GetCommitmentController() {
		
		observer = new GetCommitmentRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the GetRequirementController or creates one if it does not
	 * exist. */
	public static GetCommitmentController getInstance()
	{
		if(instance == null)
		{
			instance = new GetCommitmentController();
		}
		
		return instance; 
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {


		// Send a request to the core to save this requirement
		final Request request = Network.getInstance().makeRequest(
				"calendar/commitment", HttpMethod.GET); // GET == read
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
	/*public void receivedMessages(CalendarItem[] items) {
		// Empty the local model to eliminate duplications
		CommitmentListModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (items != null) {
			
			// add the requirements to the local model
			CommitmentListModel.getInstance().addCommitments(items);
		}
	}*/
	
	

}

