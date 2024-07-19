/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class reportDetail {
    private String shopName;
    private String address;
    private String reasonReport;
    private int productId;
    private String fullName;
    private int reportproductid;

    public reportDetail() {
    }

    public reportDetail(String shopName, String address, String reasonReport, int productId, String fullName, int reasonId) {
        this.shopName = shopName;
        this.address = address;
        this.reasonReport = reasonReport;
        this.productId = productId;
        this.fullName = fullName;
        this.reportproductid = reasonId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReasonReport() {
        return reasonReport;
    }

    public void setReasonReport(String reasonReport) {
        this.reasonReport = reasonReport;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getReasonId() {
        return reportproductid;
    }

    public void setReasonId(int reasonId) {
        this.reportproductid = reasonId;
    }

    public int getReportproductid() {
        return reportproductid;
    }

    public void setReportproductid(int reportproductid) {
        this.reportproductid = reportproductid;
    }
    
    

    @Override
    public String toString() {
        return "reportDetail{" + "shopName=" + shopName + ", address=" + address + ", reasonReport=" + reasonReport + ", productId=" + productId + ", fullName=" + fullName + ", reasonId=" + reportproductid + '}';
    }
    
}
