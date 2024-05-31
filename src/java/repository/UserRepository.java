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
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserRepository extends DBConnection {

    public ArrayList<User> getAll() {
        ArrayList<User> user = new ArrayList<>();
        String stm1 = "select * from USERS";
        ResultSet re;
        try {
            PreparedStatement p1 = connection.prepareStatement(stm1);

            re = p1.executeQuery();
            while (re.next()) {
                user.add(new User(re.getInt(1),
                        re.getString(2),
                        re.getString(3),
                        re.getString(4),
                        re.getInt(5),
                        re.getString(6),
                        re.getString(7),
                        re.getBoolean(8)));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return user;
    }

    public User getAccountByEmail(String username) {
        String sql = "select * from USERS where email=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User();
                c.setUserid(rs.getInt(1));
                c.setUsername(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setPassword(rs.getString(4));
                c.setRoleid(rs.getInt(5));
                c.setImgavt(rs.getString(5));
                c.setImgbg(rs.getString(6));
                c.setBanstatus(rs.getBoolean(7));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getAccountByUsername(String username) {
        String sql = "select * from USERS where username=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User();
                c.setUserid(rs.getInt(1));
                c.setUsername(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setPassword(rs.getString(4));
                c.setRoleid(rs.getInt(5));
                c.setImgavt(rs.getString(5));
                c.setImgbg(rs.getString(6));
                c.setBanstatus(rs.getBoolean(7));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void newUser(User c) {
        String sql = "insert into USERS (username, email, password, roleid, banstatus) VALUES (?, ?, ?, 3, 0);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getUsername());
            st.setString(2, c.getEmail());
            st.setString(3, c.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void resetPassword(User c){
        String sql = "update USERS set password = ? where email = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, c.getPassword());
            st.setString(2, c.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        UserRepository list = new UserRepository();
        System.out.println(list.getAccountByEmail("minhhien30201@gmail.com"));
        User c = new User("Linhdeptrai", "caohoanglinh203@gmail.com", "qwer1234");
        list.newUser(c);
    }
}
