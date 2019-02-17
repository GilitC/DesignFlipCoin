package View.add;

import Control.Logic.CategoryLogic;
import Exceptions.MissingInputException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import View.WindowManager;

public class addCategoryController {

    @FXML
    private Button clearButton;

    @FXML
    private AnchorPane addReco;

    @FXML
    private Button back;

    @FXML
    private Button addButton;

    @FXML
    private TextField tfCatName;

    @FXML
    private Label labelSuccess;

	/**
	 * adds a Category once the add button is pressed
	 * 
	 * @param event add button is pressed
	 * @throws MissingInputException missing input might occur
	 */
    @FXML
    void addCatt(ActionEvent event) throws MissingInputException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Category");
		alert.setHeaderText("");


		String cat = tfCatName.getText();

		try {

			if (cat.isEmpty()) {
				throw new MissingInputException("Category Name");
			}


			if (CategoryLogic.getInstance().addCategory(cat)) {

				tfCatName.setText("");
				labelSuccess.setText("New Category was added succesfully!");

			} else {
				System.out.println("Failed");
				alert.setHeaderText("Unable to add Category.");
				alert.setContentText("Category wasn't added.");
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
	 * initializes, also makes sure every text field will be only letters
	 * or only numbers, as desired
	 */
	public void initialize() {

		tfCatName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("/^[a-zA-Z\\s]*$/")) {
				tfCatName.setText(newValue.replaceAll("^/^[a-zA-Z\\s]*$/", ""));
			}
		});


	}
	
	/**
	 * Clears everything in the form.
	 * @param event when clear button is pressed.
	 */
	@FXML
	void clearForm(ActionEvent event) {

		tfCatName.setText("");
		labelSuccess.setText("");
	}

}
