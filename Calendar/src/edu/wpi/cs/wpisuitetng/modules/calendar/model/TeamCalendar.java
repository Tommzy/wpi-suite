/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Brittany Owens
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import com.google.gson.Gson;

/**
 * This is a class for a Team Calendar structure. It includes information about the project it belongs to.
 * 
 * @author Team3
 * 
 */

public class TeamCalendar extends CalendarModel{
	
	String projName; //Do we want to print the project name in the gui?
  
  /**
   * @return a JSON-encoded string representation of this message object
   */
  @Override
  public String toJSON() {
    return new Gson().toJson(this, TeamCalendar.class);
  }

  /**
   * Returns an instance of TeamCalendar constructed using the given
   * TeamCalendar encoded as a JSON string.
   * 
   * @param json the json-encoded TeamCalendar to parse
   * @return the TeamCalendar contained in the given JSON
   */
  public static TeamCalendar fromJSON(String json) {
    final Gson parser = new Gson();
    return parser.fromJson(json, TeamCalendar.class);
  }

}
