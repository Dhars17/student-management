package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            // Ensure the MySQL driver is available (will be a no-op on newer drivers that auto-register)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ignored) {}

            String url = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = "WJ28@krhps";

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
