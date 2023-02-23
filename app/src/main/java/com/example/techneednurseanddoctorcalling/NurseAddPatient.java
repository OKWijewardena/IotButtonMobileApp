package com.example.techneednurseanddoctorcalling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NurseAddPatient extends AppCompatActivity {
    private EditText PatientName,BedNumber;
    private Button AddButton;
    String str_name,str_bdNumber,str_email;
    String emailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_add_patient);

        PatientName = findViewById(R.id.NpatientName);
        BedNumber = findViewById(R.id.NbedNumber);
        AddButton = (Button) findViewById(R.id.AddNPatientButton);
        emailID = getIntent().getStringExtra("emailID");

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPatient();
            }
        });
    }

    private void AddPatient(){
        str_name = PatientName.getText().toString();
        str_bdNumber = BedNumber.getText().toString();
        str_email = String.valueOf(emailID);

        String regtime = ""+System.currentTimeMillis();
        HashMap<String,Object> data = new HashMap<>();
        data.put("Patient_Name",str_name);
        data.put("Bed_Number",str_bdNumber);
        data.put("email",str_email);
        DatabaseReference reference = FirebaseDatabase.getInstance("https://techneed-project-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Nurse Patient");
        reference.child(String.valueOf(str_name)).setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Patient Added Successfull", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}