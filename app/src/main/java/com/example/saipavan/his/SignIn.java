package com.example.saipavan.his;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Administrator on 16-03-2017.
 */

public class SignIn extends AppCompatActivity {
    Button signout;
    private FirebaseAuth mAuth;
    TextView username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        mAuth = FirebaseAuth.getInstance(); // important Call
        signout = (Button)findViewById(R.id.signout);
        username = (TextView) findViewById(R.id.tvName);

        //Again check if the user is Already Logged in or Not
        if(mAuth.getCurrentUser() == null)
        {
            //User NOT logged In
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        //Fetch the Display name of current User
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            username.setText("Welcome, " + user.getDisplayName());
        }


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });




    }
}