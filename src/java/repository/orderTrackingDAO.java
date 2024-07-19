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
                + "                    O.orderid,\n"
                + "                    O.productid,\n"
                + "                    P.productname,\n"
                + "                    I.image,\n"
                + "                    S.shopname,\n"
                + "                    O.userid,\n"
                + "                    O.quantity,\n"
                + "                    O.nameofreceiver,\n"
                + "                    O.phonenumber,\n"
                + "                    O.address,\n"
                + "                    O.reasoncancel,\n"
                + "                    O.statusorder,\n"
                + "                    O.totalprice,\n"
                + "                    O.dateorder,\n"
                + "                    O.promotionid,\n"
                + "                    O.color,\n"
                + "                    O.size,\n"
                + "                    O.paymentmethods,\n"
                + "                    PR.promotionname\n"
                + "                FROM \n"
                + "                    ORDERS O\n"
                + "                JOIN \n"
                + "                    PRODUCTS P ON O.productid = P.productid\n"
                + "                JOIN \n"
                + "                    (SELECT \n"
                + "                         productid, \n"
                + "                         MIN(imageid) AS min_imageid\n"
                + "                     FROM \n"
                + "                         IMAGEPRODUCTS \n"
                + "                     GROUP BY \n"
                + "                         productid\n"
                + "                    ) FirstImage ON P.productid = FirstImage.productid\n"
                + "                JOIN \n"
                + "                    IMAGEPRODUCTS I ON FirstImage.min_imageid = I.imageid\n"
                + "                JOIN \n"
                + "                    SHOPS S ON P.shopid = S.shopid\n"
                + "                LEFT JOIN \n"
                + "                    PROMOTION PR ON O.promotionid = PR.promotionid\n"
                + "                WHERE \n"
                + "                    O.userid = ?; \n"
                + "                   ";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new orders(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getInt(13),
                        rs.getString(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public orders getOrderByOrderId(int orderId) {
        String query = "SELECT * FROM ORDERS WHERE orderid = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        orders order = null; // Assume Order class is defined to hold the result

        try {
            conn = new DBConnection().getConnection(); // Replace with your DB connection logic
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Retrieve data from ResultSet and populate Order object
                order = new orders();
                order.setOrderid(rs.getInt("orderid"));
                order.setProductid(rs.getInt("productid"));
                order.setUserid(rs.getInt("userid"));
                order.setQuantity(rs.getInt("quantity"));
                order.setNameofreceiver(rs.getString("nameofreceiver"));
                order.setPhonenumber(rs.getString("phonenumber"));
                order.setAddress(rs.getString("address"));
                order.setReasoncancel(rs.getString("reasoncancel"));
                order.setStatusorder(rs.getString("statusorder"));
                order.setTotalprice(rs.getInt("totalprice"));
                order.setDateorder(rs.getString("dateorder"));
                order.setPromotionid(rs.getInt("promotionid"));
                order.setColor(rs.getString("color"));
                order.setSize(rs.getString("size"));
                order.setPaymentmethods(rs.getString("paymentmethods"));
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            // Close resources in reverse order of opening
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace(); // Handle exception properly in your application
            }
        }

        return order;
    }

    public static void main(String[] args) {
        orderTrackingDAO s = new orderTrackingDAO();
        orders a = s.getOrderByOrderId(2);
        System.out.println(a);
    }
}
