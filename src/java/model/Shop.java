/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class Shop  {
    private int shopId;
    public String shopName;
    private String address;
    private String desshop;
    private int userId;
    
    public Shop(int shopId, String shopName, String address, int userId) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.address = address;
        this.userId = userId;
    }

    public Shop(int shopId, String shopName, String address, String desshop, int userId) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.address = address;
        this.desshop = desshop;
        this.userId = userId;
    }

    public Shop(String shopName, String address, String desshop, int userId) {
        this.shopName = shopName;
        this.address = address;
        this.desshop = desshop;
        this.userId = userId;
    }
    
    public Shop(){
        
    }

    public int getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getAddress() {
        return address;
    }

    public int getUserId() {
        return userId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDesshop() {
        return desshop;
    }

    public void setDesshop(String desshop) {
        this.desshop = desshop;
    }
    
    
     @Override
    public String toString() {
        return "Shop{" + "shopId=" + shopId + ", shopName=" + shopName + ", address=" + address + ", desshop=" + desshop + ", userId=" + userId + '}';
    }
}
