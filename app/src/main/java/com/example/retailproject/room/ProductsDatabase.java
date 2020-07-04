package com.example.retailproject.room;

import com.example.retailproject.models.ProductsModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {ProductsModel.class},version = 2,exportSchema = false)
public abstract class ProductsDatabase extends RoomDatabase {

    public abstract ProductDAO getProductDao();

}
