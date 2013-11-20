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

/**
 * This is a class for a Personal Calendar structure. It includes information about the user it belongs to.
 * 
 * @author Team3
 * 
 */

public class PersonalCalendar extends CalendarModel{
	//These data fields may become redundant, but for now they are included for convenience.
  String userName;
  int userID;
  
  @Override
  public String toJSON() {
    // TODO Auto-generated method stub
    return null;
  }
  
  /**
   * Returns an instance of PersonalCalendar constructed using the given
   * JSON string.
   * 
   * @param json the json-encoded PersonalCalendar to parse
   * @return the PersonalCalendar contained in the given JSON string
   */
  public static TeamCalendar fromJSON(String json) {
    //TODO 
    return null;
  }
  
  
}
