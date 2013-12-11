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
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.DeleteEventController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;

/**
 * @author Yuchen Zhang
 *
 */
public class UpdateEventListener implements MouseListener {
	Event event;
	
	public UpdateEventListener (Event event) {
		this.event = event;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getClickCount() == 2) && (e.getButton() == MouseEvent.BUTTON1)) {
			AddEventPanel newEventPanel = new AddEventPanel(new MigLayout());
			newEventPanel.populateEvent(event);
			AddEventPanelController.getInstance().getTabbedPane().add(newEventPanel);
			AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Edit Event");
			AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
			newEventPanel.initiateFocus();
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			JPopupMenu menu = new JPopupMenu();
			JMenuItem anItem = new JMenuItem(" Delete ");
		    anItem.addActionListener(new DeleteEventController(event.getId()));
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
