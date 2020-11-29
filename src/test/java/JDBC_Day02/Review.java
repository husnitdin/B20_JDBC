package JDBC_Day02;
import JDBC_Utitlity.DB_Utility;
import java.sql.*;

public class Review {
    public static void main(String[] args) throws SQLException {

        DB_Utility.createConnection();
        ResultSet rs   =   DB_Utility.runQuery("Select * from Jobs") ;

        rs.first();
        System.out.println("First column value in Jobs, row 1: " +  rs.getString(1)   );
        System.out.println("Second column value in Jobs, row 1: " +  rs.getString(2)   );

        rs.absolute(7);
        System.out.println("First column value in Jobs, row 7: " +  rs.getString(1)   );
        System.out.println("Second column value in Jobs, row 7: " +  rs.getString(2)   );

        rs.last();
        System.out.println("First column value in Jobs, last row: " +  rs.getString(1)   );
        System.out.println("Second column value in Jobs, last row: " +  rs.getString(2)   );

        rs.previous();
        System.out.println("First column value in Jobs, previous before last row: " +  rs.getString(1)   );
        System.out.println("Second column value in Jobs, previous before last row: " +  rs.getString(2)   );

        System.out.println("===========================================");

        rs.beforeFirst();
        while ( rs.next() ) {
            System.out.println("Jobs Table : ");
            System.out.println("Job ID     : " +  rs.getString(1)   );
            System.out.println("Job title  : " +  rs.getString(2)   );
            System.out.println("===========================================");
        }

        rs.afterLast();
        while ( rs.previous() ) {
            System.out.println("Jobs Table : ");
            System.out.println("Min Salary : $" +  rs.getDouble("MIN_SALARY")   );
            System.out.println("Max Salary : $" +  rs.getDouble("MAX_SALARY")   );
            System.out.println("===========================================");
        }

        DB_Utility.destroy();
    }
}
