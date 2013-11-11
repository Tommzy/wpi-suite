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

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class GroupEvent extends Event{
	ArrayList<User> Users ;

	public GroupEvent(String name, Date date, float startTime, float endTime, User[] user) {
		super(name, date, startTime, endTime);
		this.Users = new ArrayList<User>();
		int size = user.length;
		//Adding the given users to the user list.
		for(int i = 0; i < size;i++){
			this.Users.add(user[i]);
		}
			
		// TODO Auto-generated constructor stub
	}
	
}
