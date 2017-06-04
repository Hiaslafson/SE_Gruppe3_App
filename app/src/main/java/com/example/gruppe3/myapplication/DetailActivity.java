package com.example.gruppe3.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gruppe3.myapplication.eventclasses.EventList;
import com.example.gruppe3.myapplication.eventclasses.Match;
import com.example.gruppe3.myapplication.eventclasses.Matches;
import com.example.gruppe3.myapplication.eventclasses.SportsEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.gruppe3.myapplication.MainActivity.myIP;
import static com.example.gruppe3.myapplication.eventclasses.InOut.jsonStringToEventList;
import static com.example.gruppe3.myapplication.eventclasses.InOut.printTextSnackbar;

public class DetailActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    private List<String> liste;

    // Create a List from String Array elements
    private List<String> matchList = new ArrayList<>();
    private Matches matches = new Matches();
    private Activity a = this;
    private SportsEvent event = new SportsEvent(); //TODO only temp

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details);
            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
            //setSupportActionBar(toolbar);

            Bundle b = getIntent().getExtras();
            String value = ""; // or other values
            if(b != null)
                value = b.getString("id");

            //TODO liest es nochmals aus, ev mitgeben ------------------------------
            String myUrl =  myIP + "/events";
            String result;
            //Instantiate new instance of our class
            GetJson getRequest = new GetJson();
            try {
                result = getRequest.execute(myUrl).get();
                EventList events = jsonStringToEventList(result);

                for (SportsEvent e : events.getSportsEventList()) {
                    if (e.getEvendId().equalsIgnoreCase(value)) {
                        event = e;
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
            }


            //------------------------------

            // Get reference of widgets from XML layout
            final ListView lv = (ListView) findViewById(R.id.lv_matches);

            // Create a List from String Array elements
            matchList = new ArrayList<String>();

            for (Match m : event.getEventMatches().getMatches()) {
                if (event.getEventType().contains("Fußball")) {
                    matchList.add(m.getTeam1() + " vs. " + m.getTeam2() + ", Ergebnis: " + m.getRes1() + " : " + m.getRes2());
                }
                else {
                    matchList.add("Fahrer: " + m.getTeam1() + ", Startnummer: " + m.getRes1() + " Fahrzeit: " +  " : " + m.getTeam2());
                }
                matches.add(m);
            }

            // Create an ArrayAdapter from List
            arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, matchList);

            // DataBind ListView with items from ArrayAdapter
            lv.setAdapter(arrayAdapter);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Intent i = null;
                        if (event.getEventType().contains("Fußball")) {
                            i = new Intent(DetailActivity.this, AddMatchActivity.class);
                        } else {
                            i = new Intent(DetailActivity.this, AddDriverActivity.class);
                        }

                        DetailActivity.this.startActivityForResult(i, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                        printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
                    }
                }
            });

            FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
            fabSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                        printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
                    }
                }
            });



            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
                    Object o = lv.getItemAtPosition(position);

                    String mid = matches.getMatches().get(position).getMatchId();
                    System.out.print(mid);

                    AlertDialog.Builder builder = new AlertDialog.Builder(a);
                    builder.setMessage("Do you want to delete this match?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Handle Ok
                                    System.out.print("delete");

                                    JSONObject object = new JSONObject();
                                    try {
                                        object.put("eventId", event.getEvendId());
                                        object.put("team1", matches.getMatches().get(position).getTeam1());
                                        object.put("team2",matches.getMatches().get(position).getTeam2());
                                        object.put("result1", matches.getMatches().get(position).getRes1());
                                        object.put("result2", matches.getMatches().get(position).getRes2());

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    String message = object.toString();
                                    PostJson post = new PostJson();
                                    String stringUrl =  myIP +"/events/" + matches.getMatches().get(position).getMatchId() + "/deleteMatches";
                                    try {
                                        String result;
                                        result = post.execute(stringUrl, message).get();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                                        printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
                                    }





                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Handle Cancel
                                    System.out.print("Cancel");
                                }
                            })
                            .create();
                    builder.show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
            printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
        }
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

    //public void setListAdapter(ArrayAdapter<String> listAdapter) {
     //   this.adapter = listAdapter;
    //}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                try {
                    Match m = (Match) data.getParcelableExtra("match");
                    matches.add(m);

                    if (event.getEventType().contains("Fußball")){
                        matchList.add(m.getTeam1() + " vs. " + m.getTeam2() + ", Ergebnis: " + m.getRes1() + " : " + m.getRes2());
                    }
                    else {
                        matchList.add("Fahrer: " + m.getTeam1() + ", Startnummer: " + m.getRes1() + " Fahrzeit: " +  " : " + m.getTeam2());
                    }

                    JSONObject object = new JSONObject();
                    try {
                        object.put("team1", m.getTeam1());
                        object.put("team2", m.getTeam2());
                        object.put("result1", m.getRes1());
                        object.put("result2", m.getRes2());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String message = object.toString();
                    PostJson post = new PostJson();
                    String stringUrl =  myIP + "/events/" + event.getEvendId() + "/matches" ;
                    try {
                        String result;
                        result = post.execute(stringUrl, message).get();

                    } catch (Exception e) {
                        e.printStackTrace();
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                        printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
                    }



                    arrayAdapter.notifyDataSetChanged();
                } catch (Exception e ) {
                    e.printStackTrace();
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                    printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
