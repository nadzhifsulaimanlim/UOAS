package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BatteryActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private Button button_logout, button_newbattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        firebaseAuth = FirebaseAuth.getInstance();
        Button();
    }

    private void Button() {
        button_logout = findViewById(R.id.button_logout);
        button_newbattery = findViewById(R.id.button_newbattery);
        button_newbattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BatteryActivity.this, UBatteryActivity.class));
                finish();
            }
        });
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth != null)
                    firebaseAuth.signOut();
                startActivity(new Intent(BatteryActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
