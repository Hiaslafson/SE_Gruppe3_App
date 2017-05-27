package com.example.gruppe3.myapplication.eventclasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.format;
import static com.example.gruppe3.myapplication.eventclasses.InOut.getDateFormat;

/**
 * Created by MatthiasW on 23.05.2017.
 */

public class SportsEvent implements Parcelable {

    private String evendId;
    private String eventName;
    private String eventType;
    private String eventInfo;
    private Date eventDate;
    private Points eventPoints;
    private Matches eventMatches;
    private static int id = 0;

    public SportsEvent(String eventId, String eventName, String eventType, String eventInfo, Date eventDate, Points eventPoints, Matches eventMatches) {
        this.evendId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventInfo = eventInfo;
        this.eventDate = eventDate;
        this.eventPoints = eventPoints;
        this.eventMatches = eventMatches;

    }

    public SportsEvent(String eventName, String eventType, String eventInfo, Date eventDate) {
        this.evendId = "_id" + String.valueOf(getId()); //TODO for testing
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventInfo = eventInfo;
        this.eventDate = eventDate;
        this.eventPoints = new Points();
        this.eventMatches = new Matches();

    }

    public SportsEvent() {
        //TODO only for testing
        this.eventPoints = new Points();
        this.eventMatches = new Matches();
    }

    public String getEvendId() {
        return evendId;
    }

    public void setEvendId(String evendId) {
        this.evendId = evendId;
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

    public static int getId() {
        int retid = id;
        id++;
        return retid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(evendId);
        out.writeString(eventName);
        out.writeString(eventType);
        out.writeString(eventInfo);

        String strDate = getDateFormat().format(eventDate.getTime());
        out.writeString(strDate); //TODO
        //TODO this.eventPoints = new Points();
        //TODO this.eventMatches = new Matches();
     }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<SportsEvent> CREATOR = new Parcelable.Creator<SportsEvent>() {
        public SportsEvent createFromParcel(Parcel in) {
            return new SportsEvent(in);
        }

        public SportsEvent[] newArray(int size) {
            return new SportsEvent[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private SportsEvent(Parcel in) {
        this.evendId = in.readString();
        this.eventName = in.readString();
        this.eventType = in.readString();
        this.eventInfo = in.readString();

        try {
            eventDate = getDateFormat().parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //TODO this.eventPoints = eventPoints;
        //TODO  this.eventMatches = eventMatches;
    }
}
