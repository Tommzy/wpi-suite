package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;

/**
 * this is the fake model for commitment
 * this class is singleton 
 * this class provides a list of commitment for testing purpose.
 * @author Hongbo
 *
 */
public class FakeCommitmentModel {
	
	public static FakeCommitmentModel instance;
	ArrayList<Commitment> cmtList = new ArrayList<Commitment>();
	FakeCommitmentModel() {
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		
		cal.set(2013, 10, 11, 10, 0);

		cmtList.add(new Commitment("Presentation", (GregorianCalendar) cal.clone(), "presentation for next ite"));
		cal.set(2013, 10, 12);
	
		cmtList.add(new Commitment("abc", (GregorianCalendar) cal.clone(), "123"));
		cal.set(2013, 11, 11);
		cmtList.add(new Commitment("def",  (GregorianCalendar)cal.clone(), "345"));
		cal.set(2013, 11, 12);
		cmtList.add(new Commitment("efg",  (GregorianCalendar)cal.clone(), "456"));
		
		cal.set(2013, 10, 18);
		cmtList.add(new Commitment("zz",  (GregorianCalendar)cal.clone(), "rr"));
		cal.set(2013, 10, 18);
		cmtList.add(new Commitment("ssf",  (GregorianCalendar)cal.clone(), "var"));
		
		
		cal.set(2014, 10, 11);
		cmtList.add(new Commitment("Presentation",  (GregorianCalendar)cal.clone(), "presentation for next ite"));
		cal.set(2014, 10, 12);
		cmtList.add(new Commitment("abc",  (GregorianCalendar)cal.clone(), "123"));
		cal.set(2014, 11, 11);
		cmtList.add(new Commitment("def",  (GregorianCalendar)cal.clone(), "345"));
		cal.set(2014, 11, 12);
		cmtList.add(new Commitment("efg",  (GregorianCalendar)cal.clone(), "456"));
		
		cal.set(2014, 10, 18);
		cmtList.add(new Commitment("zz",  (GregorianCalendar)cal.clone(), "rr"));
		cal.set(2014, 10, 18);
		cmtList.add(new Commitment("ssf",  (GregorianCalendar)cal.clone(), "var"));
		

	}
	public static FakeCommitmentModel getInstance() {
		if (instance == null) {
			instance = new FakeCommitmentModel();
		}
		return instance;
	}
	
	public Collection<Commitment> getCommitmentList() {
		return CommitmentsModel.getInstance().getCommitments();
	}
	
	
}
