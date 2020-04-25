package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.aaxena.gailofficersassociation.Getter.SHARED_PREFS;
import static com.aaxena.gailofficersassociation.Getter.TEXT;

public class Dashboard extends AppCompatActivity {
    private String hello;
    TextView namehello;
    Button LogOUT ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        namehello = findViewById(R.id.namehello);
        loadData();
        namehello.setText("Hello "+hello);


        Button services = findViewById(R.id.services);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Toast.makeText(Dashboard.this, "Navigating to Services", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Dashboard.this,Services.class);
                startActivity(i);
            }
        });


        Button grievances = findViewById(R.id.grievances);
        grievances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Toast.makeText(Dashboard.this, "Navigating to Grievances", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Dashboard.this,Grievances.class);
                startActivity(i);
            }
        });

        Button activities = findViewById(R.id.activities);
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Toast.makeText(Dashboard.this, "Navigating to News", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Dashboard.this,Activities.class);
                startActivity(i);
            }
        });

        Button about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Toast.makeText(Dashboard.this, "Navigating to About Us", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Dashboard.this,Landing.class);
                startActivity(i);
            }
        });

        Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v6.vibrate(20);

        //Android Background Animation
        ConstraintLayout container = (ConstraintLayout) findViewById(R.id.myflow);
        AnimationDrawable anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);
        anim.start();

        LogOUT = (Button)findViewById(R.id.button1);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(Dashboard.this,"Logged Out Successfully", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        hello = sharedPreferences.getString(TEXT,"");
    }
}