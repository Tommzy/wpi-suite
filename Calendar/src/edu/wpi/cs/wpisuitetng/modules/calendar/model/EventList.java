package edu.wpi.cs.wpisuitetng.modules.calendar.model;



/**
 * This is a model for Event. It contains all the variables to be
 * displayed on the view.
 * 
 * @author Team3
 * 
 */
@SuppressWarnings({ "serial" })
public class EventList extends CalendarItemListModel{
	
	public EventList (){
		super();
	}
	
	/**add new event to the  list. 
	 * ToDo: through exception
	 * @param newItem
	 */
	public void addEvent(CalendarItem newEvent) {
		addCalendarItem(newEvent);
	}
	
	/**empty the event list
	 * 
	 */
	public void emptyEventList(){
		emptyModel();
	}
	
	/**return the event list size
	 * @return the size of the event list
	 */
	public int getEventListSize() {
		// TODO Auto-generated method stub
		return getSize();
	}
	
	/**provide the element through the index
	 * @param index position of the event
	 * @return the event at that position
	 */
	public Object getEvent(int index){
		return  getElementAt(index);
	}
	
	

}
