package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class GetCommitmentRequestObserver implements RequestObserver{

	public GetCommitmentController controller;
	
	
	public GetCommitmentRequestObserver(GetCommitmentController controller) {
		this.controller = controller;
	}

	
	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		Commitment[] items = Commitment.fromJsonArray(iReq.getResponse().getBody());
		//TODO
		//put this back in
	
		System.out.println("Success! Here is GetCommitmentRequestController in the JSON way"+ "   " + iReq.getResponse().getBody());
		System.out.println("Success! Here is GetCommitmentRequestController in object way "+ "   " + items[0]+items[3]+items[4].getName()+items[4].getStartTime());
		System.out.println("Success! Here is GetCommitmentRequestController in object to string way "+ "   " + items.toString()+ items[0].toString()+items[3].toString()+items[4].getName().toString()+items[4].getStartTime().toString());

		controller.receivedCommitments(items);

		
	}
	
	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
		System.err.println("The request to get commitments Errored. " + iReq.toString());
	}


	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
		Commitment[] errorCommitment = { new Commitment("Error", null, "Error") };
		controller.receivedCommitments(errorCommitment);
		System.err.println("The request to get commitments Errored.");
		
	}


}

