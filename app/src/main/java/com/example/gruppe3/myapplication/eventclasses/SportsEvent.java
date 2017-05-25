package com.example.gruppe3.myapplication.eventclasses;

import java.util.Date;

/**
 * Created by MatthiasW on 23.05.2017.
 */

public class SportsEvent {

    private String eventName;
    private String eventType;
    private String eventInfo;
    private Date eventDate;
    private Points eventPoints;
    private Matches eventMatches;

    public SportsEvent(String eventName, String eventType, String eventInfo, Date eventDate, Points eventPoints, Matches eventMatches) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventInfo = eventInfo;
        this.eventDate = eventDate;
        this.eventPoints = eventPoints;
        this.eventMatches = eventMatches;

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Points getEventPoints() {
        return eventPoints;
    }

    public void setEventPoints(Points eventPoints) {
        this.eventPoints = eventPoints;
    }

    public Matches getEventMatches() {
        return eventMatches;
    }

    public void setEventMatches(Matches eventMatches) {
        this.eventMatches = eventMatches;
    }
}
