/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Hui Zheng - Team 3
 * Based on Source Code from CategoriesModels
 * V1.0
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.categories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;

/**
 * The Class CategoriesModel.
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class CategoriesModel extends AbstractListModel{
	
	/** The Categories. */
	private List<Category> categories;
	
	/** The next available ID number for the Categories that are added.. */
	private int nextID;
	
	/** The instance of CategoriesModel. */
	private static CategoriesModel instance; 

	/**
	 * Instantiates a new categories model.
	 */
	private CategoriesModel (){
		categories = new ArrayList<Category>();
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
	 * Adds a new category to the list.
	 *
	 * @param newCategory the new category
	 */
	public void addCategory(Category newCategory){
		
		this.categories.add(newCategory);
		
	}
	
	/**
	 * Gets the category based on id number.
	 *
	 * @param id the id of the category to get
	 * @return the category with the given id
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
	 * Gets all the categories in the list.
	 *
	 * @return all categories
	 */
	public List<Category> getAllCategory() {
		return categories;
	}
	
	/**
	 * Removes the category with the given id.
	 *
	 * @param removeId the id of the category to remove
	 */
	public void removeCategory(int removeId){
		// iterate through list of Categories until matching id is found
		for (int i=0; i < this.categories.size(); i++){
			if (categories.get(i).getId() == removeId){
				// remove the id
				categories.remove(i);
				break;
			}
		}
	}

	/* 
	 * Gets the size of the list of categories.
	 * @return the size of the category list
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return categories.size();
	}
	
	/**
	 * Gets the next id. Increments nextID.
	 *
	 * @return the next id
	 */
	public int getNextID()
	{
		
		return this.nextID++;
	}

	/*
	 * Gets the category at an index
	 * @return the category at the given index
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Category getElementAt(int index) {
		return categories.get(categories.size() - 1 - index);
	}

	/**
	 * Empty the model. Removes all categories from the list.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Category> iterator = categories.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
	}
	
	/**
	 * Adds an array of Categories.
	 *
	 * @param Categories the Categories to add to the list
	 */
	public void addCategories(Category[] categories) {
		for (int i = 0; i < categories.length; i++) {
			this.categories.add(categories[i]);
			if(categories[i].getId() >= nextID) nextID = categories[i].getId() + 1;
		}
	}



	/**
	 * Gets the children.
	 * Currently not implemented
	 *
	 * @param category the category
	 * @return the category list
	 */
	public List<Category> getChildren(Category category) {
		List<Category> children = new ArrayList<Category>();
//		for(Category possibleChild : categories)
//		{
//			if(possibleChild.getParentID() == category.getId()) children.add(possibleChild);
//		}
		return children;
	}
	
	/**
	 * Gets the possible children.
	 * Currently not implemented
	 *
	 * @param req the category to get possible children of.
	 */
	public ListModel<Category> getPossibleChildren(Category req)
	{
		DefaultListModel<Category> possibleChildren = new DefaultListModel<Category>();	
//		for(Category possChild : categories)
//		{
//			if(possChild.isAncestor(req.getId()) || possChild.getParentID() != -1) continue;
//			if(possChild == req) continue;
//			if(possChild.getStatus() == EventStatus.COMPLETE || possChild.getStatus() == EventStatus.DELETED) continue;
//			possibleChildren.addElement(possChild);
//		}	
		return possibleChildren;
	}
	
	
	/**
	 * Gets the possible parents.
	 * Currently not implemented.
	 *
	 * @param req the req
	 * @return the possible parents
	 */
	public ListModel<Category> getPossibleParents(Category cate)
	{
		DefaultListModel<Category> possibleParents = new DefaultListModel<Category>();
//		for(Category possParent : categories)
//		{
//			if(possParent.hasAncestor(req.getId())) continue;
//			if(possParent == cate) continue;
//			if(possParent.getStatus() == EventStatus.COMPLETE || possParent.getStatus() == EventStatus.DELETED) continue;
//			possibleParents.addElement(possParent);
//		}
		return possibleParents;
	}
	
	/**
	 * Getter function for the categories list.
	 * @return the categories held within the CategoriesModel.
	 * */
	public List<Category> getCategories() {
		return categories;
	}
}
