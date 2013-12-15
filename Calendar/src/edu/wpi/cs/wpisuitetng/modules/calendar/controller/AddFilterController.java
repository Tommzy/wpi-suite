package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.HashMap;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.filters.FiltersModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the Filter text fields to the model as a new
 * requirement.
 * @version $Revision: 1.0 $
 * @author Hui Zheng
 */
public class AddFilterController implements ActionListener{

	private final FiltersModel model;
	private final Filter filterToBeAdded;
	 
	//Filter testCommit1 = new Filter("First test",new GregorianCalendar(1992,8,19,23,4),"Success ><!");
	 
	/**
	 * Construct an AddFilterController for the given model, view pair
	 * @param model the model containing the messages
	 * @param viewFilter the view where the user enters new messages
	 */
	public AddFilterController(FiltersModel model, Filter filterToBeAdded) {
		this.model = model;
		this.filterToBeAdded = filterToBeAdded;
	}
  
	AddFilterRequestObserver observer = new AddFilterRequestObserver(this);
	
//	public void addTestToDatabase(){
//		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.PUT); // PUT == create
//		request.setBody(testCommit1.toJSON()); // put the new message in the body of the request
//		request.addObserver(observer); // add an observer to process the response
//		request.send();
//	}
	
	public void addFilterToDatabase(Filter filter){
		final Request request = Network.getInstance().makeRequest("calendar/filter", HttpMethod.PUT); // PUT == create
		request.setBody(filter.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddFilterRequestObserver(this)); // add an observer to process the response
		request.send();
	}
	
	public Filter testReturn(){
		return observer.testReturn();
	}
	
	 
	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//addTestToDatabase();
//		System.out.println("this is event!----->" + event.getActionCommand().toString());
		// Get the text that was entered
//		String name =  commitmentToBeAdded.getName();
//		GregorianCalendar startTime = commitmentToBeAdded.getStartTime();
//		String description = (String) commitmentToBeAdded.getDescription();
//		String invitee = (String) commitmentToBeAdded.getInvitee();
		 
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		
//		//don't want to make a new event if there is no name
//		if(name.length() > 0){
//			Filter sentFilter = new Filter(name, startTime, description);
//			// Add the message to the model
//			model.addFilter(sentFilter);
//		}
		
		// Send a request to the core to save this message
		// Add the message to the model
		final Request request = Network.getInstance().makeRequest("calendar/filter", HttpMethod.PUT); // PUT == create
		request.setBody(filterToBeAdded.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddFilterRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		System.out.println("from AddFilterController." + request.getBody());


	}


	/** ATTENTION AT THIS PART
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addFilterToModel(Filter item) {
		model.addFilter(item);
	}
}