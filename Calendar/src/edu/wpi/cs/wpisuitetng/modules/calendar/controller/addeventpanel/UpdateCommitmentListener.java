/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.DeleteCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;

/**
 * @author Yuchen Zhang
 *
 */
public class UpdateCommitmentListener implements MouseListener {
	Commitment commitment; 
	
	public UpdateCommitmentListener (Commitment commitment) {
		this.commitment = commitment;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getClickCount() == 2) && (e.getButton() == MouseEvent.BUTTON1)) {
			AddCommitmentPanel newCommitmentPanel = new AddCommitmentPanel(new MigLayout());
			newCommitmentPanel.populateCommitment(commitment);
			AddEventPanelController.getInstance().getTabbedPane().add(newCommitmentPanel);
			AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Edit Commitment");
			AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
	        newCommitmentPanel.initiateFocus();
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			JPopupMenu menu = new JPopupMenu();
			JMenuItem anItem = new JMenuItem(" Delete ");
		    anItem.addActionListener(new DeleteCommitmentController(commitment.getId()));
			menu.add(anItem);    
	        menu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}