package com.aaxena.gailofficersassociation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeName extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_change_name);


        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ChangeName.this, Settings.class);
                startActivity(back);
                finish();
            }
        });

        Button begin = findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v9 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v9.vibrate(27);
                saveData();
                Toast.makeText(ChangeName.this,"Restarting App Please Wait!",Toast.LENGTH_LONG).show();
                Intent mStartActivity = new Intent(ChangeName.this, Dashboard.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(ChangeName.this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, mPendingIntent);
                finishAffinity();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent go_back = new Intent(ChangeName.this, Settings.class);
        startActivity(go_back);
    }
}
