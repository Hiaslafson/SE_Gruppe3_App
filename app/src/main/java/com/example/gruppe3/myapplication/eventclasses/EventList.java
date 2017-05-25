package com.example.gruppe3.myapplication.eventclasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 25.05.2017.
 */

public class  EventList {

    public List<SportsEvent> getSportsEventList() {
        return sportsEventList;
    }

    public void setSportsEventList(List<SportsEvent> sportsEventList) {
        this.sportsEventList = sportsEventList;
    }

    private List<SportsEvent> sportsEventList;

    public EventList () {

        this.sportsEventList = new ArrayList<>();
    }

    public void add (SportsEvent sportsEvent)  {
        this.sportsEventList.add(sportsEvent);
    }
}
