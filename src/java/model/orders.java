package model;

/**
 *
 * @author TranHoangAnh
 */
public class orders {

    private int orderID;
    private String productName;
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
    private int productid;

    public orders() {
    }

    //orderhis
    public orders(int orderID, String productName, String image, int quantity, String address, String statusOrder, int totalPrice, String dateOrder, String color, String size, String paymentmethod, String shopname, int productid) {
        this.orderID = orderID;
        this.productName = productName;
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
        this.productid = productid;
    }
    
    
    public orders(int orderID, String productName, String image, int quantity, String address, String statusOrder, int totalPrice, String dateOrder, String color, String size, String paymentmethod, String shopname) {
        this.orderID = orderID;
        this.productName = productName;
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

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }
    
    

    @Override
    public String toString() {
        return "orders{" + "orderID=" + orderID + ", productName=" + productName + ", image=" + image + ", quantity=" + quantity + ", address=" + address + ", statusOrder=" + statusOrder + ", totalPrice=" + totalPrice + ", dateOrder=" + dateOrder + ", color=" + color + ", size=" + size + ", paymentmethod=" + paymentmethod + ", shopname=" + shopname + ", productid=" + productid + '}';
    }

    
    
}
