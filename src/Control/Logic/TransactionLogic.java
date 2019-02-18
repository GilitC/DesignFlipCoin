package Control.Logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;

//import org.json.simple.JsonArray;
//import org.json.simple.JsonObject;
//import org.json.simple.Jsoner;

import Model.Consts;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public abstract class TransactionLogic {
    
    /**
     * outputs report at runtime.
     * @return
     */
	public static JFrame compileTransactionsReport(Date givenDate) {
        try {
        	System.out.println("Defining class.forName");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
            	System.out.println("Connection initiated");
            	HashMap<String, Object> toSend = new HashMap<>();
            	toSend.put("myDate", givenDate);
            	System.out.println("Attempting to open jasper: " + TransactionLogic.class.getResource("../../View/TransactionsReport.jasper"));
            	JasperPrint print = JasperFillManager.fillReport(
            			TransactionLogic.class.getResourceAsStream("../../View/TransactionsReport.jasper"),
                        toSend, conn);
                JFrame frame = new JFrame("Transactions Report");
                frame.getContentPane().add(new JRViewer(print));
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.pack();
                return frame;
            } catch (SQLException | JRException | NullPointerException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
//    /**
//     * exports pay transactions from db to json.
//     */
//	public void exportPayTransactionsToJSON() {
//    	   try {
//               Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//               try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//                       PreparedStatement stmt = conn.prepareStatement(
//                               Consts.SQL_SEL_TRANSPAY_PENDING_STATUS);
//                       ResultSet rs = stmt.executeQuery()) {
//            	   JsonArray customers = new JsonArray();
//                   while (rs.next()) {
//                	   JsonObject customer = new JsonObject();
//                	   
//                	   for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
//                		   customer.put(rs.getMetaData().getColumnName(i), rs.getString(i));
//                	   
//                	   customers.add(customer);
//                   }
//                   
//            	   JsonObject doc = new JsonObject();
//            	   doc.put("Customers_info", customers);
//                   
//                   File file = new File("json/customers.json");
//                   file.getParentFile().mkdir();
//                   
//                   try (FileWriter writer = new FileWriter(file)) {
//                	   writer.write(Jsoner.prettyPrint(doc.toJson()));
//                	   writer.flush();
//                       System.out.println("customers data exported successfully!");
//                   } catch (IOException e) {
//                	   e.printStackTrace();
//                   }
//               } catch (SQLException | NullPointerException e) {
//                   e.printStackTrace();
//               }
//           } catch (ClassNotFoundException e) {
//               e.printStackTrace();
//           }	
//    }
}
