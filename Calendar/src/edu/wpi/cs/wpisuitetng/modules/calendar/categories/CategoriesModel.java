package edu.wpi.cs.wpisuitetng.modules.calendar.categories;
/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Hui Zheng
 * Based on Source Code from CategoriesModels
 * V1.0
 ******************************************************************************/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;



import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;



// TODO: Auto-generated Javadoc
/**
 * The Class CategoriesModel.
 */
@SuppressWarnings("serial")
public class CategoriesModel extends AbstractListModel{
	
	/** The Categories. */
	private List<Category> categories;
	
	/** The next id. */
	private int nextID; // the next available ID number for the Categories that are added.
	
	//the static object to allow the Category model to be 
	/** The instance. */
	private static CategoriesModel instance; 

	/**
	 * Instantiates a new categories model.
	 */
	private CategoriesModel (){
		this.categories = new ArrayList<Category>();
		nextID = 0;
	}
	
	
	
	/**
	 * Gets the single instance of CategoriesModel.
	 *
	 * @return single instance of CategoriesModel
	 */
	public static CategoriesModel getInstance()
	{
		if(instance == null)
		{
			instance = new CategoriesModel();
		}
		
		return instance;
	}
	
	/**
	 * Adds the category.
	 *
	 * @param newCategory the new category
	 */
	public void addCategory(Category newCategory){
		
		this.categories.add(newCategory);
//		
//		for (int i = 0; i < Categories.size(); i++) {
//			System.out.println("Event out put    " + Categories.get(i).toString());
//		}
	}
	
	/**
	 * Gets the category.
	 *
	 * @param id the id
	 * @return the event
	 */
	public Category getCategory(int id)
	{
		Category temp = null;
		// iterate through list of Categories until id is found
		for (int i=0; i < this.categories.size(); i++){
			temp = categories.get(i);
			if (temp.getId() == id){
				break;
			}
		}
		return temp;
	}
	
	/**
	 * Gets the all categories.
	 *
	 * @return the all categories
	 */
	public List<Category> getAllCategory() {
//		for (int i = 0; i < Categories.size(); i++) {
//			System.out.println("Category out put    " + Categories.get(i).toString());
//		}
		return categories;
	}
	
	/**
	 * Removes the category.
	 *
	 * @param removeId the remove id
	 */
	public void removeCategory(int removeId){
		// iterate through list of Categories until id of project is found
		for (int i=0; i < this.categories.size(); i++){
			if (categories.get(i).getId() == removeId){
				// remove the id
				categories.remove(i);
				break;
			}
		}
//		try {
//			ViewEventController.getInstance().refreshTable();
//			ViewEventController.getInstance().refreshTree();
//		}
//		catch(Exception e) {}
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return categories.size();
	}
	
	/**
	 * Gets the next id.
	 *
	 * @return the next id
	 */
	public int getNextID()
	{
		
		return this.nextID++;
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Category getElementAt(int index) {
		return categories.get(categories.size() - 1 - index);
	}

	/**
	 * Empty model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Category> iterator = categories.iterator();
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
	 * Adds the Categories.
	 *
	 * @param Categories the Categories
	 */
	public void addCategories(Category[] categories) {
		for (int i = 0; i < categories.length; i++) {
			this.categories.add(categories[i]);
			if(categories[i].getId() >= nextID) nextID = categories[i].getId() + 1;
		}
		//this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
		
		
		//************Refresh GUI HERE!!!!!!!!!!!!!!!!!!!!!!!!!***************************************
//		ViewEventController.getInstance().refreshTable();
//		ViewEventController.getInstance().refreshTree();
	}



	/**
	 * Gets the children.
	 *
	 * @param category the category
	 * @return the category list
	 */
	public List<Category> getChildren(Category category) {
		List<Category> children = new ArrayList<Category>();
		
		for(Category possibleChild : categories)
		{
//			if(possibleChild.getParentID() == category.getId()) children.add(possibleChild);
		}
		
		return children;
	}
	
	/**
	 * Gets the possible children.
	 *
	 * @param req the req
	 */
	public ListModel<Category> getPossibleChildren(Category req)
	{
		DefaultListModel<Category> possibleChildren = new DefaultListModel<Category>();
		
		for(Category possChild : categories)
		{
//			if(possChild.isAncestor(req.getId()) || possChild.getParentID() != -1) continue;
//			if(possChild == req) continue;
//			if(possChild.getStatus() == EventStatus.COMPLETE || possChild.getStatus() == EventStatus.DELETED) continue;
			possibleChildren.addElement(possChild);
		}
		
		return possibleChildren;
	}
	
	
	/**
	 * Gets the possible parents.
	 *
	 * @param req the req
	 * @return the possible parents
	 */
	public ListModel<Category> getPossibleParents(Category cate)
	{
		DefaultListModel<Category> possibleParents = new DefaultListModel<Category>();
		
		for(Category possParent : categories)
		{
//			if(possParent.hasAncestor(req.getId())) continue;
			if(possParent == cate) continue;
//			if(possParent.getStatus() == EventStatus.COMPLETE || possParent.getStatus() == EventStatus.DELETED) continue;
			possibleParents.addElement(possParent);
		}
		
		return possibleParents;
	}

//	/**
//	 * Returns the list of Events that are assigned to the given iteration
//	 * @param name the iteration name
//	
//	 * @return the list of Events */
//	public List<Event> getEventsForIteration(String name) {
//		List<Event> reqForIteration = new LinkedList<Event>();
//		
//		boolean backlog = false;
//		if(name.trim().length() == 0) backlog = true;
//		
//		for(Event req : Events)
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
	
	 * @return the categoris held within the CategoriesModel. */
	public List<Category> getCategories() {
		return categories;
	}
}
