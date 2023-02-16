
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class OraProc3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 ORCALE 사원코드 ? ");
        String empno = sc.nextLine();

        Connection conn = null;
        CallableStatement cs = null;

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

        // Function Call 할때의 Format
        // 사번을 입력하여 -> 급여인상, 변경된 급여 Return
        String sql = "{? = call func_sal(?)}";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,"scott","tiger");
            cs = conn.prepareCall(sql);
            cs.setString(2, empno); //변경할 사번
            cs.registerOutParameter(1, java.sql.Types.INTEGER); // 변경급여
            cs.executeQuery();
            int sal = cs.getInt(1);
            if(sal > 0) {
                System.out.println("수정 성공 ");
                System.out.println("변경사번 : "+empno);
                System.out.println("변경급여 : "+sal);
            } else {
                System.out.println("변경 실패 ㅠ ");
            }


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("에러 발생 ");
            System.out.println(e.getMessage());
        }
    }
}
