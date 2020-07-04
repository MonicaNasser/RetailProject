package com.example.retailproject.asyncTasks;

import android.os.AsyncTask;

import com.example.retailproject.models.ProductsModel;
import com.example.retailproject.room.ProductDAO;

public class UpdateAsyncTask extends AsyncTask<ProductsModel,Void,Void> {

    private ProductDAO productDAO;

    public UpdateAsyncTask(ProductDAO productDAO) {
        this.productDAO = productDAO;
        }

    @Override
    protected Void doInBackground(ProductsModel... productsModel) {
        productDAO.updateProduct(productsModel[0]);
        return null;
        }
}
