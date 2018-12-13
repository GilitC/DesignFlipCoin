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

public class TransactionLogic {
	/**Singleton instance.*/
    private static TransactionLogic instance;
    
    /**
     * private constructor for singleton purposes.
     */
    private TransactionLogic() {}
    
    /**
     * Singleton getter.
     * @return instance of this.
     */
    public static TransactionLogic getInstance() {
        if (instance == null)
            instance = new TransactionLogic();
        return instance;
    }
    
    /**
     * outputs report at runtime.
     * @return
     */
	public JFrame compileTransactionsReport(Date givenDate) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
            	HashMap<String, Object> toSend = new HashMap<>();
            	toSend.put("myDate", givenDate);
            	JasperPrint print = JasperFillManager.fillReport(
            			getClass().getResourceAsStream("../../View/TransactionsReport.jasper"),
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
