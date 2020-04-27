package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainServices extends AppCompatActivity {
    private TextView infol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_services);

        //Spinner
        final Spinner spinAbout;
        spinAbout= (Spinner) findViewById(R.id.spinAbout);//fetch the spinner from layout file
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
                        infol.setText("Executive Benefits to Be Listed Here!");
                        break;
                    case 1:
                        Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(33);
                        infol = findViewById(R.id.infos);
                        infol.setText("Important Contacts Here!");
                        break;
                    case 2:
                        Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v3.vibrate(33);
                        infol = findViewById(R.id.infos);
                        infol.setText("Live Surveys to Be Hosted Here!");
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
}