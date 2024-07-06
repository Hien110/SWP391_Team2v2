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
public class viewEvaluateDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<evaluate> getAllOrderByUID(int productid) {
        List<evaluate> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    U.username,\n"
                + "    U.imgavt,\n"
                + "    E.comment,\n"
                + "    E.star\n"
                + "FROM \n"
                + "    USERS U\n"
                + "INNER JOIN \n"
                + "    EVALUATE E ON U.userid = E.userid\n"
                + "WHERE \n"
                + "    E.productid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new evaluate(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int countEvaluate(int shopid) {
        int evaCount = 0;
        String query = "SELECT COUNT(*) AS TotalEvaluate\n"
                + "FROM [SWP391_DBv6].[dbo].[EVALUATE] e\n"
                + "JOIN [SWP391_DBv6].[dbo].[PRODUCTS] p ON e.productid = p.productid\n"
                + "WHERE p.shopid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, shopid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    evaCount = rs.getInt("TotalEvaluate");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaCount;
    }

    public static void main(String[] args) {
        viewEvaluateDAO s = new viewEvaluateDAO();
        List<evaluate> list = new ArrayList<>();
        list = s.getAllOrderByUID(1);
        for (evaluate eval : list) {
            System.out.println(eval.getUserName());
        }
    }
}
