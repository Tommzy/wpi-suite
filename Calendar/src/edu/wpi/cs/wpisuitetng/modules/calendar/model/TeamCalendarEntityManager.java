package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;

public class TeamCalendarEntityManager implements EntityManager<TeamCalendar> {
  
  //The database
  Data db;
  
  //-------------Functions------------
  /**
   * Receives a message in JSON form and parses it into an actual TeamCalendar,
   *   then saves it to the database.
   *   
   * @param s Session object containing info about the user making the request
   * @param content JSON string to be converted.
   * 
   * @return A new TeamCalendar derived from the JSON string
   * 
   */
  @Override
  public TeamCalendar makeEntity(Session s, String content)
      throws BadRequestException, ConflictException, WPISuiteException {

    // Parse the message from JSON
    final TeamCalendar newTeamCalendar = TeamCalendar.fromJSON(content);

    // Save the message in the database if possible, otherwise throw an exception
    // We want the message to be associated with the project the user logged in to
    if (!db.save(newTeamCalendar, s.getProject())) {
      throw new WPISuiteException();
    }

    // Return the newly created message (this gets passed back to the client)
    return newTeamCalendar;
  }

  @Override
  public TeamCalendar[] getEntity(Session s, String id)
      throws NotFoundException, WPISuiteException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * @param s Info about the user making the request
   * @return all TeamCalendars that have been saved in the database
   */
  @Override
  public TeamCalendar[] getAll(Session s) throws WPISuiteException {

    // Ask the database to retrieve all objects of the type TeamCalendar.
    // Passing a dummy TeamCalendar lets the db know what type of object to retrieve
    // Passing the project makes it only get the Calendar(s?) from that project
    List<Model> tCalendars = db.retrieveAll(new TeamCalendar(), s.getProject());

    // Return the list of messages as an array
    return tCalendars.toArray(new TeamCalendar[0]);
  }

  /**
   * Update this team's calendar.
   * @param s Information about the user who's updating the 
   */
  @Override
  public TeamCalendar update(Session s, String content)
      throws WPISuiteException {
    //How do???
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Save the given calendar in the database
   */
  @Override
  public void save(Session s, TeamCalendar model) throws WPISuiteException {
    
    db.save(model);
  }

  @Override
  public boolean deleteEntity(Session s, String id) throws WPISuiteException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String advancedGet(Session s, String[] args)
      throws WPISuiteException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteAll(Session s) throws WPISuiteException {
    // TODO Auto-generated method stub
    
  }

  /**
   * @return the number of TeamCalendars currently in the database
   */
  @Override
  public int Count() throws WPISuiteException {
    return db.retrieveAll(new TeamCalendar()).size();
  }

  @Override
  public String advancedPut(Session s, String[] args, String content)
      throws WPISuiteException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String advancedPost(Session s, String string, String content)
      throws WPISuiteException {
    // TODO Auto-generated method stub
    return null;
  }

}
