package com.example.retailproject.asyncTasks;

import android.os.AsyncTask;

import com.example.retailproject.models.ProductsModel;
import com.example.retailproject.room.ProductDAO;

    public class InsertAsyncTask extends AsyncTask<ProductsModel,Void,Void> {

        private ProductDAO productDAO;

        public InsertAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(ProductsModel... productsModel) {
            productDAO.insertProduct(productsModel[0]);
            return null;
        }
    }
