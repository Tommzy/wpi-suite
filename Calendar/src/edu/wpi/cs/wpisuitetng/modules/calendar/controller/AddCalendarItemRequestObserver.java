package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

<<<<<<< HEAD
public class AddCalendarItemRequestObserver implements RequestObserver	{
	
	AddCalendarItemController controller;
=======
public class AddCalendarItemRequestObserver implements RequestObserver {

	private final AddCalendarItemController controller;
>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3
	
	public AddCalendarItemRequestObserver(AddCalendarItemController controller) {
		this.controller = controller;
	}
<<<<<<< HEAD
	
=======

>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
<<<<<<< HEAD

		// Parse the message out of the response body
		final CalendarItem item = CalendarItem.fromJSON(response.getBody());

		// Pass the message back to the controller
=======
		
		//Parse the calendar item out of the response body
		final CalendarItem item = CalendarItem.fromJSON(response.getBody());
		
		//Pass the messaged back to the controller
>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3
		controller.addCalendarItemToModel(item);
	}

	@Override
	public void responseError(IRequest iReq) {
<<<<<<< HEAD
		System.err.println("The request to add a Calendar Item failed.");
		
=======
		System.err.println("The request to add a calendar item failed.");	
>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
<<<<<<< HEAD
		System.err.println("The request to add a Calendar Item failed.");
		
=======
		System.err.println("The request to add a calendar item failed.");
>>>>>>> c1fa8dade0b6e737ab6e21a33fcb1de5e671feb3
	}
}
