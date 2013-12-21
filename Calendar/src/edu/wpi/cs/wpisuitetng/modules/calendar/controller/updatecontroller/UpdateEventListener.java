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

package edu.wpi.cs.wpisuitetng.modules.calendar.controller.updatecontroller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.deletecontroller.DeleteEventController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.AddEventPanel;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving updateEvent events.
 * The class that is interested in processing a updateEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addUpdateEventListener<code> method. When
 * the updateEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @author Yuchen Zhang
 * @version $Revision: 1.0 $
 */
public class UpdateEventListener implements MouseListener {
	
	/** The event. */
	Event event;

	/**
	 * Instantiates a new update event listener.
	 *
	 * @param event the event
	 */
	public UpdateEventListener (Event event) {
		this.event = event;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getClickCount() == 2) && (e.getButton() == MouseEvent.BUTTON1)) {
			final AddEventPanel newEventPanel = new AddEventPanel(new MigLayout());
			newEventPanel.populateEvent(event);
			AddEventPanelController.getInstance().getTabbedPane().add(newEventPanel);
			AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Edit Event");
			AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
			newEventPanel.initiateFocus();
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			final JPopupMenu menu = new JPopupMenu();
			final JMenuItem anItem = new JMenuItem(" Delete ");
			anItem.addActionListener(new DeleteEventController(event.getId()));
			menu.add(anItem);    
			menu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {	
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

}
