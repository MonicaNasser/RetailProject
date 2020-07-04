package com.example.retailproject.asyncTasks;

import android.os.AsyncTask;

import com.example.retailproject.models.ProductsModel;
import com.example.retailproject.room.ProductDAO;

import java.util.List;

public class DeleteAsyncTasks extends AsyncTask< Void,Void, Void> {

    private ProductDAO productDAO;

    public DeleteAsyncTasks(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        productDAO.deleteAllProducts();
        return null;
    }
}
