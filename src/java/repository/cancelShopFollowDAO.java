package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TranHoangAnh
 */
public class cancelShopFollowDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void cancelFollowShop(int userID, int shopID) {
        String query = "DELETE FROM WISHLIST \n"
                + "WHERE userid = ? \n"
                + "  AND shopid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, shopID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        cancelShopFollowDAO c = new cancelShopFollowDAO();
        c.cancelFollowShop(2, 1);
    }
}
