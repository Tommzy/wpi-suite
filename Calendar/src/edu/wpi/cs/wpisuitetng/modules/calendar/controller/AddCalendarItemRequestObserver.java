package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class AddCalendarItemRequestObserver implements RequestObserver	{
	
	AddCalendarItemController controller;
	
	public AddCalendarItemRequestObserver(AddCalendarItemController controller) {
		this.controller = controller;
	}
	
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();

		// Parse the message out of the response body
		final CalendarItem item = CalendarItem.fromJSON(response.getBody());

		// Pass the message back to the controller
		controller.addCalendarItemToModel(item);
	}

	@Override
	public void responseError(IRequest iReq) {
		System.err.println("The request to add a Calendar Item failed.");
		
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to add a Calendar Item failed.");
		
	}

}
