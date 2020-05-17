package com.example.intern_task_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity
{
    FirebaseAuth mAuth;
    Button msg,phn,brow,fb,insta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth = FirebaseAuth.getInstance();

        msg =  findViewById(R.id.msg);
        phn = findViewById(R.id.phone);
        brow = findViewById(R.id.browser);
        fb = findViewById(R.id.fb);
        insta = findViewById(R.id.insta);

        phn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:7008683249"));
                startActivity(intent);

            }
        });


        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"));
                startActivity(intent);

            }
        });


        brow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.spectrumcet.com"));
                startActivity(intent);

            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/spCETrum/"));
                startActivity(intent);

            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/spectrum.cetb/?hl=en"));
                startActivity(intent);


            }
        });
    }



}
