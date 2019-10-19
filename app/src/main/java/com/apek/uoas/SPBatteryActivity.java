package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class SPBatteryActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button button_logout, button_done;
    private TextInputLayout textInputLayoutbb, textInputLayoutbprice;
    private TextInputEditText textInputEditTextbb, textInputEditTextbprice;
    private String spUid;

    private void id() {
        textInputLayoutbb = findViewById(R.id.layout_bb);
        textInputLayoutbprice = findViewById(R.id.layout_bprice);
        textInputEditTextbb = findViewById(R.id.edit_text_bb);
        textInputEditTextbprice = findViewById(R.id.edit_text_bprice);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null)
            spUid = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spbattery);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        Button();
        id();

    }

    private void Button() {
        button_logout = findViewById(R.id.button_logout);
        button_done = findViewById(R.id.button_done);
        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(textInputEditTextbb.getText().toString())) {
                    textInputLayoutbb.setError("Please Fill Up Your BatteryBrand");
                    textInputLayoutbprice.setError(null);
                    textInputEditTextbb.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextbprice.getText().toString())) {
                    textInputLayoutbprice.setError("Please Fill Up Your Price");
                    textInputLayoutbb.setError(null);
                    textInputEditTextbprice.requestFocus();

                } else {
                    final String uid = databaseReference.push().getKey();
                    final String bb = textInputEditTextbb.getText().toString();
                    final Double bprice = Double.valueOf(textInputEditTextbprice.getText().toString());
                    final String date = new Date().toString();
                    Battery battery = new Battery(uid, bb, bprice, date);
                    if (uid != null)
                        databaseReference.child("Battery").child(spUid).child(uid).setValue(battery).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Added Battery Successfully  ", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SPBatteryActivity.this, SPHomeActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });


                }

            }
        });
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseUser != null) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(SPBatteryActivity.this, MainActivity.class));
                    finish();
                }

            }
        });
    }
}

