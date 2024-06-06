package model;

/**
 *
 * @author TranHoangAnh
 */
public class wishlist {
    private int shopID;
    private int userID;

    public wishlist() {
    }

    public wishlist(int shopID, int userID) {
        this.shopID = shopID;
        this.userID = userID;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "whishlist{" + "shopID=" + shopID + ", userID=" + userID + '}';
    }
    
    
}
