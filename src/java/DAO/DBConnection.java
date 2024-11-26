package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    protected Connection connection;

    public DBConnection() {
        try {
            String dbURL = "jdbc:sqlserver://localhost;databaseName=SWP391_Database;user=sa;password=123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        if (connection != null) {
            System.out.println("Connect successfully");
        } else {
            System.out.println("Connect fail");
        }
    }

}