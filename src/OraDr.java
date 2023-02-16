import java.sql.Connection;
import java.sql.DriverManager;

public class OraDr {
    public static void main(String[] args) {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "scott", "tiger");
            System.out.println("Start");
            if(conn != null) {
                System.out.println("Success 연결 성공");
            } else{
                System.out.println("FAIL ");
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
