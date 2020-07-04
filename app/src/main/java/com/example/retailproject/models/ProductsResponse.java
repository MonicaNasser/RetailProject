package com.example.retailproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {
    @SerializedName("products")
    private List<ProductsModel>poductsList;

    public List<ProductsModel> getPoductsList() {
        return poductsList;
    }

    public void setPoductsList(List<ProductsModel> poductsList) {
        this.poductsList = poductsList;
    }
}
