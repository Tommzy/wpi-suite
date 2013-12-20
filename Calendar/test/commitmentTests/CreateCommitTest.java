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

package commitmentTests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.getcontroller.GetCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.modellist.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockNetwork;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

public class CreateCommitTest {

	@Before
	public void DoSetUp() {
		Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
		new NetworkConfiguration("http://wpisuitetng"));
	}
	
	
	/*
	@Test
	public void TestAddCommitmentController() {
		Commitment commit1 = new Commitment("Test Commitment 1", new GregorianCalendar(), "Test Description 1");
		CommitmentsModel cm = CommitmentsModel.getInstance();
		AddCommitmentController acc = new AddCommitmentController(cm, null);
		acc.addCommitmentToDatabase(commit1);
		
		GetCommitmentController con = new GetCommitmentController();
		con.retrieveCommitments();
		
		//CommitmentsModel comMod = CommitmentsModel.getInstance();
		assertEquals(cm.getSize(), 1);
		
		
		//fail("F");
	}
*/
}


