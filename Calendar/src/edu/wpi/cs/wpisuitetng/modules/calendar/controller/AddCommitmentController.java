package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddCommitmentController implements ActionListener{

	private final Commitment model;
	private final AddCommitmentPanel viewCommitment;
	 
	Commitment testCommit1 = new Commitment("First test",new GregorianCalendar(1992,8,19,23,4),"Success ><!");
	 
	/**
	 * Construct an AddMessageController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddCommitmentController(Commitment model, AddCommitmentPanel viewCommitment) {
		this.model = model;
		this.viewCommitment = viewCommitment;

	}
  
	AddCommitmentRequestObserver observer = new AddCommitmentRequestObserver(this);
	
	public void addTestToDatabase(){
		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.PUT); // PUT == create
		request.setBody(testCommit1.toJSON()); // put the new message in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send();
	}
	
	public Commitment testReturn(){
		return observer.testReturn();
	}
	
	 
	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		addTestToDatabase();
		System.out.print(event);
		// Get the text that was entered
		String name = viewCommitment.getTxtNewname();
		GregorianCalendar startTime = null;
		startTime = viewCommitment.getNewDate("startTime");
		String description = viewCommitment.getNewDescription();
		 
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		
//		//don't want to make a new event if there is no name
//		if(name.length() > 0){
//			Commitment sentCommitment = new Commitment(name, startTime, description);
//			// Add the message to the model
//			model.addCommitment(sentCommitment);
//		}
		
		// Send a request to the core to save this message
		if(name.length() > 0){
			Commitment sentCommitment = new Commitment(name, startTime, description);
			// Add the message to the model
			final Request request = Network.getInstance().makeRequest("calendar/calendaritem", HttpMethod.PUT); // PUT == create
			request.setBody(sentCommitment.toJSON()); // put the new message in the body of the request
			request.addObserver(new AddCommitmentRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}


	}


	/** ATTENTION AT THIS PART
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param message
	 */
	/*public void addCommitmentToModel(CalendarItem item) {
		model.addCommitment(item);
	}*/
}


