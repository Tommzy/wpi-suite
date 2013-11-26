package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Commitment extends CalendarItem{

	public Commitment(String name, GregorianCalendar startTime,
			String description) {
		super(name, startTime, description);
		// TODO Auto-generated constructor stub
	}


}
