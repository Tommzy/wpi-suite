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

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**commitement 
 * @author Tommzy
 *
 */
public class GroupCommitment extends Commitment{
	ArrayList<User> groupMembers ;
	User owner;
	
	public GroupCommitment(String name, GregorianCalendar startTime, String location,
			String description,User owner,ArrayList<User> groupMembers) {
		super(name, startTime, location, description);
		this.groupMembers = new ArrayList<User>();
		this.owner = owner;
		int size = groupMembers.size;
		
		//Adding the given users to the user list.
		for(int i = 0; i < size;i++){
			this.gourpMembers.add(gourpMembers[i]);
		}
		// TODO Auto-generated constructor stub
	}

}
