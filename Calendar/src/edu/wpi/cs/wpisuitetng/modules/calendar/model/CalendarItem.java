/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CalDev
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import java.util.GregorianCalendar;
import java.util.Iterator;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;


/**
 * Model to contain a single calendar item on the calendar
 * 
 * @author Hui Zheng
 *
 */
public abstract class CalendarItem {
	
	String name;
	GregorianCalendar startTime;
	String description;
	

	/**
	 * Constructs a Calendar Item for the given values
	 * @param message
	 */
	public CalendarItem(String name, GregorianCalendar startTime, String description){
		this.name = name;
		this.startTime = startTime;
		this.description = description;

	}
	
	/**
	 * Returns an array of PostBoardMessage parsed from the given JSON-encoded
	 * string.
	 * 
	 * @param json a string containing a JSON-encoded array of PostBoardMessage
	 * @return an array of PostBoardMessage deserialzied from the given json string
	 */
	public static CalendarItem[] fromJsonArray(String json){
		final Gson parser = new Gson();
		return parser.fromJson(json, CalendarItem[].class);
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// Format the date-time stamp
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm a");
		
		return name + ":	"+ dateFormat.format(startTime);
	}

	/*
	 * The methods below are required by the model interface, however they
	 * do not need to be implemented for a basic model like PostBoardMessage. 
	 */

	public void save() {}

	public void delete() {}

	public Boolean identify(Object o) {return null;}
	
	
	

//	@Override
//	public Permission getPermission(User u) {return null;}
//
//	@Override
//	public void setPermission(Permission p, User u) {}
//
//	@Override
//	public Project getProject() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setProject(Project p) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
