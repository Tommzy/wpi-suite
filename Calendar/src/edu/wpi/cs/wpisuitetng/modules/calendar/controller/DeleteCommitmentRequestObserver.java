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

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer is called when a response is delete from a request
 * to the server to delete a commitment.
 * @version $Revision: 1.0 $
 * @author Hui Zheng & Andrew Paon & Mark & Chris turner & Jared
 */
public class DeleteCommitmentRequestObserver implements RequestObserver{
	
	public DeleteCommitmentController controller;
	public GetCommitmentController getController;

	
	
	public DeleteCommitmentRequestObserver(DeleteCommitmentController controller) {
		
		this.controller = controller;
		getController = new GetCommitmentController();
	}

	
	/*
	 * Parse the messages out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		//Commitment[] items = Commitment.fromJsonArray(iReq.getResponse().getBody());
		
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		
	
		System.out.println("After delete: Success! Here is DeleteCommitmentRequestController in the JSON way"+ "   " + result);
		
		//TODO
		//Update the view
		getController.retrieveCommitments();
		MainCalendarController.getInstance().updateAll();
		
	}
	
	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
	//	fail(iReq, null);
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());
		System.err.println("After delete: The request to delete commitments Errored. " + result);
	}


	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
//		Commitment[] errorCommitment = { new Commitment("Error", null, "Error") };
//		controller.receivedCommitments(errorCommitment);
		boolean result = Boolean.getBoolean(iReq.getResponse().getBody());

		System.err.println("After delete: The request to delete commitments failed.  " + result);
		
	}
}
