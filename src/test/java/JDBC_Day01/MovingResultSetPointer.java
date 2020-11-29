package JDBC_Day01;
import JDBC_Utitlity.DB_Utility;
import java.sql.*;

public class MovingResultSetPointer {
    public static void main(String[] args) throws SQLException {

            DB_Utility.createConnection();
            ResultSet rs   =   DB_Utility.runQuery("SELECT * FROM REGIONS") ;

            while ( rs.next() ) {
                System.out.println("Region_name: " + rs.getString("REGION_NAME"));
            }

            rs.last();
            int countRow = rs.getRow();
            System.out.println("Row Count: "+ countRow);

            DB_Utility.destroy();

    }
}
