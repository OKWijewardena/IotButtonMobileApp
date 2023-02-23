package com.example.techneednurseanddoctorcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DoctorHome extends AppCompatActivity {

    private Button AddPatient,ViewPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        AddPatient = findViewById(R.id.addPatient);
        ViewPatient = findViewById(R.id.viewPatient);

        String emailID = getIntent().getStringExtra("emailID");

        AddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DoctorAddPatient.class);
                intent.putExtra("emailID", emailID);
                startActivity(intent);
            }
        });

        ViewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DoctorViewPatient.class);
                intent.putExtra("emailID", emailID);
                startActivity(intent);
            }
        });
    }
}