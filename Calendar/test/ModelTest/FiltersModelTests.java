package ModelTest;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.calendar.filters.FiltersModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class FiltersModelTests {

	
	
	@Test
	public void addFilterTest() {
		//Set Up
		
		Filter testCom1 = new Filter("Test Filter", null);
		Filter testCom2 = new Filter("Copy Stuff", null);
		
		FiltersModel model = FiltersModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addFilter(testCom1);
		model.addFilter(testCom2);
		
		List<Filter> comList = model.getAllFilter();
		
		assertEquals(comList.size(), 2);
		
	}
	
	@Test
	public void removeFilterTest() {
		//Set Up
		
		Filter testCom1 = new Filter("Test Filter", null);
		testCom1.setId(1);
		Filter testCom2 = new Filter("Copy Stuff", null);
		testCom2.setId(2);
		
		FiltersModel model = FiltersModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addFilter(testCom1);
		model.addFilter(testCom2);
		
		model.removeFilter(2);
		
		List<Filter> comList = model.getAllFilter();
		
		assertEquals(comList.size(), 1);
		
	}
	
	@Test
	public void emptyModelTest() {
		//Set Up
		
				Filter testCom1 = new Filter("Test Filter", null);
				Filter testCom2 = new Filter("Copy Stuff", null);
				
				FiltersModel model = FiltersModel.getInstance();

				
				//Tests
				model.addFilter(testCom1);
				model.addFilter(testCom2);
				
				List<Filter> comList = model.getAllFilter();
				model.emptyModel();
				assertEquals(comList.size(), 0);
				
	}
	
	
	
	@Test
	public void getSizeTests() {
		//Set Up
		
		Filter testCom1 = new Filter("Test Filter", null);
		Filter testCom2 = new Filter("Copy Stuff", null);
		
		FiltersModel model = FiltersModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addFilter(testCom1);
		model.addFilter(testCom2);
		

		assertEquals(model.getSize(), 2);
		
	}
	
	@Test
	public void getFilterTest() {
		//Set Up
		
		Filter testCom1 = new Filter("Test Filter", null);
		testCom1.setId(1);
		Filter testCom2 = new Filter("Copy Stuff", null);
		testCom2.setId(2);
		
		FiltersModel model = FiltersModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addFilter(testCom1);
		model.addFilter(testCom2);
		
		assertEquals(model.getFilter(2), testCom2);
		
	}

}
