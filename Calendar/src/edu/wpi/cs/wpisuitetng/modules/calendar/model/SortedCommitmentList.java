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

public class SortedCommitmentList extends AbstractListModel<Commitment>{

  private ArrayList<Commitment> itemList;
  
  /**
   * This is the general-use constructor.
   */
  public SortedCommitmentList() {
    itemList = new ArrayList<Commitment>();
  }
  
  /**
   * This constructor is to be used by this class only!
   * @param itemList list of events to be used. Should already be sorted.
   */
  private SortedCommitmentList(ArrayList<Commitment> newitemList) {
    this.itemList = newitemList;
  }
  
  /**
   * Add the given Commitment to the given index.
   * @param newItem The commitment to be added to the list
   * @param where The index to add the new item at.
   */
  private void addItemHere(Commitment newItem, int where) {
    itemList.ensureCapacity(itemList.size() + 1);
    if (where >= itemList.size()) {
      itemList.add(newItem);
    }
    else {
        itemList.add(where, newItem);
    }//end else
  }//end function
  
  
  /**Add a new commitment to the list.
   * @param newCommitment the commitment to be added.
   */
  public void addCommitment(Commitment newCommitment) {
    for (int i = 0; i < itemList.size(); i++) {
      if (newCommitment.getStartTime().before(this.getElementAt(i).getStartTime())) {
        addItemHere(newCommitment, i);
        return;
      }//end if
    
    }//end for
    //If we get here, our new item was not before anything in the list.
    //Therefore, it should go to the end of the list.
    addItemHere(newCommitment, itemList.size());
  }

  /**Remove the given commitment from the list.
   * @param whatToRemove commitment to remove from the list.
   */
  public void removeCommitment(Commitment whatToRemove) {
    itemList.remove(whatToRemove);
  }
  
  /**
   * Get a SortedCommitmentList back, where all of the commitments in the new
   * list are within the given date range.
   * @param startRange GregorianCalendar of the starting date for the range.
   * @param endRange GregorianCalendar of the ending date for the range.
   * @return SortedCommitmentList of the dates in the range.
   */
  public SortedCommitmentList getCommitmentsBetween(GregorianCalendar startRange, GregorianCalendar endRange) {
    ArrayList<Commitment> itemsInRange = new ArrayList<Commitment>(itemList.size());
    for (int i = 0; i < itemList.size(); i++) {
      if ( ( (this.getElementAt(i).getStartTime().after(startRange)) && 
          (this.getElementAt(i).getStartTime().before(endRange)) )
          || (this.getElementAt(i).getStartTime().equals(startRange)) || 
              (this.getElementAt(i).getStartTime().equals(endRange)) ) {
        itemsInRange.add(this.getElementAt(i));
      }//end if
    }//end for
	  return new SortedCommitmentList(itemsInRange);
  }
  
  
  
  /**
   * @return the number of items in the list
   */
  public int getSize() {
    return itemList.size();
  }

  @Override
  public Commitment getElementAt(int index) {
    Object[] itemArray = itemList.toArray();
    return (Commitment) itemArray[index];
  }
  
}
