package com.example.retailproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductsModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long incrementalId;

    @SerializedName("id")
    private int ProductID;


    @SerializedName("name")
    @ColumnInfo (name="details")
    private String details;

    @SerializedName("title")
    @ColumnInfo (name="title")
    private String title;

    @SerializedName("category_id")
    private int categoryID;

    @SerializedName("description")
    private String description;

    @SerializedName("Price")
    private int price;

    @SerializedName("discount")
    private int discount;

    @SerializedName("discount_type")
    private String discountType;

    @SerializedName("currency")
    private String currency;

    @SerializedName("in_stock")
    private int inStock;

    @SerializedName("avatar")
    @ColumnInfo (name="productPhoto")
    private String productPhoto;

    @SerializedName("price_final")
    private double finalPrice;

    @SerializedName("price_final_text")
    @ColumnInfo (name="price_final_text")
    private String finalPriceText;

    @ColumnInfo(name="quantity")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductsModel(String details, String title, String productPhoto, String finalPriceText) {
        this.details = details;
        this.title = title;
        this.productPhoto = productPhoto;
        this.finalPriceText = finalPriceText;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getFinalPriceText() {
        return finalPriceText;
    }

    public void setFinalPriceText(String finalPriceText) {
        this.finalPriceText = finalPriceText;
    }

    public long getIncrementalId() {
        return incrementalId;
    }

    public void setIncrementalId(long incrementalId) {
        this.incrementalId = incrementalId;
    }
}
