

/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:E.J. Murphy, Andrew Paon, Eric Wilcox
 ******************************************************************************/



package edu.wpi.cs.wpisuitetng.modules.calendar.controller;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateCommitmentController.
 */
public class UpdateCommitmentController{
	
	/** The instance. */
	private static UpdateCommitmentController instance;
	
	/** The observer. */
	private UpdateCommitmentRequestObserver observer;
	
	/**
	 * Instantiates a new update commitment controller.
	 */
	private UpdateCommitmentController() {
		observer = new UpdateCommitmentRequestObserver(this);
	}
	
	/**
	 * Gets the single instance of UpdateCommitmentController.
	 *
	 * @return single instance of UpdateCommitmentController
	 */
	public static UpdateCommitmentController getInstance()
	{
		if(instance == null)
		{
			instance = new UpdateCommitmentController();
		}
		
		return instance;
	}

	/**
	 * Update Commitment.
	 *
	 * @param newCommitment the new commitment
	 */
	public void updateCommitment(Commitment newCommitment) 
	{
		Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.POST); // POST == update
		request.setBody(newCommitment.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}

