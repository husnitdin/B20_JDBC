package JDBC_Day02;
import JDBC_Utitlity.DB_Utility;
import java.sql.*;

public class GettingMoreInfoAboutResultSetObject {
    public static void main(String[] args) throws SQLException {

        DB_Utility.createConnection();
        ResultSet rs   =   DB_Utility.runQuery("SELECT * FROM JOBS") ;

        // meta data = > data about the data
        // ResultSetMetaData --> data about ResultSet Object that contains results of rows and columns
        // for example : column name, column count ...
        // this is how we get them:

        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCount = rsmd.getColumnCount();      // retrieves column count
        System.out.println("column count is: " + columnCount);

        for (int i = 1; i <= columnCount; i++) {
            String colName = rsmd.getColumnLabel(i);      // retrieves column name (column index number)
            System.out.println( i+" column name is: " + rsmd.getColumnLabel(i) );
        }

        DB_Utility.destroy();
    }
}
