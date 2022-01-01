package com.example.animetrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AnimeWebView extends AppCompatActivity {

    String url;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_web_view);

        initComponents();

        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT < 18) {
            webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        }

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

    private void initComponents() {

        Bundle extras = getIntent().getExtras();
        url = extras.getString("url");
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{ super.onBackPressed(); }
    }
}