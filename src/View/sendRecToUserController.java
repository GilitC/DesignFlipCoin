package View;

import Control.Logic.RecommendationLogic;
import Control.Logic.UserLogic;
import Model.Recommendation;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import View.WindowManager;
import utils.E_Levels;

public class sendRecToUserController {

		@FXML
	    private ListView<User> listofSentUsers;

	    @FXML
	    private ListView<Recommendation> listRecommendations;

	    @FXML
	    private Button buttonSend;

	    @FXML
	    private ListView<User> listUsers;

	    @FXML
	    private ComboBox<E_Levels> comboBoxLevl;

	    @FXML
	    private Button back;

	    @FXML
	    private AnchorPane viewRecommend;


	    @FXML
	    void showRowDetails(ActionEvent event) {
//
	    }

	    @FXML
	    void goToSendToUser(ActionEvent event) {

	    }
	    
	    public void initialize() {
	        ObservableList<Recommendation> recs = FXCollections.observableArrayList(RecommendationLogic.getInstance().getRecommendation());
	        listRecommendations.setItems(recs);
	        
	        ObservableList<User> userLst = FXCollections.observableArrayList(UserLogic.getInstance().getEmployees());
	        listUsers.setItems(userLst);
	        
	        comboBoxLevl.getItems().setAll(E_Levels.values());
	    }
	    
	    @FXML
	    void goBack(ActionEvent event) {
	    	WindowManager.goBack();
	    }
}


