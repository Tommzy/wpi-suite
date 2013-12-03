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

public class SortedEventList extends SortedCalendarItemList{
	
  /**
   * This constructor is for use by other parts of the code.
   */
  SortedEventList() {
    
  }
  /**
   * This constructor is to be used by this class only!
   * @param itemList list of events to be used. Should already be sorted.
   */
  private SortedEventList(ArrayList<CalendarItem> itemList) {
    super(itemList);
  }

  /**Add a new event to the list.
   * @param newEvent the event to be added.
   */
  public void addEvent(Event newEvent) {
    addCalendarItem((CalendarItem)newEvent);
  }
  
  /**Remove the given event from the list.
   * @param whatToRemove Event to remove from the list.
   */
  public void removeEvent(Event whatToRemove) {
    removeCalendarItem((CalendarItem) whatToRemove);
  }
  
  /**
   * Get a SortedEventList back, where all of the events in the new
   * list are within the given date range.
   * @param startRange GregorianCalendar of the starting date for the range.
   * @param endRange GregorianCalendar of the ending date for the range.
   * @return SortedEventList of the dates in the range.
   */
  public SortedEventList getEventsBetween(GregorianCalendar startRange, GregorianCalendar endRange) {
	  ArrayList<CalendarItem> newItemList;
	  newItemList = getDateRange(startRange, endRange);
	  return new SortedEventList(newItemList);
  }
  
}
