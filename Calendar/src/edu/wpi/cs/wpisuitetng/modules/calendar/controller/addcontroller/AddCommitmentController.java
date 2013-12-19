/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.CommitmentsModel;
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

	/** Reference to the Commitments model */
	private final CommitmentsModel model;
	
	/** The new commitment to add */
	private final Commitment commitmentToBeAdded;
	
	/** The observer associated with this controller */
	AddCommitmentRequestObserver observer = new AddCommitmentRequestObserver(this);
	
	/**
	 * Construct an AddCommitmentController for the given model, view pair
	 * @param model the model containing the messages
	 * @param viewCommitment the view where the user enters new messages
	 */
	public AddCommitmentController(CommitmentsModel model, Commitment commitmentToBeAdded) {
		this.model = model;
		this.commitmentToBeAdded = commitmentToBeAdded;
	}


	/**
	 * Make and send the request for adding the commitment to the server.
	 * @param commit the commitment to add to the database
	 */
	public void addCommitmentToDatabase(Commitment commit){
		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.PUT); // PUT == create
		request.setBody(commit.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddCommitmentRequestObserver(this)); // add an observer to process the response
		request.send();
	}

	/**
	 * This method is called when the user clicks the Submit button
	 * @param event The event for the action
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Send a request to the core to save this message
		// Add the message to the model
		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.PUT); // PUT == create
		request.setBody(commitmentToBeAdded.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddCommitmentRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		System.out.println("from AddCommitmentController." + request.getBody());
	}


	/**
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param item the category to add to the local model
	 */
	public void addCommitmentToModel(Commitment item) {
		model.addCommitment(item);
	}
}
