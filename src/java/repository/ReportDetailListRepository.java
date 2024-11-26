/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import DAO.DBConnection;
import model.reportDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ReportDetailListRepository {
      Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
            public List<reportDetail> getAllReportProduct() {
        List<reportDetail> list = new ArrayList<>();
        String query = "SELECT SHOPS.shopname, SHOPS.address, REPORTPRODUCT.reasonreport, PRODUCTS.productid, USERS.fullname, REPORTPRODUCT.reportproductid FROM REPORTPRODUCT " +
"                INNER JOIN SHOPS ON REPORTPRODUCT.userid = SHOPS.userid" +
"                INNER JOIN PRODUCTS on REPORTPRODUCT.productid =PRODUCTS.productid" +
"                INNER JOIN USERS on REPORTPRODUCT.userid = USERS.userid";
        
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new reportDetail(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6)
            ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    private void closeConnections() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ReportDetailListRepository rd = new ReportDetailListRepository();
        List<reportDetail> list = rd.getAllReportProduct();
        System.out.println(list);
    }
}
