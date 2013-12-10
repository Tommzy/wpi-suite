package ModelTest;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockData;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.CommitmentEntityManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import static org.junit.Assert.*;

import org.junit.Test;

public class CommitmentEntityManagerTests {

	@Test
	public void test() throws WPISuiteException {
		
		Commitment com1 = new Commitment("Commitment 1", null, "Commitment 1");
		Commitment com2 = new Commitment("Commitment 2", null, "Commitment 2");
		Commitment com3 = new Commitment("Commitment 3", null, "Commitment 3");
		Commitment com4 = new Commitment("Commitment 4", null, "Commitment 4");
		
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		Project testProject = new Project("test", "1");
		
		MockData fakeDB = new MockData(new HashSet<Object>());
		fakeDB = new MockData(new HashSet<Object>());
		
		CommitmentEntityManager comEntMan = new CommitmentEntityManager(fakeDB);
		comEntMan.assignUniqueID(com1);
		fakeDB.save(com1, testProject);
		comEntMan.assignUniqueID(com2);
		fakeDB.save(com2, testProject);
		comEntMan.assignUniqueID(com3);
		fakeDB.save(com3, testProject);
		comEntMan.assignUniqueID(com4);
		fakeDB.save(com4, testProject);

		fakeDB.save(admin);
		

		
		assertEquals(comEntMan.HighestId(), 4);

	}
}
