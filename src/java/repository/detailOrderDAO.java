package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.orders;

/**
 *
 * @author TranHoangAnh
 */
public class detailOrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public orders getDetailOrder(int userid) {
        orders ORDER = new orders();
        String query = "SELECT \n"
                + "    O.orderid,\n"
                + "    P.productname,\n"
                + "    I.image,\n"
                + "    O.quantity,\n"
                + "    R.address,\n"
                + "    O.statusorder,\n"
                + "    O.totalprice,\n"
                + "    O.dateorder,\n"
                + "    O.color,\n"
                + "    O.size,\n"
                + "    O.paymentmethods,\n"
                + "    S.shopname\n"
                + "FROM \n"
                + "    ORDERS O\n"
                + "JOIN \n"
                + "    PRODUCTS P ON O.productid = P.productid\n"
                + "JOIN \n"
                + "    IMAGEPRODUCTS I ON P.productid = I.productid\n"
                + "JOIN \n"
                + "    RECEIVERINFO R ON O.receiverinfoid = R.receiverinfoid\n"
                + "JOIN \n"
                + "    SHOPS S ON P.shopid = S.shopid\n"
                + "WHERE \n"
                + "    O.orderid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                ORDER = new orders(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));
            }
        } catch (Exception e) {
        }
        return ORDER;
    }

    public static void main(String[] args) {
        detailOrderDAO s = new detailOrderDAO();
        orders a = s.getDetailOrder(1);
        System.out.println(a);
    }
}
