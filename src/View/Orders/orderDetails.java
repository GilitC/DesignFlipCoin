/**
 * Sample Skeleton for 'search.fxml' Controller Class
 */

package View.Orders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Control.SysData;
import Control.Logic.CategoryLogic;
import Control.Logic.ProductLogic;
import Model.Product;
import Model.ProductCategory;
import Model.User;
import View.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.NameToWindow;

public class orderDetails {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="addReco"
	private AnchorPane addReco; // Value injected by FXMLLoader

	@FXML // fx:id="productName"
	private TextField productName; // Value injected by FXMLLoader

	@FXML // fx:id="minPrice"
	private Slider minPrice; // Value injected by FXMLLoader

	@FXML // fx:id="maxPrice"
	private Slider maxPrice; // Value injected by FXMLLoader

	@FXML // fx:id="btnSearch"
	private Button btnSearch; // Value injected by FXMLLoader

	@FXML // fx:id="btnCancel"
	private Button btnCancel; // Value injected by FXMLLoader

	@FXML // fx:id="lblMinPrice"
	private Text lblMinPrice; // Value injected by FXMLLoader

	@FXML // fx:id="lblMaxPrice"
	private Text lblMaxPrice; // Value injected by FXMLLoader

    @FXML
    private ComboBox<ProductCategory> selectCategory;
    
	@FXML
	void doCancel(ActionEvent event) {
		// Cancel
		  Stage stage = (Stage) btnCancel.getScene().getWindow();
		    // do what you have to do
		    stage.close();
	}

	@FXML
	void doSearch(ActionEvent event) {
		// Search
		// Set the products list
		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		String addrs = loggedIn.getPublicAddress();
		String signt = loggedIn.getUserSignature();

		// Min Price
		Integer minPriceInt = (int) minPrice.getValue();
		Integer maxPriceInt = (int) minPrice.getValue();
		
		if(maxPriceInt == 0)
			maxPriceInt = Integer.MAX_VALUE;
		
		String keywords = "";
		if(productName.getText().length() > 0)
		{
			keywords = productName.getText();	
		}
		
		// Category
		Integer category = 0;
		if (selectCategory.getSelectionModel().getSelectedItem() != null)
		{
			category = selectCategory.getSelectionModel().getSelectedItem().getCategoryID();
		}
		
		List<Product> productsList = ProductLogic.getInstance().getProdListWITHOUTSellingUser(addrs, signt, keywords, category, minPriceInt, maxPriceInt);
		
		WindowManager.openWindow(NameToWindow.VIEW_PRODUCTS_FORSALE);
		
		  Stage stage = (Stage) btnCancel.getScene().getWindow();
		    // do what you have to do
		    stage.close();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert addReco != null : "fx:id=\"addReco\" was not injected: check your FXML file 'search.fxml'.";
		assert productName != null : "fx:id=\"productName\" was not injected: check your FXML file 'search.fxml'.";
		assert minPrice != null : "fx:id=\"minPrice\" was not injected: check your FXML file 'search.fxml'.";
		assert maxPrice != null : "fx:id=\"maxPrice\" was not injected: check your FXML file 'search.fxml'.";
		assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'search.fxml'.";
		assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'search.fxml'.";
		assert lblMinPrice != null : "fx:id=\"lblMinPrice\" was not injected: check your FXML file 'search.fxml'.";
		assert lblMaxPrice != null : "fx:id=\"lblMaxPrice\" was not injected: check your FXML file 'search.fxml'.";
		lblMaxPrice.setText("$0");
		lblMinPrice.setText("$0");

		minPrice.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.doubleValue() > maxPrice.getValue()) {
				lblMinPrice.setFill(javafx.scene.paint.Color.RED);
			} else {
				lblMinPrice.setFill(javafx.scene.paint.Color.BLACK);
			}

			Integer intValue = newValue.intValue();
			lblMinPrice.setText("$" + intValue);
		});
		maxPrice.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.doubleValue() < minPrice.getValue()) {
				lblMaxPrice.setFill(javafx.scene.paint.Color.RED);
			} else {
				lblMaxPrice.setFill(javafx.scene.paint.Color.BLACK);
			}
			Integer intValue = newValue.intValue();
			lblMaxPrice.setText("$" + intValue);
		});

		
		selectCategory.getItems().setAll(CategoryLogic.getInstance().getAllCategories());
		
	}
}
