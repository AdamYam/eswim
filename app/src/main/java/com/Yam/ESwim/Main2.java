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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Main2 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextBestSwim;
    private EditText editTextComp;
    private EditText editTextAgeGroup;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.edittext_name);
        editTextAge = findViewById(R.id.edittext_age);
        editTextBestSwim = findViewById(R.id.edittext_bestswim);
        editTextComp = findViewById(R.id.edittext_comp);
        editTextAgeGroup = findViewById(R.id.edittext_agegroup);

        findViewById(R.id.button_save).setOnClickListener(this);
        findViewById(R.id.textview_view_products).setOnClickListener(this);
    }

    private boolean hasValidationErrors(String name, String age, String bestswim, String comp, String agegroup) {
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

        if (bestswim.isEmpty()) {
            editTextBestSwim.setError("Best Stroke required");
            editTextBestSwim.requestFocus();
            return true;
        }

        if (comp.isEmpty()) {
            editTextComp.setError("Competition Taken required");
            editTextComp.requestFocus();
            return true;
        }

        if (agegroup.isEmpty()) {
            editTextAgeGroup.setError("Age Group required");
            editTextAgeGroup.requestFocus();
            return true;
        }
        return false;
    }


    private void saveProduct(){
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String bestswim = editTextBestSwim.getText().toString().trim();
        String comp = editTextComp.getText().toString().trim();
        String agegroup = editTextAgeGroup.getText().toString().trim();

        if (!hasValidationErrors(name, age, bestswim, comp, agegroup)) {

            CollectionReference dbProducts = db.collection("E-Swim");

            Product product = new Product(
                    name,
                    age,
                    bestswim,
                    comp,
                    agegroup
            );

            dbProducts.add(product)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Main2.this, "Profile Added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Main2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button_save:
                saveProduct();
                break;

            case R.id.textview_view_products:
                startActivity(new Intent(this, ProductsActivity.class));
                break;
        }

    }
}
