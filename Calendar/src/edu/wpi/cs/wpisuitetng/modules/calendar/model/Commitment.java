package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Commitment extends CalendarItem{

	public Commitment(String name, GregorianCalendar startTime, String location,
			String description) {
		super(name, startTime, location, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Permission getPermission(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPermission(Permission p, User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project getProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProject(Project p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toJSON() {
		return new Gson().toJson(this, CalendarItem.class);
	}

	@Override
	public  CalendarItem fromJSON(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, CalendarItem.class);
	}

	@Override
	public CalendarItem[] fromJsonArray(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, CalendarItem[].class);
	}

}
