package com.aaxena.gailofficersassociation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.util.Base64Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.aaxena.gailofficersassociation.Getter.SHARED_PREFS;
import static com.aaxena.gailofficersassociation.Getter.TEXT;
import static com.aaxena.gailofficersassociation.RegisterActivity.CPF;

public class Grievances extends AppCompatActivity {
    private EditText mEditTextSubject;
    private TextView mEditTextTo;
    private EditText mEditTextMessage;
    private TextView mEditTextcpf;
    private TextView mEditTextFrom;
    private String name;
    private String cpf;
    private String emaillo;
    private EditText namee;
    private CheckBox feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_grievances);
        mEditTextTo = findViewById(R.id.edit_text_to);
        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        feedback = findViewById(R.id.feedbx);
        mEditTextSubject = findViewById(R.id.edit_text_subject);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        mEditTextcpf = findViewById(R.id.edit_text_cpf);
        mEditTextFrom = findViewById(R.id.edit_text_from);
        loadData();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            emaillo = email;
        }
        else {
            Intent i=new Intent(Grievances.this,Email.class);
            startActivity(i);
        }
        namee = findViewById(R.id.edit_text_name);
        namee.setText(name);
        mEditTextcpf.setText(cpf);
        mEditTextFrom.setText(emaillo);

        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEditTextcpf.getText())||TextUtils.isEmpty(mEditTextFrom.getText())){
                    mEditTextcpf.setError("CPF Number is Required");
                    mEditTextFrom.setError("GAIL Email Required");
                    Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v6.vibrate(32);
                }
                else {sendMail();
                Vibrator v6 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v6.vibrate(29);
                }
            }
        });

    }

    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");
        if (feedback.isChecked()){
            String subject = "Feedback from "+namee.getText().toString()+" | "+mEditTextSubject.getText().toString();
            String message = "CPF Number: "+ mEditTextcpf.getText().toString()+" "+ "GAIL Email ID:"+mEditTextFrom.getText().toString()+"\n\n"+mEditTextMessage.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose Your Email App"));
        }
        else {
            String subject = "Grievance from "+namee.getText().toString()+" | "+mEditTextSubject.getText().toString();
            String message = "CPF Number:"+ mEditTextcpf.getText().toString()+"\n"+ "GAIL Email ID:"+mEditTextFrom.getText().toString()+"\n\n"+mEditTextMessage.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose Your Email App"));
        }
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        name = sharedPreferences.getString(TEXT,"");
        cpf = sharedPreferences.getString(CPF,"");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent go_back = new Intent(Grievances.this, Dashboard.class);
        startActivity(go_back);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
