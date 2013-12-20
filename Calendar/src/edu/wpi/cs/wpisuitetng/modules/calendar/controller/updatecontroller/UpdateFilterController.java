

/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jared Wormley
 ******************************************************************************/



package edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateFilterController.
 * @version $Revision: 1.0 $
 * @author Jared
 */
public class UpdateFilterController implements ActionListener{
	
	/** The instance. */
	private static UpdateFilterController instance= getInstance();
	
	/** The observer. */
	final UpdateFilterRequestObserver observer;
	
	/** The updated Filter. */
	private static Filter updatedFilter = getInstance().getUpdatedFilter();
	
	/**
	 * Instantiates a new update Filter controller.
	 *
	 * @param updatedFilter the updated Filter
	 */
	public UpdateFilterController(Filter updatedFilter) {
		observer = new UpdateFilterRequestObserver(this);
		UpdateFilterController.updatedFilter = updatedFilter;
	}
	
	/**
	 * Gets the single instance of UpdateFilterController.
	 *
	 * @return single instance of UpdateFilterController
	 */
	public static UpdateFilterController getInstance()
	{
		if(instance == null)
		{
			instance = new UpdateFilterController(updatedFilter);
		}
		
		return instance;
	}
	
	/**
	 * Gets the updated Filter.
	 *
	 * @return the updated Filter
	 */
	public Filter getUpdatedFilter(){
		return UpdateFilterController.updatedFilter;
	}

	/**
	 * Update Filter.
	 *
	 * @param newFilter the new Filter
	 */
	public void updateFilter(Filter newFilter) 
	{
		final Request request = Network.getInstance().makeRequest("calendar/Filter", HttpMethod.POST); // POST == update
		request.setBody(newFilter.toJSON()); // put the new Filter in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// get Filter
		UpdateFilterController.getInstance()
		.updateFilter(UpdateFilterController.getInstance()
		.getUpdatedFilter());
		
	}
	
	/**
	 * Update sucess.
	 *
	 * @param newComm the new Filter
	 * @return true, if successful
	 */
	public boolean updateSucess(Filter newComm){
		return UpdateFilterController.getInstance().getUpdatedFilter().equals(newComm);
	}
}

