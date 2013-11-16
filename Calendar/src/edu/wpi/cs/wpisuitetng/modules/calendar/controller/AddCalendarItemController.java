package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItemListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;

public class AddCalendarItemController implements ActionListener{

	private final CalendarItemListModel model;
	private final AddEventPanel viewEvent;
	private final AddCommitmentPanel viewCommitment;
	
	/**
	 * Construct an AddMessageController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddCalendarItemController(CalendarItemListModel model, AddEventPanel viewEvent, AddCommitmentPanel viewCommitment) {
		this.model = model;
		this.viewEvent = viewEvent;
		this.viewCommitment = viewCommitment;
	}

	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the text that was entered
		String name = viewEvent.getTxtNewname();
		GregorianCalendar startTime = null;
		try {
			startTime = viewEvent.getNewDate("startTime");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GregorianCalendar endTime = null;
		try {
			endTime = viewEvent.getNewDate("endTime");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String location = viewEvent.getNewLocation();
		String description = viewEvent.getNewDescription();
		
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		
		//don't want to make a new event if there is no name
		if ((name.length() > 0) & (endTime != null)) {
			Event sentEvent = new Event(name, startTime, endTime, location, description);
			// Add the message to the model
			model.addCalendarItem(sentEvent);
		}else if((name.length() > 0) & (endTime == null)){
			Commitment sentCommitment = new Commitment(name, startTime,location, description);
			// Add the message to the model
			model.addCalendarItem(sentCommitment);
		}
	}
	
//	@Override
//	public void actionAddCommitment(ActionEvent commitment) {
//		// Get the text that was entered
//		String name = viewCommitment.getTxtNewname();
//		Date startTime = viewCommitment.getNewDate("startTime");
//		String location = viewCommitment.getNewLocation();
//		String description = viewCommitment.getNewDescription();
//		
//		// Make sure there is text
//		// OR THROUGH EXCEPTION?
//		
//		//don't want to make a new event if there is no name
//		if (name.length() > 0) {
//			Commitment sentCommitment = new Commitment(name, startTime, location, description);
//			// Add the message to the model
//			model.addCalendarItem(sentCommitment);
//		}
//	}


}
