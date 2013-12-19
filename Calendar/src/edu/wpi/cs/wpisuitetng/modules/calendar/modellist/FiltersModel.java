/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors: CalDev
 * 
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.modellist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;



import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;


/**List of Filters being pulled from the server
 * 
 *
 * @version $Revision: 1.0 $
 * @author Tommzy
 */
@SuppressWarnings("serial")
public class FiltersModel extends AbstractListModel{
	
	/** The Filters. */
	private List<Filter> filters;
	
	/** The next id. */
	private int nextID; // the next available ID number for the filters that are added.
	
	//the static object to allow the filter model to be 
	/** The instance. */
	private static FiltersModel instance; 

	/**
	 * Instantiates a new events model.
	 */
	private FiltersModel (){
		this.filters = new ArrayList<Filter>();
		nextID = 0;
	}
	
	
	
	/**
	 * Gets the single instance of FiltersModel.
	 *
	 * @return single instance of FiltersModel
	 */
	public static FiltersModel getInstance()
	{
		if(instance == null)
		{
			instance = new FiltersModel();
		}
		
		return instance;
	}
	
	/**
	 * Adds the event.
	 *
	 * @param newFilter the new event
	 */
	public void addFilter(Filter newFilter){
		
		this.filters.add(newFilter);
//		
//		for (int i = 0; i < Filters.size(); i++) {
//			System.out.println("Filter out put    " + Filters.get(i).toString());
//		}
	}
	
	/**
	 * Gets the event.
	 *
	 * @param id the id
	 * @return the event
	 */
	public Filter getFilter(int id)
	{
		Filter temp = null;
		// iterate through list of Filters until id is found
		for (int i=0; i < this.filters.size(); i++){
			temp = filters.get(i);
			if (temp.getId() == id){
				break;
			}
		}
		return temp;
	}
	
	/**
	 * Gets the all events.
	 *
	 * @return the all events
	 */
	public List<Filter> getAllFilter() {
		return filters;
	}
	
	/**
	 * Removes the event.
	 *
	 * @param removeId the remove id
	 */
	public void removeFilter(int removeId){
		// iterate through list of Filters until id of project is found
		for (int i=0; i < this.filters.size(); i++){
			if (filters.get(i).getId() == removeId){
				// remove the id
				filters.remove(i);
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return filters.size();
	}
	
	/**
	 * Gets the next id.
	 *
	 * @return the next id
	 */
	public int getNextID()
	{
		
		return this.nextID++;
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Filter getElementAt(int index) {
		return filters.get(filters.size() - 1 - index);
	}

	/**
	 * Empty model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Filter> iterator = filters.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
	}
	
	/**
	 * Adds the Filters.
	 *
	 * @param newfilters the events
	 */
	public void addFilters(Filter[] newfilters) {
		for (int i = 0; i < newfilters.length; i++) {
			this.filters.add(newfilters[i]);
			if(newfilters[i].getId() >= nextID) nextID = newfilters[i].getId() + 1;
		}
	}

	/**
	 * Gets the children.
	 *
	 * @param newFilter the event
	 * @return the Filter list
	 */
	public List<Filter> getChildren(Filter newFilter) {
		List<Filter> children = new ArrayList<Filter>();
		
		return children;
	}
	
	/**
	 * Gets the possible children.
	 *
	 * @param req the req
	 */
	public ListModel<Filter> getPossibleChildren(Filter req)
	{
		DefaultListModel<Filter> possibleChildren = new DefaultListModel<Filter>();
		
		for(Filter possChild : filters)
		{
			possibleChildren.addElement(possChild);
		}
		
		return possibleChildren;
	}
	
	
	/**
	 * Gets the possible parents.
	 *
	 * @param req the req
	 * @return the possible parents
	 */
	public ListModel<Filter> getPossibleParents(Filter req)
	{
		DefaultListModel<Filter> possibleParents = new DefaultListModel<Filter>();
		
		for(Filter possParent : filters)
		{
			if(possParent == req) continue;
			possibleParents.addElement(possParent);
		}
		
		return possibleParents;
	}

//	/**
//	 * Returns the list of Filters that are assigned to the given iteration
//	 * @param name the iteration name
//	
//	 * @return the list of Filters */
//	public List<Filter> getFiltersForIteration(String name) {
//		List<Filter> reqForIteration = new LinkedList<Filter>();
//		
//		boolean backlog = false;
//		if(name.trim().length() == 0) backlog = true;
//		
//		for(Filter req : Filters)
//		{
//			if(backlog)
//			{
//				if(req.getIteration().equals("Backlog") || req.getIteration().trim().equals(""))
//				{
//					reqForIteration.add(req);
//				}
//			}
//			else
//			{
//				if(req.getIteration().equals(name))
//				{
//					reqForIteration.add(req);
//				}
//			}
//		}
//		
//		return reqForIteration;
//	}

	
	
	
	/**
	
	 * @return the Filters held within the EventsModel. */
	public List<Filter> getFilters() {
		return filters;
	}
}