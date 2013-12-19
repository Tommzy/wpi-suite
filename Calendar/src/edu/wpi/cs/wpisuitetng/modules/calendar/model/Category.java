/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors: CalDev
 * 
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Category implements Model{
  /** The name shown in the GUI */
  String name;

  /** The id. */
  private int id;
  
  /** Can you make new events or commitments belong to this category?*/
  boolean isActive;
  
  /** What color is associated with this category? */
  Color color;
  
  /** True is this is a personal category, false is this is a team category */
  boolean isPersonal;
  
  /** The ID of the user this belongs to. */
  String userID;
  
  /** The permission map. */
  private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
  
  /** The project. */
  private Project project;
  
  //------------------Non-Interface Functions------------------------
  
  /**
   * Constructor
   * @param newName the name for this category
   * @param newColor the color associated with this category
   */
  
  public Category(String newName, boolean isItPersonal, Color newColor) {
    this.name = newName;
    this.color = newColor;
    id = -1;
    userID = "-1";
    this.isPersonal = isItPersonal;
    this.isActive = true;
  }
  
  
  public void copy(Category donor) {
    this.name = donor.name;
    this.id = donor.id;
    this.isActive = donor.isActive;
    this.project = donor.project;
    this.color = donor.color;
    this.userID = donor.userID;
    this.isPersonal = donor.isPersonal;
    this.isActive = donor.isActive;
  }
  

  /**
   * Gets the id.
   *
   * @return the id
   */

  public int getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name){
    this.name = name;
  }
  
  /**
   * @return a string of the name.
   */
  public String getName() {
    return name;
  }
  
  /**
   * @return the userID
   */

  public String getUserId() {
    return userID;
  }

  /**
   * Sets the userID.
   * @param id the new userID
   */
  public void setUserId(String id) {
    this.userID = id;
  }
  
  /**
   * @return the color associated with this category
   */
  public Color getColor() {
    return color;
  }
  
  /**
   * Sets the color to the given color.
   * @param newColor the new color
   */
  public void setColor(Color newColor) {
    this.color = newColor;
  }
  
  /**
   * Tell us whether this is a personal category or not.
   * @return true if personal, else return false.
   */
  public boolean isPersonal() {
    return isPersonal;
  }
  
  /**
   * Set whether this category is personal or not
   * @param isThisPersonal boolean which is true if we want the category to be personal.
   */
  public void setIsPersonal(boolean isThisPersonal) {
    this.isPersonal = isThisPersonal;
  }
  /**
   * Indicates if a category is active or not
   * @return true if active, else return false
   */
  public boolean isActive() {
		return isActive;
	}

  /**
   * Set whether this category is active or not
   * @param isActive boolean which is true if we want the category to be personal.
   */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object cat) {
		if (cat instanceof Category) {
			if (this.id == ((Category)cat).getId()) {
				return true;
			}
		}
		return false;
	}
  
  //---------------------Interface-Functions----------------------
  
 

@Override
  public void save() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete() {
    // TODO Auto-generated method stub
    
  }
  
  /**
   * Converts this object to a JSON string.
   * @return the JSON string representing this object
   * @see edu.wpi.cs.wpisuitetng.modules.Model#toJSON()
   */
  @Override
  public String toJSON() {
    return new Gson().toJson(this, Category.class);
  }
  
  /**
   * Converts JSON string to Category
   * @param json the json string
   * @return the category encoded in that string
   */
  public static Category fromJSON(String json) {
    final Gson parser = new Gson();
    return parser.fromJson(json, Category.class);
  }
  
  /**
	 * From json array.
	 *
	 * @param json the json
	 * @return the category[]
	 */
	public static Category[] fromJsonArray(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Category[].class);
	}

  @Override
  public Boolean identify(Object o) {
    // TODO Auto-generated method stub
    return null;
  }
  
  /**
   * @see edu.wpi.cs.wpisuitetng.modules.Model#getPermission(edu.wpi.cs.wpisuitetng.modules.core.models.User)
   */
  @Override
  public Permission getPermission(User u) {
    return permissionMap.get(u);
  }


  /**
   * @see edu.wpi.cs.wpisuitetng.modules.Model#setPermission(edu.wpi.cs.wpisuitetng.Permission, edu.wpi.cs.wpisuitetng.modules.core.models.User)
   */
  @Override
  public void setPermission(Permission p, User u) {
    permissionMap.put(u, p);
    
  }
  
/**
 * @return the project that this category belongs to
 * @see edu.wpi.cs.wpisuitetng.modules.Model#getProject()
 */
  @Override
  public Project getProject() {
    return project;
  }

  /**
   * Sets the project that this category belongs to
   * @param p The project that this belongs to.
   * @see edu.wpi.cs.wpisuitetng.modules.Model#setProject(edu.wpi.cs.wpisuitetng.modules.core.models.Project)
   */
  @Override
  public void setProject(Project p) {
    this.project = p;
    
  }


}
