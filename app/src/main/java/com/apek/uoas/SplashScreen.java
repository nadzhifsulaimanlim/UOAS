package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private boolean isuser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove the top of the info, that date, notifications stuff
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //Hide the appbar
        //We still need to put this one, because it is appbar not included for the full screen stuff
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        if (firebaseUser != null) {
            //Check at database if user is user or service svp
            Query query = databaseReference.child("User").orderByChild("email").equalTo(firebaseUser.getEmail());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    isuser = dataSnapshot.exists();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isuser) {
                        startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreen.this, SPHomeActivity.class));
                        finish();
                    }

                }
            }, 3000);

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();


                }
            }, 3000);
        }


    }

}
