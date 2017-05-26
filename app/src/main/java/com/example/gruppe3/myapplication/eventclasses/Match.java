package com.example.gruppe3.myapplication.eventclasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tom on 25.05.2017.
 */

public class Match implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(matchId);
        out.writeString(team1);
        out.writeString(team2);
        out.writeInt(res1);
        out.writeInt(res2);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Match(Parcel in) {
        matchId = in.readString();
        team1 = in.readString();
        team2 = in.readString();
        res1 = in.readInt();
        res2 = in.readInt();
    }
}

