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
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates delete category
 * from the server.
 * @version $Revision: 1.0 $
 * @author Jared
 */
public class DeleteCategoryController implements ActionListener {

	/** The observer associated with this controller */
	private DeleteCategoryRequestObserver observer ;
	
	/** The id of the category to delete */
	private int id;


	public DeleteCategoryController(int id) {
		this.id = id;
		this.observer = new DeleteCategoryRequestObserver(this);
	}
	
	/**
	 * Getter function for id
	 * @return id the id to delete
	 */
	public int getID(){
		return id;
	}

	/**
	 * Called when the user clicks the delete button
	 * @param e the ActionEvent passed to this listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to delete this category
		final Request request = Network.getInstance().makeRequest("calendar/catgory" + Integer.toString(id), HttpMethod.DELETE); // DELETE == delete
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is DeleteCategoryController.actionPerformed" + "   "+ request.getBody());
		request.send(); // send the request
	}
}
