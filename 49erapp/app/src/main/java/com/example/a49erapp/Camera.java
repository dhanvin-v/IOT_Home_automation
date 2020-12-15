package com.example.a49erapp;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Camera extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

//        WebView camera = (WebView) findViewById(R.id.activity_camera);
//        setContentView(camera);
 //       camera.setWebViewClient(new WebViewClient());
//        camera.loadUrl("http://172.20.10.6:8000/");
//        camera.loadUrl("https://www.google.com");
        WebView myWebView = (WebView) findViewById(R.id.activity_camera);
        myWebView.loadUrl("http://172.20.10.6:8000/");

    }

}
