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

import edu.wpi.cs.wpisuitetng.modules.calendar.filters.FiltersModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItem;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the requirements
 * from the server.
 *
 * @version $Revision: 1.0 $
 * @author Jared Wormley
 */
public class GetFilterController implements ActionListener {

	private GetFilterRequestObserver observer;

	public GetFilterController() {
		observer = new GetFilterRequestObserver(this);;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("calendar/filter", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is GetFilterController.actionPerformed" + "   "+ request.getBody());
		request.send(); // send the request
		}
	
	/**
	 * Sends an HTTP request to retrieve all requirements
	 */
	public void retrieveFilters() {
		final Request request = Network.getInstance().makeRequest("calendar/filter", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		System.out.println("Here is GetFilterController.retrieveFilters() to update the FiltersList" + "   "+ request.getBody());
		request.send(); // send the request
	}
	
	/**
	 * Add the given Filters to the local model (they were received from the core).
	 * This method is called by the GetFiltersRequestObserver
	 * 
	 * @param Filters an array of Filters received from the server
	 */
	public void receivedFilters(Filter[] Filters) {
//		System.out.println(Filters.length);
		// Empty the local model to eliminate duplications
		FiltersModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (Filters != null) {
			
			// add the Filters to the local model
			FiltersModel.getInstance().addFilters(Filters);
//			System.out.println(FiltersModel.getInstance().getAllFilter().size());
		}
	}

	
	
	

}

