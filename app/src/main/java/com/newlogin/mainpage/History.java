package com.newlogin.mainpage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import com.newlogin.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class History extends Activity {

    TextView name;
    TextView bloodgrp;
    TextView date;
    TextView address;
    TextView contactno;
    TextView tv1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        StrictMode.enableDefaults();
        SharedPreferences sharedPreferences = getSharedPreferences(com.newlogin.Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(com.newlogin.Config.EMAIL_SHARED_PREF,"Not Available");
        tv1 = (TextView) findViewById(R.id.tv1);
        //Showing the current logged in email to textview
        tv1.setText("Current User: " + email);//STRICT MODE ENABLED

        name = (TextView) findViewById(R.id.name);
        bloodgrp = (TextView) findViewById(R.id.bloodgrp);
        date = (TextView) findViewById(R.id.date);
        address = (TextView) findViewById(R.id.address);
        contactno = (TextView) findViewById(R.id.contactno);

        getData();
    }
    public void getData() {
        String result = "";
        InputStream isr = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.1.102/history/json.php"); //YOUR PHP SCRIPT ADDRESS
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
            name.setText("Couldnt connect to database");
        }
        //convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        //parse json data
        try {
            String n = "";
            String a = "";
            String j = "";
            String k = "";
            String l = "";
            JSONArray jArray = new JSONArray(result);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);
                n = n + "Name : " + json.getString("name") + "\n";
               a = a + "Blood Group : " + json.getString("bloodgrp") + "\n";
                j = j + "Date: " + json.getString("date") + "\n";
                k = k + "Address: " + json.getString("address") + "\n";
               l = l + "Contactno: " + json.getString("contactno") + "\n";
            }

            name.setText(n);
           bloodgrp.setText(a);
            date.setText(j);
            address.setText(k);
            contactno.setText(l);

        } catch (Exception e) {
            Log.e("log_tag", "Error Parsing Data " + e.toString());
        }

    }        }