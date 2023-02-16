import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OraSelect1 {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("부서코드를 입력하세요");
        int deptno = sc.nextInt();
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String sql = "SELECT DNAME, LOC FROM DEPT WHERE DEPTNO = "+ deptno;

        Connection conn = null; 	// db 연결
        Statement stmt = null; 		// sql 문장 수행객체 미리 선언
        ResultSet rs = null;		// sql 문장 select 시 내용을 받아놓는 객체

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(rs.next()) {
//				String dname = rs.getString("dname");
//				String loc = rs.getString("loc");
                // 번호로도 줄수 있음
                String dname = rs.getString(1);
                String loc = rs.getString(2);

                System.out.println("부서번호	: "+deptno);
                System.out.println("부서명  	: "+dname);
                System.out.println("부서위치 	: "+loc);
            }else {
                System.out.println("자료가 없습니다.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if(conn != null) conn.close();
            if(stmt != null) stmt.close();
            if(rs != null) rs.close();
        }
        sc.close();


    }

}
