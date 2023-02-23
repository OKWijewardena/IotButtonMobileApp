package com.example.techneednurseanddoctorcalling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText UserName,UserEmail,UserPassword;
    Button Register,Click;
    Spinner UserRole;

    String str_name,str_email,str_role,str_password;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserName = findViewById(R.id.UserName);
        UserEmail = findViewById(R.id.UserEmail);
        UserRole = findViewById(R.id.UserRole);
        UserPassword = findViewById(R.id.UserPassword);

        Register = findViewById(R.id.RegisterBtn);
        Click = findViewById(R.id.Click);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        UserRole.setAdapter(adapter);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();

            }
        });

        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });
    }
//    private void Validation(){
//        str_name = UserName.getText().toString();
//        str_email = UserEmail.getText().toString();
//        str_role = UserRole.getText().toString();
//        str_password = UserPassword.getText().toString();
//
//        if(str_name.isEmpty()){
//            UserName.setError("please fill the field");
//            UserName.requestFocus();
//            return;
//        }
//        if(str_email.isEmpty()){
//            UserEmail.setError("please fill the field");
//            UserEmail.requestFocus();
//            return;
//        }
//        if(str_role.isEmpty()){
//            UserRole.setError("please fill the field");
//            UserRole.requestFocus();
//            return;
//        }
//        if(str_password.isEmpty()){
//            UserPassword.setError("please fill the field");
//            UserPassword.requestFocus();
//            return;
//        }
//
//    }

    private void CreateAccount(){

        str_name = UserName.getText().toString();
        str_email = UserEmail.getText().toString();
        str_role = UserRole.getSelectedItem().toString();
        str_password = UserPassword.getText().toString();

        String regtime = ""+System.currentTimeMillis();
        HashMap<String,Object> data = new HashMap<>();
        data.put("name",str_name);
        data.put("email",str_email);
        data.put("role",str_role);
        data.put("password",str_password);

        DatabaseReference reference = FirebaseDatabase.getInstance("https://techneed-project-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("User");
        reference.child(String.valueOf(str_name)).setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);

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