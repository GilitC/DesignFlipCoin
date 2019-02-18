package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Control.Logic.TransactionLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utils.NameToWindow;

public class AdminMenuController implements Initializable{

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
	void GoToCreateReports(ActionEvent event) {
		loadUI(NameToWindow.MENU_REPORTS);
	}

	
	//Export transactions in state: pending to Flip coin mining (
    @FXML
    void GoToExportTransactions(ActionEvent event) {
		loadUI(NameToWindow.WELCOMESCREEN);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Export");
		alert.setHeaderText("");

		if(TransactionLogic.exportPayTransactionsToJSON()) {
			alert.setHeaderText("Success");
			alert.setContentText("Exported Transactions in JSON File to FlipCoin Mining Successfully!");
			alert.show();	
		}
    }

    @FXML
    void GoToImportTransactions(ActionEvent event) {
		loadUI(NameToWindow.WELCOMESCREEN);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Import");
		alert.setHeaderText("");

		TransactionLogic.importConfirmedTransactions();
	    alert.setHeaderText("Success");
	    alert.setContentText("Transactions are Imported from FlipCoin Mining Automatically :)");
		alert.show();	
		
    }

	@FXML
	void GoToRecommendmenu(ActionEvent event) {
		loadUI(NameToWindow.MENU_RECOMMENDATIONS);
	}


    @FXML
    void goToAddCategory(ActionEvent event) {
    	loadUI(NameToWindow.ADD_CATEGORY);
    }

    @FXML
    void goToEditCategory(ActionEvent event) {
    	loadUI(NameToWindow.UPDATE_CATEGORY);
    }

    @FXML
    void goToManageSystemPar(ActionEvent event) {
    	loadUI(NameToWindow.MANAGE_SYSTEM_PARAMETERS);
    }


	@FXML
	void GoToLogin(ActionEvent event) throws IOException {
		Stage stage = (Stage) menupane.getScene().getWindow();

		//		SysData.getInstance().setUserCustomer(null);

		stage.close();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		Stage primaryStage = new Stage();
		Parent root = load.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

