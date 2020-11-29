package JDBC_Day01;
import JDBC_Utitlity.DB_Utility;
import java.sql.*;

public class LoopResults {
        public static void main(String[] args) throws SQLException {

            DB_Utility.createConnection();
            ResultSet ls   =   DB_Utility.runQuery("SELECT * FROM LOCATIONS") ;

            // rs.next(); returns boolean value
            while ( ls.next() ) {
                System.out.println("Location_ID:    "+ ls.getString("LOCATION_ID"));
                System.out.println("Street Address: "+ ls.getString("STREET_ADDRESS"));
                System.out.println("Postal Code:    "+ ls.getString("POSTAL_CODE"));
                System.out.println("City:           "+ ls.getString("CITY"));
                System.out.println("State Province: "+ ls.getString("STATE_PROVINCE"));
                System.out.println("Country ID:     "+ ls.getString("COUNTRY_ID"));
                System.out.println("===============================================");
            }
            DB_Utility.destroy();
        }
    }
