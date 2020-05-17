package com.example.intern_task_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText editTextemail,editTextpassword;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            finish();
        }

        editTextemail = findViewById(R.id.editTextemail);
        editTextpassword = findViewById(R.id.editTextpassword);
        progressBar =findViewById(R.id.progressbarlogin);

        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.textViewSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }


    private void userLogin()
    {
        String email = editTextemail.getText().toString();
        String password = editTextpassword.getText().toString();

        if(email.isEmpty())
        {
            editTextemail.setError("Email-Id is required");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextemail.setError("Please enter a valid email address");
            editTextemail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editTextpassword.setError("Password is required");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editTextpassword.setError("Password must have minimum 6 characters");
            editTextpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    startActivity(new  Intent(MainActivity.this,WelcomeActivity.class));

                    Toast.makeText(MainActivity.this, "User Logged In", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }




}
