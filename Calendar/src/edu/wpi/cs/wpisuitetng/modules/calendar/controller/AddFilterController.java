package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.filters.FiltersModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the Filter text fields to the model as a new
 * requirement.
 * @version $Revision: 1.0 $
 * @author Hui Zheng
 */
public class AddFilterController implements ActionListener{

	/** Reference to the Filters model */
	private final FiltersModel model;
	
	/** The new filter to add */
	private final Filter filterToBeAdded;

	/** The observer associated with this controller */
	AddFilterRequestObserver observer = new AddFilterRequestObserver(this);
	
	/**
	 * Construct an AddFilterController for the given model
	 * @param model the model containing the messages
	 * @param filterToBeAdded the filter to add to the model
	 */
	public AddFilterController(FiltersModel model, Filter filterToBeAdded) {
		this.model = model;
		this.filterToBeAdded = filterToBeAdded;
	}
  
	/**
	 * Make and send the request to add the filter to the database.
	 * @param filter The filter to send
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void addFilterToDatabase(Filter filter){
		final Request request = Network.getInstance().makeRequest("calendar/filter", HttpMethod.PUT); // PUT == create
		request.setBody(filter.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddFilterRequestObserver(this)); // add an observer to process the response
		request.send();
	}
	 
	/**
	 * This method is called when the user clicks the Submit button
	 * @param event The event for the actio
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Send a request to the core to save this message
		// Add the message to the model
		final Request request = Network.getInstance().makeRequest("calendar/filter", HttpMethod.PUT); // PUT == create
		request.setBody(filterToBeAdded.toJSON()); // put the new message in the body of the request
		request.addObserver(new AddFilterRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		System.out.println("from AddFilterController." + request.getBody());
	}


	/**
	 * When the new Calendar item is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addFilterToModel(Filter item) {
		model.addFilter(item);
	}
}
