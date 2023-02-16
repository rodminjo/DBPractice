import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class OraInsert {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("부서번호 입력 ?");	String deptno = sc.nextLine();
        System.out.println("부서명 입력 ?");	String dname = sc.nextLine();
        System.out.println("위치 입력 ?");		String loc = sc.nextLine();

        Connection conn = null;
        Statement stmt = null;
        String driver = "oracle.jdbc.driver.OracleDriver";
        // Localhost -> 127.0.0.1: (localhost로 입력해도 됨), port 번호 : 1521, xe(orcl) -> Service ID(SID)
        //String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        // dept Table Insert 를 완성
        // String sql = "Insert into dept values(deptno,'dname','loc')";
        // 'dname' 표시한 것은 문자열로 들어가기 위함 .
        String sql = String.format("Insert into dept values(%s,'%s','%s')",deptno,dname,loc);
        System.out.println("SQL -> " +sql);
        try {
            // 1. JDBC 드라이버 클래스(다운받았던 드라이버) 로딩
            Class.forName(driver);

            // 2. Connection 객체를 생성하여 데이터베이스 연결
            conn = DriverManager.getConnection(url,"scott","tiger");
            // 3. connection 객체의 메서드를 이용하여 stmt(쿼리문) 생성
            stmt = conn.createStatement();
            // 4. 반환받을값이 없다면(insert 등) excuteUpdate(), 있다면(select 등) executeQuery()
            // 수정된 row 갯수를 리턴함
            int result = stmt.executeUpdate(sql);

            if(result >0) {
                System.out.println("입력 성공!! ");
            }else {
                System.out.println("입력 실패 ㅠ.ㅠ ");
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
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

