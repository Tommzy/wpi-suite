package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Commitment implements Model{

	private String name;
	private GregorianCalendar startTime;
	private String description;
	private int id = -1;
	
	

	private Map<User, Permission> permissionMap = new HashMap<User, Permission>(); // annotation for User serialization
	private Project project;
	
	public Commitment(String name, GregorianCalendar startTime,
			String description) {
		this.name = name;
		this.startTime = startTime;
		this.description = description;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getter function for id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter function for id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter function for name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter function of name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter function of description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter function of description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter function of startTime
	 * @return startTime
	 */
	public GregorianCalendar getStartTime() {
		return startTime;
	}

	/**
	 * Setter function of startTime
	 * @param startTime
	 */
	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}


	
	@Override
	public Permission getPermission(User u) 
	{
		
		return permissionMap.get(u);
	}

	@Override
	public void setPermission(Permission p, User u) 
	{
		permissionMap.put(u, p);
	}
	
	@Override
	public Project getProject() {
		return project;
	}
	
	@Override
	public void setProject(Project p) {
		this.project = p;
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

}
