package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CommitmentListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddCommitmentController implements ActionListener{

	private final CommitmentListModel model;
	private final AddCommitmentPanel viewCommitment;
	
	/**
	 * Construct an AddMessageController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddCommitmentController(CommitmentListModel model, AddCommitmentPanel viewCommitment) {
		this.model = model;
		this.viewCommitment = viewCommitment;
	}

	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the text that was entered
		String name = viewCommitment.getTxtNewname();
		GregorianCalendar startTime = null;
		try {
			startTime = viewCommitment.getNewDate("startTime");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String description = viewCommitment.getNewDescription();
		
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		
		//don't want to make a new event if there is no name
		if(name.length() > 0){
			Commitment sentCommitment = new Commitment(name, startTime, description);
			// Add the message to the model
			model.addCommitment(sentCommitment);
		}
		
		// Send a request to the core to save this message
		if(name.length() > 0){
			Commitment sentCommitment = new Commitment(name, startTime, description);
			// Add the message to the model
			final Request request = Network.getInstance().makeRequest("calendar/commitmentlist", HttpMethod.PUT); // PUT == create
			request.setBody(sentCommitment.toJSON()); // put the new message in the body of the request
			request.addObserver(new AddCommitmentRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}

	}


	/** ATTENTION AT THIS PART
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addCommitmentToModel(CalendarItem item) {
		model.addCommitment(item);
	}
}


