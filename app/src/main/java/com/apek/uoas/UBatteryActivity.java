package com.apek.uoas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UBatteryActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private Button button_logout, button_done, button_nego, button_map;
    private Spinner cnspinner, bbspinner, pspinner;
    private String spUid, battery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ubattery);
        spUid = "i1NFQaVsXihjakyxYT9rnqmqHHf1";   //firebaseUser.getUid();          <-- change

        fetchBattery(); // call function to get (battery) list from firebase
        Button();
        // Spinner(mainArr);
        //        //if (firebaseUser != null)


    }

    private void fetchBattery() {

        final List<String> batteryArr = new ArrayList<>();
        databaseReference.child("Battery").child("i1NFQaVsXihjakyxYT9rnqmqHHf1").addValueEventListener(new ValueEventListener() {   //<-- 2nd chil tukar spUid
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                    //not sure why 3rd tree not needed
                    battery = areaSnapshot.child("battery").getValue(String.class);

                    batteryArr.add(battery);

                }
                Spinner(batteryArr);
                Log.i("fetch", "fetch " + batteryArr);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", databaseError.toString());
            }
        });

    }

    private void Spinner(List<String> mainArr) {

        cnspinner = findViewById(R.id.cnspinner);

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mainArr);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cnspinner.setAdapter(dataAdapter);

        cnspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // buat apa lepas select (send data to somewhere/etc)

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //x masuk somehow
            }
        });


    }
//    public class MySpinner extends Spinner {
//        OnItemSelectedListener listener;
//
//        public MySpinner(Context context, AttributeSet attrs) {
//            super(context, attrs);
//        }
//
//        @Override
//        public void setSelection(int position) {
//            super.setSelection(position);
//            if (listener != null)
//                listener.onItemSelected(null, null, position, 0);
//        }
//
//        public void setOnItemSelectedEvenIfUnchangedListener(
//                OnItemSelectedListener listener) {
//            this.listener = listener;
//        }
//    }
    //   private void Spinner() {
    //      cnspinner = findViewById(R.id.cnspinner);
    //      List<String> cn = new ArrayList<>();
    //     cn.add(0, "Choose Company Name");
    //     cn.add("test3");

//        ArrayAdapter<String> dataAdapter;
//        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cn);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        cnspinner.setAdapter(dataAdapter);
//        cnspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//                if (parent.getItemAtPosition(position).equals("Choose Company Name")) {
//
//               } else {
//                    String item = parent.getItemAtPosition(position).toString();
//
//                   Toast.makeText(parent.getContext(), "Select:" + item, Toast.LENGTH_SHORT).show();
//                }
//           }
//
//           @Override
//           public void onNothingSelected(AdapterView<?> adapterView) {
//
//           }
//       });
//        bbspinner = findViewById(R.id.bbspinner);
//        List<String> bb = new ArrayList<>();
//        bb.add(0, "Choose Battery Brand");
//        bb.add("battery");
//
//        ArrayAdapter<String> dataAdapter1;
//        dataAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bb);
//
//        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        bbspinner.setAdapter(dataAdapter1);
//        bbspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//                if (parent.getItemAtPosition(position).equals("Choose Battery Brand")) {
//
//                } else {
//                    String item = parent.getItemAtPosition(position).toString();
//
//                    Toast.makeText(parent.getContext(), "Select:" + item, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView1) {
//
//            }
//        });
//        pspinner = findViewById(R.id.pspinner);
//        List<String> p = new ArrayList<>();
//        p.add(0, "00.00");
//        p.add("230.88");
//
//        ArrayAdapter<String> dataAdapter2;
//        dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, p);
//
//        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        pspinner.setAdapter(dataAdapter2);
//        pspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//                if (parent.getItemAtPosition(position).equals("230.88")) {
//                    String item = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(parent.getContext(), "Select:" + item, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView1) {
//
//            }
//        });
//    }

    private void Button() {

        button_logout = findViewById(R.id.button_logout);
        button_map = findViewById(R.id.button_map);
        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UBatteryActivity.this, MapsActivity.class));
                finish();
            }
        });

        button_nego = findViewById(R.id.button_nego);
        button_nego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UBatteryActivity.this, SPBNegoActivity.class));
                finish();
            }
        });
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth != null)
                    firebaseAuth.signOut();
                startActivity(new Intent(UBatteryActivity.this, MainActivity.class));
                finish();
            }
        });
        button_done = findViewById(R.id.button_done);
        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Order Successfully  ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(UBatteryActivity.this, HomeActivity.class));
                finish();

            }
        });

    }
}


