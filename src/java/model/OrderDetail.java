package model;
// Linh
public class OrderDetail {

    private String productName;
    private Float subtotal;
    private Float shipping;
    private Float tax;
    private Float total;

    public OrderDetail(String productName, String subtotal, String shipping, String tax, String total) {
        this.productName = productName;
        this.subtotal = Float.parseFloat(subtotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }

    public String getProductName() {
        return productName;
    }

    public String getSubtotal() {
        return String.format("%.2f", subtotal);
    }

    public String getShipping() {
        return String.format("%.2f", shipping);
    }

    public String getTax() {
        return String.format("%.2f", tax);
    }

    public String getTotal() {
        return String.format("%.2f", total);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
