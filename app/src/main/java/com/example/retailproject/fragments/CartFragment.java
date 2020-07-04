package com.example.retailproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.retailproject.R;
import com.example.retailproject.RoomFactory;
import com.example.retailproject.adapters.CartAdapter;
import com.example.retailproject.asyncTasks.DeleteAsyncTasks;
import com.example.retailproject.asyncTasks.GetProductsAsyncTask;
import com.example.retailproject.asyncTasks.UpdateAsyncTask;
import com.example.retailproject.models.ProductsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private RecyclerView carttRV;
    private CartAdapter cartAdapter;
    private List<ProductsModel> productsList =new ArrayList<>();
    Button clearCartBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        carttRV=view.findViewById(R.id.cart_rv);
        clearCartBtn=view.findViewById(R.id.clear_cart_btn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView ();
        
        getAllProductsFromRoomDB();
        
        setUpClickListener();
    }

    private void setUpClickListener() {

        clearCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new DeleteAsyncTasks(RoomFactory.createOrGetRoomObject(getContext()).getProductDao()).execute();
            productsList.clear();
            cartAdapter.notifyDataSetChanged();
            }
        });

    }

    private void getAllProductsFromRoomDB() {
        try {
            productsList.addAll(new GetProductsAsyncTask(RoomFactory.createOrGetRoomObject(requireContext()).getProductDao()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setUpRecyclerView() {

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(requireContext());
        carttRV.setLayoutManager(layoutManager);
        carttRV.addItemDecoration(new DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL));
        cartAdapter =new CartAdapter(requireContext(), productsList,
                new CartAdapter.OnIncrementClickListener() {
                    @Override
                    public void onIncrementProduct(View view, int position) {

                        productsList.get(position).setQuantity(productsList.get(position).getQuantity()+1);
                        new UpdateAsyncTask(RoomFactory.createOrGetRoomObject(requireContext()).getProductDao()).execute(productsList.get(position));
                        cartAdapter.notifyDataSetChanged();
                    }
                },

                new CartAdapter.OnDecrementClickListener() {
                    @Override
                    public void onDecrementProduct(View view, int position) {
                        productsList.get(position).setQuantity(productsList.get(position).getQuantity()-1);
                        new UpdateAsyncTask(RoomFactory.createOrGetRoomObject(requireContext()).getProductDao()).execute(productsList.get(position));
                        cartAdapter.notifyDataSetChanged();
                    }
                });
        carttRV.setAdapter(cartAdapter);
    }
}
