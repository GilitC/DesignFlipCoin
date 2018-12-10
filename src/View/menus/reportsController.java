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
	    	TransactionLogic.getInstance().compileTransactionsReport();
	    }

	
}
