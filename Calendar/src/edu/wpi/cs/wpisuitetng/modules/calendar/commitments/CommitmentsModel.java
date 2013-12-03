/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 * Based on Source Code from CommitmentsModels
 * V1.0
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.commitments;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;



/**List of Commitments being pulled from the server
 * 
 *
 * @version $Revision: 1.0 $
 * @author Tommzy
 */
@SuppressWarnings("serial")
public class CommitmentsModel extends AbstractListModel{
	
	/**
	 * The list in which all the Commitments for a single project are contained
	 */
	private List<Commitment> Commitments;
	private int nextID; // the next available ID number for the Commitments that are added.
	
	//the static object to allow the Commitment model to be 
	private static CommitmentsModel instance; 

	/**
	 * Constructs an empty list of Commitments for the project
	 */
	private CommitmentsModel (){
		Commitments = new ArrayList<Commitment>();
		nextID = 0;
	}
	
	
	
	/**
	
	 * @return the instance of the Commitment model singleton. */
	public static CommitmentsModel getInstance()
	{
		if(instance == null)
		{
			instance = new CommitmentsModel();
		}
		
		return instance;
	}
	
	/**
	 * Adds a single Commitment to the Commitments of the project
	 * 
	 * @param newComm The Commitment to be added to the list of Commitments in the project
	 */
	public void addCommitment(Commitment newComm){
		// add the Commitment
		Commitments.add(newComm);
		try 
		{
			AddCommitmentController Controller = new AddCommitmentController(null, null);
			Controller.addCommitmentToModel(newComm);
//			ViewEventController.getInstance().refreshTable();
//			ViewEventController.getInstance().refreshTree();
		}
		catch(Exception e)
		{
			
		}
	}
	/**
	 * Returns the Commitment with the given ID
	 * 
	 * @param id The ID number of the Commitment to be returned
	
	 * @return the Commitment for the id or null if the Commitment is not found */
	public Commitment getCommitment(int id)
	{
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
	
	public List<Commitment> getAllCommitment() {
		for (int i = 0; i < Commitments.size(); i++) {
			System.out.println("Commitment out put    " + Commitments.get(i).toString());
		}
		return Commitments;
	}
	
	/**
	 * Removes the Commitment with the given ID
	 * 
	 * @param removeId The ID number of the Commitment to be removed from the list of Commitments in the project
	 */
	public void removeCommitment(int removeId){
		// iterate through list of Commitments until id of project is found
		for (int i=0; i < this.Commitments.size(); i++){
			if (Commitments.get(i).getId() == removeId){
				// remove the id
				Commitments.remove(i);
				break;
			}
		}
//		try {
//			ViewEventController.getInstance().refreshTable();
//			ViewEventController.getInstance().refreshTree();
//		}
//		catch(Exception e) {}
	}

	/**
	 * Provides the number of elements in the list of Commitments for the project. This
	 * function is called internally by the JList in NewCommitmentPanel. Returns elements
	 * in reverse order, so the newest Commitment is returned first.
	 * 
	
	
	
	 * @return the number of Commitments in the project * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return Commitments.size();
	}
	
	/**
	 * 
	 * Provides the next ID number that should be used for a new Commitment that is created.
	 * 
	
	 * @return the next open id number */
	public int getNextID()
	{
		
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
	 * 
	 * NOTE: One cannot simply construct a new instance of
	 * the model, because other classes in this module have
	 * references to it. Hence, we manually remove each Commitment
	 * from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Commitment> iterator = Commitments.iterator();
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
	 * Adds the given array of Commitments to the list
	 * 
	 * @param Commitments the array of Commitments to add
	 */
	public void addCommitments(Commitment[] Commitments) {
		for (int i = 0; i < Commitments.length; i++) {
			this.Commitments.add(Commitments[i]);
			if(Commitments[i].getId() >= nextID) nextID = Commitments[i].getId() + 1;
		}
		//this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
		
		
		//************Refresh GUI HERE!!!!!!!!!!!!!!!!!!!!!!!!!***************************************
//		ViewEventController.getInstance().refreshTable();
//		ViewEventController.getInstance().refreshTree();
	}



	/**
	 * Returns the list of children for the given Commitment.
	 * @param Commitment the parent Commitment to find children for.
	
	 * @return The list of children. */
	public List<Commitment> getChildren(Commitment Commitment) {
		List<Commitment> children = new ArrayList<Commitment>();
		
		for(Commitment possibleChild : Commitments)
		{
//			if(possibleChild.getParentID() == Commitment.getId()) children.add(possibleChild);
		}
		
		return children;
	}
	
	/**
	 * Returns the possible children for the given Commitment.
	 * @param req the given Commitment
	
	 * @return the list model of possiblechildren */
	public ListModel<Commitment> getPossibleChildren(Commitment req)
	{
		DefaultListModel<Commitment> possibleChildren = new DefaultListModel<Commitment>();
		
		for(Commitment possChild : Commitments)
		{
//			if(possChild.isAncestor(req.getId()) || possChild.getParentID() != -1) continue;
//			if(possChild == req) continue;
//			if(possChild.getStatus() == CommitmentStatus.COMPLETE || possChild.getStatus() == CommitmentStatus.DELETED) continue;
			possibleChildren.addElement(possChild);
		}
		
		return possibleChildren;
	}
	
	
	/**
	 * Returns the possible parents for the given Commitment.
	 * @param req the given Commitment
	
	 * @return the list model of possibleParents */
	public ListModel<Commitment> getPossibleParents(Commitment req)
	{
		DefaultListModel<Commitment> possibleParents = new DefaultListModel<Commitment>();
		
		for(Commitment possParent : Commitments)
		{
//			if(possParent.hasAncestor(req.getId())) continue;
			if(possParent == req) continue;
//			if(possParent.getStatus() == CommitmentStatus.COMPLETE || possParent.getStatus() == CommitmentStatus.DELETED) continue;
			possibleParents.addElement(possParent);
		}
		
		return possibleParents;
	}

//	/**
//	 * Returns the list of Commitments that are assigned to the given iteration
//	 * @param name the iteration name
//	
//	 * @return the list of Commitments */
//	public List<Commitment> getCommitmentsForIteration(String name) {
//		List<Commitment> reqForIteration = new LinkedList<Commitment>();
//		
//		boolean backlog = false;
//		if(name.trim().length() == 0) backlog = true;
//		
//		for(Commitment req : Commitments)
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
	 * Gets the summed estimates of all Commitments in the given iteration.
	 * @param displayIteration the iteration to get Commitments for
	
	 * @return the summed estimates of the Commitments. */
//	public int getCommitmentEstimateForIteration(Iteration displayIteration) {
//		int estimate = 0;
//		List<Commitment> inIteration = getCommitmentsForIteration(displayIteration.getName());
//		
//		for(Commitment req : inIteration)
//		{
//			estimate += req.getEstimate();
//		}
//		
//		return estimate;
//	}
//	
	
	
	/**
	
	 * @return the Commitments held within the CommitmentsModel. */
	public List<Commitment> getCommitments() {
		return Commitments;
	}
}
