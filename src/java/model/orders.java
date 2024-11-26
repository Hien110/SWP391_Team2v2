package model;

/**
 *
 * @author TranHoangAnh
 */
public class orders {

    private int orderid;
    private int productid;
    private String productname;
    private String image;
    private String shopname;
    private int userid;
    private int quantity;
    private String nameofreceiver;
    private String phonenumber;
    private String address;
    private String reasoncancel;
    private String statusorder;
    private int totalprice;
    private String dateorder;
    private int promotionid;
    private String color;
    private String size;
    private String paymentmethods;
    private String promotionname;

    public orders() {
    }

    public orders(int orderid, int productid, String productname, String image, String shopname, int userid, int quantity, String nameofreceiver, String phonenumber, String address, String reasoncancel, String statusorder, int totalprice, String dateorder, int promotionid, String color, String size, String paymentmethods, String promotinname) {
        this.orderid = orderid;
        this.productid = productid;
        this.productname = productname;
        this.image = image;
        this.shopname = shopname;
        this.userid = userid;
        this.quantity = quantity;
        this.nameofreceiver = nameofreceiver;
        this.phonenumber = phonenumber;
        this.address = address;
        this.reasoncancel = reasoncancel;
        this.statusorder = statusorder;
        this.totalprice = totalprice;
        this.dateorder = dateorder;
        this.promotionid = promotionid;
        this.color = color;
        this.size = size;
        this.paymentmethods = paymentmethods;
        this.promotionname = promotinname;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNameofreceiver() {
        return nameofreceiver;
    }

    public void setNameofreceiver(String nameofreceiver) {
        this.nameofreceiver = nameofreceiver;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReasoncancel() {
        return reasoncancel;
    }

    public void setReasoncancel(String reasoncancel) {
        this.reasoncancel = reasoncancel;
    }

    public String getStatusorder() {
        return statusorder;
    }

    public void setStatusorder(String statusorder) {
        this.statusorder = statusorder;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getDateorder() {
        return dateorder;
    }

    public void setDateorder(String dateorder) {
        this.dateorder = dateorder;
    }

    public int getPromotionid() {
        return promotionid;
    }

    public void setPromotionid(int promotionid) {
        this.promotionid = promotionid;
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

    public String getPaymentmethods() {
        return paymentmethods;
    }

    public void setPaymentmethods(String paymentmethods) {
        this.paymentmethods = paymentmethods;
    }

    public String getPromotinname() {
        return promotionname;
    }

    public void setPromotinname(String promotinname) {
        this.promotionname = promotinname;
    }

    public String getPromotionname() {
        return promotionname;
    }

    public void setPromotionname(String promotionname) {
        this.promotionname = promotionname;
    }
    
    
    
    @Override
    public String toString() {
        return "orders{" + "orderid=" + orderid + ", productid=" + productid + ", productname=" + productname + ", image=" + image + ", shopname=" + shopname + ", userid=" + userid + ", quantity=" + quantity + ", nameofreceiver=" + nameofreceiver + ", phonenumber=" + phonenumber + ", address=" + address + ", reasoncancel=" + reasoncancel + ", statusorder=" + statusorder + ", totalprice=" + totalprice + ", dateorder=" + dateorder + ", promotionid=" + promotionid + ", color=" + color + ", size=" + size + ", paymentmethods=" + paymentmethods + ", promotionname=" + promotionname + '}';
    }


}
