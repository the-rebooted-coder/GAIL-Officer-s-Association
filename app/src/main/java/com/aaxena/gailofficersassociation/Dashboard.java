package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.aaxena.gailofficersassociation.Email.EMAIL;
import static com.aaxena.gailofficersassociation.Getter.SHARED_PREFS;
import static com.aaxena.gailofficersassociation.Getter.TEXT;

public class Dashboard extends AppCompatActivity {
    private String hello;
    TextView namehello;
    Button LogOUT ;
    private String emaillo;
    TextView fader;
    private TextView afterfade;
    TextView emailhello;
    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
    protected AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        emailhello = findViewById(R.id.emailhello);
        namehello = findViewById(R.id.namehello);
        loadData();

        emailhello.setText("Logged in as: "+emaillo);

        emailhello.startAnimation(fadeIn);
        emailhello.startAnimation(fadeOut);

        fader = findViewById(R.id.textView6);
        fader.startAnimation(fadeIn);
        fader.startAnimation(fadeOut);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(600);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(3200+fadeIn.getStartOffset());


        afterfade = findViewById(R.id.afterfades);
        int splash_screen_time_out = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                afterfade.startAnimation(fadeIn);
                afterfade.setText("Dashboard");
                namehello.startAnimation(fadeIn);
                namehello.setText("Hello "+hello+" !");
            }
        }, splash_screen_time_out);


        Button blog = findViewById(R.id.blog);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.firebase.codelab.friendlychat");
                if (intent != null) {
                    // We found the activity now start the activity
                    Vibrator v11 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v11.vibrate(25);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    // Dialog Box
                    new AlertDialog.Builder(Dashboard.this)
                            .setTitle("Companion App Not Installed!")
                            .setMessage(R.string.sensitive)
                            .setPositiveButton("Get GAIL Chat", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(35);
                                    Uri uri = Uri.parse("https://drive.google.com/open?id=12DkyGeDEn3W5wUkLhN04kulTY0FIXN1C");
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("Nope, I'm Fine", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(25);
                                }
                            })
                            .create().show();
                }
            }
        });

        Button services = findViewById(R.id.services);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Toast.makeText(Dashboard.this, "Navigating to Activities", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Dashboard.this,Services.class);
                startActivity(i);
            }
        });


        Button main_services = findViewById(R.id.services_main);
        main_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Toast.makeText(Dashboard.this, "Navigating to Services", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Dashboard.this,MainServices.class);
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


        LogOUT = (Button)findViewById(R.id.button1);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Toast.makeText(Dashboard.this, "Opening Preferences", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Dashboard.this,Settings.class);
                startActivity(i);

            }
        });
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        hello = sharedPreferences.getString(TEXT,"");
        emaillo = sharedPreferences.getString(EMAIL,"");
    }
}