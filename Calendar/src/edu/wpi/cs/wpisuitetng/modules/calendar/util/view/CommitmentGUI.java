package edu.wpi.cs.wpisuitetng.modules.calendar.util.view;

import java.util.Calendar;

/**
 * a commitment class for gui
 * 
 * @author Hongbo
 *
 */
public class CommitmentGUI {
	private String name, description;
	private Calendar startTime;
	
	public CommitmentGUI(String name, String descr, Calendar startTime) {
		this.name = name;
		description = descr;
		this.startTime = startTime;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Calendar getStartTime() {
		return startTime;
	}
	
	
}
