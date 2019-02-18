package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Control.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utils.NameToWindow;

public class UserMenuController implements Initializable{

	@FXML
	private FlowPane menupane;

	@FXML
	private Button logout;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);
		loadUI(NameToWindow.WELCOMESCREEN);
	}


	public void loadUI(NameToWindow ui)
	{
		WindowManager.openWindow(ui);
	}

	public FlowPane getMenupane() {
		return menupane;
	}

	@FXML
	private void GoToHomePage(ActionEvent event) {
		loadUI(NameToWindow.WELCOMESCREEN);
	}

    @FXML
    void GoToAddProd(ActionEvent event) {
    	loadUI(NameToWindow.PUBLISH_PRODUCT);
    }

    @FXML
    void GoToConfirmTrans(ActionEvent event) {
    	loadUI(NameToWindow.WELCOMESCREEN);
    }

    @FXML
    void GoToEditProd(ActionEvent event) {
    	loadUI(NameToWindow.UPDATE_PRODUCT);
    }


    @FXML
    void GoToPayTrans(ActionEvent event) {
    	loadUI(NameToWindow.MANAGE_ORDERS);
    }

    @FXML
    void GoToTransactions(ActionEvent event) {
    	loadUI(NameToWindow.WELCOMESCREEN);
    }

    @FXML
    void GoToViewRecommendations(ActionEvent event) {
    	loadUI(NameToWindow.VIEW_MY_RECOMMENDATIONS);
    }

    @FXML
    void goToMyInfo(ActionEvent event) {
    	loadUI(NameToWindow.MY_INFO);
    }

    @FXML
    void goToSearchProd(ActionEvent event) {
    	loadUI(NameToWindow.VIEW_PRODUCTS_FORSALE);
    }

    
	@FXML
	void goToWalletsMenu(ActionEvent event) {
		loadUI(NameToWindow.WALLETS_MENU);
	}


	@FXML
	void GoToLogin(ActionEvent event) throws IOException {
		Stage stage = (Stage) menupane.getScene().getWindow();
		// logout user
        SysData.getInstance();
		SysData.setLoggedInUser(null);
		stage.close();
		
		FXMLLoader load = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		Stage primaryStage = new Stage();
		Parent root = load.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

