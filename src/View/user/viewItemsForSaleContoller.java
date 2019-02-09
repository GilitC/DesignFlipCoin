package View.user;

import java.util.ArrayList;

import Control.SysData;
import Control.Logic.ProductLogic;
import Model.Product;
import Model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import View.WindowManager;

public class viewItemsForSaleContoller {


	    @FXML
	    private GridPane grid;

	    @FXML
	    private Button back;

	    @FXML
	    private AnchorPane viewRecommend;

		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		private String addrs = loggedIn.getPublicAddress();
		private String signt = loggedIn.getUserSignature();
	    
	    public void initialize() {
	    	int i=0;
	        ArrayList<Product> allprods = ProductLogic.getInstance().getProdListWITHOUTSellingUser(addrs, signt);
	       for(Product p: allprods) {
	    	//   grid.add(p, 1, i);
	       }
	       //help[pppppp
	       //https://stackoverflow.com/questions/45450489/how-to-implement-a-gridview-style-in-javafx
	    }
	    
	    @FXML
	    void goBack(ActionEvent event) {
	    	WindowManager.goBack();
	    }
}


