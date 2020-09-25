package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ReVerification extends AppCompatActivity {
    private EditText emailTV, passwordTV;
    private Button loginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_re_verification);

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
        progressBar.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.INVISIBLE);
        String email, password;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();

        if (TextUtils.isEmpty(email)) {
            loginBtn.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Please Enter Email!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            loginBtn.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Please Enter Password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Vibrator v11 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            v11.vibrate(23);
                            Intent intent = new Intent(ReVerification.this, Dashboard.class);
                            startActivity(intent);
                            loginBtn.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            finish();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed check credentials", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            loginBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);

        loginBtn = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
    }
}
