package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_settings);

        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Settings.this, Dashboard.class);
                startActivity(back);
                finish();
            }
        });

        Button chngname = findViewById(R.id.chngname);
        chngname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v6.vibrate(25);
                Intent i=new Intent(Settings.this,ChangeName.class);
                startActivity(i);
                finish();
            }
        });

        Button abt_dev = findViewById(R.id.abt);
        abt_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v6.vibrate(30);
                Toast.makeText(Settings.this, R.string.app_developer,Toast.LENGTH_LONG).show();
            }
        });

        Button logg = findViewById(R.id.logg);
        logg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v6.vibrate(35);
                Toast.makeText(Settings.this,"Log Out Successful",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Settings.this,Email.class);
                startActivity(i);
                finish();
            }
        });
        Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v6.vibrate(19);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent go_back = new Intent(Settings.this, Dashboard.class);
        startActivity(go_back);
        finish();
    }
}
