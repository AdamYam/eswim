package com.Yam.ESwim;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Yam.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextVenue;
    private EditText editTextDate;
    private EditText editTextTime;
    private EditText editTextStatus;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.edittext_name);
        editTextAge = findViewById(R.id.edittext_age);
        editTextVenue = findViewById(R.id.edittext_venue);
        editTextDate = findViewById(R.id.edittext_date);
        editTextTime = findViewById(R.id.edittext_time);
        editTextStatus = findViewById(R.id.edittext_status);

        auth = FirebaseAuth.getInstance();

        findViewById(R.id.button_save2).setOnClickListener(this);
        findViewById(R.id.textview_view_products).setOnClickListener(this);
    }

    private boolean hasValidationErrors(String name, String brand, String desc, String price, String qty, String status) {
        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return true;
        }

        if (brand.isEmpty()) {
            editTextAge.setError("Age required");
            editTextAge.requestFocus();
            return true;
        }

        if (desc.isEmpty()) {
            editTextVenue.setError("Venue required");
            editTextVenue.requestFocus();
            return true;
        }

        if (price.isEmpty()) {
            editTextDate.setError("Date required");
            editTextDate.requestFocus();
            return true;
        }

        if (qty.isEmpty()) {
            editTextTime.setError("Time required");
            editTextTime.requestFocus();
            return true;
        }
        if (status.isEmpty()) {
            editTextStatus.setError("Status required");
            editTextStatus.requestFocus();
            return true;
        }
        return false;
    }


    private void saveProduct() {
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String venue = editTextVenue.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        String status = editTextStatus.getText().toString().trim();

        if (!hasValidationErrors(name, age, venue, date, time, status)) {

            CollectionReference dbProducts = db.collection("booking");

            Product2 product2 = new Product2(
                    name,
                    age,
                    venue,
                    date,
                    time,
                    status
            );

            dbProducts.add(product2)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(MainActivity2.this, "Booking Added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_save2:
                saveProduct();
                break;

            case R.id.textview_view_products:
                startActivity(new Intent(this, ProductActivity2.class));
                break;

        }




// this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                    finish();
                }
            }
        };



    }
}


