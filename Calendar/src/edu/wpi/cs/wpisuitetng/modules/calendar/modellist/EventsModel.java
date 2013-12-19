/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: E.J. Murphy
 * Based on Source Code from EventsModels
 * V1.0
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



// TODO: Auto-generated Javadoc
/**
 * The Class EventsModel.
 */
@SuppressWarnings("serial")
public class EventsModel extends AbstractListModel{
	
	/** The Events. */
	private List<Event> Events;
	
	/** The next id. */
	private int nextID; // the next available ID number for the Events that are added.
	
	//the static object to allow the Event model to be 
	/** The instance. */
	private static EventsModel instance; 

	/**
	 * Instantiates a new events model.
	 */
	private EventsModel (){
		this.Events = new ArrayList<Event>();
		nextID = 0;
	}
	
	
	
	/**
	 * Gets the single instance of EventsModel.
	 *
	 * @return single instance of EventsModel
	 */
	public static EventsModel getInstance()
	{
		if(instance == null)
		{
			instance = new EventsModel();
		}
		
		return instance;
	}
	
	/**
	 * Adds the event.
	 *
	 * @param newEvent the new event
	 */
	public void addEvent(Event newEvent){
		
		this.Events.add(newEvent);
//		
//		for (int i = 0; i < Events.size(); i++) {
//			System.out.println("Event out put    " + Events.get(i).toString());
//		}
	}
	
	/**
	 * Gets the event.
	 *
	 * @param id the id
	 * @return the event
	 */
	public Event getEvent(int id)
	{
		Event temp = null;
		// iterate through list of Events until id is found
		for (int i=0; i < this.Events.size(); i++){
			temp = Events.get(i);
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
	public List<Event> getAllEvent() {
//		for (int i = 0; i < Events.size(); i++) {
//			System.out.println("Event out put    " + Events.get(i).toString());
//		}
		return Events;
	}
	
	/**
	 * Removes the event.
	 *
	 * @param removeId the remove id
	 */
	public void removeEvent(int removeId){
		// iterate through list of Events until id of project is found
		for (int i=0; i < this.Events.size(); i++){
			if (Events.get(i).getId() == removeId){
				// remove the id
				Events.remove(i);
				break;
			}
		}
//		try {
//			ViewEventController.getInstance().refreshTable();
//			ViewEventController.getInstance().refreshTree();
//		}
//		catch(Exception e) {}
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return Events.size();
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
	public Event getElementAt(int index) {
		return Events.get(Events.size() - 1 - index);
	}

	/**
	 * Empty model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Event> iterator = Events.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
//		try{
//			ViewEventController.getInstance().refreshTable();
//			ViewEventController.getInstance().refreshTree();
//		}
//		catch (Exception e) {}
	}
	
	/**
	 * Adds the Events.
	 *
	 * @param Events the events
	 */
	public void addEvents(Event[] Events) {
		for (int i = 0; i < Events.length; i++) {
			this.Events.add(Events[i]);
			if(Events[i].getId() >= nextID) nextID = Events[i].getId() + 1;
		}
		//this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
		
		
		//************Refresh GUI HERE!!!!!!!!!!!!!!!!!!!!!!!!!***************************************
//		ViewEventController.getInstance().refreshTable();
//		ViewEventController.getInstance().refreshTree();
	}



	/**
	 * Gets the children.
	 *
	 * @param Event the event
	 * @return the event list
	 */
	public List<Event> getChildren(Event Event) {
		List<Event> children = new ArrayList<Event>();
		
		for(Event possibleChild : Events)
		{
//			if(possibleChild.getParentID() == Event.getId()) children.add(possibleChild);
		}
		
		return children;
	}
	
	/**
	 * Gets the possible children.
	 *
	 * @param req the req
	 */
	public ListModel<Event> getPossibleChildren(Event req)
	{
		DefaultListModel<Event> possibleChildren = new DefaultListModel<Event>();
		
		for(Event possChild : Events)
		{
//			if(possChild.isAncestor(req.getId()) || possChild.getParentID() != -1) continue;
//			if(possChild == req) continue;
//			if(possChild.getStatus() == EventStatus.COMPLETE || possChild.getStatus() == EventStatus.DELETED) continue;
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
	public ListModel<Event> getPossibleParents(Event req)
	{
		DefaultListModel<Event> possibleParents = new DefaultListModel<Event>();
		
		for(Event possParent : Events)
		{
//			if(possParent.hasAncestor(req.getId())) continue;
			if(possParent == req) continue;
//			if(possParent.getStatus() == EventStatus.COMPLETE || possParent.getStatus() == EventStatus.DELETED) continue;
			possibleParents.addElement(possParent);
		}
		
		return possibleParents;
	}

//	/**
//	 * Returns the list of Events that are assigned to the given iteration
//	 * @param name the iteration name
//	
//	 * @return the list of Events */
//	public List<Event> getEventsForIteration(String name) {
//		List<Event> reqForIteration = new LinkedList<Event>();
//		
//		boolean backlog = false;
//		if(name.trim().length() == 0) backlog = true;
//		
//		for(Event req : Events)
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
	
	 * @return the Events held within the EventsModel. */
	public List<Event> getEvents() {
		return Events;
	}
}
