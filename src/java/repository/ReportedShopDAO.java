package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ShopReport;
import DAO.DBConnection;

public class ReportedShopDAO {

    public List<ShopReport> getAllReportedShops() {
        List<ShopReport> reportedShops = new ArrayList<>();
        DBConnection dbConnection = new DBConnection();
        Connection con = dbConnection.getConnection();

        if (con != null) {
            try {
                String query = "SELECT shopid, reasonreport, userid FROM REPORTSHOP";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ShopReport report = new ShopReport();
                    report.setShopId(rs.getInt("shopid"));
                    report.setReason(rs.getString("reasonreport"));
                    report.setReportedBy(rs.getInt("userid"));
                    reportedShops.add(report);
                }
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed.");
        }
        return reportedShops;
    }

    public void insertReportShop(int userid, int shopid, String reason) {
        DBConnection dbConnection = new DBConnection();
        Connection con = dbConnection.getConnection();

        if (con != null) {
            try {
                String query = "INSERT INTO REPORTSHOP (userid, shopid, reasonreport) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, userid);
                ps.setInt(2, shopid);
                ps.setString(3, reason);
                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed.");
        }
    }



    // Các phương thức khác như đã cung cấp trước đó

    public void deleteReport(int shopId, int userId) {
        DBConnection dbConnection = new DBConnection();
        Connection con = dbConnection.getConnection();

        if (con != null) {
            try {
                String query = "DELETE FROM REPORTSHOP WHERE shopid = ? AND userid = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, shopId);
                ps.setInt(2, userId);
                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed.");
        }
    }
   }


           
