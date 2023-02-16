
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OraDelete {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 부서번호 입력 ?");		String deptno = sc.nextLine();

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String driver = "oracle.jdbc.driver.OracleDriver";

        String sql = String.format("DELETE DEPT WHERE DEPTNO = %s",deptno);
        System.out.println("SQL ->" +sql);

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);

            if(result >0) {
                System.out.println("삭제 성공");
                System.out.println("삭제된 행: "+result+"개");
            }else {
                System.out.println("삭제 실패..");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(conn != null) conn.close();
            if(stmt != null) stmt.close();
        }
        sc.close();
    }

}

