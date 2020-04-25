package com.aaxena.gailofficersassociation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Landing extends AppCompatActivity {
    private TextView infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);


        //Spinner
        final Spinner spinAbout;
        spinAbout= (Spinner) findViewById(R.id.spinAbout);//fetch the spinner from layout file
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.about_us_array));//setting the country_array to spinner
                
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
                        infos = findViewById(R.id.infos);
                        infos.setText(getString(R.string.about_us_txt));
                        break;
                    case 1:
                        Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(33);
                        Toast.makeText(parent.getContext(), "Loading Executive Bodies", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Landing.this, WebPage.class);
                        startActivity(i);
                        break;
                    case 2:
                        Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v3.vibrate(33);
                        Toast.makeText(parent.getContext(), "Loading Committees", Toast.LENGTH_LONG).show();
                        Intent i4=new Intent(Landing.this, WebPage3.class);
                        startActivity(i4);
                        break;
                    case 3:
                        Vibrator v4 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v4.vibrate(33);
                        Toast.makeText(parent.getContext(), "Loading Sports Coordinators", Toast.LENGTH_LONG).show();
                        Intent i2=new Intent(Landing.this,WebPage2.class);
                        startActivity(i2);
                        break;
                    case 4:
                        Vibrator v5 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v5.vibrate(33);
                        Intent i3=new Intent(Landing.this,Preamble.class);
                        startActivity(i3);
                        break;
                    default:
                        infos = findViewById(R.id.infos);
                        infos.setText(getString(R.string.about_us_txt));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
