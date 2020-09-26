package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Landing extends AppCompatActivity {
    private TextView infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_landing);


        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Spinner
        final Spinner spinAbout;
        spinAbout= findViewById(R.id.spinAbout);//fetch the spinner from layout file
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
                    case 1:
                        Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(33);
                        Toast.makeText(parent.getContext(), "Loading Executive Bodies", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Landing.this, WebPage.class);
                        startActivity(i);
                        finish();
                        break;
                    case 2:
                        Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v3.vibrate(33);
                        Toast.makeText(parent.getContext(), "Loading Committees", Toast.LENGTH_SHORT).show();
                        Intent i4=new Intent(Landing.this, WebPage3.class);
                        startActivity(i4);
                        finish();
                        break;
                    case 3:
                        Vibrator v4 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v4.vibrate(33);
                        Toast.makeText(parent.getContext(), "Loading Sports Coordinators", Toast.LENGTH_SHORT).show();
                        Intent i2=new Intent(Landing.this,WebPage2.class);
                        startActivity(i2);
                        finish();
                        break;
                    case 4:
                        Vibrator v5 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v5.vibrate(33);
                        Intent i3=new Intent(Landing.this,Preamble.class);
                        startActivity(i3);
                        finish();
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent go_back = new Intent(Landing.this, Dashboard.class);
        startActivity(go_back);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
