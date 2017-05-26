package com.example.gruppe3.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.gruppe3.myapplication.eventclasses.Match;
import com.example.gruppe3.myapplication.eventclasses.Matches;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    private List<String> liste;

    // Create a List from String Array elements
    private List<String> matchList;
    private Matches matches = new Matches();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details);
            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
            //setSupportActionBar(toolbar);

            // Get reference of widgets from XML layout
            final ListView lv = (ListView) findViewById(R.id.lv_matches);

            // Initializing a new String Array
            String[] matches = new String[]{
                    "Match 1",
                    "Match 2",
            };

            // Create a List from String Array elements
            matchList = new ArrayList<String>(Arrays.asList(matches));

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
                        Intent i = new Intent(DetailActivity.this, AddMatchActivity.class);
                        DetailActivity.this.startActivityForResult(i, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
            fabSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("team1", "test");
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });



            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
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
                    matchList.add(m.getTeam1() + " vs. " + m.getTeam2() + ", Ergebnis: " + m.getRes1() + " : " + m.getRes2());

                    arrayAdapter.notifyDataSetChanged();
                } catch (Exception e ) {
                    e.printStackTrace();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
