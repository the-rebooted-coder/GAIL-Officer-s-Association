package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Services extends AppCompatActivity {
    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
    protected AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_services);

        TextView activities = findViewById(R.id.act);
        activities.startAnimation(fadeIn);
        activities.startAnimation(fadeOut);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(600);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(3200+fadeIn.getStartOffset());

        //Spinner
        final Spinner spinAbout;
        spinAbout= findViewById(R.id.spinAbout);//fetch the spinner from layout file
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.activities_array));//setting the country_array to spinner

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAbout.setAdapter(adapter);
//if you want to set any action you can do in this listener
        spinAbout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(33);
                        Toast.makeText(parent.getContext(), "GBM General Body Meeting", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(33);
                        Toast.makeText(parent.getContext(), "Loading GBM Meetings", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Services.this, GWeb.class);
                        startActivity(i);
                        break;
                    default:
                        Toast.makeText(parent.getContext(), "Hello!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}