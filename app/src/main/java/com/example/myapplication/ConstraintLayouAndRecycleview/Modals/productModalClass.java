package com.example.myapplication.ConstraintLayouAndRecycleview.Modals;

public class productModalClass {

    String oldPrice, newPrice, productQuantity, productName, productDeliveryStatus, productType;
    int vegOrNonVegImage, productImage;

    public productModalClass(String oldPrice, String newPrice, String productQuantity, String productName, String productDeliveryStatus, int vegOrNonVegImage, int productImage, String productType) {
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.productQuantity = productQuantity;
        this.productName = productName;
        this.productDeliveryStatus = productDeliveryStatus;
        this.vegOrNonVegImage = vegOrNonVegImage;
        this.productImage = productImage;
        this.productType = productType;
    }

    public int getVegOrNonVegImage() {
        return vegOrNonVegImage;
    }

    public void setVegOrNonVegImage(int vegOrNonVegImage) {
        this.vegOrNonVegImage = vegOrNonVegImage;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDeliveryStatus() {
        return productDeliveryStatus;
    }

    public void setProductDeliveryStatus(String productDeliveryStatus) {
        this.productDeliveryStatus = productDeliveryStatus;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
