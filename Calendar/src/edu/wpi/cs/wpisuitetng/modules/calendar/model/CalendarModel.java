/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * This is a class for a Calendar structure. It includes all information related to one calendar.
 * 
 * @author Team3
 * 
 */
public abstract class CalendarModel implements Model {
  EventList events;
  CommitmentList commitments;
  
  @Override
  public void save() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void delete() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public Boolean identify(Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Permission getPermission(User u) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setPermission(Permission p, User u) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Project getProject() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setProject(Project p) {
    // TODO Auto-generated method stub
    
  }
}
