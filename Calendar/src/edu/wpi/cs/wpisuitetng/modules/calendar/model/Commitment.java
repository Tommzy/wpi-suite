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


/**
 * Basic Commitment class that contains the data to be stored for a Commitment
 * 
 * 
 * @version $Revision: 1.0 $
 * @author Hui Zheng
 */
public class Commitment implements Model{

	private String name;
	private GregorianCalendar startTime;
	private String description;
	private int id = -1;
	
	

	private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
	private Project project;
	
	public Commitment(String name, GregorianCalendar startTime,
			String description) {
		this.name = name;
		this.startTime = startTime;
		this.description = description;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getter function for id
	 * @return id
	 */

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

	/**
	 * Setter function of startTime
	 * @param startTime
	 */
	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}
	
    /**
     * Determine if the Commitment is active during a certain time stamp
     * For GUI use
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


	
	@Override
	public Permission getPermission(User u) 
	{
		
		return permissionMap.get(u);
	}

	@Override
	public void setPermission(Permission p, User u) 
	{
		permissionMap.put(u, p);
	}
	
	@Override
	public Project getProject() {
		return project;
	}
	
	@Override
	public void setProject(Project p) {
		this.project = p;
	}

	@Override
	public String toJSON() {
		return new Gson().toJson(this, Commitment.class);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO javadoc comment
	public static Commitment fromJSON(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Commitment.class);
	}

	public static Commitment[] fromJsonArray(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Commitment[].class);
	}

	public GregorianCalendar getStartTime() {
		return startTime;
	}

	public String toString() {
		if (startTime.equals(null)){
			return "D:LKJHGFLlkhghjh";
		}
			
//		return "Temp Date";
		return startTime.get(GregorianCalendar.YEAR) + " "
				+ startTime.get(GregorianCalendar.MONTH) + " "
				+ startTime.get(GregorianCalendar.DATE) + " " + name + " " + description;

	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}


}
