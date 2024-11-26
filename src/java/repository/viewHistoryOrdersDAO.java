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
public class viewHistoryOrdersDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<orders> getAllOrderByUID(int userid) {
        List<orders> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    O.orderid,\n"
                + "    O.productid,\n"
                + "    P.productname,\n"
                + "    I.image,\n"
                + "    S.shopname,\n"
                + "    O.userid,\n"
                + "    O.quantity,\n"
                + "    O.nameofreceiver,\n"
                + "    O.phonenumber,\n"
                + "    O.address,\n"
                + "    O.reasoncancel,\n"
                + "    O.statusorder,\n"
                + "    O.totalprice,\n"
                + "    O.dateorder,\n"
                + "    O.promotionid,\n"
                + "    O.color,\n"
                + "    O.size,\n"
                + "    O.paymentmethods,\n"
                + "    PR.promotionname\n"
                + "FROM \n"
                + "    ORDERS O\n"
                + "JOIN \n"
                + "    PRODUCTS P ON O.productid = P.productid\n"
                + "JOIN \n"
                + "    (SELECT \n"
                + "         productid, \n"
                + "         MIN(imageid) AS min_imageid\n"
                + "     FROM \n"
                + "         IMAGEPRODUCTS \n"
                + "     GROUP BY \n"
                + "         productid\n"
                + "    ) FirstImage ON P.productid = FirstImage.productid\n"
                + "JOIN \n"
                + "    IMAGEPRODUCTS I ON FirstImage.min_imageid = I.imageid\n"
                + "JOIN \n"
                + "    SHOPS S ON P.shopid = S.shopid\n"
                + "LEFT JOIN \n"
                + "    PROMOTION PR ON O.promotionid = PR.promotionid\n"
                + "WHERE \n"
                + "    O.userid = ?\n"
                + "    AND O.statusorder = N'Đã giao';";
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

    public int countDelivered(String productid) {
        int orderCount = 0;

        // Câu lệnh SQL để đếm số lượng đơn hàng đã giao cho sản phẩm có id là productid
        String query = "SELECT COUNT(*) AS order_count "
                + "FROM [SWP391_DBv6].[dbo].[ORDERS] "
                + "WHERE statusorder = N'Đã giao' AND productid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, productid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orderCount = rs.getInt("order_count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderCount;
    }

    public int countDeliveredShop(int shopid) {
        int orderCount = 0;

        // Câu lệnh SQL để đếm số lượng đơn hàng đã giao cho sản phẩm có id là productid
        String query = "SELECT COUNT(*) AS NumberOfOrdersDelivered\n"
                + "FROM [SWP391_DBv6].[dbo].[ORDERS] o\n"
                + "JOIN [SWP391_DBv6].[dbo].[PRODUCTS] p ON o.[productid] = p.[productid]\n"
                + "WHERE p.[shopid] = ?\n"
                + "AND o.[statusorder] = N'Đã giao';";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, shopid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orderCount = rs.getInt("NumberOfOrdersDelivered");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderCount;
    }

    public static void main(String[] args) {
        viewHistoryOrdersDAO s = new viewHistoryOrdersDAO();
        int list = s.countDeliveredShop(11);
        System.out.println(list);
    }
}
