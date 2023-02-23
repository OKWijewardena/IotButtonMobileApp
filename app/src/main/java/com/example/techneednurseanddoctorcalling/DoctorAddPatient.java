package com.example.techneednurseanddoctorcalling;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DoctorAddPatient extends AppCompatActivity {

    private EditText Name,BdNumber;
    private Button AddButton ,GenarateButton;
    String str_pname,str_bdNumber,str_email;
    String emailID;
    public static String ID_CHAR = "P";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add_patient);

        Name = findViewById(R.id.patientName);
        BdNumber = findViewById(R.id.bedNumber);
        AddButton = (Button) findViewById(R.id.AddPatientButton);
        emailID = getIntent().getStringExtra("emailID");




        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPatient();

            }
        });

//        GenarateButton.setOnClickListener(view -> GenarateID());

    }

//    private void GenarateID() {
//        List<Integer> idArray = new ArrayList<>();
//
//        idArray.add(1000);
//        idArray.add(2000);
//        idArray.add(3000);
//        idArray.add(4000);
//
//        StringBuffer stringBuffer = new StringBuffer();
//        Random random = new Random();
//        int id = random.nextInt(idArray.size());
//
//        String genaratedID = stringBuffer.append(ID_CHAR).append(idArray.get(id)).toString();
//
//        Toast.makeText(this, "ID is : " + genaratedID, Toast.LENGTH_SHORT).show();
//    }

    private void AddPatient(){
        str_pname = Name.getText().toString();
        str_bdNumber = BdNumber.getText().toString();
        str_email = String.valueOf(emailID);

        String regptime = ""+System.currentTimeMillis();
        HashMap<String,Object> pdata = new HashMap<>();
        pdata.put("Patient_Name",str_pname);
        pdata.put("Bed_Number",str_bdNumber);
        pdata.put("email",str_email);
        DatabaseReference reference = FirebaseDatabase.getInstance("https://techneed-project-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Doctor Patient");
        reference.child(String.valueOf(str_bdNumber)).setValue(pdata)
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