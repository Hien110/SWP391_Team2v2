package repository;

import model.ProductReport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.DBConnection;

public class ReportedProductDAO {

    private final DBConnection dbConnection;

    public ReportedProductDAO() {
        this.dbConnection = new DBConnection();
    }

    public List<ProductReport> getReportedProducts() throws SQLException {
        List<ProductReport> reportedProducts = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dbConnection.getConnection(); // Lấy kết nối với cơ sở dữ liệu
            if (con != null) {
                String query = "SELECT reportproductid, userid, productid, reasonreport " +
                               "FROM REPORTPRODUCT";
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ProductReport report = new ProductReport(
                        rs.getInt("reportproductid"),
                        rs.getInt("userid"),
                        rs.getInt("productid"),
                        rs.getString("reasonreport")
                    );
                    reportedProducts.add(report);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException("Error fetching reported products: " + ex.getMessage());
        } finally {
            closeResources(con, ps, rs);
        }
        return reportedProducts;
    }

    public void deleteProductShopOwner(int productId) {
        String query
                = "DELETE FROM EVALUATE WHERE productid = ?;\n"
                + "DELETE FROM IMAGEPRODUCTS WHERE productid = ?;\n"
                + "DELETE FROM PRODUCTINFOR WHERE productid = ?;\n"
                + "DELETE FROM CART WHERE productid = ?;\n"
                + "DELETE FROM ORDERS WHERE productid = ?;\n"
                + "DELETE FROM REPORTPRODUCT WHERE productid = ?;\n"
                + "DELETE FROM PRODUCTS WHERE productid = ?;";
        try {
            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, productId);
            ps.setInt(3, productId);
            ps.setInt(4, productId);
            ps.setInt(5, productId);
            ps.setInt(6, productId);
            ps.setInt(7, productId);
            ps.setInt(8, productId);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
    }
    public void deleteProduct(int productId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dbConnection.getConnection();
            if (con != null) {
                String query = "DELETE FROM PRODUCTS WHERE productid = ?"; 
                ps = con.prepareStatement(query);
                ps.setInt(1, productId);
                int rowsDeleted = ps.executeUpdate();
                if (rowsDeleted == 0) {
                    throw new SQLException("Delete product failed, no rows affected.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException("Error deleting product: " + ex.getMessage());
        } finally {
            closeResources(con, ps, null);
        }
    }
    public void deleteReportsByProductId(int productId) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = dbConnection.getConnection();
        if (con != null) {
            String query = "DELETE FROM REPORTPRODUCT WHERE productid = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            ps.executeUpdate();
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReportedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        throw new SQLException("Error deleting reports for product: " + ex.getMessage());
    } finally {
        closeResources(con, ps, null);
    }
}

    private void closeResources(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
