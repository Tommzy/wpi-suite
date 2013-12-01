package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;

public class CommitmentFilter {
	private GregorianCalendar startTime, endTime;
	
	public CommitmentFilter(GregorianCalendar startTime, GregorianCalendar endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
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
