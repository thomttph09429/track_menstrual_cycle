package com.poly.mycalendar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.poly.mycalendar.R;
import com.poly.mycalendar.listener.ProgressBarDialog;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvSkip;
    private Button btnLogin;
    private EditText edtPass, edtEmail;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        initActions();
        setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
//        if (auth.getCurrentUser() != null ) {
//            startActivity(new Intent(this, HomeActivity.class));
//            finish();
//
//        }
    }

    private void initViews() {
        tvSkip = findViewById(R.id.tv_skip);
        btnLogin = findViewById(R.id.btn_login);
        edtPass = findViewById(R.id.edt_pass);
        edtEmail = findViewById(R.id.edt_email);

    }

    private void initActions() {
        tvSkip.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                startActivity(new Intent(this, HomeActivity.class));
                finish();

                break;
            case R.id.btn_login:
//                register();
                break;
            default:
                break;
        }

    }

    private void dimissProgress() {
        ProgressBarDialog.getInstance(this).closeDialog();
    }

    private void showProgress() {
        ProgressBarDialog.getInstance(this).showDialog("Please wait..", this);
    }

    private void register() {
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Password must have 6 characters!", Toast.LENGTH_SHORT).show();
        } else {
            registerEmailPass(email, pass);
        }
    }

    public void registerEmailPass(final String email, String password) {

        showProgress();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dimissProgress();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            dimissProgress();
                            Toast.makeText(LoginActivity.this, "You can't login with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}