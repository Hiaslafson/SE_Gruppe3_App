package com.example.gruppe3.myapplication.eventclasses;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.text.Layout;

import com.example.gruppe3.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tom on 27.05.2017.
 */

public class InOut {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static void printTextSnackbar(CoordinatorLayout layout, String text) {
        Snackbar snackbar = Snackbar
                .make(layout, text, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static EventList jsonStringToEventList(String jsonString) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonString);
        EventList sportsEvents = new EventList();

        for (int i = 0; i < jsonArray.length(); i++) {
            String eventId = null;
            String name = null;
            String eventType = null;
            String eventInfo = null;
            Date eventDate = null;
            Points eventPoints = new Points();
            Matches eventMatches = new Matches();

            try {
                eventId = (String) jsonArray.getJSONObject(i).get("_id");
                name = (String) jsonArray.getJSONObject(i).get("name");
                eventType = (String) jsonArray.getJSONObject(i).get("type");
                eventInfo = (String) jsonArray.getJSONObject(i).get("info");
                String dateStr = (String) jsonArray.getJSONObject(i).get("eventDate");
                eventDate = getDateFormat().parse(dateStr);

                JSONArray pointArray = (JSONArray) jsonArray.getJSONObject(i).get("points");
                for (int j = 0; j < pointArray.length(); j++) {
                    String team = (String) pointArray.getJSONObject(j).get("team");
                    int points = (int) pointArray.getJSONObject(j).get("points");
                    eventPoints.add(new Point(team, points));
                }

                JSONArray matchesArray = (JSONArray) jsonArray.getJSONObject(i).get("matches");
                for (int j = 0; j < matchesArray.length(); j++) {
                    String matchId = (String) matchesArray.getJSONObject(j).get("_id");
                    String team1 = (String) matchesArray.getJSONObject(j).get("team1");
                    String team2 = (String) matchesArray.getJSONObject(j).get("team2");
                    int result1 = (int) matchesArray.getJSONObject(j).get("result1");
                    int result2 = 0;

                    if (matchesArray.getJSONObject(j).has("result2")) {
                        result2 = (int) matchesArray.getJSONObject(j).get("result2");
                    }
                    eventMatches.add(new Match(matchId, team1, team2, result1, result2));
                }

            } catch (Exception e) {
                e.printStackTrace();
                //TODO CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                //printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
            }

            sportsEvents.add(new SportsEvent(eventId, name, eventType, eventInfo, eventDate, eventPoints, eventMatches));
        }
        return sportsEvents;
    }
}


