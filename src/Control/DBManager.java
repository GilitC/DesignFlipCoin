package Control;

import java.sql.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ucanaccess.jdbc.UcanaccessSQLException;

/**
 * DB Connection Manager
 * @author Administrator
 */
public class DBManager {
	
    private static Connection conn;
       
    public DBManager() throws ClassNotFoundException, SQLException{
        String dtbsFile = (new File("sources/database.accdb")).getAbsolutePath();
        Control.Logger.log("DB File: "+dtbsFile);
        String driver="net.ucanaccess.jdbc.UcanaccessDriver"; 
        Class.forName(driver); 
        try{
            System.err.println("CONNECTING DB TO: jdbc:ucanaccess://"+dtbsFile);
        DBManager.conn=DriverManager.getConnection("jdbc:ucanaccess://"+dtbsFile); 
        
        }
        catch(Exception e){
            
        	dtbsFile = (new File("src/sources/database.accdb")).getAbsolutePath();
            System.err.println("Using second URL: jdbc:ucanaccess://"+dtbsFile);
            DBManager.conn=DriverManager.getConnection("jdbc:ucanaccess://"+dtbsFile);

        }      
    }
    
    /**
     * This method returns a ResultSet on given SQL Query
     * @param SQL
     * @return 
     */
    public static ResultSet query(String SQL){
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = stmt.executeQuery(SQL);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return result;
    }
    

    public void setConn(Connection conn) {
        DBManager.conn = conn;
    }
    
    public Connection getConnection(){
        return DBManager.conn;
    }
    

}


