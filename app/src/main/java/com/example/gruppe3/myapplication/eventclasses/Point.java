package com.example.gruppe3.myapplication.eventclasses;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Tom on 25.05.2017.
 */

public class Point {

    @JsonProperty("team")
    private String team;
    @JsonProperty("points")
    private int points;

    public Point(String team, int points) {
        this.team = team;
        this.points = points;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
