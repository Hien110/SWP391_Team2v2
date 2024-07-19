package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.orders;

/**
 *
 * @author TranHoangAnh
 */
public class productShopListDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProductByShopID(int shopid) {
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
                + "    p.shopid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        productShopListDAO s = new productShopListDAO();
        List<Product> list = s.getAllProductByShopID(1);
        System.out.println(list);
    }
}
