package Control.Logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
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
	
}
