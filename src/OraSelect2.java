import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class OraSelect2 {

    public static void main(String[] args) throws SQLException {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String sql = "SELECT * FROM EMP";
        System.out.println("SQL -> "+sql);
        System.out.println();

        Connection conn = null; 	// db 연결
        Statement stmt = null; 		// sql 문장 수행객체 미리 선언
        ResultSet rs = null;		// sql 문장 select 시 내용을 받아놓는 객체

        System.out.println("사원명단");
        System.out.println("사원코드 \t사원명\t업무\t\t급여\t일자");
        System.out.println("======================================================");

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(rs.next()) {
                do {
                    int empno		=	rs.getInt(1);
                    String ename 	=	rs.getString(2);
                    String job		=	rs.getString(3);
                    int sal 		= 	rs.getInt("SAL");
                    // java.util.date 가져올것
                    Date hireDate 	= 	rs.getDate("HIREDATE");

                    if(job.length() > 7) {
                        System.out.printf("%d\t%s\t%s\t%d\t%TF\n",empno,ename,job,sal,hireDate);
                    }else {
                        System.out.printf("%d\t%s\t%s\t\t%d\t%TF\n",empno,ename,job,sal,hireDate);
                    }
                } while(rs.next());
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

    }


}
