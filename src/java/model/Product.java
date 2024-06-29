/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class Product extends Shop{


    
    private int productId;
    private String productName;
    private double price;
    private String description;
    private int quantityp;  
    private double averageStar;
    private int shopId;
    private String image;
    private String color;
    private String size;
    private int typeId;


//listShop
    public Product(int productId, String productName, double price, String description, int quantityp, double averageStar, String image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantityp = quantityp;
        this.averageStar = averageStar;
        this.image = image;
    }
     
//....................................................................................
   // Constructor

    public Product(int productId, String productName, double price, String description, int quantityp, double averageStar, String image, String color, String size, int shopId, int typeId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantityp = quantityp;
        this.averageStar = averageStar;
        this.shopId = shopId;
        this.image = image;
        this.color = color;
        this.size = size;
        this.typeId = typeId;
    }

    public Product(int productId, String productName, double price, String description, int quantityp, double averageStar, String image, String color, String size, int typeId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantityp = quantityp;
        this.averageStar = averageStar;
        this.image = image;
        this.color = color;
        this.size = size;
        this.typeId = typeId;
    }
    public Product(int productId, String productName, double price, String description, int quantityp, double averageStar, String image, String color, String size, int typeId, int shopId, String shopName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantityp = quantityp;
        this.averageStar = averageStar;
        this.image = image;
        this.color = color;
        this.size = size;
        this.typeId = typeId;
        this.shopId = shopId;
        this.shopName = shopName;
    }
    

    //Constructor productshopdetail HAnh
    public Product(int productId, String productName, double price, String image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.image = image;
    }
    
    
    // for oder 

    public Product(String productName, double price, String description, int quantityp, String image, String color, String size) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantityp = quantityp;
        this.image = image;
        this.color = color;
        this.size = size;
    }
    
    
    public Product(){
        
    }

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

    public void setQuantity(int quantityp) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", price=" + price + ", description=" + description + ", quantityp=" + quantityp + ", averageStar=" + averageStar + ", shopId=" + shopId + ", image=" + image + ", color=" + color + ", size=" + size + ", typeId=" + typeId + '}';
    }
  
    
}
    

