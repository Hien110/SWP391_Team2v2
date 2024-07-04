/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class typeOrderShop {
    private int orderID;
    private String productName;
    private String nameReceiver;
    private String phone;
    private String image;
    private int quantity;
    private String address;
    private String statusOrder;
    private int totalPrice;
    private String dateOrder;
    private String color;
    private String size;
    private String paymentmethod;
    private String shopname;
    private double star;
    private String reasonCancel;

    public typeOrderShop() {
    }
//donbihuy

    public typeOrderShop(int orderID, String productName, String nameReceiver, String phone, String image, int quantity, String address, String statusOrder, String dateOrder, String color, String size, String shopName, String reasonCancel) {
        this.orderID = orderID; 
        this.productName = productName;
        this.nameReceiver = nameReceiver;
        this.phone = phone;
        this.image = image;
        this.quantity = quantity;
        this.address = address;
        this.statusOrder = statusOrder;
        this.dateOrder = dateOrder;
        this.color = color;
        this.size = size;
        this.shopname = shopName;
        this.reasonCancel = reasonCancel;//13
    }
//donthanhcong

    public typeOrderShop(int orderID, String productName, String nameReceiver, String phone, String image, int quantity, String address, String statusOrder, int totalPrice, String dateOrder, String color, String size, String paymentmethod, String shopname, double star) {
        this.orderID = orderID;
        this.productName = productName;
        this.nameReceiver = nameReceiver;
        this.phone = phone;
        this.image = image;
        this.quantity = quantity;
        this.address = address;
        this.statusOrder = statusOrder;
        this.totalPrice = totalPrice;
        this.dateOrder = dateOrder;
        this.color = color;
        this.size = size;
        this.paymentmethod = paymentmethod;
        this.shopname = shopname;
        this.star = star; //15
    }

   

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
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

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getReasonCancel() {
        return reasonCancel;
    }

    public void setReasonCancel(String reasonCancel) {
        this.reasonCancel = reasonCancel;
    }

    @Override
    public String toString() {
        return "typeOrderShop{" + "orderID=" + orderID + ", productName=" + productName + ", nameReceiver=" + nameReceiver + ", phone=" + phone + ", image=" + image + ", quantity=" + quantity + ", address=" + address + ", statusOrder=" + statusOrder + ", totalPrice=" + totalPrice + ", dateOrder=" + dateOrder + ", color=" + color + ", size=" + size + ", paymentmethod=" + paymentmethod + ", shopname=" + shopname + ", star=" + star + ", reasonCancel=" + reasonCancel + '}';
    }
    
}