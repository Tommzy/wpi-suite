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

public class SortedCalendarItemList {
  private CalendarItem[] itemList;
  
  /**
   * Add the given CalendarItem to the given index.
   * @param newItem The item to be added to the list
   * @param where The index to add the new item at.
   */
  private void addItemHere(CalendarItem newItem, int where) {
    if (where >= itemList.length) {
      itemList[itemList.length] = newItem;
    }
    else {
      for (int i = itemList.length; i >= where; i--) {
        itemList[i] = itemList[i - 1];
      }//end for
      itemList[where] = newItem;
    }//end else
  }//end function
  
  /**
   * Add the given CalendarItem to its correct place in the list.
   * @param newItem The CalendarItem to be added.
   */
  public void addCalendarItem(CalendarItem newItem) {
    for (int i = 0; i < itemList.length; i++) {
      if (newItem.startTime.before(itemList[i].startTime)) {
        addItemHere(newItem, i);
        return;
      }//end if
    
    }//end for
    //If we get here, our new item was not before anything in the list.
    //Therefore, it should go to the end of the list.
    addItemHere(newItem, itemList.length);
  }//end function
  
//TODO: Function to remove a specific item from a list.
  
  /**
   * Find all of the items within a specific date range, and return a sorted array of CalendarItems with only those items.
   * @param startRange GregorianCalendar with the date/time that you want to start at
   * @param endRange GregorianCalendar with the date/time that you want to end at.
   * @returns (sorted) array of CalendarItems with all of the CalendarItems between startTime and endTime.
   */
  public CalendarItem[] getDateRange(GregorianCalendar startRange, GregorianCalendar endRange) {
    CalendarItem[] itemsInRange = new CalendarItem[itemList.length];
    int numItemsFound = 0;
    for (int i = 0; i < itemList.length; i++) {
      if ( ( (itemList[i].startTime.after(startRange)) && (itemList[i].startTime.before(endRange)) )
          || (itemList[i].startTime.equals(startRange)) || (itemList[i].startTime.equals(endRange)) ) {
        itemsInRange[numItemsFound] = itemList[i];
        numItemsFound++;
      }//end if
    }//end for
    return itemsInRange;
    //TODO: Refine this function!
  } //end func
  
  /**
   * @return the number of items in the list
   */
  public int getSize() {
    return itemList.length;
  }
  
}
