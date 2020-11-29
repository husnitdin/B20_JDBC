package JDBC_Day02;
import JDBC_Utitlity.DB_Utility;
import java.sql.*;

public class DisplayingAllData {
        public static void main(String[] args) throws SQLException {

            DB_Utility.createConnection();
            ResultSet rs   =   DB_Utility.runQuery("SELECT * FROM Employees") ;

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();                // retrieves column count

            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnLabel(i) + "\t");    // retrieves column name
            }

            System.out.println("\n-----------------------------");

            rs.beforeFirst();
            while ( rs.next() ) {
                for (int i = 1; i <= columnCount; i++) {
                    // System.out.print(rs.getString(i) + "\t");
                    System.out.printf( "%-14s", rs.getString(i) );
                }
                System.out.println();
            }

           DB_Utility.destroy();
        }
 }

