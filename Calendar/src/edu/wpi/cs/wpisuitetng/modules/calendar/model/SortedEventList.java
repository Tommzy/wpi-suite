/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CalDev
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.GregorianCalendar;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class SortedEventList extends AbstractListModel<Event>{
  private ArrayList<Event> itemList;
	
  /**
   * This constructor is for use by other parts of the code.
   */
  SortedEventList() {
    itemList = new ArrayList<Event>();
  }
  /**
   * This constructor is to be used by this class only!
   * @param newItemList list of events to be used. Should already be sorted.
   */
  private SortedEventList(ArrayList<Event> newItemList) {
    this.itemList = newItemList;
  }
  
  /**
   * Add the given Event to the given index.
   * @param newItem The Event to be added to the list
   * @param where The index to add the new item at.
   */
  private void addItemHere(Event newItem, int where) {
    itemList.ensureCapacity(itemList.size() + 1);
    if (where >= itemList.size()) {
      itemList.add(newItem);
    }
    else {
        itemList.add(where, newItem);
    }//end else
  }//end function

  /**Add a new event to the list.
   * @param newEvent the event to be added.
   */
  public void addEvent(Event newEvent) {
    for (int i = 0; i < itemList.size(); i++) {
      if (newEvent.getStartTime().before(this.getElementAt(i).getStartTime())) {
        addItemHere(newEvent, i);
        return;
      }//end if
    
    }//end for
    //If we get here, our new item was not before anything in the list.
    //Therefore, it should go to the end of the list.
    addItemHere(newEvent, itemList.size());
  }
  
  /**Remove the given event from the list.
   * @param whatToRemove Event to remove from the list.
   */
  public void removeEvent(Event whatToRemove) {
    itemList.remove(whatToRemove);
  }
  
  /**
   * Get a SortedEventList back, where all of the events in the new
   * list are within the given date range.
   * @param startRange GregorianCalendar of the starting date for the range.
   * @param endRange GregorianCalendar of the ending date for the range.
   * @return SortedEventList of the dates in the range.
   */
  public SortedEventList getEventsBetween(GregorianCalendar startRange, GregorianCalendar endRange) {
    ArrayList<Event> itemsInRange = new ArrayList<Event>(itemList.size());
    for (int i = 0; i < itemList.size(); i++) {
      if ( ( (this.getElementAt(i).getStartTime().after(startRange)) && 
          (this.getElementAt(i).getStartTime().before(endRange)) )
          || (this.getElementAt(i).getStartTime().equals(startRange)) || 
              (this.getElementAt(i).getStartTime().equals(endRange)) ) {
        itemsInRange.add(this.getElementAt(i));
      }//end if
    }//end for
    return new SortedEventList(itemsInRange);
  }
  
  /**
   * @return the number of items in the list
   */
  public int getSize() {
    return itemList.size();
  }

  @Override
  public Event getElementAt(int index) {
    Object[] itemArray = itemList.toArray();
    return (Event) itemArray[index];
  }
}
