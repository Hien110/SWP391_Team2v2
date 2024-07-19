package model;

/**
 *
 * @author HP
 */
public class orderShop {
    private int orderId;
    private int quantity;
    private String statusOrder;
    private double totalPrice;
    private String dateOrder;
    private String productName;
    private String nameOfReceiver;
    private String phoneNumber;
    private String reciever_address;
    private String image;
    private String colors;
    private String size;
    private String paymentmethods;

    public orderShop() {
    }

    public orderShop(int orderId, int quantity, String statusOrder, double totalPrice, String dateOrder, String productName, String nameOfReceiver, String phoneNumber, String reciever_address, String Image, String colors, String size, String paymentmethods) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.statusOrder = statusOrder;
        this.totalPrice = totalPrice;
        this.dateOrder = dateOrder;
        this.productName = productName;
        this.nameOfReceiver = nameOfReceiver;
        this.phoneNumber = phoneNumber;
        this.reciever_address = reciever_address;
        this.image = Image;
        this.colors = colors;
        this.size = size;
        this.paymentmethods = paymentmethods;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNameOfReceiver() {
        return nameOfReceiver;
    }

    public void setNameOfReceiver(String nameOfReceiver) {
        this.nameOfReceiver = nameOfReceiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReciever_address() {
        return reciever_address;
    }

    public void setReciever_address(String reciever_address) {
        this.reciever_address = reciever_address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String Image) {
        this.image = Image;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPaymentmethods() {
        return paymentmethods;
    }

    public void setPaymentmethods(String paymentmethods) {
        this.paymentmethods = paymentmethods;
    }

    @Override
    public String toString() {
        return "orderShop{" + "orderId=" + orderId + ", quantity=" + quantity + ", statusOrder=" + statusOrder + ", totalPrice=" + totalPrice + ", dateOrder=" + dateOrder + ", productName=" + productName + ", nameOfReceiver=" + nameOfReceiver + ", phoneNumber=" + phoneNumber + ", reciever_address=" + reciever_address + ", Image=" + image + ", colors=" + colors + ", size=" + size + ", paymentmethods=" + paymentmethods + '}';
    }

}