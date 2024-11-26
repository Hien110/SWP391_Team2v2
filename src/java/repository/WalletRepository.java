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
import model.walletHeartsteal;

/**
 *
 * @author ADMIN
 */
public class WalletRepository extends DBConnection {

    public ArrayList<walletHeartsteal> getAll() {
        ArrayList<walletHeartsteal> wallet = new ArrayList<>();
        String stm1 = "select * from WALLET";
        ResultSet re;
        try {
            PreparedStatement p1 = connection.prepareStatement(stm1);

            re = p1.executeQuery();
            while (re.next()) {
                wallet.add(new walletHeartsteal(re.getInt(1),
                        re.getInt(2),
                        re.getInt(3)));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return wallet;
    }

    public void newHeartstealPay(walletHeartsteal w) {
        String sql = "insert into WALLET values (?,0)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, w.getUserid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void paymentHeartstealPay(walletHeartsteal w) {
        String sql = "  update WALLET set surplus =surplus+ ? where userid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(1, w.getSurplus());
            st.setInt(2, w.getUserid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void withdrawHeartstealPay(walletHeartsteal w) {
        String sql = "  update WALLET set surplus =surplus- ? where userid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(1, w.getSurplus());
            st.setInt(2, w.getUserid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public walletHeartsteal getWalletByUserid(int userid) {
        String sql = "select * from WALLET where userid=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, userid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                walletHeartsteal c = new walletHeartsteal();
                c.setWalletid(rs.getInt(1));
                c.setUserid(rs.getInt(2));
                c.setSurplus(rs.getFloat(3));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        WalletRepository cb = new WalletRepository();
        walletHeartsteal Wallet = cb.getWalletByUserid(2);
        System.out.println(Wallet);
    }
}
