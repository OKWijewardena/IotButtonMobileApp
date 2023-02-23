package com.example.techneednurseanddoctorcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NurseHome extends AppCompatActivity {

    private Button AddPatient,ViewPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_home);
        AddPatient = findViewById(R.id.NaddPatient);
        ViewPatient = findViewById(R.id.NviewPatient);

        String emailID = getIntent().getStringExtra("emailID");

        AddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NurseAddPatient.class);
                intent.putExtra("emailID", emailID);
                startActivity(intent);
            }
        });
        ViewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NurseViewPatient.class);
                intent.putExtra("emailID", emailID);
                startActivity(intent);
            }
        });
    }
}