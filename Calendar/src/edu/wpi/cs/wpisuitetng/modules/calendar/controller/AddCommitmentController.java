/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.HashMap;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the commitment text fields to the model as a new
 * requirement.
 * @version $Revision: 1.0 $
 * @author Hui Zheng & EJ & Jared
 */
public class AddCommitmentController implements ActionListener{

	private final CommitmentsModel model;
	private final HashMap<String, Object> commitmentDetails;
	 
	//Commitment testCommit1 = new Commitment("First test",new GregorianCalendar(1992,8,19,23,4),"Success ><!");
	 
	/**
	 * Construct an AddCommitmentController for the given model, view pair
	 * @param model the model containing the messages
	 * @param viewCommitment the view where the user enters new messages
	 */
	public AddCommitmentController(CommitmentsModel model, HashMap<String, Object> commitmentDetails) {
		this.model = model;
		this.commitmentDetails = commitmentDetails;
	}
  
	AddCommitmentRequestObserver observer = new AddCommitmentRequestObserver(this);
	
//	public void addTestToDatabase(){
//		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.PUT); // PUT == create
//		request.setBody(testCommit1.toJSON()); // put the new message in the body of the request
//		request.addObserver(observer); // add an observer to process the response
//		request.send();
//	}
	
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
		
		//addTestToDatabase();
		System.out.println("this is event!----->" + event.getActionCommand().toString());
		// Get the text that was entered
		String name = (String) commitmentDetails.get("name");
		GregorianCalendar startTime = (GregorianCalendar) commitmentDetails.get("startDateTime");
		String description = (String) commitmentDetails.get("desc");
		String invitee = (String) commitmentDetails.get("invitee");
		 
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
			final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.PUT); // PUT == create
			request.setBody(sentCommitment.toJSON()); // put the new message in the body of the request
			request.addObserver(new AddCommitmentRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
			System.out.println("from AddCommitmentController." + request.getBody());
		}


	}


	/** ATTENTION AT THIS PART
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addCommitmentToModel(Commitment item) {
		model.addCommitment(item);
	}
}


