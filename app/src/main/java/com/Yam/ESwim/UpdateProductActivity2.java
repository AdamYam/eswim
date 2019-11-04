package com.Yam.ESwim;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Yam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateProductActivity2 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextVenue;
    private EditText editTextDate;
    private EditText editTextTime;
    private EditText editTextStatus;


    private FirebaseFirestore db;

    private Product2 product2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product2);

        product2 = (Product2) getIntent().getSerializableExtra("product");
        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.edittext_name);
        editTextAge = findViewById(R.id.edittext_age);
        editTextVenue = findViewById(R.id.edittext_venue);
        editTextDate = findViewById(R.id.edittext_date);
        editTextTime = findViewById(R.id.edittext_time);
        editTextStatus = findViewById(R.id.edittext_status);


        editTextName.setText(product2.getName());
        editTextAge.setText(product2.getAge());
        editTextVenue.setText(product2.getVenue());
        editTextDate.setText(product2.getDate());
        editTextTime.setText(product2.getTime());
        editTextStatus.setText(product2.getStatus());


        findViewById(R.id.button_update).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
    }

    private boolean hasValidationErrors(String name, String age, String venue, String date, String time, String status) {
        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return true;
        }

        if (age.isEmpty()) {
            editTextAge.setError("Age required");
            editTextAge.requestFocus();
            return true;
        }

        if (venue.isEmpty()) {
            editTextVenue.setError("Venue required");
            editTextVenue.requestFocus();
            return true;
        }

        if (date.isEmpty()) {
            editTextDate.setError("Date required");
            editTextDate.requestFocus();
            return true;
        }

        if (time.isEmpty()) {
            editTextTime.setError("Time required");
            editTextTime.requestFocus();
            return true;
        }

        if (status.isEmpty()) {
            editTextStatus.setError("Time required");
            editTextStatus.requestFocus();
            return true;
        }
        return false;
    }


    private void updateProduct() {
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String venue = editTextVenue.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        String status = editTextStatus.getText().toString().trim();


        if (!hasValidationErrors(name, age, venue, date, time, status)) {

            Product2 p = new Product2(
                    name, age, venue,
                    date,
                    time,
                    status
            );


            db.collection("products").document(product2.getId())
                    .update(
                            "age", p.getAge(),
                            "venue", p.getVenue(),
                            "name", p.getName(),
                            "date", p.getDate(),
                            "time", p.getTime(),
                            "status",p.getStatus()
                    )
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateProductActivity2.this, "Booking Updated", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    private void deleteProduct() {
        db.collection("product").document(product2.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateProductActivity2.this, "Booking deleted", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(UpdateProductActivity2.this, ProductActivity2.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update:
                updateProduct();
                break;
            case R.id.button_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure about this?");
                builder.setMessage("Deletion is permanent!");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteProduct();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();

                break;
        }
    }
}
