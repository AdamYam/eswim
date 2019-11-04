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

public class UpdateProductActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextComp;
    private EditText editTextBestSwim;
    private EditText editTextAgeGroup;

    private FirebaseFirestore db;

    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        product = (Product) getIntent().getSerializableExtra("product");
        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.edittext_name);
        editTextAge = findViewById(R.id.edittext_age);
        editTextBestSwim = findViewById(R.id.edittext_bestswim);
        editTextComp = findViewById(R.id.edittext_comp);
        editTextAgeGroup = findViewById(R.id.edittext_agegroup);

        editTextName.setText(product.getName());
        editTextAge.setText(product.getAge());
        editTextBestSwim.setText(product.getBestSwim());
        editTextComp.setText(String.valueOf(product.getComp()));
        editTextAgeGroup.setText(String.valueOf(product.getAgegroup()));


        findViewById(R.id.button_update).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
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


    private void updateProduct() {
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String bestswim = editTextBestSwim.getText().toString().trim();
        String comp = editTextComp.getText().toString().trim();
        String agegroup = editTextAgeGroup.getText().toString().trim();

        if (!hasValidationErrors(name, age, bestswim, comp, agegroup)) {

            Product p = new Product(
                    name, age, bestswim,
                    comp,
                    agegroup
            );


            db.collection("E-Swim").document(product.getId())
                    .update(
                            "age", p.getAge(),
                            "bestswim", p.getBestSwim(),
                            "name", p.getName(),
                            "comp", p.getComp(),
                            "agegroup", p.getAgegroup()
                    )
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateProductActivity.this, "Profile Updated", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    private void deleteProduct() {
        db.collection("E-Swim").document(product.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateProductActivity.this, "Profile deleted", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(UpdateProductActivity.this, ProductsActivity.class));
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
