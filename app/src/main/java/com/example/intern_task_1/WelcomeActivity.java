package com.example.intern_task_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener
{
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.msg).setOnClickListener(this);
        findViewById(R.id.phone).setOnClickListener(this);
        findViewById(R.id.browser).setOnClickListener(this);
        findViewById(R.id.fb).setOnClickListener(this);
        findViewById(R.id.insta).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.msg:
                Intent intent = new Intent(Intent.ACTION_SEND);
                startActivity(intent);
                break;
            case R.id.phone:
                Intent intent1 = new Intent(Intent.ACTION_DIAL,Uri.parse("7008683249"));
                startActivity(intent1);
                break;
            case R.id.browser:
                Intent intent2 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.spectrumcet.com"));
                startActivity(intent2);
                break;
            case R.id.fb:
                Intent intent3 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/spCETrum/"));
                startActivity(intent3);
                break;
            case R.id.insta:
                Intent intent4 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/spectrum.cetb/?hl=en"));
                startActivity(intent4);
                break;
        }

    }
}
