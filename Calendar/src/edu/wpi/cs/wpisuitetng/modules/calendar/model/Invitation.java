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
 * @author Eric Willcox, Andrew Paon
 */
public class Invitation implements Model{
  private String currentUser;

	/** The name. */
	private String name;

	/** The date */
	private String date;

	/** The description. */
	private String description;

	/** The id. */
	private int id;

	/** HashMap containing availability at each time */
	private HashMap<String, String[]> availablity;

	/** represents whether or not this goes to the entire team
		currently removed until we add necessary functionality

	private boolean isTeamInvitation;

	 */

	/** Map of users to their permissions */
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
	public Invitation(String name, String date,
			String description) {
		this.name = name;
		this.description = description;
		this.id = -1;
		this.date = date;
		this.availablity = initializeAvailability();
	}

	/**
	 * creates a HashMap h that maps every hour of the workday
	 * to an empty list of strings.  this string will eventually
	 * contain the list of all available users for
	 * each time 
	 * 
	 * @return h
	 */

	private HashMap<String, String[]> initializeAvailability() {
		HashMap<String, String[]> h = new HashMap<String, String[]>();

		for(int i=8; i<17; i++){
			h.put(Integer.toString(i), new String[0]);
		}

		return h;
	}

	/** Getters and Setters */

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public HashMap<String, String[]> getAvailablity() {
		return availablity;
	}

	public void setAvailablity(HashMap<String, String[]> availablity) {
		this.availablity = availablity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
	  return getName();
	}

	/**End of Getters and Setters.  Real Functions follow */


	/**
	 * Sets all the fields in the current Invitation equal to another's
	 * 
	 * @param inv the other invitation
	 */
	public void copy(Invitation inv){
		this.setDescription(inv.getDescription());
		this.setId(inv.getId());
		this.setName(inv.getName());
		this.setProject(inv.getProject());
		this.setAvailablity(inv.getAvailablity());
		this.setDate(inv.getDate());
		this.setCurrentUser(inv.getCurrentUser());
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


	/** Override functions */

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

  public String getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(String currentUser) {
    this.currentUser = currentUser;
  }
}
