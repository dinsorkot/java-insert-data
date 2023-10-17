import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cn_db_mysql {

    private Connection con;

    public Connection getConnection() {
        try {
            // สร้างการเชื่อมต่อฐานข้อมูล โดยกำหนด url ของฐานข้อมูล ชื่อผู้ใช้ (username) และรหัสผ่าน (password)
            String url = "jdbc:mysql://localhost:3306/university"; // เปลี่ยน "your_database_url" เป็น url ของฐานข้อมูลที่ต้องการเชื่อมต่อ
            String username = "root"; // เปลี่ยน "username" เป็นชื่อผู้ใช้ฐานข้อมูล
            String password = "1234"; // เปลี่ยน "password" เป็นรหัสผ่านฐานข้อมูล
            return con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    // ให้แน่ใจว่าจะปิดการเชื่อมต่อฐานข้อมูลอย่างถูกต้องเมื่อใช้งานเสร็จสิ้น
    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}