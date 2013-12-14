package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request
 * to the server to add a filter.
 * @version $Revision: 1.0 $
 * @author Hui Zheng
 */
public class AddFilterRequestObserver implements RequestObserver {

	private final AddFilterController controller;
	
	public AddFilterRequestObserver(AddFilterController controller) {
		this.controller = controller;
	}
	public Filter testItem;

	
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
		
		//Parse the calendar item out of the response body
		final Filter item = Filter.fromJSON(response.getBody());
		//Pass the messaged back to the controller
		//Needs to put commitment back into the system
		//TODO
		controller.addFilterToModel(item);
		MainCalendarController.getInstance().updateAll();
		System.out.print("From AddFilterObserver." + response.getBody());
		
	} 
	
	public Filter testReturn(){
		return testItem;
	}

	@Override
	public void responseError(IRequest iReq) {
		System.err.println(iReq.getBody());
		System.err.println(iReq.getResponse().getStatusMessage());
		System.err.println("The request to add a commitment errored.");	
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to add a commitment failed.");
	}
}