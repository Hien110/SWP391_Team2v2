package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Shop;


/**
 *
 * @author TranHoangAnh
 */
public class shopDetailDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Shop getShopByID(int shopID) {
        Shop shop = new Shop();
        String query = "SELECT \n"
                + "    S.shopid,\n"
                + "    S.shopname,\n"
                + "    S.address,\n"
                + "    S.desshop,\n"
                + "    S.userid,\n"
                + "    (SELECT COUNT(*) FROM PRODUCTS P WHERE P.shopid = S.shopid) AS totalProduct,\n"
                + "    (SELECT COUNT(*) FROM WISHLIST W WHERE W.shopid = S.shopid) AS totalFollower,\n"
                + "    U.imgavt\n"
                + "FROM \n"
                + "    SHOPS S\n"
                + "JOIN \n"
                + "    USERS U ON S.userid = U.userid\n"
                + "WHERE \n"
                + "    S.shopid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopID);
            rs = ps.executeQuery();
            while (rs.next()) {
                shop = new Shop(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
        }
        return shop;
    }

    public static void main(String[] args) {
        shopDetailDAO s = new shopDetailDAO();
        Shop shop = new Shop();
        shop = s.getShopByID(1);
        System.out.println(shop);
    }
}
