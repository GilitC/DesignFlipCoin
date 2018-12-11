package View.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import Control.Logic.*;

	public class reportsController {

	    @FXML
	    private AnchorPane recoMenu;

	    @FXML
	    private Button addRec;

	    @FXML
	    void goToProduceTransRPT(ActionEvent event) {
	    	java.sql.Date d = new java.sql.Date(2018-1900, 10, 1);
	    	TransactionLogic.getInstance().compileTransactionsReport(d).setVisible(true);;
	    }

	
}
