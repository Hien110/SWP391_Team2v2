package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.orders;

/**
 *
 * @author TranHoangAnh
 */
public class orderTrackingDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<orders> getAllOrderByUID(int userid) {
        List<orders> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    O.orderid AS orderid,\n"
                + "    P.productname AS productname,\n"
                + "    IP.image AS image,\n"
                + "    O.quantity AS quantity,\n"
                + "    RI.address AS address,\n"
                + "    O.statusorder AS statusorder,\n"
                + "    O.totalprice AS totalprice,\n"
                + "    O.dateorder AS dateorder,\n"
                + "    O.color AS color,\n"
                + "    O.size AS size,\n"
                + "    O.paymentmethods AS paymentmethods,\n"
                + "    S.shopname AS shopname\n"
                + "FROM \n"
                + "    ORDERS O\n"
                + "JOIN \n"
                + "    PRODUCTS P ON O.productid = P.productid\n"
                + "JOIN \n"
                + "    IMAGEPRODUCTS IP ON P.productid = IP.productid\n"
                + "JOIN \n"
                + "    RECEIVERINFO RI ON O.receiverinfoid = RI.receiverinfoid\n"
                + "JOIN \n"
                + "    SHOPS S ON P.shopid = S.shopid\n"
                + "WHERE \n"
                + "    O.userid = ?\n"
                + "    AND O.statusorder != 'Delivered';";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new orders(rs.getInt(1),
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
                        rs.getString(12)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        orderTrackingDAO s = new orderTrackingDAO();
        List<orders> list = s.getAllOrderByUID(1);
        System.out.println(list);
    }
}
