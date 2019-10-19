package com.apek.uoas;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataRetrieve extends AppCompatActivity {

    DatabaseReference databaseReference;
    List<ServicesProviderDetails> servicesProviderDetailsList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieve);

        listView = findViewById(R.id.list_view2);

        databaseReference = FirebaseDatabase.getInstance().getReference("ServicesProvider");

        servicesProviderDetailsList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ServicesProviderSnopshot : dataSnapshot.getChildren()) {
                    ServicesProviderDetails servicesProviderDetails = ServicesProviderSnopshot.getValue(ServicesProviderDetails.class);
                    servicesProviderDetailsList.add(servicesProviderDetails);
                    Log.d("checking:", servicesProviderDetailsList.toString());
                }
                ServiceProviderAdapter serviceProviderAdapter = new ServiceProviderAdapter(DataRetrieve.this, servicesProviderDetailsList);
                listView.setAdapter(serviceProviderAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
