package com.example.intern_task_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import javax.sql.StatementEvent;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextemail, editTextpassword;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextemail = findViewById(R.id.editTextemail);
        editTextpassword = findViewById(R.id.editTextpassword);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);
        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String email = editTextemail.getText().toString();
        String password = editTextpassword.getText().toString();

        if (email.isEmpty()) {
            editTextemail.setError("Email-Id is required");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Please enter a valid email address");
            editTextemail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextpassword.setError("Password is required");
            editTextpassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextpassword.setError("Password must have minimum 6 characters");
            editTextpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(SignUpActivity.this, "User Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        finish();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Toast.makeText(SignUpActivity.this, "User already registered with the Email ID", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}

