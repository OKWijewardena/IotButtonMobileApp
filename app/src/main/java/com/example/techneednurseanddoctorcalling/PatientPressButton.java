package com.example.techneednurseanddoctorcalling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientPressButton extends AppCompatActivity {

    private Button Emergency;
    public static String ID_CHAR = "P";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_press_button);

        Emergency = findViewById(R.id.Emergency);

        Emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenarateID();
            }
        });
    }

        private void GenarateID() {
        List<Integer> idArray = new ArrayList<>();

        idArray.add(25);

        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        int id = random.nextInt(idArray.size());

        String genaratedID = stringBuffer.append(idArray.get(id)).toString();

            String regptime = ""+System.currentTimeMillis();
            HashMap<String,Object> bdata = new HashMap<>();
            bdata.put("Bed_Number",genaratedID);
            DatabaseReference reference = FirebaseDatabase.getInstance("https://techneed-project-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Emergency");
            reference.child(genaratedID).setValue(bdata)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Emergency bed : " +genaratedID, Toast.LENGTH_SHORT).show();
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