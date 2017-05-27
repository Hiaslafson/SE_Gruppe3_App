package com.example.gruppe3.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gruppe3.myapplication.eventclasses.Match;

import static com.example.gruppe3.myapplication.eventclasses.InOut.printTextSnackbar;

/**
 * Created by Tom on 25.05.2017.
 */

public class AddDriverActivity extends AppCompatActivity {

    private Button btOk;
    private EditText team1;
    private EditText team2;
    private EditText res1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_driver);
            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
            //setSupportActionBar(toolbar);

            btOk = (Button) findViewById(R.id.buttonOk);
            team1 = (EditText) findViewById(R.id.editTextTeam1);
            team2 = (EditText) findViewById(R.id.editTextTeam2);
            res1 = (EditText) findViewById(R.id.editTextResult1);

            btOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent returnIntent = new Intent();
                    try {

                        returnIntent.putExtra("match", new Match("", team1.getText().toString(), team2.getText().toString(), Integer.parseInt(res1.getText().toString())));
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                        printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
                    }

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


}
