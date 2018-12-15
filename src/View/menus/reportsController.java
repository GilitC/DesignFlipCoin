package View.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import Control.Logic.*;

	public class reportsController {

	    @FXML
	    private AnchorPane recoMenu;

	    @FXML
	    private Button addRec;    

	    @FXML
	    private DatePicker datepick;

	    @FXML
	    void goToProduceTransRPT(ActionEvent event) {
	    	
	    	java.sql.Date d;
	    	
	    	if(datepick.getValue()==null)
	    		d = new java.sql.Date(2018-1900, 10, 1);
	    	else
	    		d = java.sql.Date.valueOf(datepick.getValue());
	    	
	    	System.out.println("ProductTrans - Trying to open report");
	    	TransactionLogic.compileTransactionsReport(d).setVisible(true);
	    }

	
}
