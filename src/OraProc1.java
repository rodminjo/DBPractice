import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class OraProc1 {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("입력할 oracle 부서코드 ?");
        String deptno = sc.nextLine();
        System.out.println("입력할 oracle 부서명  ?");
        String dname = sc.nextLine();
        System.out.println("입력할 oracle 근무지  ?");
        String loc = sc.nextLine();

        Connection conn = null;
        // procedure 호출 객체
        CallableStatement cs = null;

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

        String sql = "{call dept_Insert(?,?,?)}";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "scott", "tiger");
            cs = conn.prepareCall(sql);
            cs.setString(1, deptno);
            cs.setString(2, dname);
            cs.setString(3, loc);
            // boolean 형식으로 받을수도 있음. execute 는 resultSet형태로 돌려받으면 true, update 혹은 실패했으면 false 출력
            // boolean resultBool = cs.execute();
            int result = cs.executeUpdate();

            if (result > 0) {
                System.out.println("입력 성공");
            } else {
                System.out.println("입력 실패 ㅠ.ㅠ");
            }


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        sc.close();
    }
}