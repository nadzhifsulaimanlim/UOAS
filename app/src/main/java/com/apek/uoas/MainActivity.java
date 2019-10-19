package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView textView;


    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private Button button_login, button_login1, button_singupuser, button_singupsp;
    private TextInputLayout textInputLayoutname1, textInputLayoutpw;
    private TextInputEditText textInputEditTextname1, textInputEditTextpw;

    private void id() {
        textInputLayoutname1 = findViewById(R.id.layout_name1);
        textInputLayoutpw = findViewById(R.id.layout_pw);
        textInputEditTextname1 = findViewById(R.id.edit_text_name1);
        textInputEditTextpw = findViewById(R.id.edit_text_pw);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        id();
        Button();


    }

    private void Button() {
        button_login = findViewById(R.id.button_login);
        button_login1 = findViewById(R.id.button_login1);
        button_singupuser = findViewById(R.id.button_signupuser);
        button_singupsp = findViewById(R.id.button_signupsp);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth != null) {
                    if (TextUtils.isEmpty(textInputEditTextname1.getText().toString())) {
                        textInputLayoutname1.setError("Please Fill Up Your Name");
                        textInputEditTextname1.requestFocus();
                    } else if (TextUtils.isEmpty(textInputEditTextpw.getText().toString())) {
                        textInputLayoutpw.setError("Please Fill Up Your Password");
                        textInputEditTextpw.requestFocus();
                    } else {
                        final String name = textInputEditTextname1.getText().toString();
                        final String password = textInputEditTextpw.getText().toString();

                        //Check at database if user is useer or service rpvo
                        Query query = databaseReference.child("User").orderByChild("email").equalTo(name.toLowerCase());
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    firebaseAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(MainActivity.this, "Error !!! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                                } else {
                                    Toast.makeText(MainActivity.this, "User not exist", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                }


            }
        });

        button_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth != null) {
                    if (TextUtils.isEmpty(textInputEditTextname1.getText().toString())) {
                        textInputLayoutname1.setError("Please Fill Up Your Name");
                        textInputEditTextname1.requestFocus();
                    } else if (TextUtils.isEmpty(textInputEditTextpw.getText().toString())) {
                        textInputLayoutpw.setError("Please Fill Up Your Password");
                        textInputEditTextpw.requestFocus();
                    } else {
                        final String name = textInputEditTextname1.getText().toString();
                        final String password = textInputEditTextpw.getText().toString();

                        //Check at database if user is useer or service rpvo
                        Query query = databaseReference.child("ServicesProvider").orderByChild("email").equalTo(name.toLowerCase());
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    firebaseAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(MainActivity.this, SPHomeActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(MainActivity.this, "Error !!! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                                } else {
                                    Toast.makeText(MainActivity.this, "sv not exist", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                }

            }
        });
        button_singupuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
        button_singupsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ServicesProvider.class));
            }
        });
    }


}