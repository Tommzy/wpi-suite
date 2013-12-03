
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

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request
 * to the server to add a commitment.
 * @version $Revision: 1.0 $
 * @author Hui Zheng & EJ & Jared
 */
public class AddCommitmentRequestObserver implements RequestObserver {

	private final AddCommitmentController controller;
	
	public AddCommitmentRequestObserver(AddCommitmentController controller) {
		this.controller = controller;
	}
	public Commitment testItem;

	
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
		
		//Parse the calendar item out of the response body
		final Commitment item = CalendarItem.fromJSON(response.getBody());
		//Pass the messaged back to the controller
		//Needs to put commitment back into the system
		//TODO
		//controller.addCommitmentToModel(item);
		System.out.print("From AddCommitmentObserver." + response.getBody());
		
	} 
	
	public Commitment testReturn(){
		return testItem;
	}

	@Override
	public void responseError(IRequest iReq) {
		System.err.println("The request to add a commitment failed.");	
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to add a commitment failed.");
	}
}
