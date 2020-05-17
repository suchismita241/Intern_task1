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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextemail,editTextpassword;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

        editTextemail = findViewById(R.id.editTextemaillogin);
        editTextpassword = findViewById(R.id.editTextpasswordlogin);
        progressBar =findViewById(R.id.progressbarlogin);

        mAuth = FirebaseAuth.getInstance();

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
                    finish();
                    Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "User Logged In", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.textViewSignup:
                finish();
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            case R.id.buttonLogin:
                userLogin();
                break;

        }
    }
}
