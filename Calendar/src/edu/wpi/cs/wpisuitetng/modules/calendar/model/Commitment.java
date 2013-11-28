package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Commitment implements Model{

	String name;
	Calendar startTime;
	String description;
	int id = -1;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Commitment(String name, Calendar calendar,
			String description) {
		this.name = name;
		this.startTime = calendar;
		this.description = description;
		//super(name, startTime, description);
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
		return new Gson().toJson(this, Commitment.class);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO javadoc comment
	public static Commitment fromJSON(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Commitment.class);
	}
	
	public static Commitment[] fromJsonArray(String json){
		final Gson parser = new Gson();
		return parser.fromJson(json, Commitment[].class);
	}


	public Calendar getStartTime() {
		return startTime;
	}


	public String getName() {
		return name;
	}

}
