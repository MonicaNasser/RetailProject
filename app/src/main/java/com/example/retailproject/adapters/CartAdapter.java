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

    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<ProductsModel> productsList;
        private OnIncrementClickListener onIncrementClickListener;
        private OnDecrementClickListener onDecrementClickListener;

        public interface OnIncrementClickListener{
            void onIncrementProduct(View view, int position);
        }
        public interface OnDecrementClickListener{
            void onDecrementProduct(View view, int position);
        }

        public CartAdapter(Context context, List<ProductsModel> productsList, OnIncrementClickListener onIncrementClickListener, OnDecrementClickListener onDecrementClickListener) {
            this.context = context;
            this.productsList = productsList;
            this.onIncrementClickListener = onIncrementClickListener;
            this.onDecrementClickListener = onDecrementClickListener;
        }

        @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.cart_rv_items,parent,false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
    ProductsModel productsModel=productsList.get(position);
        Glide.with(context).load(productsModel.getProductPhoto()).into(holder.productIV);
        holder.productTitleTV.setText(productsModel.getTitle());
        holder.productDetailsTV.setText(productsModel.getDetails());
        holder.productPriceTV.setText(productsModel.getFinalPriceText());
        holder.productQuantityTV.setText(productsModel.getQuantity()+"");

        holder.incIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onIncrementClickListener.onIncrementProduct(view, holder.getAdapterPosition());
            }
        });
        holder.decIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDecrementClickListener.onDecrementProduct(view,holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {

        return productsList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView productIV;
        TextView productTitleTV;
        TextView productPriceTV;
        TextView productQuantityTV;
        TextView productDetailsTV;
        ImageButton incIB;
        ImageButton decIB;

    public CartViewHolder(@NonNull View itemView) {

        super(itemView);
        productIV = itemView.findViewById(R.id.product_cart_iv);
        productTitleTV=itemView.findViewById(R.id.product_title_cart_tv);
        productDetailsTV=itemView.findViewById(R.id.product_details_cart_tv);
        productPriceTV=itemView.findViewById(R.id.product_price_cart_tv);
        productQuantityTV=itemView.findViewById(R.id.quantity_tv);
        incIB=itemView.findViewById(R.id.inc_item_cart_ib);
        decIB=itemView.findViewById(R.id.dec_item_cart_ib);
    }


}
}
