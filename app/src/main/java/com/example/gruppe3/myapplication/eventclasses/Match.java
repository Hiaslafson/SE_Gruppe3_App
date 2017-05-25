package com.example.gruppe3.myapplication.eventclasses;

/**
 * Created by Tom on 25.05.2017.
 */

public class Match {

    private String matchId;
    private String team1;
    private String team2;
    private int res1;
    private int res2;

    public Match(String matchId, String team1, String team2, int res1, int res2) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.res1 = res1;
        this.res2 = res2;
    }

    public int getRes2() {
        return res2;
    }

    public void setRes2(int res2) {
        this.res2 = res2;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }


    public int getRes1() {
        return res1;
    }

    public void setRes1(int res1) {
        this.res1 = res1;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}

