
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
     public  Connection conn;
     public Statement stmt;
    Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "");
            stmt = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}