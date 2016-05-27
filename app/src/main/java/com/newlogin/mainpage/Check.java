package com.newlogin.mainpage;

/**
 * Created by tarun on 5/17/2016.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.newlogin.R;


public class Check extends Activity {

        private WebView wvAboutUs;

        @SuppressLint("Recycle")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.check);

            wvAboutUs = (WebView) findViewById(R.id.wvAboutUs);

            // uri loding in webview
            wvAboutUs.loadUrl("file:///android_asset/Form.html");

            // url loding in webview
            //wvAboutUs.loadUrl("http://androidlift.info/");
            wvAboutUs.clearCache(true);
            wvAboutUs.clearHistory();
            wvAboutUs.getSettings().setJavaScriptEnabled(true);
            wvAboutUs.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        }
    }

