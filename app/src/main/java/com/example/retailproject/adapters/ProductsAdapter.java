package com.example.retailproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retailproject.R;
import com.example.retailproject.models.ProductsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder > {
    private Context context;
    private List<ProductsModel> productsList;
    private OnProductsClickListener onProductsClickListener;
    private OnAddProductClickListener onAddProductClickListener;

    public interface OnAddProductClickListener{
        void onAddProduct(View view, int position);
    }
    public interface OnProductsClickListener{
        void onProductClick(View view, int position);
    }

    public ProductsAdapter(Context context, List<ProductsModel> productsList, OnAddProductClickListener onAddProductClickListener, OnProductsClickListener onProductsClickListener) {
        this.context = context;
        this.productsList = productsList;
        this.onProductsClickListener = onProductsClickListener;
        this.onAddProductClickListener = onAddProductClickListener;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.products_rv_item,parent,false);

        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, final int position) {
    ProductsModel productsModel=productsList.get(position);
        Glide.with(context).load(productsModel.getProductPhoto()).into(holder.productIV);
        holder.productTitleTV.setText(productsModel.getTitle());
        holder.productDetailsTV.setText(productsModel.getDetails());
        holder.productFinalPriceTV.setText(productsModel.getFinalPriceText());
        holder.addProductToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddProductClickListener.onAddProduct(view, position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onProductsClickListener.onProductClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder{

        ImageView productIV;
        TextView productTitleTV;
        TextView productDetailsTV;
        TextView productFinalPriceTV;
        ImageButton addProductToCart;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            productIV=itemView.findViewById(R.id.product_iv);
            productTitleTV=itemView.findViewById(R.id.product_title_tv);
            productDetailsTV=itemView.findViewById(R.id.product_details_tv);
            productFinalPriceTV=itemView.findViewById(R.id.product_price_tv);
            addProductToCart=itemView.findViewById(R.id.add_product_ib);
        }
    }
}
