package com.example.gruppe3.myapplication.eventclasses;

/**
 * Created by Tom on 25.05.2017.
 */

public class Point {

    private String team;
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
