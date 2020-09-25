package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IndianNewsLanding extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_indian_news_landing);

        Button ndtv = findViewById(R.id.ndtv);
        ndtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(26);
                Toast.makeText(IndianNewsLanding.this, "Navigating to khabar.ndtv.in", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(IndianNewsLanding.this,NDTV.class);
                startActivity(i);
            }
        });
        Button bbc = findViewById(R.id.bbc);
        bbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(26);
                Toast.makeText(IndianNewsLanding.this, "Navigating to bbc/news", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(IndianNewsLanding.this,BBC.class);
                startActivity(i);
            }
        });

        Button google_news = findViewById(R.id.google);
        google_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(26);
                Toast.makeText(IndianNewsLanding.this, "Navigating to news.google.com", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(IndianNewsLanding.this,GoogleNews.class);
                startActivity(i);
            }
        });

        Button aajtak = findViewById(R.id.aajtak);
        aajtak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(26);
                Toast.makeText(IndianNewsLanding.this, "Navigating to aajtak.indiatoday", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(IndianNewsLanding.this,AajTak.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent go_back = new Intent(IndianNewsLanding.this, Dashboard.class);
        startActivity(go_back);
    }
}
