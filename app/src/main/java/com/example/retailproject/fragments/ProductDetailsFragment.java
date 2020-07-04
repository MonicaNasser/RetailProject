package com.example.retailproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retailproject.R;
import com.example.retailproject.RoomFactory;
import com.example.retailproject.asyncTasks.InsertAsyncTask;
import com.example.retailproject.models.ProductsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {

    ImageView productIV;
    TextView titleTV;
    TextView detailsTV;
    TextView priceTV;
    TextView descriptionTV;
    Button addToCartBtn;
    ProductsModel productsModel = null;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        productIV = view.findViewById(R.id.products_details_iv);
        titleTV = view.findViewById(R.id.product_details_title_tv);
        detailsTV = view.findViewById(R.id.product_details_details_tv);
        priceTV = view.findViewById(R.id.product_details_price_tv);
        descriptionTV = view.findViewById(R.id.product_details_description_tv);
        addToCartBtn = view.findViewById(R.id.add_to_cart_btn);
        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {

            productsModel = (ProductsModel) args.getSerializable("productsModel");

            titleTV.setText(productsModel.getTitle());
            detailsTV.setText(productsModel.getDetails());
            priceTV.setText(productsModel.getFinalPriceText());
            descriptionTV.setText(productsModel.getDescription());
            Glide.with(requireContext()).load(productsModel.getProductPhoto()).into(productIV);
        }
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new InsertAsyncTask(RoomFactory.createOrGetRoomObject(requireContext()).getProductDao()).execute(productsModel);

                Navigation.findNavController(view).navigate(R.id.action_productDetailsFragment2_to_cartFragment);
            }
        });

        }
    }


