package View.add;

import java.net.MalformedURLException;
import java.net.URL;

import Control.Logger;
import Control.SysData;
import Control.Logic.CategoryLogic;
import Control.Logic.ProductLogic;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.ProductCategory;
import Model.User;
import View.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class addProductController {

    @FXML
    private TextField quantity;

    @FXML
    private Button clearButton;

    @FXML
    private TextField price;

    @FXML
    private TextField name;

    @FXML
    private TextField link;

    @FXML
    private AnchorPane addReco;

    @FXML
    private Button back;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<ProductCategory> category;

    @FXML
    private Label labelSuccess;

    @FXML
    private TextArea desc;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    @FXML
    void addProduct(ActionEvent event) throws MissingInputException, ListNotSelectedException, MalformedURLException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Product");
		alert.setHeaderText("");

		SysData.getInstance();
		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		String addrs = loggedIn.getPublicAddress();
		String signt = loggedIn.getUserSignature();
		
		String prodName = name.getText();
		String decsription = desc.getText();
		ProductCategory c = category.getSelectionModel().getSelectedItem();
		
		try {

			if (category.getSelectionModel().isEmpty()) {
				throw new ListNotSelectedException("Category");
			}

			if (prodName.isEmpty()) {
				throw new MissingInputException("Name");
			}
			if (link.getText().isEmpty()) {
				throw new MissingInputException("Image");
			}

			if (decsription.isEmpty()) {
				throw new MissingInputException("decsription");
			}
			
			if (price.getText().isEmpty()) {
				throw new MissingInputException("price");
			}
			
			if (quantity.getText().isEmpty()) {
				throw new MissingInputException("quantity");
			}

			URL pic = new URL(link.getText());
			Double itemPrice = Double.parseDouble(price.getText());
			int amount = Integer.parseInt(quantity.getText());
			
			if (ProductLogic.getInstance().addProduct(prodName, pic, decsription, itemPrice, amount, c.getCategoryID(), addrs, signt)) {
				alert.setHeaderText("Success");
				alert.setContentText("Added Product succesfully!");
				alert.show();			
				initialize();
			} else {
				alert.setHeaderText("Unable to add the Product.");
				alert.setContentText("Item wasn't added.");
				alert.show();
			}


		} catch (ListNotSelectedException e) {
		} catch (MissingInputException e) {
		}
		catch (MalformedURLException e) {
			Logger.log("Problem with url");
		}

    }

    @FXML
    void clearForm(ActionEvent event) {
    	name.setText("");
    	link.setText("");
    	desc.setText("");
    	price.setText("");
    	quantity.setText("");
    	category.getItems().setAll(CategoryLogic.getInstance().getAllCategories());
    }

    //Initialize the combobox with the available categories
    public void initialize() {
        category.getItems().setAll(CategoryLogic.getInstance().getAllCategories());
        
		price.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("^\\\\d*\\\\.\\\\d+|\\\\d+\\\\.\\\\d*$")) {
				price.setText(newValue.replaceAll("^\\\\d*\\\\.\\\\d+|\\\\d+\\\\.\\\\d*$", ""));
			}
		});
		
		quantity.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("^\\d+$")) {
				quantity.setText(newValue.replaceAll("^\\d+$", ""));
			}
		});
		
    }

}