package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        Button chngname = findViewById(R.id.chngname);
        chngname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v6.vibrate(25);
                Intent i=new Intent(Settings.this,Getter.class);
                startActivity(i);
            }
        });

        Button chngemail = findViewById(R.id.chngemail);
        chngemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v6.vibrate(25);
                Intent i=new Intent(Settings.this,Email.class);
                startActivity(i);
            }
        });

        Button logg = findViewById(R.id.logg);
        logg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v6.vibrate(35);
                Toast.makeText(Settings.this,"Sign Out Successful",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Settings.this,Email.class);
                startActivity(i);
            }
        });



        Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v6.vibrate(19);

        //Android Background Animation
        ConstraintLayout container = (ConstraintLayout) findViewById(R.id.set);
        AnimationDrawable anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);
        anim.start();
    }
}
