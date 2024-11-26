package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TranHoangAnh
 */
public class submitReviewDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertEvaluate(int userID, int productID, String comment, int star) {
        String query = "insert into EVALUATE\n"
                + "values\n"
                + "(?,?,?,?);"
                + "UPDATE PRODUCTS\n"
                + "SET avagerstar = E.avagerstar\n"
                + "FROM (\n"
                + "    SELECT productid, CAST(AVG(CAST(star AS FLOAT)) AS FLOAT) AS avagerstar\n"
                + "    FROM EVALUATE\n"
                + "    GROUP BY productid\n"
                + ") E\n"
                + "WHERE PRODUCTS.productid = E.productid;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, userID);
            ps.setString(3, comment);
            ps.setInt(4, star);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        submitReviewDAO s = new submitReviewDAO();
        s.insertEvaluate(1, 1, "abcv", 5);
    }
}
