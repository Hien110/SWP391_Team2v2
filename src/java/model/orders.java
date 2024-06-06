package model;

/**
 *
 * @author TranHoangAnh
 */
public class orders {
    private String productName;
    private String image;
    private int quantity;
    private String statusOrder;
    private int totalPrice;
    private String dateOrder;

    public orders(String productName, String image, int quantity, String statusOrder, int totalPrice, String dateOrder) {
        this.productName = productName;
        this.image = image;
        this.quantity = quantity;
        this.statusOrder = statusOrder;
        this.totalPrice = totalPrice;
        this.dateOrder = dateOrder;
    }

    public orders() {
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

    @Override
    public String toString() {
        return "orders{" + "productName=" + productName + ", image=" + image + ", quantity=" + quantity + ", statusOrder=" + statusOrder + ", totalPrice=" + totalPrice + ", dateOrder=" + dateOrder + '}';
    }
    
    
}
