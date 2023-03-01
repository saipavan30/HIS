package com.example.saipavan.his;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String pid = intent.getStringExtra("pid");

        //TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etUsername = (EditText) findViewById(R.id.etEmail);
        EditText etPid = (EditText) findViewById(R.id.etPid);

        // Display user details
        String message = " Welcome "+ name+"!";
       // tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etPid.setText(pid);
    }
}
