package Config;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() {
    try {
        if (conn == null || conn.isClosed()) { 
            String url = "jdbc:mysql://localhost:3306/perpussmakhadijah";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(url, user, pass);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
    }
    return conn;
}
     
}