

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateCommitmentController.
 */
public class UpdateCommitmentController implements ActionListener{
	
	/** The instance. */
	private static UpdateCommitmentController instance;
	
	/** The observer. */
	private UpdateCommitmentRequestObserver observer;
	
	/** The updated commitment. */
	private static Commitment updatedCommitment;
	
	/**
	 * Instantiates a new update commitment controller.
	 *
	 * @param updatedCommitment the updated commitment
	 */
	private UpdateCommitmentController(Commitment updatedCommitment) {
		observer = new UpdateCommitmentRequestObserver(this);
		this.updatedCommitment = updatedCommitment;
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
			instance = new UpdateCommitmentController(updatedCommitment);
		}
		
		return instance;
	}
	
	/**
	 * Gets the updated commitment.
	 *
	 * @return the updated commitment
	 */
	public Commitment getUpdatedCommitment(){
		return UpdateCommitmentController.getInstance().updatedCommitment;
	}

	/**
	 * Update commitment.
	 *
	 * @param newCommitment the new commitment
	 */
	public void updateCommitment(Commitment newCommitment) 
	{
		Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.POST); // POST == update
		request.setBody(newCommitment.toJSON()); // put the new commitment in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// get commitment
		UpdateCommitmentController.getInstance()
		.updateCommitment(UpdateCommitmentController.getInstance()
		.getUpdatedCommitment());
		
	}
	
	/**
	 * Update sucess.
	 *
	 * @param newComm the new commitment
	 * @return true, if successful
	 */
	public boolean updateSucess(Commitment newComm){
		return UpdateCommitmentController.getInstance().getUpdatedCommitment().equals(newComm);
	}
}

