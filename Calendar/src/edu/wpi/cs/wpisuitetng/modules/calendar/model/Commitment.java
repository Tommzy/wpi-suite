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

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;


// TODO: Auto-generated Javadoc
/**
 * The Class Commitment.
 */
public class Commitment implements Model{

	/** The name. */
	private String name;

	/** The start time. */
	private GregorianCalendar startTime;

	/** The description. */
	private String description;

	/** The id. */
	private int id;

	/** The owner of this commitment. */
	private String username;

	/** The status of this commitment. */
	private int status;


	/** The is team commitment. */
	private boolean isTeamCommitment = true;


	/**
	 * Checks if is team commitment.
	 *
	 * @return true, if is team commitment
	 */
	public boolean isTeamCommitment() {
		return isTeamCommitment;
	}

	/**
	 * Sets the team commitment.
	 *
	 * @param isTeamCommitment the new team commitment
	 */
	public void setTeamCommitment(boolean isTeamCommitment) {
		this.isTeamCommitment = isTeamCommitment;
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
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;

	}

	/** The permission map. */
	private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
	/** The project. */
	private Project project;

	/**
	 * Instantiates a new commitment.
	 *
	 * @param name the name
	 * @param startTime the start time
	 * @param description the description
	 */
	public Commitment(String name, GregorianCalendar startTime,
			String description) {
		this.name = name;
		this.startTime = startTime;
		this.description = description;
		this.id = -1;
		this.status = 1;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Copies all fields in the parameter commitment to the commitment calling this function.
	 *
	 * @param comm the comm
	 */
	public void copy(Commitment comm){
		this.setDescription(comm.getDescription());
		this.setId(comm.getId());
		this.setName(comm.getName());
		this.setProject(comm.getProject());
		this.setStartTime(comm.getStartTime());
		this.setStatus(comm.getStatus());
		this.setUsername(comm.getUsername());
		this.setTeamCommitment(comm.isTeamCommitment());
		//this.setPermission(comm.getPermission(comm.g));
		
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

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * Checks if is active during time stamp.
	 *
	 * @param when the when
	 * @return true, if is active during time stamp
	 */
	public boolean isActiveDuringTimeStamp(GregorianCalendar when) {
		// On Calendar view, commitment will be shown as an one-hour long block. 
		GregorianCalendar endTimeOnGUI = (GregorianCalendar) startTime.clone();
		endTimeOnGUI.add(GregorianCalendar.HOUR, 1);
		if (when.before(startTime) || when.after(endTimeOnGUI)) {
			return false;
		} else {
			return true;
		}
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
		return new Gson().toJson(this, Commitment.class);
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.Model#save()
	 */
	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.Model#delete()
	 */
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.Model#identify(java.lang.Object)
	 */
	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO javadoc comment
	/**
	 * From json.
	 *
	 * @param json the json
	 * @return the commitment
	 */
	public static Commitment fromJSON(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Commitment.class);
	}

	/**
	 * From json array.
	 *
	 * @param json the json
	 * @return the commitment[]
	 */
	public static Commitment[] fromJsonArray(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Commitment[].class);
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public GregorianCalendar getStartTime() {
		return startTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (startTime.equals(null)){
			return "D:LKJHGFLlkhghjh";
		}

		//		return "Temp Date";
		return startTime.get(GregorianCalendar.YEAR) + " "
		+ startTime.get(GregorianCalendar.MONTH) + " "
		+ startTime.get(GregorianCalendar.DATE) + " " + name + " " + description;

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
