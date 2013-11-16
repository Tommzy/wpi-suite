/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Yuchen Zhang
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;



/**
 * This is a model for CalendarModel. It contains all the variables to be
 * displayed on the view.
 * 
 * @author Team3
 * 
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class CalendarItemListModel extends AbstractListModel {

	/** The list of messages on the board */
	List<CalendarItem> itemList;

	/**
	 * Constructs a new CalendarItemList with no messages.
	 */
	public CalendarItemListModel() {
		itemList = new ArrayList<CalendarItem>();

	}

	/**add new calendar item to the list. 
	 * ToDo: through exception
	 * @param newItem
	 */
	public void addCalendarItem(CalendarItem newItem) {
		// Add the message
			itemList.add(newItem);

		
		// Notify the model that it has changed so the GUI will be udpated
		this.fireIntervalAdded(this, 0, 0);
	}
	
	
	/**
	 * Adds the given array of CalendarItem to the list
	 * 
	 * @param itemArray the array of CalendarItem to add
	 */
	public void addCalendarItems(CalendarItem[] itemArray) {
		for (int i = 0; i < itemArray.length; i++) {
				this.itemList.add(itemArray[i]);
		}
		
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
	}
	
	
	/**ToDo: what is the function of this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
	 * Removes all events from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of
	 * the model, because other classes in this module have
	 * references to it. Hence, we manually remove each message
	 * from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<CalendarItem> iterator = itemList.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}	
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	
	/* 
	 * Returns the Event at the given index. This method is called
	 * internally by the JList in BoardPanel. Note this method returns
	 * elements in reverse order, so newest messages are returned first.
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return itemList.get(itemList.size() - 1 - index).toString();
	}

}
