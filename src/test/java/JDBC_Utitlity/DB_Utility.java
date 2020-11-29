package JDBC_Utitlity;
import java.sql.*;
import java.util.*;

public class DB_Utility {

    static Connection conn;     // make it static so that we can reuse in every method
    static ResultSet rs;
    static Statement stmnt;

    // a method to create CONNECTION
    public static void createConnection () {
        String connectionStr = "jdbc:oracle:thin:@54.172.215.237:1521:XE";
        String username = "hr" ;
        String password = "hr" ;

        try {
            conn = DriverManager.getConnection(connectionStr,username,password) ;
            System.out.println("Connection has been successful!");

        } catch (SQLException e) {
            System.out.println( "Connection has failed! " + e.getMessage() );
            // here e.getMessage() --> gives more core message about the error
            // ex: Connection has failed! ORA-01017: invalid username/password; logon denied
        }
    }

    // a method to create a method to RUN QUERY which returns ResultSet Objects
    public static ResultSet runQuery (String query) {
        try {
            stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println( "Error while getting resultSet " + e.getMessage() );
        }
        return rs;
    }

    // a method to CLEANING UP  the Connection, statement and ResultSet after using
    public static void destroy()  {
        try {
            rs.close();     // close ResultSet
            stmnt.close();  // close statement
            conn.close();   // close connection

        } catch (SQLException e) {
            System.out.println( "Error while closing "+e.getMessage() );
        }
    }

    // a method to get the ROW COUNT
    public static int getRowCount()  {
        int rowCount = 0;
        try {
            rs.last();
            rowCount = rs.getRow();

            // move the cursor back to beforeFirst to avoid starting from wrong place
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("Error while getting row count "+e.getMessage());
        }
        return rowCount;
    }

    // a method to get the COLUMN COUNT
    public static int getColumnCount()  {
        int columnCount = 0;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("Error while getting column count "+e.getMessage());
        }

        return  columnCount;
    }

    // a method that returns ALL COLUMN NAMES as a List<String>
    public static List<String> getColumnName(){
        List<String> columnList = new ArrayList<>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int colNum = 1; colNum < getColumnCount(); colNum++) {
                columnList.add( rsmd.getColumnLabel( colNum ) );
            }
        } catch (SQLException e) {
            System.out.println("Error while getting list of column names" + e.getMessage());
        }
        return columnList;
    }

    /** a method that returns row data as a List<String> from a CERTAIN ROW
     * @param rowNum Row Number you want to get the data
     * @return the row data as List Objects
    */
    public static List<String> getRowDataAsList( int rowNum ){
        List<String> rowDataList = new ArrayList<>();
        try {

            rs.absolute(rowNum);
                for (int i = 1; i < getColumnCount(); i++) {
                    rowDataList.add( rs.getString( i ) );
                }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("Error while getting list of data from the certain row" + e.getMessage());
        }
        return rowDataList;
    }

    /** a method that returns row data as a List<String> from a CERTAIN COLUMN
     * @param colNum Column Number you want to get the data
     * @return the column data as List Objects
     */
    public static List<String> getColumnDataAsList( int colNum ){

        List<String> rowDataList = new ArrayList<>();

        try {
            while(rs.next()){
                rowDataList.add( rs.getString( colNum ) );
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting data from a certain column" + e.getMessage());
        }
        return rowDataList;
    }

    /** a method that returns row data as a List<String> from a CERTAIN COLUMN
     * @param colName Column Name you want to get the data
     * @return the column data as List Objects
     */
    public static List<String> getColumnDataAsList( String colName ){

        List<String> rowDataList = new ArrayList<>();

        try {
            while(rs.next()){
                rowDataList.add( rs.getString( colName ) );
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting data from a certain column" + e.getMessage());
        }
        return rowDataList;
    }

    /** a method that returns value of a CERTAIN CELL
     * @param rowNum    row number
     * @param colNum    column number
     * @return cell value as a string
     */
    public static String getCellData( int rowNum, int colNum ){

        String result = "";
        try {
            rs.absolute(rowNum);
            result = rs.getString(colNum);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting data from a cell" + e.getMessage());
        }
        return result;
    }

    /** a method that returns value of a CERTAIN CELL
     * @param rowNum    row number
     * @param colName   column name
     * @return cell value as a string
     */
    public static String getCellData( int rowNum, String colName ){

        String result = "";
        try {
            rs.absolute(rowNum);
            result = rs.getString(colName);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting data from a cell" + e.getMessage());
        }
        return result;
    }

    // a method that returns ALL THE DATA
    public static List<String> getAllData(){
        List<String> allData = new ArrayList<>();
        try {
            rs.beforeFirst();
            while ( rs.next() ) {
                for (int i = 1; i <= getColumnCount(); i++) {
                    allData.add( rs.getString(i) + "\t" );
                }
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting all data from the table " + e.getMessage());
        }
        return allData;
    }

    /** a method that returns data in MAP (Key, Value) format
     * @param rowNum    row number
     * @return Map object -- column name as Key, cell value as Value
     */
    public static Map<String, String> getRowMap(int rowNum){

        Map< String, String > rowMap  = new LinkedHashMap<>();
        try {
            rs.absolute( rowNum );
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String columnName = rsmd.getColumnLabel( i );
                String cellValue = rs.getString( i );
                rowMap.put( columnName, cellValue );
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println( "Error while getting data as Map "+e.getMessage() );
        }
        return rowMap;
    }

    /** a method that returns LIST OF MAPS
     * @return List of Maps
     */
    public static List< Map <String, String> > getAllDataAsListOfMaps(){

        List< Map <String, String> > rowMapList = new ArrayList<>();

        try {
            for (int rowNum = 1; rowNum <= getRowCount(); rowNum++) {
                rs.absolute(rowNum);
                rowMapList.add( getRowMap( rowNum ) );
            }
        } catch (SQLException e) {
            System.out.println( "Error while getting list of Maps "+e.getMessage() );
        }
        return rowMapList;
    }

}
