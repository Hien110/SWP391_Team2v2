/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class InfoCustomer {
    private int customerid;
    private String customerName;
    private String phoneCustomer;
    private String addressCustomer;
    private String userid;

    public InfoCustomer(int customerid,String customerName, String phoneCustomer, String addressCustomer, String userid) {
        this.customerid = customerid;
        this.customerName = customerName;
        this.phoneCustomer = phoneCustomer;
        this.addressCustomer = addressCustomer;
        this.userid = userid;
    }

    public InfoCustomer(String customerName, String phoneCustomer, String addressCustomer, String userid) {
        this.customerName = customerName;
        this.phoneCustomer = phoneCustomer;
        this.addressCustomer = addressCustomer;
        this.userid = userid;
    }
    
    

    public InfoCustomer() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    @Override
    public String toString() {
        return "InfoCustomer{" + "customerid=" + customerid + ", customerName=" + customerName + ", phoneCustomer=" + phoneCustomer + ", addressCustomer=" + addressCustomer + ", userid=" + userid + '}';
    }

    

    
    
}
