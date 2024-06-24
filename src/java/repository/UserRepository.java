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
                        re.getBoolean(5),
                        re.getString(6),
                        re.getString(7),
                        re.getString(8),
                        re.getInt(9),
                        re.getString(10),
                        re.getString(11),
                        re.getString(12),
                        re.getString(13),
                        re.getBoolean(14)));
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
                c.setFullname(rs.getString(3));
                c.setPhonenumber(rs.getString(4));
                c.setGender(rs.getBoolean(5));
                c.setDob(rs.getString(6));
                c.setEmail(rs.getString(7));
                c.setPassword(rs.getString(8));
                c.setRoleid(rs.getInt(9));
                c.setImgavt(rs.getString(10));
                c.setBankname(rs.getString(11));
                c.setBanknumber(rs.getString(12));
                c.setEmailpaypal(rs.getString(13));
                c.setBanstatus(rs.getBoolean(14));
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
            c.setFullname(rs.getString(3));
            c.setPhonenumber(rs.getString(4));
            Boolean gender = rs.getBoolean(5);
            if (rs.wasNull()) {
                gender = null;
            }
            c.setGender(gender);
            c.setDob(rs.getString(6));
            c.setEmail(rs.getString(7));
            c.setPassword(rs.getString(8));
            c.setRoleid(rs.getInt(9));
            c.setImgavt(rs.getString(10));
            c.setBankname(rs.getString(11));
            c.setBanknumber(rs.getString(12));
            c.setEmailpaypal(rs.getString(13));
            c.setBanstatus(rs.getBoolean(14));
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

    public void resetPassword(User c) {
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

    public void updatePassword(User c) {
        String sql = "update USERS set password=? where username=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, c.getPassword());
            st.setString(2, c.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateAvata(String username, String imgavt) {
        String sql = "update USERS set imgavt=? where username=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, imgavt);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateRoleWaiting(int userid) {
        String sql = "update USERS set roleid=4 where userid=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, userid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateProfileUser(User c) {
        if (c.getGender() != null) {
            String sql = "update USERS set fullname=?, phonenumber=?, gender=?, dob=? where userid = ?";
            try {
                PreparedStatement st = connection.prepareCall(sql);
                st.setString(1, c.getFullname());
                st.setString(2, c.getPhonenumber());
                st.setBoolean(3, c.getGender());
                st.setString(4, c.getDob());
                st.setInt(5, c.getUserid());
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            if (c.getGender() == null) {
                String sql = "update USERS set fullname=?, phonenumber=?, gender=null, dob=? where userid = ?";
                try {
                    PreparedStatement st = connection.prepareCall(sql);
                    st.setString(1, c.getFullname());
                    st.setString(2, c.getPhonenumber());
                    st.setString(3, c.getDob());
                    st.setInt(4, c.getUserid());
                    st.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        UserRepository list = new UserRepository();
        User c = new User(1, "Nguyen Minh Hiển", "0356555425", true, "14-6-2003");
        list.updateProfileUser(c);
    }
}
