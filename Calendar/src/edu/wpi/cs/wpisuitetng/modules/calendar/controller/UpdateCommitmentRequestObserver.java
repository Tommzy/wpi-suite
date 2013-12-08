

/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: E.J. Murphy, Andrew Paon, Eric Wilcox
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications
 * about UpdateCommitmentRequest information as the UpdateCommitmentRequest is constructed.
 */
public class UpdateCommitmentRequestObserver implements RequestObserver {
	
	/** The controller. */
	private final UpdateCommitmentController controller;
	
	/**
	 * This method is called when information about an UpdateCommitmentRequest
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param controller the controller
	 */
	public UpdateCommitmentRequestObserver(UpdateCommitmentController controller) {
		this.controller = controller;
	}
	
	
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
		
		// Parse the commitment out of the response body
		final Commitment commitment = Commitment.fromJSON(response.getBody());	
		UpdateCommitmentController.getInstance().updateSucess(commitment);
		System.out.println("Success! Here is GetCommitmentRequestController in the JSON way"+ "   " + iReq.getResponse().getBody());
	}
	

	@Override
	public void responseError(IRequest iReq) {
		System.err.println(iReq.getResponse().getStatusMessage());
		System.err.println("The request to update a commitment failed.");
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to update a commitment failed.");
	}

}
