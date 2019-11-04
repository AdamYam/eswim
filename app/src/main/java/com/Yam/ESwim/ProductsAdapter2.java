package com.Yam.ESwim;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Yam.R;

import java.util.List;

public class ProductsAdapter2 extends RecyclerView.Adapter<ProductsAdapter2.ProductViewHolder> {

    private Context mCtx;
    private List<Product2> productList;

    public ProductsAdapter2(Context mCtx, List<Product2> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_product2, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product2 product2 = productList.get(position);

        holder.textViewName.setText("Name: " +product2.getName());
        holder.textViewAge.setText("Age: " +product2.getAge());
        holder.textViewVenue.setText("Venue: " +product2.getVenue());
        holder.textViewDate.setText("Date " + product2.getDate());
        holder.textViewTime.setText("Time: " + product2.getTime());
        holder.textViewStatus.setText("Status: " + product2.getStatus());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewAge, textViewVenue, textViewDate, textViewTime, textViewStatus;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textview_name);
            textViewAge = itemView.findViewById(R.id.textview_age);
            textViewVenue = itemView.findViewById(R.id.textview_venue);
            textViewDate = itemView.findViewById(R.id.textview_date);
            textViewTime = itemView.findViewById(R.id.textview_time);
            textViewStatus = itemView.findViewById(R.id.textview_status);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Product2 product2 = productList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, UpdateProductActivity2.class);
            intent.putExtra("product", product2);
            mCtx.startActivity(intent);
        }
    }
}
