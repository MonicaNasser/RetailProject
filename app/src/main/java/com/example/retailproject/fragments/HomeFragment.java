package com.example.retailproject.fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retailproject.R;
import com.example.retailproject.RetrofitFactory;
import com.example.retailproject.RoomFactory;
import com.example.retailproject.WebServices;
import com.example.retailproject.adapters.ProductsAdapter;
import com.example.retailproject.asyncTasks.GetProductsAsyncTask;
import com.example.retailproject.asyncTasks.InsertAsyncTask;
import com.example.retailproject.models.ProductsModel;
import com.example.retailproject.models.ProductsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private SingleObserver<ProductsResponse> productsObserver;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private RecyclerView productsRV;
    private ProductsAdapter productsAdapter;
    List<ProductsModel>productsList =new ArrayList<>();
    private WebServices webServices;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_home, container, false);
       productsRV=view.findViewById(R.id.product_rv);
       return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpProgressDialog();
        dialog.show();
        setUpRecyclerView();
        //getProuctsAPI();
        setUpProductsObserver();
        doProductsSubscription();
    }

    private void doProductsSubscription() {
        webServices= RetrofitFactory.getRetrofit().create(WebServices.class);
        Single<ProductsResponse> getProducts = webServices.getProducts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

        getProducts.subscribe(productsObserver);
    }

    private void setUpProductsObserver() {
        productsObserver = new SingleObserver<ProductsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(ProductsResponse productsResponse) {
                Toast.makeText(requireContext(),"Success",Toast.LENGTH_LONG).show();
                dialog.dismiss();
                productsList.clear();
                productsList.addAll(productsResponse.getPoductsList());
                productsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        };

    }

   /* private void getProuctsAPI() {
        webServices= RetrofitFactory.getRetrofit().create(WebServices.class);
        Call<ProductsResponse>getProducts=webServices.getProducts();
        getProducts.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                Toast.makeText(requireContext(),"Success",Toast.LENGTH_LONG).show();
                dialog.dismiss();
                productsList.clear();
                productsList.addAll(response.body().getPoductsList());
                productsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Log.d("test", t.toString());

            }
        });
    }*/

    private void setUpProgressDialog() {
        dialog=new ProgressDialog(requireContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void setUpRecyclerView() {


        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(requireContext(),2);
        productsRV.setLayoutManager(layoutManager);
        productsRV.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(12), true));
        productsAdapter=new ProductsAdapter(requireContext(), productsList, new ProductsAdapter.OnAddProductClickListener() {
            @Override
            public void onAddProduct(View view, int position) {
                ProductsModel productsModel=productsList.get(position);

                productsModel.setQuantity(1);

                new InsertAsyncTask(RoomFactory.createOrGetRoomObject(requireContext()).getProductDao()).execute(productsModel);

                Navigation.findNavController(view).navigate(R.id.action_homeFragment2_to_cartFragment);

            }
        }, new ProductsAdapter.OnProductsClickListener() {
            @Override
            public void onProductClick(View view, int position) {
                ProductsModel productsModel=productsList.get(position);
                Bundle bundle=new Bundle();
                bundle.putSerializable("productsModel" , productsModel);
                Navigation.findNavController(view).navigate(R.id.action_homeFragment2_to_productDetailsFragment2,bundle);
            }
        });
        productsRV.setAdapter(productsAdapter);
        productsAdapter.notifyDataSetChanged();
    }



    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        compositeDisposable.clear();
    }
}
