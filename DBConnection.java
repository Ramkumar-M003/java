package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/patientdb",
                        "root",
                        "rkvk18003" // 🔹 change this to your MySQL password
                );
                System.out.println("✅ Database Connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
