package com.example.gruppe3.myapplication.ActivityController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gruppe3.myapplication.R;
import com.example.gruppe3.myapplication.eventclasses.Match;
import com.example.gruppe3.myapplication.eventclasses.SportsEvent;

import java.util.Calendar;
import java.util.Date;

import static com.example.gruppe3.myapplication.eventclasses.InOut.printTextSnackbar;

/**
 * Created by Tom on 25.05.2017.
 */

public class AddEventActivity extends Activity {

    private EditText editTextEventName;
    private EditText editTextInfo;
    private Spinner spinnerType;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);

        // Get reference of widgets from XML layout
        editTextEventName = (EditText) findViewById(R.id.editTextEventName);
        editTextInfo = (EditText) findViewById(R.id.editTextInfo);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        Button btOk = (Button) findViewById(R.id.buttonOk);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();

                try {
                    Date date = getDateFromDatePicket(datePicker);
                    SportsEvent se = new SportsEvent(editTextEventName.getText().toString(),
                            spinnerType.getSelectedItem().toString(), editTextInfo.getText().toString(), date);
                    returnIntent.putExtra("event", se);
                    setResult(Activity.RESULT_OK, returnIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                    printTextSnackbar(coordinatorLayout, "Error: " + e.getMessage());
                }
                finish();

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

    /**
     *
     * @param datePicker
     * @return a java.util.Date
     */
    public static java.util.Date getDateFromDatePicket(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }


}
