package com.example.gruppe3.myapplication.Requests;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.example.gruppe3.myapplication.eventclasses.SportsEvent;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MatthiasW on 28.05.2017.
 */

public class PostJson extends AsyncTask<String,Void, String> {

    public static final String REQUEST_METHOD = "POST";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;


    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }

    @Override
    protected String doInBackground(String... params) {
        String stringUrl = params[0];
       // String s = params[1];
       String message = params[1];
        boolean result;
        String inputLine;



        HttpClient hc = new DefaultHttpClient();

        HttpPost p = new HttpPost(stringUrl);
        try {



            p.setEntity(new StringEntity(message, "UTF8"));
            p.setHeader("Content-type", "application/json");
            HttpResponse resp = hc.execute(p);
            if (resp != null) {
                if (resp.getStatusLine().getStatusCode() == 204)
                    result = true;
            }

            Log.d("Status line", "" + resp.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();

        }

        return "Test";

    }

}
