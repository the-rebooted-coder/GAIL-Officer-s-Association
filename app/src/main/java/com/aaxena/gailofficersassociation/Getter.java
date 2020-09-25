package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Getter extends AppCompatActivity {
    LottieAnimationView donee;
    int timeout = 1950;
    EditText name;
    int vib=500;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_getter);
        donee = findViewById(R.id.animatored3);
        final Button begin = findViewById(R.id.begin);
        final boolean isAnimated=false;
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v9 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v9.vibrate(24);
                if (!isAnimated) {
                    begin.setVisibility(View.INVISIBLE);
                    donee.playAnimation();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Vibrator v9 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v9.vibrate(27);
                                }
                            }, vib);
                            saveData();
                        Intent i = new Intent(Getter.this, Email.class);
                        startActivity(i);
                        finish();
                        }
                    }, timeout);
                }
            }
        });

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        name = findViewById(R.id.name);
        editor.putString(TEXT,name.getText().toString());
        editor.commit();
    }
}
