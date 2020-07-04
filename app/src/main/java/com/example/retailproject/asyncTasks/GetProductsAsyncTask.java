package com.example.retailproject.asyncTasks;

import android.os.AsyncTask;

import com.example.retailproject.models.ProductsModel;
import com.example.retailproject.room.ProductDAO;

import java.util.List;

public class GetProductsAsyncTask extends AsyncTask< Void,Void, List<ProductsModel>> {

    private ProductDAO productDAO;

    public GetProductsAsyncTask(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    protected List<ProductsModel> doInBackground(Void... voids) {
        return productDAO.getAllProducts();
    }
}
