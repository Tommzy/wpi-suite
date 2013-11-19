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

import java.util.Date;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;

public class SingleCommitment extends Commitment{

	public SingleCommitment(String name,  GregorianCalendar startTime,String location, String description) {
		super(name, startTime,location, description);
		// TODO Auto-generated constructor stub
	}

}
