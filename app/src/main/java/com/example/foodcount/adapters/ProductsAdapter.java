package com.example.foodcount.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcount.R;
import com.example.foodcount.models.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    Context context;
    List<Product> products;
    Product mProduct;
    private OnProductListener onProductListener;

    public ProductsAdapter(Context context, List<Product> products, OnProductListener onProductListener) {
        this.context = context;
        this.products = products;
        this.onProductListener = onProductListener;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view, onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {
        mProduct = products.get(position);

        holder.productsText.setText(mProduct.getId().toString() + " | " + mProduct.getName());
        holder.id = mProduct.getId();

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productsText;
        OnProductListener onProductListener;
        Integer id;


        public ViewHolder(@NonNull View itemView, OnProductListener onProductListener) {
            super(itemView);
            productsText = itemView.findViewById(R.id.tv_productNameItem);
            this.onProductListener = onProductListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProductListener.onProductClick(id);
        }
    }

    public interface OnProductListener{
        void onProductClick(int position);
    }
}

