/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 * V1.0
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.modellist;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;



/**List of Commitments being pulled from the server
 * 
 *
 * @version $Revision: 1.0 $
 * @author Tommzy
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class CommitmentsModel extends AbstractListModel {
	
	/**
	 * The list in which all the Commitments for a single project are contained
	 */
	private List<Commitment> Commitments;
	/** The next available ID number for the Commitments that are added */
	private int nextID;
	
	/** The static instance of the Commitments model */
	private static CommitmentsModel instance; 

	/**
	 * Constructs an empty list of Commitments for the project
	 */
	private CommitmentsModel () {
		Commitments = new ArrayList<Commitment>();
		nextID = 0;
	}
	
	
	
	/**
	 * Gets the static instance of the Commitments Model
	 * @return the instance of the Commitment model singleton.
	 * */
	public static CommitmentsModel getInstance() {
		if(instance == null)
		{
			instance = new CommitmentsModel();
		}
		return instance;
	}
	
	/**
	 * Adds a single Commitment to the list
	 * 
	 * @param newComm The Commitment to be added to the list of Commitments in the project
	 */
	public void addCommitment(Commitment newComm) {
		this.Commitments.add(newComm);
	}
	
	
	/**
	 * Returns the Commitment with the given ID
	 * 
	 * @param id The ID number of the Commitment to be returned
	 * @return the Commitment for the id or null if the Commitment is not found
	 * */
	public Commitment getCommitment(int id) {
		Commitment temp = null;
		// iterate through list of Commitments until id is found
		for (int i=0; i < this.Commitments.size(); i++){
			temp = Commitments.get(i);
			if (temp.getId() == id){
				break;
			}
		}
		return temp;
	}
	
	/**
	 * Getter function for the list of commitments.
	 * @return all of the commitments
	 */
	public List<Commitment> getAllCommitment() {
		return Commitments;
	}
	
	/**
	 * Removes the Commitment with the given ID.
	 * @param removeId The ID number of the Commitment to be removed from the list of Commitments in the project
	 */
	public void removeCommitment(int removeId) {
		// iterate through list of Commitments until id of project is found
		for (int i=0; i < this.Commitments.size(); i++){
			if (Commitments.get(i).getId() == removeId){
				// remove the id
				Commitments.remove(i);
				break;
			}
		}
	}

	/**
	 * Provides the number of elements in the list of Commitments for the project. This
	 * function is called internally by the JList in NewCommitmentPanel.
	 * 
	 * @return the number of Commitments in the project
	 * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return Commitments.size();
	}
	
	/**
	 * Provides the next ID number that should be used for a new Commitment that is created.
	 * 
	 * @return the next open id number */
	public int getNextID() {
		return this.nextID++;
	}

	/**
	 * This function takes an index and finds the Commitment in the list of Commitments
	 * for the project. Used internally by the JList in NewCommitmentsModel.
	 * 
	 * @param index The index of the Commitment to be returned
	
	
	
	 * @return the Commitment associated with the provided index * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Commitment getElementAt(int index) {
		return Commitments.get(Commitments.size() - 1 - index);
	}

	/**
	 * Removes all Commitments from this model
	 * (Other classes in this module have references to this.
	 * Remove each Commitment instead of creating a new instance.)
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Commitment> iterator = Commitments.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
	}
	
	/**
	 * Adds the given array of Commitments to the list
	 * 
	 * @param Commitments the array of Commitments to add
	 */
	public void addCommitments(Commitment[] Commitments) {
		for (int i = 0; i < Commitments.length; i++) {
			this.Commitments.add(Commitments[i]);
			if(Commitments[i].getId() >= nextID) nextID = Commitments[i].getId() + 1;
		}
	}



	/**
	 * Returns the list of children for the given Commitment.
	 * Not currently implemented.
	 * 
	 * @param Commitment the parent Commitment to find children for.
	 * @return The list of children. */
	public List<Commitment> getChildren(Commitment Commitment) {
		List<Commitment> children = new ArrayList<Commitment>();
//		for(Commitment possibleChild : Commitments)
//		{
//			if(possibleChild.getParentID() == Commitment.getId()) children.add(possibleChild);
//		}	
		return children;
	}
	
	/**
	 * Returns the possible children for the given Commitment.
	 * Not currently implemented.
	 * 
	 * @param req the given Commitment
	 * @return the list model of possiblechildren */
	public ListModel<Commitment> getPossibleChildren(Commitment req)
	{
		DefaultListModel<Commitment> possibleChildren = new DefaultListModel<Commitment>();
//		for(Commitment possChild : Commitments)
//		{
//			if(possChild.isAncestor(req.getId()) || possChild.getParentID() != -1) continue;
//			if(possChild == req) continue;
//			if(possChild.getStatus() == CommitmentStatus.COMPLETE || possChild.getStatus() == CommitmentStatus.DELETED) continue;
//			possibleChildren.addElement(possChild);
//		}	
		return possibleChildren;
	}
	
	
	/**
	 * Returns the possible parents for the given Commitment.
	 * Not currently implemented.
	 * @param req the given Commitment
	 * @return the list model of possibleParents */
	public ListModel<Commitment> getPossibleParents(Commitment req)
	{
		DefaultListModel<Commitment> possibleParents = new DefaultListModel<Commitment>();	
//		for(Commitment possParent : Commitments)
//		{
//			if(possParent.hasAncestor(req.getId())) continue;
//			if(possParent == req) continue;
//			if(possParent.getStatus() == CommitmentStatus.COMPLETE || possParent.getStatus() == CommitmentStatus.DELETED) continue;
//			possibleParents.addElement(possParent);
//		}	
		return possibleParents;
	}
		
	/**
	 * Getter function to get the entire list of commitments.
	 * @return the Commitments held within the CommitmentsModel.
	 */
	public List<Commitment> getCommitments() {
		return Commitments;
	}
}
