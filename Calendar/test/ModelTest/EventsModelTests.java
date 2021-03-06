/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team 3
 * V1.0
 ******************************************************************************/
package ModelTest;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.EventsModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class EventsModelTests {

	
	
	@Test
	public void addEventTest() {
		//Set Up
		
		Event testCom1 = new Event("Test Event", null, null, "no where", "Test Event");
		Event testCom2 = new Event("Test Event", null, null, "no where", "Test Event");
		
		EventsModel model = EventsModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addEvent(testCom1);
		model.addEvent(testCom2);
		
		List<Event> comList = model.getAllEvent();
		
		assertEquals(comList.size(), 2);
		
	}
	
	@Test
	public void removeEventTest() {
		//Set Up
		
		Event testCom1 = new Event("Test Event", null, null, "no where", "Test Event");
		testCom1.setId(1);
		Event testCom2 = new Event("Test Event", null, null, "no where", "Test Event");
		testCom2.setId(2);
		
		EventsModel model = EventsModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addEvent(testCom1);
		model.addEvent(testCom2);
		
		model.removeEvent(2);
		
		List<Event> comList = model.getAllEvent();
		
		assertEquals(comList.size(), 1);
		
	}
	
	@Test
	public void emptyModelTest() {
		//Set Up
		
				Event testCom1 = new Event("Test Event", null, null, "no where", "Test Event");
				Event testCom2 = new Event("Test Event", null, null, "no where", "Test Event");
				
				EventsModel model = EventsModel.getInstance();

				
				//Tests
				model.addEvent(testCom1);
				model.addEvent(testCom2);
				
				List<Event> comList = model.getAllEvent();
				model.emptyModel();
				assertEquals(comList.size(), 0);
				
	}
	
	
	
	@Test
	public void getSizeTests() {
		//Set Up
		
		Event testCom1 = new Event("Test Event", null, null, "no where", "Test Event");
		Event testCom2 = new Event("Test Event", null, null, "no where", "Test Event");
		
		EventsModel model = EventsModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addEvent(testCom1);
		model.addEvent(testCom2);
		

		assertEquals(model.getSize(), 2);
		
	}
	
	@Test
	public void getEventTest() {
		//Set Up
		
		Event testCom1 = new Event("Test Event", null, null, "no where", "Test Event");
		testCom1.setId(1);
		Event testCom2 = new Event("Test Event", null, null, "no where", "Test Event");
		testCom2.setId(2);
		
		EventsModel model = EventsModel.getInstance();
		model.emptyModel();
		
		//Tests
		model.addEvent(testCom1);
		model.addEvent(testCom2);
		
		assertEquals(model.getEvent(2), testCom2);
		
	}

}
