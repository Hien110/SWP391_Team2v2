/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class CartItem {

    private int cartId;
    private int productId;
    private int userId;
    private int quantity;
    private String size;
    private String color;
    private String productName;
    private double price;
    private String image;
    private String description;
    private String shopname;
    private int shopId;

    public CartItem(int userId, int productId, String productName, String size, String color, double price, int quantity, String image, String description, int shopId) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.shopId = shopId;
    }

    public CartItem(int cartId, int productId, int userId, int quantity, String size, String color, String productName, double price, String image, String description, int shopId, String shopname) {
        this.cartId = cartId;
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.description = description;
        this.shopname = shopname;
        this.shopId = shopId;
    }

    public CartItem(int productId, int userId, int quantity, String size, String color) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CartItem{" + "cartId=" + cartId + ", productId=" + productId + ", userId=" + userId + ", quantity=" + quantity + ", size=" + size + ", color=" + color + ", productName=" + productName + ", price=" + price + ", image=" + image + ", description=" + description + ", shopname=" + shopname + ", shopId=" + shopId + '}';
    }

    

}
