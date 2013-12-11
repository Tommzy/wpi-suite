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

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the requirements
 * from the server.
 *
 * @version $Revision: 1.0 $
 * @author Hui Zheng & EJ & Jared
 */
public class GetCommitmentController implements ActionListener {

	private GetCommitmentRequestObserver observer;

	public GetCommitmentController() {
		observer = new GetCommitmentRequestObserver(this);;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is GetCommitmentController.actionPerformed" + "   "+ request.getBody());
		request.send(); // send the request
		}
	
	/**
	 * Sends an HTTP request to retrieve all requirements
	 */
	public void retrieveCommitments() {
		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is GetCommitmentController.retrieveCommitments() to update the CommitmentsList" + "   "+ request.getBody());
		request.send(); // send the request
	}
	
	/**
	 * Add the given Commitments to the local model (they were received from the core).
	 * This method is called by the GetCommitmentsRequestObserver
	 * 
	 * @param Commitments an array of Commitments received from the server
	 */
	public void receivedCommitments(Commitment[] Commitments) {
//		System.out.println(Commitments.length);
		// Empty the local model to eliminate duplications
		CommitmentsModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (Commitments != null) {
			
			// add the Commitments to the local model
			CommitmentsModel.getInstance().addCommitments(Commitments);
//			System.out.println(CommitmentsModel.getInstance().getAllCommitment().size());
		}
	}

	
	
	

}

