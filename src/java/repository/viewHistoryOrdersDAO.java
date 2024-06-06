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
                + "    p.productname, \n"
                + "    ip.image, \n"
                + "    o.quantity, \n"
                + "    o.statusorder, \n"
                + "    o.totalprice, \n"
                + "    o.dateorder\n"
                + "FROM \n"
                + "    ORDERS o\n"
                + "JOIN \n"
                + "    PRODUCTS p ON o.productid = p.productid\n"
                + "JOIN \n"
                + "    IMAGEPRODUCTS ip ON p.productid = ip.productid\n"
                + "WHERE \n"
                + "    o.userid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new orders(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static void main(String[] args) {
        viewHistoryOrdersDAO s = new viewHistoryOrdersDAO();
        List<orders> list = s.getAllOrderByUID(1);
        System.out.println(list);
    }
}
