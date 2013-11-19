/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team3
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.dataitem;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class GroupEvent extends Event{
	ArrayList<User> Users ;

	public GroupEvent(String name,  GregorianCalendar startTime, GregorianCalendar endTime, User[] user,String location, String description) {
		super(name, startTime, endTime, location, description);
		this.Users = new ArrayList<User>();
		int size = user.length;
		//Adding the given users to the user list.
		for(int i = 0; i < size;i++){
			this.Users.add(user[i]);
		}
			
		// TODO Auto-generated constructor stub
	}
	
}
