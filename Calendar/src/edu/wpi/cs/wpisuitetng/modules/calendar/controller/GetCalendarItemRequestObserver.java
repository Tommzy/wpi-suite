package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class GetCalendarItemRequestObserver implements RequestObserver{

	public GetCalendarItemController controller;
	
	
	public GetCalendarItemRequestObserver(GetCalendarItemController controller) {
		this.controller = controller;
	}

	
	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		CalendarItem[] items = CalendarItem.fromJsonArray(iReq.getResponse().getBody());
		controller.receivedMessages(items);
	}
	
	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
	}


	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
		
	}

//	/*
//	 * Put an error message in the PostBoardPanel if the request fails.
//	 * 
//	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
//	 */
//	@Override
//	public void fail(IRequest iReq, Exception exception) {
//		CalendarItem[] errorMessage = {new CalendarItem()};
//		controller.receivedMessages(errorMessage);
//		
//	}

}
