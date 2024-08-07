/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DAO.DBConnection;
import com.paypal.api.payments.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.InfoCustomer;
import model.orders;

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
                        re.getInt(5)));
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
            st.setInt(4, c.getUserid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<InfoCustomer> getInfoByUserid(int userid) {
        String sql = "SELECT * FROM RECEIVERINFO WHERE userid=?";
        List<InfoCustomer> customerList = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                InfoCustomer c = new InfoCustomer();
                c.setCustomerid(rs.getInt(1));
                c.setCustomerName(rs.getString(2));
                c.setPhoneCustomer(rs.getString(3));
                c.setAddressCustomer(rs.getString(4));
                c.setUserid(rs.getInt(5));
                customerList.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return customerList;
    }

    public InfoCustomer getInfoByCusid(String cusid) {
        String sql = "SELECT * FROM RECEIVERINFO WHERE receiverinfoid=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, cusid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                InfoCustomer c = new InfoCustomer();
                c.setCustomerid(rs.getInt(1));
                c.setCustomerName(rs.getString(2));
                c.setPhoneCustomer(rs.getString(3));
                c.setAddressCustomer(rs.getString(4));
                c.setUserid(rs.getInt(5));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void deleteInfoReciver(String receiverid) {
        String sql = "delete from RECEIVERINFO where receiverinfoid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, receiverid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateInforReceiver(InfoCustomer in) {
        String sql = "update RECEIVERINFO set nameofreceiver = ?, phonenumber = ?, address =? where receiverinfoid =? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, in.getCustomerName());
            st.setString(2, in.getPhoneCustomer());
            st.setString(3, in.getAddressCustomer());
            st.setInt(4, in.getCustomerid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        InfoCustomerRepository s = new InfoCustomerRepository();
    }

}
