package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private Button button_logout;
    private CardView cardViewbattery, cardViewprofile, cardViewpetrol, cardViewtowtruck, cardViewtyre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        cardViewbattery = findViewById(R.id.battery);
        setCardViewbattery();
        Button();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void setCardViewbattery() {

        cardViewpetrol = findViewById(R.id.petrol);
        cardViewpetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PetrolActivity.class));
            }
        });

        cardViewprofile = findViewById(R.id.profile);
        cardViewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DataRetrieve.class));
            }
        });
        cardViewbattery = findViewById(R.id.battery);
        cardViewbattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BatteryActivity.class));

            }
        });

    }

    private void Button() {
        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth != null)
                    firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}

