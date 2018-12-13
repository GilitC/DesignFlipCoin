package View.add;

import Control.Logic.RecommendationLogic;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Levels;
import View.WindowManager;

public class addRecomController {

    @FXML
    private Button clearButton;

    @FXML
    private TextField publicAdd;

    @FXML
    private AnchorPane addReco;

    @FXML
    private Button back;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker dateCreate;

    @FXML
    private TextField userSig;

    @FXML
    private Label labelSuccess;

    @FXML
    private TextField chanceChos;

    @FXML
    private TextField amountTax;

	@FXML
	/**
	 * adds a Recommendation once the add button is pressed
	 * 
	 * @param event coach button is pressed
	 * @throws IOException           input exception might occur
	 * @throws MissingInputException missing input exception that i created.
	 * @throws InvalidInputException if instead of numbers in some fields text was
	 *                               input
	 */
	void addRecommend(ActionEvent event) throws MissingInputException, ListNotSelectedException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Recomendation");
		alert.setHeaderText("");

 //REVIEW
		String addrs = publicAdd.getText();
		String signt = userSig.getText();
		String chancec = chanceChos.getText();
		String taxreco = amountTax.getText();
		try {

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

			if (dateCreate.getValue() == null) {
				throw new MissingInputException("Date Created");
			}

			java.sql.Date datecreat = java.sql.Date.valueOf(dateCreate.getValue());

			if (RecommendationLogic.getInstance().addRecommendation(datecreat, Double.parseDouble(chancec), Double.parseDouble(taxreco), addrs, signt)) {
				System.out.println("success");
				labelSuccess.setText("New Recommendation was added succesfully!");
				dateCreate.valueProperty().set(null);
				userSig.setText("");
				publicAdd.setText("");
				amountTax.setText("");
				chanceChos.setText("");
				labelSuccess.setText("");

			} else {
				System.out.println("failed");
				alert.setHeaderText("Unable to add Recommendation.");
				alert.setContentText("Recommendation wasn't added.");
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
	/**
	 * Clears everything in the form.
	 * @param event when clear button is pressed.
	 */
	@FXML
	void clearForm(ActionEvent event) {
		dateCreate.valueProperty().set(null);
		userSig.setText("");
		publicAdd.setText("");
		amountTax.setText("");
		chanceChos.setText("");
		labelSuccess.setText("");
	}

}
