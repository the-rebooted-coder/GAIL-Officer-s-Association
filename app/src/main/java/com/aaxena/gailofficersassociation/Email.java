package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Email extends AppCompatActivity {

    private EditText emailTV, passwordTV;
    private Button loginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_email);

        final TextView textView = findViewById(R.id.greetings_behalf);
        final int[] array = {R.string.text1, R.string.text2,R.string.text3,R.string.text4,R.string.text5,R.string.text6};
        textView.post(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                textView.setText(array[i]);
                i++;
                if (i ==5)
                    i = 0;
                textView.postDelayed(this, 5000);
            }
        });

        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v11 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v11.vibrate(25);
                Intent intent = new Intent(Email.this, ResetPassword.class);
                startActivity(intent);
            }
        });

        Button acc = findViewById(R.id.acc);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent i=new Intent(Email.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        initializeUI();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount() {
        loginBtn.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        String email, password;
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(28);
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();
      //  if (email.endsWith("@gail.co.in")) {
            if (TextUtils.isEmpty(email)) {
                loginBtn.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Please Enter Email!", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                loginBtn.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Please Enter Password!", Toast.LENGTH_LONG).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Vibrator v11 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                v11.vibrate(25);
                                checkIfEmailVerified();
                                loginBtn.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);

                            } else {
                                loginBtn.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "Login failed! Please Try Again", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
  //   }
  /*
     else {
           progressBar.setVisibility(View.INVISIBLE);
           loginBtn.setVisibility(View.VISIBLE);
           Toast.makeText(this, "Please Enter Valid GAIL Email", Toast.LENGTH_LONG).show();
       }

   */
 }

    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);

        loginBtn = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
    }
    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            finish();
            Toast.makeText(Email.this, "Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Email.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(Email.this, "Email Not Yet Verified!", Toast.LENGTH_SHORT).show();
            //restart this activity

        }
    }
}