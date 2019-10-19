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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutname, textInputLayoutcarplatenumber, textInputLayoutcarmodel, textInputLayoutyear, textInputLayoutemail, textInputLayoutpassword;
    private TextInputEditText textInputEditTextname, textInputEditTextcarplatenumber, textInputEditTextcarmodel, textInputEditTextyear, textInputEditTextemail, textInputEditTextpassword;
    private Button button_done;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        id();
        button();

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

    private void id() {
        textInputLayoutname = findViewById(R.id.layout_name);
        textInputLayoutcarplatenumber = findViewById(R.id.layout_carplatenumber);
        textInputLayoutcarmodel = findViewById(R.id.layout_carmodel);
        textInputLayoutyear = findViewById(R.id.layout_year);
        textInputLayoutpassword = findViewById(R.id.layout_password);
        textInputLayoutemail = findViewById(R.id.layout_email);
        textInputEditTextname = findViewById(R.id.edit_text_name);
        textInputEditTextcarplatenumber = findViewById(R.id.edit_text_carplatenumber);
        textInputEditTextcarmodel = findViewById(R.id.edit_text_carmodel);
        textInputEditTextyear = findViewById(R.id.edit_text_year);
        textInputEditTextemail = findViewById(R.id.edit_text_email);
        textInputEditTextpassword = findViewById(R.id.edit_text_password);
        button_done = findViewById(R.id.button_done);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("User");
    }

    private void button() {
        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(textInputEditTextname.getText().toString())) {
                    textInputLayoutname.setError("Please Fill Up Your Name");
                    textInputLayoutcarmodel.setError(null);
                    textInputLayoutcarplatenumber.setError(null);
                    textInputLayoutyear.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputEditTextname.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextcarmodel.getText().toString())) {
                    textInputLayoutcarmodel.setError("Please Fill Up Your Car Model");
                    textInputLayoutname.setError(null);
                    textInputLayoutcarplatenumber.setError(null);
                    textInputLayoutyear.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputEditTextcarmodel.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextyear.getText().toString())) {
                    textInputLayoutyear.setError("Please Fill Up Your Car Plate Number");
                    textInputLayoutcarmodel.setError(null);
                    textInputLayoutcarplatenumber.setError(null);
                    textInputLayoutname.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputEditTextyear.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextcarplatenumber.getText().toString())) {
                    textInputLayoutcarplatenumber.setError("Please Fill Up Your Car Plate Number");
                    textInputLayoutcarmodel.setError(null);
                    textInputLayoutname.setError(null);
                    textInputLayoutyear.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputEditTextcarplatenumber.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextemail.getText().toString())) {
                    textInputLayoutcarplatenumber.setError("Please Fill Up Your Car Plate Number");
                    textInputLayoutcarmodel.setError(null);
                    textInputLayoutname.setError(null);
                    textInputLayoutyear.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputEditTextcarplatenumber.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextpassword.getText().toString())) {
                    textInputLayoutcarplatenumber.setError("Please Fill Up Your Car Plate Number");
                    textInputLayoutcarmodel.setError(null);
                    textInputLayoutname.setError(null);
                    textInputLayoutyear.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputEditTextcarplatenumber.requestFocus();
                } else {
                    final String name = textInputEditTextname.getText().toString();
                    final String email = textInputEditTextemail.getText().toString();
                    final String password = textInputEditTextpassword.getText().toString();
                    final String carmodel = textInputEditTextcarmodel.getText().toString();
                    final String carplatenumber = textInputEditTextcarplatenumber.getText().toString();
                    final String year = textInputEditTextyear.getText().toString();
                    final String date = new Date().toString();
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                if (firebaseUser != null) {
                                    final String uid = firebaseUser.getUid();
                                    User user = new User(uid, name, email, carmodel, carplatenumber, year, date);
                                    databaseReference.child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Sign Up Successfully  ", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }

            }
        });
    }
}