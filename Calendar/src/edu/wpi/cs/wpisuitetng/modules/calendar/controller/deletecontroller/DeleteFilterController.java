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
 * This controller coordinates delete Filter
 * from the server.
 * @version $Revision: 1.0 $
 * @author Jared
 */
public class DeleteFilterController implements ActionListener {
	
	private DeleteFilterRequestObserver observer ;
	private int id;


	public DeleteFilterController(int id) {
		this.id = id;
		observer = new DeleteFilterRequestObserver(this);
	}
	
	//getter
	public int getID(){
		return id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to delete this Filter
		
		final Request request = Network.getInstance().makeRequest("calendar/filter/" + Integer.toString(id), HttpMethod.DELETE); // DELETE == delete
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is DeleteFilterController.actionPerformed" + "   "+ request.getBody());
		request.send(); // send the request
		}
	
//	/**
//	 * Sends an HTTP request to retrieve all filters
//	 */
//	public void retrieveFilters() {
//		final Request request = Network.getInstance().makeRequest("calendar/filter", HttpMethod.GET); // GET == read
//		request.addObserver(observer); // add an observer to process the response
//		request.send(); // send the request
//	}
	
}
