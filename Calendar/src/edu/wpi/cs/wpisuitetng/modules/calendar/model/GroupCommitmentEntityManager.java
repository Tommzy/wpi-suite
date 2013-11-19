package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.GregorianCalendar;
import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.calendar.dataitem.GroupCommitment;

public class GroupCommitmentEntityManager extends CommitmentEntityManager {

	public GroupCommitmentEntityManager(Data db) {
		super(db);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CalendarItem[] getAll(Session s) throws WPISuiteException {

		// Ask the database to retrieve all objects of the type Commitment
		// Passing a dummy PostBoardMessage lets the db know what type of object to retrieve
		// Passing the project makes it only get messages from that project
		List<Model> messages = db.retrieveAll(new GroupCommitment("dummy",new GregorianCalendar(),"dummy","dummy", null, null), s.getProject());

		// Return the list of commitments as an array
		return messages.toArray(new GroupCommitment[0]);
	}
	
	@Override
	public int Count() throws WPISuiteException {
		// Return the number of PostBoardMessages currently in the database
		return db.retrieveAll(new GroupCommitment("dummy",new GregorianCalendar(),"dummy","dummy", null, null)).size();
	}

}
