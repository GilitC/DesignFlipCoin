package View.update;

import java.util.Date;

import Control.Logic.RecommendationLogic;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.Recommendation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import View.WindowManager;

public class updateRecomController {

    @FXML
    private ComboBox<Recommendation> chooseRecCombo;

    @FXML
    private TextField publicAdd;

    @FXML
    private AnchorPane addReco;

    @FXML
    private Button back;

    @FXML
    private Button addButton;

    @FXML
    private TextField dateCreated;

    @FXML
    private TextField userSig;

    @FXML
    private Label labelSuccess;

    @FXML
    private TextField chanceChos;

    @FXML
    private TextField amountTax;

  //When a recommendation is chosen, fill the textfields with relevant data
    @FXML
    void chooseRec(ActionEvent event) {
    	Recommendation selected = chooseRecCombo.getSelectionModel().getSelectedItem();
    	//Show the Recommendation Selected and let the user edit their desired fields, except for id who stays the same
    	publicAdd.setText(selected.getPublicAddress());
    	userSig.setText(selected.getUserSignature());
    	chanceChos.setText(String.valueOf(selected.getChanceChosen()));
    	amountTax.setText(String.valueOf(selected.getAmountTaxRecommended()));
    	dateCreated.setText(String.valueOf(selected.getDateCreated()));
    	
    	publicAdd.setDisable(false);
    	userSig.setDisable(false);
    	chanceChos.setDisable(false);
    	amountTax.setDisable(false);
    	addButton.setDisable(false);
    	dateCreated.setDisable(false);

    }
    
	@FXML
	void updateRecommend(ActionEvent event) throws MissingInputException, ListNotSelectedException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Update Recomendation");
		alert.setHeaderText("");

		Integer idRec = chooseRecCombo.getSelectionModel().getSelectedItem().getRecommedID();
		String addrs = publicAdd.getText();
		String signt = userSig.getText();
		String chancec = chanceChos.getText();
		String taxreco = amountTax.getText();
		try {
			if( chooseRecCombo.getSelectionModel().isEmpty()) {
				throw new ListNotSelectedException("Recommendation List selection");
			}
			if (chancec.isEmpty()) {
				throw new MissingInputException("Chance Chosen");
			}
			if (taxreco.isEmpty()) {
				throw new MissingInputException("Amount of Tax Recommended");
			}
			
			if (addrs.isEmpty()) {
				throw new MissingInputException("Public Address");
			}
			if (signt.isEmpty()) {
				throw new MissingInputException("User Signature");
			}

			if (RecommendationLogic.getInstance().updateRecommendation(idRec, chooseRecCombo.getSelectionModel().getSelectedItem().getDateCreated(), Double.parseDouble(chancec), Double.parseDouble(taxreco), addrs, signt)) {
				labelSuccess.setText("Updated Recommendation succesfully!");

			} else {
				alert.setHeaderText("Unable to update Recommendation.");
				alert.setContentText("Recommendation wasn't updated.");
				alert.show();
			}


		} catch (MissingInputException e) {
		}
	}

	/**
	 * goes back to previous page
	 * 
	 * @param event back button is pressed
	 */
	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}

	/**
	 * initializes combobox/lists, making sure every text field will be only letters
	 * or only numbers, as desired
	 */
	public void initialize() {

		//Fill combobox with existing recommendations
		if(RecommendationLogic.getInstance().getRecommendation().size()>0) {
			chooseRecCombo.getItems().addAll(RecommendationLogic.getInstance().getRecommendation());
    	}
		
		//unable to edit text fields
    	publicAdd.setDisable(true);
    	userSig.setDisable(true);
    	chanceChos.setDisable(true);
    	amountTax.setDisable(true);
    	addButton.setDisable(true);
		
		chanceChos.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("^\\\\d*\\\\.\\\\d+|\\\\d+\\\\.\\\\d*$")) {
				chanceChos.setText(newValue.replaceAll("^\\\\d*\\\\.\\\\d+|\\\\d+\\\\.\\\\d*$", ""));
			}
		});
		amountTax.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("^([0-9]+\\\\.?[0-9]*|[0-9]*\\\\.[0-9]+)$")) {
				amountTax.setText(newValue.replaceAll("^([0-9]+\\\\.?[0-9]*|[0-9]*\\\\.[0-9]+)$", ""));
			}
		});
//		userSig.textProperty().addListener((observable, oldValue, newValue) -> {
//			if (!newValue.matches("\\sa-zA-Z*")) {
//				userSig.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
//			}
//		});
//		publicAdd.textProperty().addListener((observable, oldValue, newValue) -> {
//			if (!newValue.matches("\\sa-zA-Z*")) {
//				publicAdd.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
//			}
//		});

	}



}
