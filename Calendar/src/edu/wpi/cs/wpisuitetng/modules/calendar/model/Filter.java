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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**Filter for events and commitments
 * @author Brittany
 * @version v1.0
 */
public class Filter implements Model {
  
  /** The string that the user assigns as the name of this filter */
  String name;
  
  /** The unique ID belonging to this filter */
  private int id;
  
  /** The user that this filter belongs to */
  String userID;
  
  /** The list of categories within this filter */
  List<Category> categories;
  
  /** Is the user currently using this filter on the GUI? */
  boolean isActive;

  /** The permission map. */
  final private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
  
  /** The project. */
  private Project project;
  
  // ------ Non-Interface Functions -----
  

  /**
   * constructor of Filter
   * @param newName the name 
   * @param newCategories the category this filter contianed 
   */
public Filter(String newName, List<Category> newCategories) {
    name = newName;
    categories = newCategories;
    id = -1;
    userID = null;
    isActive = true;
  }
  
  /**
   * 
   * @param donor filter we want to copy from.
   */
  public void copy(Filter donor) {
    name = donor.name;
    id = donor.id;
    isActive = donor.isActive;
    project = donor.project;
    userID = donor.userID;
    categories = donor.categories;
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
    userID = id;
  }
  
  /**
   * Tell us whether this is a personal category or not.
   * @return true if personal, else return false.
   */
  public boolean isActive() {
    return isActive;
  }
  
  /**
   * Set whether this category is personal or not
   * @param isThisPersonal boolean which is true if we want the category to be personal.
   */
  public void setActiveness(boolean isThisActive) {
    isActive = isThisActive;
  }
  
  /**
   * @return categories (the list of categories for this filter)
   */
  public List<Category> getCategories() {
    return categories;
  }
  
  /**
   * Set this filter's list of categories to the given list of categories
   * @param newCategories the categories we want to be in this filter
   */
  public void setCategories(List<Category> newCategories) {
    categories = newCategories;
  }
  
  // ----- Interface Functions -----
  
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
    return new Gson().toJson(this, Filter.class);
  }
  
  /**
   * Converts JSON string to Category
   * @param json the json string
   * @return the category encoded in that string
   */
  public static Filter fromJSON(String json) {
    final Gson parser = new Gson();
    return parser.fromJson(json, Filter.class);
  }
  
  /**
   * From json array.
   *
   * @param json the json
   * @return the Filter[]
   */
  public static Filter[] fromJsonArray(String json) {
    final Gson parser = new Gson();
    return parser.fromJson(json, Filter[].class);
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
      project = p;
      
    }

}
