package com.example.gruppe3.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.gruppe3.myapplication.eventclasses.EventList;
import com.example.gruppe3.myapplication.eventclasses.Match;
import com.example.gruppe3.myapplication.eventclasses.Matches;
import com.example.gruppe3.myapplication.eventclasses.Point;
import com.example.gruppe3.myapplication.eventclasses.Points;
import com.example.gruppe3.myapplication.eventclasses.SportsEvent;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private List<String> liste;

    private EventList events = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String params =  "";
        //Some url endpoint that you may have
        //String myUrl = "http://10.0.0.28:3000/events";
        String myUrl = "http://192.168.0.2:3000/events";

        //String to place our result in
        String result;
        //Instantiate new instance of our class
        GetJson getRequest = new GetJson();
        //Perform the doInBackground method, passing in our url

        events = new EventList();

        try {
            result = getRequest.execute(myUrl).get();
            events = jsonStringToEventList(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get reference of widgets from XML layout
        final ListView lv = (ListView) findViewById(R.id.lv);

        // Create a List from String Array elements
        final List<String> eventList = new ArrayList<String>();

        for (int e = 0; e < events.getSportsEventList().size(); e++) {
            eventList.add("Event: " + events.getSportsEventList().get(e).getEventName() + ", Typ: " + events.getSportsEventList().get(e).getEventType() + ", Info: " + events.getSportsEventList().get(e).getEventInfo());
        }

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, eventList);

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent i = new Intent(MainActivity.this, AddEventActivity.class);
                    MainActivity.this.startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //TODO entfernen
                //eventList.add("fb 3, Fußball, 13.5.2017");
                arrayAdapter.notifyDataSetChanged();
            }
        });

        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object o = lv.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this,DetailActivity.class);
                MainActivity.this.startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setListAdapter(ArrayAdapter<String> listAdapter) {
        this.adapter = listAdapter;
    }

    public EventList jsonStringToEventList(String jsonString) throws JSONException {

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

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                eventDate = dateFormat.parse(dateStr);

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
            }

            sportsEvents.add(new SportsEvent(eventId, name, eventType, eventInfo, eventDate, eventPoints, eventMatches));
        }
        return sportsEvents;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String team1 = data.getStringExtra("team1");
                String team2 = data.getStringExtra("team2");
                String res1 = data.getStringExtra("result1");
                String res2 = data.getStringExtra("result2");
                int res1Int = Integer.parseInt(res1);
                int res2Int = Integer.parseInt(res2);
                new Match("", team1, team2, res1Int, res2Int);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

}
