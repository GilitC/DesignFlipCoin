package View;

import Control.Main;
import Control.Logic.RecommendationLogic;
import Control.Logic.UserLogic;
import Exceptions.ListNotSelectedException;
import Model.Recommendation;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import View.WindowManager;
import utils.commitment;

public class sendRecToUserController {

	    @FXML
	    private ListView<Recommendation> listRecommendations;

	    @FXML
	    private Button buttonSend;

	    @FXML
	    private ListView<User> listUsers;

	    @FXML
	    private ComboBox<commitment> comboBoxLevl;

	    @FXML
	    private Button back;

	    @FXML
	    private AnchorPane viewRecommend;


	    @FXML
	    void showRowDetails(ActionEvent event) {
//
	    }

	    @FXML
	    void goToSendToUser(ActionEvent event) throws ListNotSelectedException {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Send Recommendation");
			alert.setHeaderText("");

			try {

				if (listRecommendations.getSelectionModel().isEmpty()) {
					throw new ListNotSelectedException("Recommendation");
				}
				if (listUsers.getSelectionModel().isEmpty()) {
					throw new ListNotSelectedException("User");
				}
				if (comboBoxLevl.getSelectionModel().isEmpty()) {
					throw new ListNotSelectedException("Level");
				}
				
				String level = comboBoxLevl.getSelectionModel().getSelectedItem().returnLevel(comboBoxLevl.getSelectionModel().getSelectedItem());
				String publicAddress = listUsers.getSelectionModel().getSelectedItem().getPublicAddress();
				String userSignature = listUsers.getSelectionModel().getSelectedItem().getUserSignature();
				Integer recommedID = listRecommendations.getSelectionModel().getSelectedItem().getRecommedID();
				String userEmail = listUsers.getSelectionModel().getSelectedItem().getEmail();
				
				
				if (RecommendationLogic.getInstance().addRecommendationToUser(publicAddress, userSignature, recommedID, level)) {
					
					String from = Main.getUSER_NAME();
					String pass = Main.getPASSWORD();
					String[] to = { userEmail }; // list of recipient email addresses
					String subject = "New Recommendation";
					String body = "Hello " +  listUsers.getSelectionModel().getSelectedItem().getUsername() + ", You have recieved a new recommendation in FlipCoin Transfer. Please logon to view it. Thank you!";
					
					//When a new recommendation is added, we need to send the details to the user
					Main.sendFromGMail(from, pass, to, subject, body);
					
					alert.setHeaderText("Success");
					alert.setContentText("Sent Recommendation Email to User succesfully!");
					alert.show();			
					initialize();
				} else {
					alert.setHeaderText("Unable to send Recommendation.");
					alert.setContentText("Recommendation wasn't sent.");
					alert.show();
				}


			} catch (ListNotSelectedException e) {
			}

	    }
	    
	    public void initialize() {
	        ObservableList<Recommendation> recs = FXCollections.observableArrayList(RecommendationLogic.getInstance().getRecommendation());
	        listRecommendations.setItems(recs);
	        
	        ObservableList<User> userLst = FXCollections.observableArrayList(UserLogic.getInstance().getALLUsers());
	        listUsers.setItems(userLst);
	        
	        comboBoxLevl.getItems().setAll(commitment.values());
	    }
	    
	    @FXML
	    void goBack(ActionEvent event) {
	    	WindowManager.goBack();
	    }
}


