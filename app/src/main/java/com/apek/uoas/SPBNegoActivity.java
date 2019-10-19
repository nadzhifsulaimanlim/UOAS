package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SPBNegoActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button button_logout, button_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spbnego);
        Button();
    }

    private void Button() {

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth != null)
                    firebaseAuth.signOut();
                startActivity(new Intent(SPBNegoActivity.this, MainActivity.class));
                finish();
            }
        });
        button_done = findViewById(R.id.button_done);
        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Thanks for making a negotiation, we will response as soon as possible", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SPBNegoActivity.this, HomeActivity.class));
                finish();

            }
        });

    }
}
