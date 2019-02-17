package View.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import View.WindowManager;

public class WalletMenuController {
	   
		@FXML
	    private AnchorPane recoMenu;

	    @FXML
	    private Button viewWallets;

	    @FXML
	    private Button purchaseBCK;

	    @FXML
	    private Button purchaceBCS;

	    /**
	     * Go to Add purchase bitcoin knots wallet
	     * @param event when purchase button is pressed
	     */
	    @FXML
	    void goToBuyBCKW(ActionEvent event) {
	    	WindowManager.openWindow(NameToWindow.BUY_BKN_WALLET);
	    }

	    /**
	     * Go to Add purchase bitcoin space wallet
	     * @param event when purchase button is pressed
	     */
	    @FXML
	    void goToBuyBTSW(ActionEvent event) {
	    	WindowManager.openWindow(NameToWindow.BUY_BS_WALLET);
	    }

	    /**
	     * Go to view all my wallets
	     * @param event when view button is pressed
	     */
	    @FXML
	    void goToViewMyWallets(ActionEvent event) {
	    	WindowManager.openWindow(NameToWindow.VIEW_MY_WALLETS);
	    }

	   
}
