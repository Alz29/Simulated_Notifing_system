package com.example.application1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    public static FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    Button res_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readdata();
        res_button= (Button) findViewById(R.id.Resbutton);
        res_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),Schedule.class);
            startActivity(intent);

            }
        });
    }
    public void readdata(){
        fireStore.collection("Devices")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                //when the data reach's this point what do you want to do? Check the isLocked == "true" (for example)
                                Toast.makeText (getApplicationContext(),"Successful", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText (getApplicationContext(),"UnSuccessful", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}