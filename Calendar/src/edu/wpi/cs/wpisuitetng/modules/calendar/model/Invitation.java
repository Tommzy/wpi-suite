/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;


/**
 * The Class Invitation.
 * 
 * @author Eric
 */
public class Invitation implements Model{

  /** The name. */
  private String name;
    
  /** The description. */
  private String description;
  
  /** The id. */
  private int id;
  
  /** The username. */
  private String username;
  
  /** The status. */
  private int status;


  /** The is team invitation. */
  private boolean isTeamInvitation = true;


  /**
   * Checks if is team invitation.
   *
   * @return true, if is team invitation
   */
  public boolean isTeamInvitation() {
    return isTeamInvitation;
  }

  /**
   * Sets the team invitation.
   *
   * @param isTeamInvitation the new team invitation
   */
  public void setTeamInvitation(boolean isTeamInvitation) {
    this.isTeamInvitation = isTeamInvitation;
  }
  
  /**
   * Gets the status.
   *
   * @return the status
   */
  public int getStatus() {
    return status;
  }

  /**
   * Sets the status.
   *
   * @param status the new status
   */
  public void setStatus(int status) {
    this.status = status;

  }

  /** The permission map. */
  private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
  
  /** The project. */
  private Project project;

  /**
   * Instantiates a new invitation.
   *
   * @param name the name
   * @param startTime the start time
   * @param description the description
   */
  public Invitation(String name, GregorianCalendar startTime,
      String description) {
    this.name = name;
    this.description = description;
    this.id = -1;
    this.status = 1;
  }

  /**
   * Copy.
   *
   * @param comm the comm
   */
  public void copy(Invitation comm){
    this.setDescription(comm.getDescription());
    this.setId(comm.getId());
    this.setName(comm.getName());
    this.setProject(comm.getProject());
    this.setStatus(comm.getStatus());
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
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description){
    this.description = description;
  }


  
  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.Model#getPermission(edu.wpi.cs.wpisuitetng.modules.core.models.User)
   */
  @Override
  public Permission getPermission(User u) 
  {
    return permissionMap.get(u);
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.Model#setPermission(edu.wpi.cs.wpisuitetng.Permission, edu.wpi.cs.wpisuitetng.modules.core.models.User)
   */
  @Override
  public void setPermission(Permission p, User u) 
  {
    permissionMap.put(u, p);
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.Model#getProject()
   */
  @Override
  public Project getProject() {
    return project;
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.Model#setProject(edu.wpi.cs.wpisuitetng.modules.core.models.Project)
   */
  @Override
  public void setProject(Project p) {
    this.project = p;
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.Model#toJSON()
   */
  @Override
  public String toJSON() {
    return new Gson().toJson(this, Invitation.class);
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.Model#save()
   */
  @Override
  public void save() {
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.Model#delete()
   */
  @Override
  public void delete() {
  }

  /* (non-Javadoc)
   * @see edu.wpi.cs.wpisuitetng.modules.Model#identify(java.lang.Object)
   */
  @Override
  public Boolean identify(Object o) {
    return null;
  }

  /**
   * From json.
   *
   * @param json the json
   * @return the invitation
   */
  public static Invitation fromJSON(String json) {
    final Gson parser = new Gson();
    return parser.fromJson(json, Invitation.class);
  }

  /**
   * From json array.
   *
   * @param json the json
   * @return the invitation[]
   */
  public static Invitation[] fromJsonArray(String json) {
    final Gson parser = new Gson();
    return parser.fromJson(json, Invitation[].class);
  }


  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username.
   *
   * @param username the new username
   */
  public void setUsername(String username) {
    this.username = username;
  }


}
