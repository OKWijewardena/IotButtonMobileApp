package com.example.techneednurseanddoctorcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    private EditText Name,Password;
    private Button Login,Register;
    String UserEnteredName,UserEnteredPassword,emailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = findViewById(R.id.UserName);
        Password = findViewById(R.id.UserPassword);
        Login = findViewById(R.id.LoginBtn);
        Register = findViewById(R.id.registerbtn);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserEnteredName = Name.getText().toString().trim();
                UserEnteredPassword = Password.getText().toString().trim();


                DatabaseReference reference = FirebaseDatabase.getInstance("https://techneed-project-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("User");
                reference.child(String.valueOf(UserEnteredName)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String passwordFromDB = snapshot.child("password").getValue(String.class);
                            String roleFromDB = snapshot.child("role").getValue(String.class);
                            String emailID = snapshot.child("email").getValue(String.class);

                            if (roleFromDB.equals("doctor")){
                                if(UserEnteredPassword.equals(passwordFromDB)){


                                    Toast.makeText(login.this,"login successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),DoctorHome.class);
                                    intent.putExtra("emailID", emailID);
                                    startActivity(intent);

                                }
                                else{
                                    Toast.makeText(login.this,"wrong password",Toast.LENGTH_SHORT).show();

                                }

                            }
                            else if (roleFromDB.equals("nurse")){
                                if(UserEnteredPassword.equals(passwordFromDB)){


                                    Toast.makeText(login.this,"login successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),NurseHome.class);
                                    intent.putExtra("emailID", emailID);
                                    startActivity(intent);

                                }
                                else{
                                    Toast.makeText(login.this,"wrong password",Toast.LENGTH_SHORT).show();

                                }

                            }

                        }
                        else{
                            Toast.makeText(login.this,"wrong name",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}