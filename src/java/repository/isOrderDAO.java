package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.evaluate;
import model.orders;

/**
 *
 * @author TranHoangAnh
 */
public class isOrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<orders> isOrder(int productid, int userid) {
        List<orders> list = new ArrayList<>();
        String query = "SELECT \n" +
"                    O.orderid,\n" +
"                    O.productid,\n" +
"                    P.productname,\n" +
"                    I.image,\n" +
"                    S.shopname,\n" +
"                    O.userid,\n" +
"                    O.quantity,\n" +
"                    O.nameofreceiver,\n" +
"                    O.phonenumber,\n" +
"                    O.address,\n" +
"                    O.reasoncancel,\n" +
"                    O.statusorder,\n" +
"                    O.totalprice,\n" +
"                    O.dateorder,\n" +
"                    O.promotionid,\n" +
"                    O.color,\n" +
"                    O.size,\n" +
"                   O.paymentmethods,\n" +
"                    PR.promotionname\n" +
"                FROM \n" +
"                    ORDERS O\n" +
"                JOIN \n" +
"                    PRODUCTS P ON O.productid = P.productid\n" +
"                JOIN \n" +
"                    IMAGEPRODUCTS I ON P.productid = I.productid\n" +
"                JOIN \n" +
"                    SHOPS S ON P.shopid = S.shopid\n" +
"                LEFT JOIN \n" +
"                    PROMOTION PR ON O.promotionid = PR.promotionid\n" +
"                WHERE \n" +
"                    O.userid = ? \n" +
"                    AND O.statusorder = N'Đã giao'\n" +
"                    AND O.productid=?";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userid);
            ps.setInt(2, productid);
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

    public static void main(String[] args) {
        isOrderDAO i = new isOrderDAO();
        List<orders> l = i.isOrder(1, 2);
        System.out.println(l);
    }
}
