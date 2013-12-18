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
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class Event.
 */
public class Event  implements Model{

	/** The name. */
	private String name;

	/** The start time. */
	private GregorianCalendar startTime;

	/** The end time. */
	private GregorianCalendar endTime;

	/** Location. */
	private String location;

	/** The description. */
	private String description;
	
	/** The category */
	private Category category;

	/** The id. */
	private int id = -1;

	/** The username. */
	private String username;

	/** The is team event. */
	private boolean isTeamEvent = true;

	/** The permission map. */
	private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization

	/** The project. */
	private Project project;

	/**
	 * Instantiates a new event.
	 *
	 * @param name the name
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param location the location
	 * @param description the description
	 */
	public Event(String name, GregorianCalendar startTime, GregorianCalendar endTime, String location, 
			String description,Category category) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location; 
		this.description = description;
		this.category = category;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public GregorianCalendar getEndTime() {
		return endTime;
	}
	
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(GregorianCalendar endTime) {
		this.endTime = endTime;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Copy.
	 *
	 * @param event the event
	 */
	public void copy(Event event){
		this.setName(event.getName());
		this.setStartTime(event.getStartTime());
		this.setEndTime(event.getEndTime());
		this.setProject(event.getProject());
		this.setDescription(event.getDescription());
		this.setId(event.getId());
		this.setUsername(event.getUsername());
		this.setTeamEvent(event.isTeamEvent());
		this.setCategory(event.getCategory());

	}

	/**
	 * Getter function for id.
	 *
	 * @return id
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
	 * Setter function of startTime.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
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
		return new Gson().toJson(this, Event.class);
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
	 * @return the event
	 */
	public static Event fromJSON(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Event.class);
	}

	/**
	 * From json array.
	 *
	 * @param json the json
	 * @return the event[]
	 */
	public static Event[] fromJsonArray(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Event[].class);
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
	 * Gets the length of an event.
	 *
	 * @return the time span
	 */
	public int getTimeSpan() {
		return (int) (getEndTime().getTime().getTime() - getStartTime().getTime().getTime()) / 60000;
	}

	/**
	 * Determine if the DayEvent is active during a certain time stamp.
	 *
	 * @param calendar the time stamp
	 * @return true if active during the time stamp, false otherwise
	 */
	public boolean isActiveDuringTimeStamp(GregorianCalendar calendar) {
		if (calendar.before(startTime) || calendar.after(endTime)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Determine if the event is active during a given day.
	 *
	 * @param day the day
	 * @return true if the event is active during that day, false otherwise
	 */
	public boolean isActiveDuringDay(Date day){
		Date startDay, endDay;
		GregorianCalendar cal = new GregorianCalendar();
		//Set the startDay to the start of the day when the
		//event is active
		cal.setTime(startTime.getTime());
		cal.set(GregorianCalendar.HOUR_OF_DAY,0);
		cal.set(GregorianCalendar.MINUTE, 0);
		startDay = cal.getTime();
		//Set the endDay to the end of the day pointed by endTime
		cal.setTime(endTime.getTime());
		cal.set(GregorianCalendar.HOUR_OF_DAY,23);
		cal.set(GregorianCalendar.MINUTE, 59);
		endDay = cal.getTime();

		return startDay.before(day) && endDay.after(day);
	}

	/**
	 * Gets location of event.
	 *
	 * @return this.location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername(){
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username){
		this.username = username;
	}


	/**
	 * Sets the checks if is team event.
	 *
	 * @param bool the new checks if is team event
	 */
	public void setTeamEvent(boolean bool){
		this.isTeamEvent = bool;
	}

	/**
	 * Gets the checks if is team event.
	 *
	 * @return the checks if is team event
	 */
	public boolean isTeamEvent(){
		return this.isTeamEvent;
	}
	

}

