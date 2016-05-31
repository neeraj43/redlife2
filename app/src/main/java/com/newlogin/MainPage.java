package com.newlogin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.newlogin.mainpage.AppUse;
import com.newlogin.mainpage.BloodBanks;
import com.newlogin.mainpage.Check;
import com.newlogin.mainpage.DonatesBlood;
import com.newlogin.mainpage.History;
import com.newlogin.mainpage.Knowledge;
import com.newlogin.mainpage.NeedBlood;
import com.newlogin.mainpage.location.GooglePlacesActivity;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class MainPage  extends Activity {

    public void DonatesBlood(View v){

        Intent i=new Intent(this,DonatesBlood.class);
        this.startActivity(i);


    }
    public void NeedBlood(View v){

        Intent i=new Intent(this,NeedBlood.class);
        this.startActivity(i);


    }
    public void BloodBanks(View v){

        Intent i=new Intent(this,BloodBanks.class);
        this.startActivity(i);


    }

    public void History(View v) throws UnsupportedEncodingException {
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");

        HttpClient client=new DefaultHttpClient();
        HttpPost getMethod=new HttpPost("http://192.168.1.102/history/json.php");
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("name","n@m.c"));

        getMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
        try {
            client.execute(getMethod);


        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent i=new Intent(this,History.class);
        this.startActivity(i);



    }
    public void AppUse(View v){

        Intent i=new Intent(this,AppUse.class);
        this.startActivity(i);


    }
    public void Knowledge(View v){

        Intent i=new Intent(this,Knowledge.class);
        this.startActivity(i);


    }
    public void Search(View v){

        Intent i=new Intent(this, GooglePlacesActivity.class);
        this.startActivity(i);


    }
    public void Check(View v){

        Intent i=new Intent(this,Check.class);
        this.startActivity(i);


    }
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        textView = (TextView) findViewById(R.id.textView);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");

        //Showing the current logged in email to textview
        textView.setText("Current User: " + email);
    }

    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MainPage.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
