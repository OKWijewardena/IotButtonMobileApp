package com.example.techneednurseanddoctorcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button DoctorBtn,NurseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DoctorBtn =findViewById(R.id.doctorBtn);
        NurseBtn = findViewById(R.id.nurseBtn);

        DoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dLogin =new Intent(MainActivity.this, login.class);
                startActivity(dLogin);
            }
        });

        NurseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }        });

    }
}