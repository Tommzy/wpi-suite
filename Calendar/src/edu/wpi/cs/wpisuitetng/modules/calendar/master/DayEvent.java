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


package edu.wpi.cs.wpisuitetng.modules.calendar.master;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * A DayEvent is an event taking place during a specified time slot. The DayEvent
 * contains information such as name, start and end time, priority and is associated
 * with a certain category.
 *
 * @author Tomas
 */
public class DayEvent{

    /**
     * Priority for a given DayEvent
     */
    public static enum Priority{
        VERY_LOW, LOW, MEDIUM,
        HIGH, VERY_HIGH;

        @Override
        public String toString(){
            if(this.name().equals("VERY_LOW"))
                return "Very low";
            else if(this.name().equals("LOW"))
                return "Low";
            else if(this.name().equals("MEDIUM"))
                return "Medium";
            else if(this.name().equals("HIGH"))
                return "High";
            else
                return "Very high";
        }
    }

    private String eventName = "No_Name";
    private GregorianCalendar startTime, endTime;

    //Default values for category and priority
    //private Category category = new Category("none");
    private Priority priority = Priority.LOW;
    private String description;
    private UUID id;

    public DayEvent() {
        id = UUID.randomUUID();
        startTime = new GregorianCalendar();
        endTime = new GregorianCalendar();
    }

    public DayEvent(String eventName) {
        this();
        this.eventName = eventName;
    }

    public DayEvent(String eventName, GregorianCalendar startTime, GregorianCalendar endTime) {
        if (startTime.compareTo(endTime) > 0) {
            throw new IllegalArgumentException("Event start time must be <= end time");
        }
        id = UUID.randomUUID();
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

//    public DayEvent(String eventName, Date startTime, Date endTime, Category category, Priority priority) {
//        this(eventName,startTime,endTime);
//        this.category = category;
//        this.priority = priority;
//    }



    /**
     * Get the time when the event ends
     * @return the ending time of the event
     */
    public GregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Determine if the DayEvent is active during a certain time stamp
     * @param when the time stamp
     * @return true if active during the time stamp, false otherwise
     */
    public boolean isActiveDuringTimeStamp(GregorianCalendar when) {
        if (when.before(startTime) || when.after(endTime)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Determine if the event is active during a given day
     * @param day the day
     * @return true if the event is active during that day, false otherwise
     */
    public boolean isActiveDuringDay(Date day){
        Date startDay, endDay;
        Calendar cal = new GregorianCalendar();
        //Set the startDay to the start of the day when the
        //event is active
        cal.setTime(startTime.getTime());
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE, 0);
        startDay = cal.getTime();
        //Set the endDay to the end of the day pointed by endTime
        cal.setTime(endTime.getTime());
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE, 59);
        endDay = cal.getTime();

       return startDay.before(day) && endDay.after(day);
    }


    /**
     * Set the time when the event should end
     * @param endTime The time when the event should end
     */
    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the name of the event
     * @return The name of the event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Set a new name for the event
     * @param eventName The new name of the event
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Get the time when the event starts
     * @return the starting time of the event
     */
    public GregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Set the time when the event should start
     * @param startTime The time when the event should start
     */
    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

//    public Category getCategory() {
//        return category;
//    }

//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public UUID getID(){
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeSpan() {
        return (int) (getEndTime().getTime().getTime() - getStartTime().getTime().getTime()) / 60000;
    }

    @Override
    public String toString() {
        return id + "\n"+ startTime + "-" + endTime + " " + eventName;
    }
}