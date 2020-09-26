package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

import static com.aaxena.gailofficersassociation.Getter.SHARED_PREFS;
import static com.aaxena.gailofficersassociation.Getter.TEXT;

public class Dashboard extends AppCompatActivity {
    private String hello;
    TextView namehello;
    Button LogOUT ;
    private String emaillo;
    private Switch dynamic;
    TextView fader;
    private TextView afterfade;
    TextView emailhello;
    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
    protected AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_dashboard);
        if (isFirstTime()) {
            new AlertDialog.Builder(this)
                    .setTitle("Dynamic Background")
                    .setMessage(R.string.dd)
                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNeutralButton("Okay!", null)
                    .create().show();
        }
         //  Making Scroll View Off
            HorizontalScrollView scrollView = findViewById(R.id.scrollView1);
            HorizontalScrollView scrollView2 = findViewById(R.id.scrollView2);
            scrollView.setVerticalScrollBarEnabled(false);
            scrollView.setHorizontalScrollBarEnabled(false);
            scrollView2.setVerticalScrollBarEnabled(false);
            scrollView2.setHorizontalScrollBarEnabled(false);

        //Authentication
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            if (email != null) {
                emaillo = email;
            } else {
                Intent i = new Intent(Dashboard.this, Email.class);
                startActivity(i);
            }
        }
        emailhello = findViewById(R.id.emailhello);
        namehello = findViewById(R.id.namehello);
        loadData();
        emailhello.setText(emaillo);
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


        final Button blog = findViewById(R.id.blog);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getPackageManager().getLaunchIntentForPackage("com.wGOAChat_10893263");
                if (intent != null) {
                    // We found the activity now start the activity
                    Vibrator v11 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v11.vibrate(25);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    // Dialog Box
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    new AlertDialog.Builder(Dashboard.this)
                            .setTitle("Companion App Not Installed!")
                            .setMessage(R.string.sensitive)
                            .setPositiveButton("Get GOA Chat", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(35);
                                    Uri uri = Uri.parse("https://drive.google.com/open?id=1LLVPtBqIZ6x9YbTmnExV6mJItt2XmHWI");
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

        final Button services = findViewById(R.id.services);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Intent i=new Intent(Dashboard.this,Services.class);
                startActivity(i);
            }
        });

        final Button indian_news = findViewById(R.id.news_india);
        indian_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(26);
                Intent i=new Intent(Dashboard.this,IndianNewsLanding.class);
                startActivity(i);
                finish();
            }
        });

        final Button main_services = findViewById(R.id.services_main);
        main_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Intent i=new Intent(Dashboard.this,MainServices.class);
                startActivity(i);
            }
        });

        final Button grievances = findViewById(R.id.grievances);
        grievances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Intent i=new Intent(Dashboard.this,Grievances.class);
                startActivity(i);
                finish();
            }
        });

        final Button activities = findViewById(R.id.activities);
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Toast.makeText(Dashboard.this, "Fetching News", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Dashboard.this,Activities.class);
                startActivity(i);
            }
        });

        final Button about = findViewById(R.id.bbc);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Intent i=new Intent(Dashboard.this,Landing.class);
                startActivity(i);
            }
        });

        final Button survey = findViewById(R.id.survey);
        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Intent i=new Intent(Dashboard.this,Survey.class);
                startActivity(i);
            }
        });


        //Dynamic Switcher
        dynamic = findViewById(R.id.dynamic);
        dynamic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.aaxena.gailofficersassociation", MODE_PRIVATE).edit();
                    editor.putBoolean("isChecked", true);
                    editor.commit();
                    Calendar c = Calendar.getInstance();
                    int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

                    if (timeOfDay >= 0 && timeOfDay < 01) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.one);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    } else if (timeOfDay >= 01 && timeOfDay < 02) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.two);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    } else if (timeOfDay >= 02 && timeOfDay < 03) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.three);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    } else if (timeOfDay >= 03 && timeOfDay < 04) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.four);
                        setButtons();
                        constraintLayout.setBackgroundDrawable(drawable);
                    } else if (timeOfDay >= 04 && timeOfDay < 05) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.five);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 05 && timeOfDay < 06) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.six);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 06 && timeOfDay < 07) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.seven);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 07 && timeOfDay < 8) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.seven);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 8 && timeOfDay < 9) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.eight);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 9 && timeOfDay < 10) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.nine);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 10 && timeOfDay < 11) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.ten);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 11 && timeOfDay < 12) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.ten);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 12 && timeOfDay < 13) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.eleven);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 13 && timeOfDay < 14) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.twelve);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 14 && timeOfDay < 15) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.thirteen);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 15 && timeOfDay < 16) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.thirteen);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 16 && timeOfDay < 17) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.fourteen);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 17 && timeOfDay < 18) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.fifteen);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 18 && timeOfDay < 19) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.sixteen);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();

                    }
                    else if (timeOfDay >= 19 && timeOfDay < 20) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.four);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 20 && timeOfDay < 21) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.three);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 21 && timeOfDay < 22) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.two);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 22 && timeOfDay < 23) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.one);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    else if (timeOfDay >= 23 && timeOfDay < 24) {
                        ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                        Drawable drawable= getResources().getDrawable(R.drawable.one);
                        constraintLayout.setBackgroundDrawable(drawable);
                        setButtons();
                    }
                    dynamic.setBackgroundColor(Color.parseColor("#46FFFFFF"));
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.aaxena.gailofficersassociation", MODE_PRIVATE).edit();
                    editor.putBoolean("isChecked", false);
                    editor.commit();
                    Window w = getWindow();
                    w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                    ConstraintLayout constraintLayout= findViewById(R.id.myflow);
                    Drawable drawable= getResources().getDrawable(R.drawable.activity_dashboard);
                    constraintLayout.setBackgroundDrawable(drawable);
                    Drawable one = getResources().getDrawable(R.drawable.goa_news);
                    Drawable two = getResources().getDrawable(R.drawable.button_activities);
                    Drawable three = getResources().getDrawable(R.drawable.button_bbc);
                    Drawable four = getResources().getDrawable(R.drawable.button_grievances);
                    Drawable five = getResources().getDrawable(R.drawable.button_india);
                    Drawable six = getResources().getDrawable(R.drawable.button_nlog);
                    Drawable seven = getResources().getDrawable(R.drawable.button_services);
                    Drawable eight = getResources().getDrawable(R.drawable.button_sirvey);
                    afterfade.setTextColor(Color.parseColor("#0336ff"));
                    fader.setTextColor(Color.parseColor("#FFFFFF"));
                    emailhello.setTextColor(Color.parseColor("#FFFFFF"));
                    about.setBackground(three);
                    services.setBackground(two);
                    blog.setBackground(six);
                    activities.setBackground(one);
                    grievances.setBackground(four);
                    main_services.setBackground(seven);
                    indian_news.setBackground(five);
                    survey.setBackground(eight);
                    Drawable back= getResources().getDrawable(R.drawable.default_button);
                    dynamic.setBackgroundDrawable(back);

                }
            }
        });

        SharedPreferences sharedPrefs = getSharedPreferences("com.aaxena.gailofficersassociation", MODE_PRIVATE);
        dynamic.setChecked(sharedPrefs.getBoolean("isChecked", false));

        // Adding click listener to Log Out button.
        LogOUT = findViewById(R.id.button1);
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v8 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v8.vibrate(27);
                Intent i=new Intent(Dashboard.this,Settings.class);
                startActivity(i);
                finish();

            }
        });
    }

    private void setButtons() {
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        Button blog = findViewById(R.id.blog);
        Button services = findViewById(R.id.services);
        Button about = findViewById(R.id.bbc);
        Button activities = findViewById(R.id.activities);
        Button grievances = findViewById(R.id.grievances);
        Button main_services = findViewById(R.id.services_main);
        Button indian_news = findViewById(R.id.news_india);
        Button survey = findViewById(R.id.survey);
        Drawable dynamics = getResources().getDrawable(R.drawable.default_button_dynamic);
        about.setBackground(dynamics);
        afterfade.setTextColor(Color.parseColor("#FFFFFFFF"));
        fader.setTextColor(Color.parseColor("#FFFFFFFF"));
        emailhello.setTextColor(Color.parseColor("#FFFFFFFF"));
        services.setBackground(dynamics);
        blog.setBackground(dynamics);
        activities.setBackground(dynamics);
        grievances.setBackground(dynamics);
        main_services.setBackground(dynamics);
        indian_news.setBackground(dynamics);
        survey.setBackground(dynamics);
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        hello = sharedPreferences.getString(TEXT,"");
    }
    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
}