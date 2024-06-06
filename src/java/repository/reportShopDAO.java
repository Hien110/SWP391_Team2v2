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
public class reportShopDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertReportShop(int userID, int shopID, String reasonReport) {
        String query = "INSERT INTO REPORTSHOP (userid, shopid, reasonreport) \n" +
"VALUES \n" +
"(?, ?, ?);";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, shopID);
            ps.setString(3, reasonReport);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
