package com.example.practicewebview3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {

    private final String USERNAME = "admin";
    private final String PASSWORD = "12345";
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView mWebView = (WebView)findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSupportZoom(false);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(USERNAME,PASSWORD);
            }
        });
        mWebView.loadUrl("http://10.26.142.123/cgi-bin/camera?resolution=640&quality=5&Language=1%20height=640%20width=480");

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mWebView.reload();
                            }
                        });
                    } catch (InterruptedException e) {
                    }
                }
            }
        });

        th.start();

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("http://10.26.142.123/cgi-bin/directctrl?zoom=1&Language=1");
            }
        });

        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("http://10.26.142.123/cgi-bin/directctrl?zoom=-1&Language=1");
            }
        });

    }
}