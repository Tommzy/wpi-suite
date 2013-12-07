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
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

/**
 * interface asks year view, month view, week view, day view and commitment table 
 * to provide the update() method.
 * Once user selects another day from one of the view, MainCalendarController 
 * should be able to use this interface to update the other views simultaneously.
 * 
 * In addition, if you are implementing a ActionListener that will potentially 
 * content of the calendar views or other views, it's your responsibility to invoke 
 * MainCalendarController.getInstance().updateAll() method.
 * 
 * There is a list of Updatable in MainCalendarController. If you want to add
 * a new component to this list in order to keep updating, use
 * MainCalendarController.getInstance().addToUpdateList(SomethingUpdatable);
 * 
 * @author Hongbo
 *
 */
public interface Updatable {
	/**
	 * update the view display of calendar view.
	 */
	public void update();
}
