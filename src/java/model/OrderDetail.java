package model;
// Linh
public class OrderDetail {

    private String productName;
    private Float subtotal;
    private Float shipping;
    private Float total;
    private Double surplus;

//    public OrderDetail(String productName, String subtotal, String shipping, String total) {
//        this.productName = productName;
//        this.subtotal = Float.parseFloat(subtotal);
//        this.shipping = Float.parseFloat(shipping);
//        this.total = Float.parseFloat(total);
//    }
    
    public OrderDetail (Double surplus){
        this.surplus = surplus;
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


    public String getTotal() {
        return String.format("%.2f", total);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }
    
    
}
