/* Copyright © 2015 Paul Koepke paul.koepke@gmail.com
Version 0.0.1:
    Minimum viable product. Opens MeFi, JavaScript is enabled, allows login to persist after closing the app, back button works, opens MeFi links in the app and opens the browser to open all other links.
    Using https://developer.chrome.com/multidevice/webview/gettingstarted as a guide.
Version 0.0.2:
    Removed padding from main RelativeLayout so the WebView expands to fill the entire screen.
    Added app icons.
*/

package com.paulkoepke.metafilter_reader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity {

    WebView mainWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Not using floating action bar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        mainWebView = (WebView) findViewById(R.id.mainWebView);

        // Enable Javascript
        // WebSettings webSettings = mainWebView.getSettings();
        // webSettings.setJavaScriptEnabled(true);
        mainWebView.getSettings().setJavaScriptEnabled(true);

        // Load main MetaFilter page
        mainWebView.loadUrl("http://www.metafilter.com");

        // Stop local links and redirects from opening in browser instead of WebView
        mainWebView.setWebViewClient(new MyAppWebViewClient());
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

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if(mainWebView.canGoBack()) {
            mainWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
