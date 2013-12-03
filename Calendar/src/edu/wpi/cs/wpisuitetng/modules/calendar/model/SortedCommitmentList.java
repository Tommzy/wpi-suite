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

public class SortedCommitmentList extends SortedCalendarItemList{
  
	
  /**
   * This constructor is for use by other parts of the code.
   */
  SortedCommitmentList() {
    
  }
  /**
   * This constructor is to be used by this class only!
   * @param itemList list of events to be used. Should already be sorted.
   */
  private SortedCommitmentList(ArrayList<CalendarItem> itemList) {
    super(itemList);
  }
  
  /**Add a new commitment to the list.
   * @param newCommitment the commitment to be added.
   */
  public void addCommitment(Commitment newCommitment) {
    addCalendarItem((CalendarItem)newCommitment);
  }

  /**Remove the given commitment from the list.
   * @param whatToRemove commitment to remove from the list.
   */
  public void removeCommitment(Commitment whatToRemove) {
    removeCalendarItem((CalendarItem) whatToRemove);
  }
  
  /**
   * Get a SortedCommitmentList back, where all of the commitments in the new
   * list are within the given date range.
   * @param startRange GregorianCalendar of the starting date for the range.
   * @param endRange GregorianCalendar of the ending date for the range.
   * @return SortedCommitmentList of the dates in the range.
   */
  public SortedCommitmentList getCommitmentsBetween(GregorianCalendar startRange, GregorianCalendar endRange) {
	  ArrayList<CalendarItem> newItemList;
	  newItemList = getDateRange(startRange, endRange);
	  return new SortedCommitmentList(newItemList);
  }
  
}
