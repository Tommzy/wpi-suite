package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;

/**
 * This class is used to filter a mist of commitments and return those that begin within a specified time slot
 *
 */
public class CommitmentFilter {
	private GregorianCalendar startTime, endTime;
	
	/**
	 * Constructor for commitment filter
	 * 
	 * @param startTime 
	 * @param endTime
	 */
	public CommitmentFilter(GregorianCalendar startTime, GregorianCalendar endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Used to create a collection of commitments that begin within the start and end time
	 * 
	 * @return The collection of commitments within the time frame
	 */
	public Collection<Commitment> getCommitmentList() {
		Collection<Commitment> list = FakeCommitmentModel.getInstance().getCommitmentList();
		Collection<Commitment> cmtList = new ArrayList<Commitment>();
		Iterator<Commitment> itr = list.iterator();
		
		while (itr.hasNext()) {
			Commitment cmt = itr.next();
			if (cmt.getStartTime().after(startTime) && cmt.getStartTime().before(endTime)) {
				cmtList.add(cmt);
			}
		}
		
		return cmtList;
	}
}
