/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ProductInfor {
    private int productinforid;
    private String color;
    private String size;
    private int quantityp;
    private int productid;

    public ProductInfor() {
    }

    public ProductInfor(int productinforid, String color, String size, int quantityp, int productid) {
        this.productinforid = productinforid;
        this.color = color;
        this.size = size;
        this.quantityp = quantityp;
        this.productid = productid;
    }

    public int getProductinforid() {
        return productinforid;
    }

    public void setProductinforid(int productinforid) {
        this.productinforid = productinforid;
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

    public int getQuantityp() {
        return quantityp;
    }

    public void setQuantityp(int quantityp) {
        this.quantityp = quantityp;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "ProductInfor{" + "productinforid=" + productinforid + ", color=" + color + ", size=" + size + ", quantityp=" + quantityp + ", productid=" + productid + '}';
    }
    
     
}
