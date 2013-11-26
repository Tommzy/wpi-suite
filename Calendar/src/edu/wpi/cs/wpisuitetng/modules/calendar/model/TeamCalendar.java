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
 * Each project should have only one TeamCalendar. The TeamCalendar can be viewed by all members of the team,
 * and members with the correct permissions will be able to add events and commitments to the TeamCalendar.
 * 
 * @author Team3
 * 
 */
public class TeamCalendar extends CalendarModel{
	
	private String projName; //Do we want to print the project name in the gui?
	private int projID;
  
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

  /**
   * @return the number ID of the project this TeamCalendar belongs to.
   */
  public int getProjID() {
    return projID;
  }
  
  /**
   * Sets the TeamCalendar's projID to the given value
   * @param newId integer value to set projID to
   */
  public void setProjID(int newId) {
    projID = newId;
  }
  
}
