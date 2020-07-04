package com.example.retailproject.room;

import com.example.retailproject.models.ProductsModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import retrofit2.http.DELETE;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM products")
    List<ProductsModel> getAllProducts();

    @Insert
    void insertProduct(ProductsModel productsModel);

    @Query("DELETE FROM products")
    void deleteAllProducts();

    @Update
    void updateProduct(ProductsModel productsModel);

}
