package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class GWeb extends AppCompatActivity {

    private WebView gbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_web);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(25);

        //WebView Implementation
        gbm = findViewById(R.id.gbm);
        gbm.setWebViewClient(new WebViewClient());
        gbm.getSettings().setJavaScriptEnabled(true);
        gbm.loadUrl("https://drive.google.com/open?id=1iXpAt_KUAf9JIsUDQymPNQ7vvLA5oSZg");
    }
}
