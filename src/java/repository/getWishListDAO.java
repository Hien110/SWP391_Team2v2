package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.evaluate;
import model.orders;
import model.wishlist;

/**
 *
 * @author TranHoangAnh
 */
public class getWishListDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public wishlist getAllOrderByUID(int shopid, int userid) {
        wishlist w = new wishlist();
        String query = "SELECT shopid, userid\n"
                + "FROM WISHLIST\n"
                + "WHERE shopid = ? AND userid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopid);
            ps.setInt(2, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                w = new wishlist(rs.getInt(1), rs.getInt(2));
            }
        } catch (Exception e) {
        }
        return w;
    }

    public static void main(String[] args) {
        getWishListDAO a = new getWishListDAO();
        System.out.println(a.getAllOrderByUID(1, 2));
    }
}
