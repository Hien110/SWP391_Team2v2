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
public class cancelOrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void editOrder(int orderID, String reason, int productid) {
        String query = "UPDATE ORDERS\n"
                + "SET statusorder = 'Cancel', reasoncancel = ?\n"
                + "WHERE orderid = ?;"
                + "UPDATE PRODUCTS\n"
                + "SET quantityp = quantityp + 1\n"
                + "WHERE productid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, reason);
            ps.setInt(2, orderID);
            ps.setInt(3, productid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        orderTrackingDAO s = new orderTrackingDAO();
        List<orders> list = s.getAllOrderByUID(1);
        System.out.println(list);
    }
}
