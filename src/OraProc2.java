import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class OraProc2 {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("정보가 궁금한 사번 ");
        int empno = sc.nextInt();

        Connection conn = null;
        CallableStatement cs = null;

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        // in ->empno 	out -> ename, sal
        String sql = "{call emp_info2(?, ?, ?)}";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "scott","tiger");
            cs = conn.prepareCall(sql);
            cs.setInt(1, empno);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);

            // 파라미터 out 으로 결과를 받는 경우 파라미터명으로 출력이 안됨.
            // 지원안된다고 2013년에 답함. 최신 자바의 경우 지원하는것 같음. 인덱스 사용

            cs.executeUpdate();
            String 	ename = 	cs.getString(2);
            int 	sal = 		cs.getInt(3);
            System.out.println("empno : "+empno);
            System.out.println("ename : "+ename);
            System.out.println("sal : "+sal);



        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println(" 클래스 오류 ");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println(" sql 오류 ");
        }finally {
            if(conn != null) {
                conn.close();
            }
            if(cs != null) {
                cs.close();
            }
        }
        sc.close();
    }
}
