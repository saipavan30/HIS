package com.example.saipavan.his;

import android.content.Intent;

import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.onesignal.OneSignal;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText email,password,name;

    private Button signin, signup;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        mAuth = FirebaseAuth.getInstance(); // important Call



        signin = (Button)findViewById(R.id.signin);

        signup = (Button)findViewById(R.id.signup);

        email = (EditText)findViewById(R.id.etEmail);

        password = (EditText)findViewById(R.id.etPassword);

        name = (EditText)findViewById(R.id.etName);



        //Check if User is Already LoggedIn

        if(mAuth.getCurrentUser() != null)

        {

            //User NOT logged In

            finish();

            startActivity(new Intent(getApplicationContext(),SignIn.class));

        }



        signin.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String getemail = email.getText().toString().trim();

                String getepassword = password.getText().toString().trim();

                callsignin(getemail,getepassword);



            }

        });



        signup.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {



                String getemail = email.getText().toString().trim();

                String getepassword = password.getText().toString().trim();

                callsignup(getemail,getepassword);



            }

        });



    }



    //Create Account

    private void callsignup(String email,String password) {



        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d("TESTING", "Sign up Successful" + task.isSuccessful());



                        // If sign in fails, display a message to the user. If sign in succeeds

                        // the auth state listener will be notified and logic to handle the

                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {

                            Toast.makeText(LoginActivity.this, "Signed up Failed", Toast.LENGTH_SHORT).show();

                        }

                        else

                        {

                            userProfile();

                            Toast.makeText(LoginActivity.this, "Created Account", Toast.LENGTH_SHORT).show();

                            Log.d("TESTING", "Created Account");

                        }

                    }

                });

    }



    //Set UserDisplay Name

    private void userProfile()

    {

        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null)

        {

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()

                    .setDisplayName(name.getText().toString().trim())

                    //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))  // here you can set image link also.

                    .build();



            user.updateProfile(profileUpdates)

                    .addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override

                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                Log.d("TESTING", "User profile updated.");

                            }

                        }

                    });

        }

    }





    //Now start Sign In Process

    //SignIn Process

    private void callsignin(String email,String password) {



        mAuth.signInWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d("TESTING", "sign In Successful:" + task.isSuccessful());



                        // If sign in fails, display a message to the user. If sign in succeeds

                        // the auth state listener will be notified and logic to handle the

                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {

                            Log.w("TESTING", "signInWithEmail:failed", task.getException());

                            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                        }

                        else {

                            Intent i = new Intent(LoginActivity.this, SignIn.class);

                            finish();

                            startActivity(i);

                        }

                    }

                });



    }





}
