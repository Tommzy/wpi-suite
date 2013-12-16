/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;

/**
 * This class is used to filter a mist of commitments and return those that begin within a specified time slot
 *
 */
public class CommitmentFilter {
	private GregorianCalendar startTime, endTime;
	private Collection<Commitment> cmtList = new ArrayList<Commitment>();
	/**
	 * Constructor for commitment filter
	 * 
	 * @param startTime 
	 * @param endTime
	 */
	public CommitmentFilter(GregorianCalendar startTime, GregorianCalendar endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Used to create a collection of commitments that begin within the start and end time
	 * 
	 * @return The collection of commitments within the time frame
	 */
	public Collection<Commitment> getCommitmentList() {
		try {
			JButton button = new JButton("");
			button.addActionListener(new GetCommitmentController());
			button.doClick();
			
			Collection<Commitment> list = CommitmentsModel.getInstance().getAllCommitment();
//			Collection<Commitment> list = FakeCommitmentModel.getInstance().getCommitmentList();
			Iterator<Commitment> itr = list.iterator();

			while (itr.hasNext()) {
				Commitment cmt = itr.next();
				if (cmt.getStartTime().after(startTime) && cmt.getStartTime().before(endTime)) {
					cmtList.add(cmt);
				}
			}
//			System.out.println("SUCCESS PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
			return cmtList;
		} catch (NullPointerException e) {
			System.out.println("commitment filter null pointer exception");
		}
		
		System.out.println("FAIL PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
		return new ArrayList<Commitment> ();
	}
	
	/**
	 * Filter commitments based on an existing category 
	 * @param categeory Key word category
	 * @return List of filtered commitments
	 */
	public Collection<Commitment> filterOnCategory(/*Category categeory*/) {
		return cmtList;
	}
	
	/**
	 * Filter commitments based on a key word string
	 * @param s Key word string
	 * @return List of filtered commitments
	 */
	public Collection<Commitment> filterOnString (String s) {
		Iterator<Commitment> itr = cmtList.iterator();
		while (itr.hasNext()) {
			Commitment cmt = itr.next();
			if ((! cmt.getName().contains(s)) && (! cmt.getDescription().contains(s))) {
				cmtList.remove(cmt);
			}
		}
		return cmtList;
	}
}
