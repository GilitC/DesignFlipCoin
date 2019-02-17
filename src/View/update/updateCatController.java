package View.update;

import Control.Logic.CategoryLogic;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.ProductCategory;
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

public class updateCatController {


    @FXML
    private AnchorPane addReco;

    @FXML
    private Button back;

    @FXML
    private TextField catNametx;

    @FXML
    private Button addButton;

    @FXML
    private Label labelSuccess;

    @FXML
    private ComboBox<ProductCategory> chooseCatCombo;

    //When a category is chosen, fill the textfields with relevant data
    @FXML
    void chooseCat(ActionEvent event) {
    	ProductCategory selected = chooseCatCombo.getSelectionModel().getSelectedItem();
    	
    	catNametx.setText(selected.getCategoryName());
    	catNametx.setVisible(true);
    	catNametx.setDisable(false);
       	addButton.setDisable(false);

    }

    @FXML
    void updateCategoryChosen(ActionEvent event) throws MissingInputException, ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Update Category");
		alert.setHeaderText("");

		Integer idCat = chooseCatCombo.getSelectionModel().getSelectedItem().getCategoryID();
		String namec = catNametx.getText();

		try {
			if( chooseCatCombo.getSelectionModel().isEmpty()) {
				throw new ListNotSelectedException("Category List selection");
			}
			if (namec.isEmpty()) {
				throw new MissingInputException("Category Name");
			}


			if (CategoryLogic.getInstance().updateCategory(namec, idCat)) {
				initialize();
				labelSuccess.setText("Updated Category succesfully!");

			} else {
				alert.setHeaderText("Unable to update Category.");
				alert.setContentText("Category wasn't updated.");
				alert.show();
			}


		} catch (ListNotSelectedException e) {
		}
		 catch (MissingInputException e) {
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
		if(CategoryLogic.getInstance().getAllCategories().size()>0) {
			chooseCatCombo.getItems().setAll(CategoryLogic.getInstance().getAllCategories());
    	}
		
		catNametx.setVisible(false);
		//unable to edit text fields

    	addButton.setDisable(true);
		
    	catNametx.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("/^[a-zA-Z\\s]*$/")) {
				catNametx.setText(newValue.replaceAll("^/^[a-zA-Z\\s]*$/", ""));
			}
		});

	}



}
