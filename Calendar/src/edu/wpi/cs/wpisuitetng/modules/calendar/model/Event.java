package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Date;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Event extends CalendarItem{
	Date endTime;

	public Event(String name, Date startTime, Date endTime, String location,
			String description) {
		super(name, startTime, location, description);
		this.endTime=endTime;
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

}
