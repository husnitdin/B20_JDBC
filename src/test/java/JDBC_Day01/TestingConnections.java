package JDBC_Day01;
import java.sql.*;

public class TestingConnections {
    public static void main(String[] args) throws SQLException {
        String connectionStr = "jdbc:oracle:thin:@54.172.215.237:1521:XE";
        String username = "hr" ;
        String password = "hr" ;

        Connection conn = DriverManager.getConnection(connectionStr,username,password) ;
        Statement stmnt = conn.createStatement();
        ResultSet rs   =   stmnt.executeQuery("SELECT * FROM REGIONS") ;

        rs.next();
        System.out.println("first column value using index: --> " +  rs.getString(1)   );
        System.out.println("second column value using index: --> " +  rs.getString(2)   );

        // cleaning up  the Connection, statement and ResultSet after using
        rs.close();         // close ResultSet
        stmnt.close();      // close statement
        conn.close();       // close connection
    }
}
