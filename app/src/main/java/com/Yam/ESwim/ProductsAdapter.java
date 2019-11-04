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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_product, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewName.setText("Name :" + product.getName());
        holder.textViewAge.setText("Age :" + product.getAge());
        holder.textViewBestSwim.setText("Best stroke : " + product.getBestSwim());
        holder.textViewComp.setText("Competition Taken " + product.getComp());
        holder.textViewAgeGroup.setText("Age Group: " + product.getAge());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewAge, textViewComp, textViewAgeGroup, textViewBestSwim;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textview_name);
            textViewAge = itemView.findViewById(R.id.textview_age);
            textViewBestSwim = itemView.findViewById(R.id.textview_bestswim);
            textViewComp = itemView.findViewById(R.id.textview_comp);
            textViewAgeGroup = itemView.findViewById(R.id.textview_agegroup);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Product product = productList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, UpdateProductActivity.class);
            intent.putExtra("product", product);
            mCtx.startActivity(intent);
        }
    }
}
