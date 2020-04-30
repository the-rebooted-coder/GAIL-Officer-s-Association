package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {


    private EditText editTextMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        editTextMobile = findViewById(R.id.editTextMobile);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();
                Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v2.vibrate(27);
                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile number");
                    editTextMobile.requestFocus();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
                finish();
            }
        });
    }
}