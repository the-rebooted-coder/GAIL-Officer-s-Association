package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

public class MainServices extends AppCompatActivity {
    private TextView infol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_main_services);


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
                .getStringArray(R.array.services_array));//setting the country_array to spinner

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAbout.setAdapter(adapter);
        //if you want to set any action you can do in this listener
        spinAbout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        infol = findViewById(R.id.infos);
                        infol.setText("Important Contacts Coming Here Soon!");
                        break;
                    case 1:
                        Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v8.vibrate(25);
                        Intent i=new Intent(MainServices.this,ExecBenefits.class);
                        startActivity(i);
                        break;
                    default:
                        Toast.makeText(parent.getContext(), "Hello:)", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(MainServices.this, Dashboard.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}