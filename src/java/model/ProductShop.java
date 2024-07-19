/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class ProductShop {
    private int productId;
    private String productName;
    private double price;
    private String description;
    private int quantityp;  // This will be used for total_sales_quantity
    private double averageStar;
    private int shopId;
    private String image;

    public ProductShop() {
    }
    //list

    public ProductShop(int productId, String productName, double price, String description, double averageStar, String image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.averageStar = averageStar;
        this.image = image;
    }
    
    
//update
    public ProductShop(int productId, String productName, double price, String description, int quantityp, double averageStar, String image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantityp = quantityp;
        this.averageStar = averageStar;
        this.image = image;
    }
//addproductShop + updateproductshop
//
//    public ProductShop(String productName, double price, String description, int quantityp, double averageStar, String image) {
//        this.productName = productName;
//        this.price = price;
//        this.description = description;
//        this.quantityp = quantityp;
//        this.averageStar = averageStar;
//        this.image = image;
//    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityp() {
        return quantityp;
    }

    public void setQuantityp(int quantityp) {
        this.quantityp = quantityp;
    }

    public double getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(double averageStar) {
        this.averageStar = averageStar;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductShop{" + "productId=" + productId + ", productName=" + productName + ", price=" + price + ", description=" + description + ", quantityp=" + quantityp + ", averageStar=" + averageStar + ", shopId=" + shopId + ", image=" + image + '}';
    }
    
}
