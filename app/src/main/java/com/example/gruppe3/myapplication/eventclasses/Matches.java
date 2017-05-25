package com.example.gruppe3.myapplication.eventclasses;

import com.example.gruppe3.myapplication.eventclasses.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 25.05.2017.
 */

public class Matches {

    private List<Match> matches;

    public Matches() {
        matches = new ArrayList<>();
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
