package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.CalendarItemListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;

public class AddCalendarItemController implements ActionListener{

	private final CalendarItemListModel model;
	private final AddEventPanel view;
	
	/**
	 * Construct an AddMessageController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddCalendarItemController(CalendarItemListModel model, AddEventPanel view) {
		this.model = model;
		this.view = view;
	}

	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the text that was entered
		String name = view.getTxtNewname().getText();
		Date startTime = view.getNewDate().getDate();
		String location = view.getNewLocation();
		String description = view.getNewDescription();
		
		// Make sure there is text
		// OR THROUGH EXCEPTION?
		if (name.length() > 0) {
			// Add the message to the model
			model.addMessage(name);
			
			// Clear the text field
			view.getTxtNewName().setText("");
		}
		
		
		
	}
	
	
}
