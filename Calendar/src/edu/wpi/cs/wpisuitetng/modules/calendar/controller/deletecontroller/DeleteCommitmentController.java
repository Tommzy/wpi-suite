/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller.deletecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates delete commitment
 * from the server.
 * @version $Revision: 1.0 $
 * @author Hui Zheng & Andrew Paon & Mark & Chris turner & Jared
 */
public class DeleteCommitmentController implements ActionListener {
	
	/** The observer associated with this controller */
	private DeleteCommitmentRequestObserver observer ;
	
	/** The id of the commitment to delete */
	final private int id;

	/**
	 * Constructor - set the id to delete by.
	 * @param id the id of the commitment to delete
	 */
	public DeleteCommitmentController(int id) {
		this.id = id;
		observer = new DeleteCommitmentRequestObserver(this);
	}
	
	/**
	 * Getter function for the id of the commitment to delete.
	 * @return the id of the commitment
	 */
	public int getID(){
		return id;
	}

	/**
	 * This is called when the user clicks the delete button
	 * @param e the ActionEvent passed to this method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to delete this commitment
		final Request request = Network.getInstance().makeRequest("calendar/commitment/" + Integer.toString(id), HttpMethod.DELETE); // DELETE == delete
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is DeleteCommitmentController.actionPerformed" + "   "+ request.getBody());
		request.send(); // send the request
		}
}
