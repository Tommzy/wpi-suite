/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.invitation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.Invitation;



/**
 * The Class InvitationModel.
 * 
 * @author Eric
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class InvitationModel extends AbstractListModel{

  /** The Invitations. */
  private List<Invitation> Invitations;

  /** The next id available. */
  private int nextID;

  /** The instance. */
  private static InvitationModel instance; 

  /**
   * Instantiates a new invitation model.
   */
  private InvitationModel (){
    Invitations = new ArrayList<Invitation>();
    nextID = 0;
  }



  /**
   * Gets the single instance of InvitationModel.
   *
   * @return single instance of InvitationModel
   */
  public static InvitationModel getInstance()
  {
    if(instance == null)
    {
      instance = new InvitationModel();
    }

    return instance;
  }

  /**
   * Adds the invitation.
   *
   * @param newInvitation the new invitation
   */
  public void addInvitation(Invitation newInvitation){    
    this.Invitations.add(newInvitation);
  }

  /**
   * Gets the invitation.
   *
   * @param id the id
   * @return the invitation
   */
  public Invitation getInvitation(int id)
  {
    Invitation matchingID = null;
    // iterate through list of invitations until id is found
    for (int i=0; i < this.Invitations.size(); i++){
      matchingID = Invitations.get(i);
      if (matchingID.getId() == id){
        break;
      }
    }
    return matchingID;
  }

  /**
   * Gets the all invitation.
   *
   * @return the all invitation
   */
  public List<Invitation> getAllInvitation() {
    return Invitations;
  }

  /**
   * Removes the invitation.
   *
   * @param removeId the remove id
   */
  public void removeInvitation(int removeId){
    // iterate through list of invitations until id of project is found
    for (int i=0; i < this.Invitations.size(); i++){
      if (Invitations.get(i).getId() == removeId){
        // remove the id
        Invitations.remove(i);
        break;
      }
    }
  }

  /* (non-Javadoc)
   * @see javax.swing.ListModel#getSize()
   */
  public int getSize() {
    return Invitations.size();
  }

  /**
   * Gets the next id.
   *
   * @return the next id
   */
  public int getNextID()
  {
    return this.nextID++;
  }

  /* (non-Javadoc)
   * @see javax.swing.ListModel#getElementAt(int)
   */
  public Invitation getElementAt(int index) {
    return Invitations.get(Invitations.size() - 1 - index);
  }

  /**
   * Empty model.
   */
  public void emptyModel() {
    int oldSize = getSize();
    Iterator<Invitation> iterator = Invitations.iterator();
    while (iterator.hasNext()) {
      iterator.next();
      iterator.remove();
    }
    this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
  }

  /**
   * Adds the invitations.
   *
   * @param Invites the invites
   */
  public void addInvitations(Invitation[] Invites) {
    for (int i = 0; i < Invites.length; i++) {
      this.Invitations.add(Invites[i]);
      if(Invites[i].getId() >= nextID) nextID = Invites[i].getId() + 1;
    }
  }



  /**
   * Gets the children.
   *
   * @param invites the invites
   * @return the children
   */
  public List<Invitation> getChildren(Invitation invites) {
    List<Invitation> children = new ArrayList<Invitation>();

    return children;
  }

  /**
   * Gets the possible children.
   *
   * @param req the req
   * @return the possible children
   */
  public ListModel<Invitation> getPossibleChildren(Invitation req)
  {
    DefaultListModel<Invitation> possibleChildren = new DefaultListModel<Invitation>();

    for(Invitation possChild : Invitations)
    {
      possibleChildren.addElement(possChild);
    }

    return possibleChildren;
  }


  /**
   * Gets the possible parents.
   *
   * @param req the req
   * @return the possible parents
   */
  public ListModel<Invitation> getPossibleParents(Invitation req)
  {
    DefaultListModel<Invitation> possibleParents = new DefaultListModel<Invitation>();

    for(Invitation possParent : Invitations)
    {
      if(possParent == req) continue;
      possibleParents.addElement(possParent);
    }

    return possibleParents;
  }



  /**
   * Gets the invitations.
   *
   * @return the invitations
   */
  public List<Invitation> getInvitations() {
    return Invitations;
  }
}
