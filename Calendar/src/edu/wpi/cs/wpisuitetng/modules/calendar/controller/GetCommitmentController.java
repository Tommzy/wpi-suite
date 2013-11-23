package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class GetCommitmentController implements ActionListener {


	private final CommitmentsModel model;

	public GetCommitmentController(CommitmentsModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("Commitment/Commitmentmessage", HttpMethod.GET); // GET == read
		request.addObserver(new GetCommitmentRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
	}
	
	/**
	 * Add the given Commitments to the local model (they were received from the core).
	 * This method is called by the GetCommitmentsRequestObserver
	 * 
	 * @param Commitments an array of Commitments received from the server
	 */
	public void receivedCommitments(Commitment[] Commitments) {
		// Empty the local model to eliminate duplications
		model.emptyModel();
		
		// Make sure the response was not null
		if (Commitments != null) {
			
			// add the Commitments to the local model
			model.addCommitments(Commitments);
		}
	}
	

	}
	
	

}

