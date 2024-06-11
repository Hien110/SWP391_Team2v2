/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DAO.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.InfoCustomer;

/**
 *
 * @author ADMIN
 */
public class InfoCustomerRepository extends DBConnection {

    public ArrayList<InfoCustomer> getAll() {
        ArrayList<InfoCustomer> info = new ArrayList<>();
        String stm1 = "select * from RECEIVERINFO";
        ResultSet re;
        try {
            PreparedStatement p1 = connection.prepareStatement(stm1);

            re = p1.executeQuery();
            while (re.next()) {
                info.add(new InfoCustomer(re.getInt(1),
                        re.getString(2),
                        re.getString(3),
                        re.getString(4),
                        re.getString(5)));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return info;
    }

     public void newAddress(InfoCustomer c) {
        String sql = "insert into RECEIVERINFO values (?,?,?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCustomerName());
            st.setString(2, c.getPhoneCustomer());
            st.setString(3, c.getAddressCustomer());
            st.setString(4, c.getUserid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     
     
    public static void main(String[] args) {
        InfoCustomerRepository s = new InfoCustomerRepository();
        InfoCustomer c = new InfoCustomer("hello", "01234401", "1241", "1");
        s.newAddress(c);
    }

}
