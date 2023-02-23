package com.example.techneednurseanddoctorcalling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class DoctorViewPatient extends AppCompatActivity {

    private ListView DListView;
    private String BedID;
    String emailID;
    String str_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_patient);

        DListView = findViewById(R.id.DoctorListView);
        emailID = getIntent().getStringExtra("emailID");

        final long [] vibe = {0,500};
        final Uri notificationsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.doctor_list_view , list);
        DListView.setAdapter(adapter);

        DatabaseReference Ereference = FirebaseDatabase.getInstance("https://techneed-project-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Emergency");
        Ereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot2 : snapshot.getChildren()){
                    EmergencyBed data = snapshot2.getValue(EmergencyBed.class);
                    BedID = data.getBed_Number();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance("https://techneed-project-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Doctor Patient");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                str_email = String.valueOf(emailID);
                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    DoctorPatientData info = snapshot1.getValue(DoctorPatientData.class);

                    if(String.valueOf(info.getEmail()).equals(str_email)){
                        String txt = info.getPatient_Name() + " : " + info.getBed_Number();
                        list.add(txt);
                    }
                }
                adapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}