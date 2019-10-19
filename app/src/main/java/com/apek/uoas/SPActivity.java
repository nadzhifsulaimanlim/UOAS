package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SPActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Button button_logout;
    private CardView battery, petrol, towtruck, tyre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        Button();
        CardView();
    }

    private void Button() {
        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseUser != null) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(SPActivity.this, MainActivity.class));
                    finish();
                }

            }
        });
    }

    private void CardView() {
        battery = findViewById(R.id.battery);
        petrol = findViewById(R.id.petrol);
        towtruck = findViewById(R.id.towtruck);
        tyre = findViewById(R.id.tyre);
        battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SPActivity.this, SPBatteryActivity.class));
                finish();
            }
        });
    }
}

