package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //Firing the Splash Screen
        fireSplashScreen();

    }

    private void fireSplashScreen() {
        int splash_screen_time_out = 1000;

        if (isFirstTime()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    vibrate();
                    Intent i=new Intent(SplashScreen.this,Details.class);
                    startActivity(i);
                    finish();
                }
            }, splash_screen_time_out);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    vibrate();
                    Intent i=new Intent(SplashScreen.this,Checker.class);
                    startActivity(i);
                    finish();
                }
            }, splash_screen_time_out);
        }
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(30);
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
