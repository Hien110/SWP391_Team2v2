package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author TranHoangAnh
 */
public class searchDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProductBySearch(String search) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    p.productid, \n"
                + "    p.productname, \n"
                + "    p.price, \n"
                + "    i.image \n"
                + "FROM \n"
                + "    PRODUCTS p\n"
                + "JOIN \n"
                + "    (SELECT \n"
                + "         productid, \n"
                + "         MIN(imageid) AS min_imageid\n"
                + "     FROM \n"
                + "         IMAGEPRODUCTS \n"
                + "     GROUP BY \n"
                + "         productid\n"
                + "    ) FirstImage ON p.productid = FirstImage.productid\n"
                + "JOIN \n"
                + "    IMAGEPRODUCTS i ON FirstImage.min_imageid = i.imageid\n"
                + "WHERE \n"
                + "    p.productname LIKE ?";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return list;
    }

    public static void main(String[] args) {
        searchDAO s = new searchDAO();
        List<Product> list = s.getAllProductBySearch("áo");
        System.out.println(list);
    }
}
