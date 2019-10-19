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

public class ServicesProvider extends AppCompatActivity {

    private TextInputLayout textInputLayoutname, textInputLayoutcompanyname, textInputLayoutcontact, textInputLayoutaddress, textInputLayoutemail, textInputLayoutpassword, textInputLayoutcompanydescription;
    private TextInputEditText textInputEditTextname, textInputEditTextcompanyname, textInputEditTextcontact, textInputEditTextaddress, textInputEditTextemail, textInputEditTextpassword, textInputEditTextcompanydescription;
    private Button button_done;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_provider);
        id();
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

    private void id() {
        textInputLayoutname = findViewById(R.id.layout_spname);
        textInputLayoutpassword = findViewById(R.id.layout_sppassword);
        textInputLayoutcompanyname = findViewById(R.id.layout_companyname);
        textInputLayoutcontact = findViewById(R.id.layout_contact);
        textInputLayoutemail = findViewById(R.id.layout_spemail);
        textInputLayoutaddress = findViewById(R.id.layout_spaddress);
        textInputLayoutcompanydescription = findViewById(R.id.layout_spdescription);
        textInputEditTextname = findViewById(R.id.edit_text_spname);
        textInputEditTextpassword = findViewById(R.id.edit_text_sppassword);
        textInputEditTextcompanyname = findViewById(R.id.edit_text_companyname);
        textInputEditTextcontact = findViewById(R.id.edit_text_contact);
        textInputEditTextemail = findViewById(R.id.edit_text_spemail);
        textInputEditTextaddress = findViewById(R.id.edit_text_spaddress);
        textInputEditTextcompanydescription = findViewById(R.id.edit_text_spdescription);
        button_done = findViewById(R.id.button_done);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("ServicesProvider");
    }

    private void Button() {
        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(textInputEditTextname.getText().toString())) {
                    textInputLayoutname.setError("Please Fill Up Your Name");
                    textInputLayoutpassword.setError(null);
                    textInputLayoutcompanyname.setError(null);
                    textInputLayoutcontact.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutaddress.setError(null);
                    textInputLayoutcompanydescription.setError(null);
                    textInputEditTextname.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextpassword.getText().toString())) {
                    textInputLayoutpassword.setError("Please Fill Up Your Password");
                    textInputLayoutname.setError(null);
                    textInputLayoutcompanyname.setError(null);
                    textInputLayoutcontact.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutaddress.setError(null);
                    textInputLayoutcompanydescription.setError(null);
                    textInputEditTextpassword.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextcompanyname.getText().toString())) {
                    textInputLayoutcompanyname.setError("Please Fill Up Your Company Name");
                    textInputLayoutname.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputLayoutcontact.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutaddress.setError(null);
                    textInputLayoutcompanydescription.setError(null);
                    textInputEditTextcompanyname.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextcontact.getText().toString())) {
                    textInputLayoutcontact.setError("Please Fill Up Your Contact");
                    textInputLayoutname.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutaddress.setError(null);
                    textInputLayoutcompanydescription.setError(null);
                    textInputEditTextcontact.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextemail.getText().toString())) {
                    textInputLayoutemail.setError("Please Fill Up Your Email");
                    textInputLayoutname.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputLayoutcontact.setError(null);
                    textInputLayoutaddress.setError(null);
                    textInputLayoutcompanydescription.setError(null);
                    textInputEditTextemail.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextaddress.getText().toString())) {
                    textInputLayoutaddress.setError("Please Fill Up Your Address");
                    textInputLayoutname.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputLayoutcontact.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutcompanydescription.setError(null);
                    textInputEditTextaddress.requestFocus();
                } else if (TextUtils.isEmpty(textInputEditTextcompanydescription.getText().toString())) {
                    textInputLayoutcompanydescription.setError("Please Fill Up Your Company Description");
                    textInputLayoutname.setError(null);
                    textInputLayoutpassword.setError(null);
                    textInputLayoutcontact.setError(null);
                    textInputLayoutemail.setError(null);
                    textInputLayoutaddress.setError(null);
                    textInputEditTextcompanydescription.requestFocus();
                } else {
                    final String name = textInputEditTextname.getText().toString();
                    final String email = textInputEditTextemail.getText().toString();
                    final String password = textInputEditTextpassword.getText().toString();
                    final String contact = textInputEditTextcontact.getText().toString();
                    final String companyname = textInputEditTextcompanyname.getText().toString();
                    final String companydescription = textInputEditTextcompanydescription.getText().toString();
                    final String address = textInputEditTextaddress.getText().toString();
                    final String date = new Date().toString();
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                if (firebaseUser != null) {
                                    final String uid = firebaseUser.getUid();
                                    ServicesProviderDetails user = new ServicesProviderDetails(uid, name, email, companyname, companydescription, address, contact, date);
                                    databaseReference.child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Sign Up Successfully  ", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(ServicesProvider.this, SPHomeActivity.class));
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
