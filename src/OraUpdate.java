import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OraUpdate {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 부서번호 입력 ?");		String deptno = sc.nextLine();
        System.out.println("부서명 입력 ?");		String dname = sc.nextLine();
        System.out.println("위치 입력 ?");		String loc = sc.nextLine();

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection conn = null;
        Statement stmt = null;
        String sql = String.format("Update dept set dname = '%s',loc = '%s' where deptno = %s",dname,loc,deptno);
        System.out.println("SQL -> "+sql);
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt= conn.createStatement();
            int result = stmt.executeUpdate(sql);
            System.out.println(result);

            if(result >0) {
                System.out.println("입력 성공!! ");
            }else {
                System.out.println("입력 실패 ㅠ.ㅠ ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        }
        sc.close();

    }

}
