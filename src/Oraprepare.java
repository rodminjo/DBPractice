import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// PreparedStatement : 사용의 주 목적은 보안. 스니핑을 막기 위함, 부 목적은 가독성

public class Oraprepare {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("입력할 oracle 부서코드 ?");		String deptno = sc.nextLine();
        System.out.println("입력할 oracle 부서명  ?");		String dname = sc.nextLine();
        System.out.println("입력할 oracle 근무지  ?");		String loc = sc.nextLine();

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO DEPT VALUES(?, ?, ?)" ;
        System.out.println("SQL -> "+sql);

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,"scott","tiger");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deptno);
            pstmt.setString(2, dname);
            pstmt.setString(3, loc);

            int result = pstmt.executeUpdate();
            if(result>0) {
                System.out.println("PreparedStatement 입력 성공 ");
            }else {
                System.out.println("입력 실패 ㅠ.ㅠ ");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(pstmt != null) {
                pstmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        }
        sc.close();

    }
}