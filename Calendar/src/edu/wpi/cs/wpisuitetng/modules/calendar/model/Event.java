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

// TODO: Auto-generated Javadoc
/**
 * The Class Event.
 */
public class Event  implements Model{
	
	/** The name. */
	private String name;
	
	/** The start time. */
	private GregorianCalendar startTime;
	
	/** The end time */
	private GregorianCalendar endTime;
	
	/** Location */
	private String location;
	
	/** The description. */
	private String description;
	
	/** The id. */
	private int id = -1;

	/** The permission map. */
	private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
	
	/** The project. */
	private Project project;
	
	/**
	 * Instantiates a new event.
	 *
	 * @param name the name
	 * @param startTime the start time
	 * @param description the description
	 */
	public Event(String name, GregorianCalendar startTime, GregorianCalendar endTime, String location, 
			String description) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location; 
		this.description = description;
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
	
    /**
     * Determine if the Commitment is active during a certain time stamp
     * For GUI use.
     *
     * @param when the time stamp
     * @return true if active during the time stamp, false otherwise
     */
    public boolean isActiveDuringTimeStamp(GregorianCalendar when) {
        // On Calendar view, commitment will be shown as an one-hour long block. 
    	GregorianCalendar endTimeOnGUI = (GregorianCalendar) startTime.clone();
    	endTimeOnGUI.set(GregorianCalendar.HOUR, 1);
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



}

