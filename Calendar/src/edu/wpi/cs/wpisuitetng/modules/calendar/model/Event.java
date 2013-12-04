package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Event {
  GregorianCalendar startTime;
	GregorianCalendar endTime;
	String name;
	String description;

	public Event(String name, GregorianCalendar startTime, GregorianCalendar endTime,
			String description) {
		this.startTime = startTime;
		this.endTime=endTime;
		this.name = name;
		this.description = description;
	}


}
