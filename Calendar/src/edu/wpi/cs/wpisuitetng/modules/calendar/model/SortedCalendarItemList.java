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

public class SortedCalendarItemList extends AbstractListModel<CalendarItem> {
  private ArrayList<CalendarItem> itemList;
  
  /**
   * This is the general-use constructor.
   */
  public SortedCalendarItemList() {
    itemList = new ArrayList<CalendarItem>();
  }
  /**
   * This constructor is for child classes only!
   * @param newItemList List of CalendarItems to be used. Should already be sorted.
   */
   public SortedCalendarItemList(ArrayList<CalendarItem> newItemList) {
    itemList = newItemList;
  }
  
  /**
   * Add the given CalendarItem to the given index.
   * @param newItem The item to be added to the list
   * @param where The index to add the new item at.
   */
  private void addItemHere(CalendarItem newItem, int where) {
    itemList.ensureCapacity(itemList.size() + 1);
    if (where >= itemList.size()) {
      itemList.add(newItem);
    }
    else {
        itemList.add(where, newItem);
    }//end else
  }//end function
  
  /**
   * Add the given CalendarItem to its correct place in the list.
   * @param newItem The CalendarItem to be added.
   */
  public void addCalendarItem(CalendarItem newItem) {
    for (int i = 0; i < itemList.size(); i++) {
      if (newItem.startTime.before(this.getElementAt(i).startTime)) {
        addItemHere(newItem, i);
        return;
      }//end if
    
    }//end for
    //If we get here, our new item was not before anything in the list.
    //Therefore, it should go to the end of the list.
    addItemHere(newItem, itemList.size());
  }//end function
  
  public void removeCalendarItem(CalendarItem whatToRemove) {
    itemList.remove(whatToRemove);
  }
  
  /**
   * Find all of the items within a specific date range, and return a sorted array of CalendarItems with only those items.
   * @param startRange GregorianCalendar with the date/time that you want to start at
   * @param endRange GregorianCalendar with the date/time that you want to end at.
   * @returns (sorted) list of CalendarItems with all of the CalendarItems between startTime and endTime.
   */
  protected ArrayList<CalendarItem> getDateRange(GregorianCalendar startRange, GregorianCalendar endRange) {
    ArrayList<CalendarItem> itemsInRange = new ArrayList<CalendarItem>(itemList.size());
    for (int i = 0; i < itemList.size(); i++) {
      if ( ( (this.getElementAt(i).startTime.after(startRange)) && 
    		  (this.getElementAt(i).startTime.before(endRange)) )
          || (this.getElementAt(i).startTime.equals(startRange)) || 
              (this.getElementAt(i).startTime.equals(endRange)) ) {
        itemsInRange.add(this.getElementAt(i));
      }//end if
    }//end for
    return itemsInRange;
  } //end func
  
  /**
   * @return the number of items in the list
   */
  public int getSize() {
    return itemList.size();
  }

  @Override
  public CalendarItem getElementAt(int index) {
    Object[] itemArray = itemList.toArray();
    return (CalendarItem) itemArray[index];
  }

  
}//end class
