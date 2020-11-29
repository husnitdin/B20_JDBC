package JDBC_Day03;
import JDBC_Utitlity.*;
import java.sql.*;
import java.util.*;

import static JDBC_Utitlity.DB_Utility.*;

public class UtilityPractice {
    public static void main(String[] args) throws SQLException {

        // test out all method you created before
        DB_Utility.createConnection();
        ResultSet rs = DB_Utility.runQuery("Select * from Jobs");

        /*
        int rowCount = getRowCount();
        System.out.println("Number of rows in the table is: " + rowCount );
        int colCount = getColumnCount();
        System.out.println("Number of columns in the table is: "+ colCount );
        System.out.println("Column Names are "+ getColumnName() );
        System.out.println( "Row data at row 3: "+ getRowDataAsList(3) );
        System.out.println("Cell Value at row 1 and column 4: " + getCellData(1, 4) );
        System.out.println("Cell Value at row 7 and column FIRST_NAME: " + getCellData(7, "FIRST_NAME") );
        System.out.println( "Row data at column 2: "+ getColumnDataAsList(2) );
        System.out.println( "Row data at column Salary: "+ getColumnDataAsList("Salary") );
        System.out.println( "Row data rom the whole table: "+ getAllData() );
        */

        System.out.println(getRowMap( 3 ));

        Map <String, String> row2Map = getRowMap( 2 );
        Map <String, String> row3Map = getRowMap( 3 );
        Map <String, String> row4Map = getRowMap( 4 );
        Map <String, String> row5Map = getRowMap( 5 );

        List< Map <String, String> > listOfMaps = new ArrayList<>(Arrays.asList(row2Map, row3Map, row4Map, row5Map));

        System.out.println( getAllDataAsListOfMaps() );

        DB_Utility.destroy();
    }
}
