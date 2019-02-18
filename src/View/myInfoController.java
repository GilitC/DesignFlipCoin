package View;

import Control.SysData;
import Control.Logic.UserLogic;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import View.WindowManager;

public class myInfoController {

    @FXML
    private TextField txtBalance;

	    @FXML
	    private Button back;

	    @FXML
	    private AnchorPane viewRecommend;

	    
		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		private String addrs = loggedIn.getPublicAddress();
		private String signt = loggedIn.getUserSignature();
	    
	    public void initialize() {
	        double balanceUser = UserLogic.getInstance().getUserBalance(addrs, signt);
	        txtBalance.setText(String.valueOf(balanceUser));
	    }
	    
	    @FXML
	    void goBack(ActionEvent event) {
	    	WindowManager.goBack();
	    }
}


