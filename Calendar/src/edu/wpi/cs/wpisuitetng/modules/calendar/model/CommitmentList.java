package edu.wpi.cs.wpisuitetng.modules.calendar.model;



/**
 * This is a model for Commitment. It contains all the variables to be
 * displayed on the view.
 * 
 * @author Team3
 * 
 */
@SuppressWarnings({ "serial" })
public class CommitmentList extends CalendarItemListModel{
	public CommitmentList (){
		super();
	}
	
	/**add new Commitment to the  list. 
	 * ToDo: through exception
	 * @param newItem
	 */
	public void addCommitment(CalendarItem newCommitment) {
		addCalendarItem(newCommitment);
	}
	
	/**empty the Commitment list
	 * 
	 */
	public void emptyCommitmentList(){
		emptyModel();
	}
	
	/**return the Commitment list size
	 * @return the size of the Commitment list
	 */
	public int getCommitmentListSize() {
		// TODO Auto-generated method stub
		return getSize();
	}
	
	/**provide the element through the index
	 * @param index position of the Commitment
	 * @return the Commitment at that position
	 */
	public Object getCommitment(int index){
		return  getElementAt(index);
	}
}
