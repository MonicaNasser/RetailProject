package com.example.retailproject;

import com.example.retailproject.models.ProductsResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {
   /* @GET("products")
    Call<ProductsResponse> getProducts();*/
   @GET("products")
    Single<ProductsResponse> getProducts();

}
