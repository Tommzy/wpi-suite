package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Category implements Model {
  /** The name shown in the GUI */
  String name;

  /** The id. */
  private int id;
  
  /** Can you make new events or commitments belong to this category?*/
  boolean isActive;
  
  /** The permission map. */
  private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
  
  /** The project. */
  private Project project;
  
  //------------------Non-Interface Functions------------------------
  
  /**
   * Constructor
   * @param newName the name for this category
   */
  
  public Category(String newName) {
    this.name = newName;
    id = -1;
  }
  
  public void copy(Category donor) {
    this.name = donor.name;
    this.id = donor.id;
    this.isActive = donor.isActive;
    this.project = donor.project;
    
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
