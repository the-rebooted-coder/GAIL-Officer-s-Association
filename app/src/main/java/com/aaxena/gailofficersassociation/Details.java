package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v6.vibrate(20);
                Intent i=new Intent(Details.this,Getter.class);
                startActivity(i);
                finish();
            }
        });

        Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v6.vibrate(21);
    }
}
