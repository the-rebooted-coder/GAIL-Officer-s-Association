package com.aaxena.gailofficersassociation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        int splash_screen_time_out = 2000;

        if (isFirstTime()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(35);
                    Intent i=new Intent(SplashScreen.this,Details.class);
                    startActivity(i);
                    finish();
                }
            }, splash_screen_time_out);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(35);
                    Intent i=new Intent(SplashScreen.this,Email.class);
                    startActivity(i);
                    finish();
                }
            }, splash_screen_time_out);
        }
    }
//First Time Checker
    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

}
