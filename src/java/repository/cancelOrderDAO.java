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

    public void editOrder(int orderID) {
        String query = "UPDATE ORDERS\n"
                + "SET statusorder = 'Cancel'\n"
                + "WHERE orderid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderID);
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
