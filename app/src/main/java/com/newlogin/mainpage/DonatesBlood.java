package com.newlogin.mainpage;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.newlogin.R;

public class DonatesBlood extends Activity {
     public void success(View v){

         Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show();


     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donateblood);
    }
}
