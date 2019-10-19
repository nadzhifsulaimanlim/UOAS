package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SPHomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Button button_logout, button_services, button_nego, button_manage, button_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sphome);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
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

    private void Button() {
        button_logout = findViewById(R.id.button_logout);
        button_nego = findViewById(R.id.button_nego);
        button_nego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SPHomeActivity.this, SPnegoActivity.class));
            }
        });
        button_history = findViewById(R.id.button_history);
        button_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SPHomeActivity.this, SPHistoryActivity.class));

            }
        });
        button_manage = findViewById(R.id.button_manage);
        button_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SPHomeActivity.this, SPManageActivity.class));

            }
        });
        button_services = findViewById(R.id.button_services);
        button_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SPHomeActivity.this, SPActivity.class));

            }
        });
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth != null)
                    firebaseAuth.signOut();
                startActivity(new Intent(SPHomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }


}
