package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.orders;

/**
 *
 * @author TranHoangAnh
 */
public class submitDeliveredDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void editOrder(int orderID) {
        String query = "UPDATE ORDERS\n"
                + "SET statusorder = N'Đã giao'\n"
                + "WHERE orderid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
