package View;

import Control.Logic.RecommendationLogic;
import Model.Recommendation;
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

public class viewRecommendController {

		@FXML
	    private ListView<User> listofSentUsers;

	    @FXML
	    private ListView<Recommendation> listRecommendations;

	    @FXML
	    private Button back;

	    @FXML
	    private AnchorPane viewRecommend;

	    //Action Event when team is selected in listView
	    @FXML
	    void showRowDetails(MouseEvent event) {
//	    	Subscription clicked = listSubs.getSelectionModel().getSelectedItem();
//	    	showDetails(clicked);
	    }
	    
	    private void showDetails(User item) {
//	        ObservableList<Match> matchess = FXCollections.observableArrayList(item.getMatches());
//	        
//	        if(!matchess.isEmpty())
//	        	matchlist.setItems(matchess);
	    }
	    
	    public void initialize() {
	        ObservableList<Recommendation> recs = FXCollections.observableArrayList(RecommendationLogic.getInstance().getRecommendation());
	        listRecommendations.setItems(recs);
	    }
	    
	    @FXML
	    void goBack(ActionEvent event) {
	    	WindowManager.goBack();
	    }
}


