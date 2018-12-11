package View.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import View.WindowManager;

public class recController {
	 @FXML
	    private AnchorPane recoMenu;

	    @FXML
	    private Button updRed;

	    @FXML
	    private Button sendToUsers;

	    @FXML
	    private Button addRec;

	    @FXML
	    private Button viewRecs;

	    /**
	     * Go to Add Recommendation Window
	     * @param event when add recommendation button is pressed
	     */
	    @FXML
	    void goToAddRec(ActionEvent event) {
	    	WindowManager.openWindow(NameToWindow.ADD_RECOMMENDATION);
	    }

	    /**
	     * Go to send Recommendation to chosen users Window
	     * @param event when Send to Users button is pressed
	     */
	    @FXML
	    void goToSendToUsers(ActionEvent event) {
	    	WindowManager.openWindow(NameToWindow.SEND_REC_TO_USER);
	    }

	    /**
	     * Go to Update Recommendation Window
	     * @param event when update recommendation button is pressed
	     */
	    @FXML
	    void goToUpdRed(ActionEvent event) {
	    	WindowManager.openWindow(NameToWindow.UPDATE_RECOMMENDATIONS);
	    }

	    /**
	     * Go to View Recommendation Window
	     * @param event when view all recommendations button is pressed
	     */
	    @FXML
	    void goToViewRecs(ActionEvent event) {
	    	WindowManager.openWindow(NameToWindow.VIEW_RECOMMENDATIONS);
	    }
	
}
