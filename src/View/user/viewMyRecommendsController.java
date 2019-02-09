package View.user;

import Control.SysData;
import Control.Logic.RecommendationLogic;
import Model.Recommendation;
import Model.RecommendationToCustomer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import View.WindowManager;

public class viewMyRecommendsController {


	    @FXML
	    private ListView<Recommendation> listOfRecos;

	    @FXML
	    private Button back;

	    @FXML
	    private AnchorPane viewRecommend;

		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		private String addrs = loggedIn.getPublicAddress();
		private String signt = loggedIn.getUserSignature();
	    
	    public void initialize() {
	    	System.out.println("Opened MyRecommendations");
	        ObservableList<Recommendation> recs = FXCollections.observableArrayList(RecommendationLogic.getInstance().getMyRecommendationsList(addrs, signt));
	        listOfRecos.setItems(recs);
	    }
	    
	    @FXML
	    void goBack(ActionEvent event) {
	    	WindowManager.goBack();
	    }
}


