/**
 * Sample Skeleton for 'pay.fxml' Controller Class
 */

package View.user;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Control.SysData;
import Control.Logic.OrderLogic;
import Control.Logic.UserLogic;
import Model.Product;
import Model.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class payController {

	public static List<Product> _products;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	private Double totalSum;

	@FXML // fx:id="addReco"
	private AnchorPane addReco; // Value injected by FXMLLoader

	@FXML
	private Label lblOrderSum;

	private static Integer pd_id = 1;

	@FXML
	private Label lblTotalBalance;

	@FXML // fx:id="btnSearch"
	private Button btnSearch; // Value injected by FXMLLoader

	@FXML // fx:id="btnCancel"
	private Button btnCancel; // Value injected by FXMLLoader
	@FXML
	private TableView<ProductData> tblProducts;

	@FXML
	void doCancel(ActionEvent event) {
		// Cancel
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	@FXML
	void doOrder(ActionEvent event) {
		// This will create an order
		Integer orderID = OrderLogic.createOrder(totalSum, _products);
		if (orderID > 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Order #"+orderID+" Created!");
			alert.setHeaderText("Your order has been successfully created!");
			alert.setContentText("Please note that in order to pay for your order, go to \"Pending Orders\" page");
			alert.showAndWait();
			Stage stage = (Stage) btnCancel.getScene().getWindow();
			// do what you have to do
			stage.close();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Oops!");
			alert.setHeaderText("We have failed creating an order.");
			alert.setContentText("Please try again!");
			alert.showAndWait();
		}
		
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		
		ResultSet rs = OrderLogic.getOrdersByLoggedInUser();
		
		try {
			while(rs.next()) {
				System.out.println(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pd_id = 1;
		assert addReco != null : "fx:id=\"addReco\" was not injected: check your FXML file 'pay.fxml'.";
		assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'pay.fxml'.";
		assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'pay.fxml'.";

		TableColumn<ProductData, Integer> col1_id = new TableColumn<>("id");
		TableColumn<ProductData, String> col2_pname = new TableColumn<>("Product");
		TableColumn<ProductData, Double> col3_price = new TableColumn<>("Price (BTC)");

		TableColumn col4_seller = new TableColumn("Seller");

		TableColumn<ProductData, String> col4_1_seller = new TableColumn<>("Address");
		TableColumn<ProductData, String> col4_2_seller = new TableColumn<>("Signature");
		col4_seller.getColumns().add(col4_1_seller);
		col4_seller.getColumns().add(col4_2_seller);

		tblProducts.getColumns().remove(0, tblProducts.getColumns().size());

		col1_id.setCellValueFactory(new PropertyValueFactory<ProductData, Integer>("counterID"));
//        col1_id.setCellValueFactory(new PropertyValueFactory<ProductData, Integer>("productID"));
		col2_pname.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productName"));
		col3_price.setCellValueFactory(new PropertyValueFactory<ProductData, Double>("productPrice"));

		col4_1_seller.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productSellerAddress"));
		col4_2_seller.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productSellerSignature"));

		tblProducts.getColumns().addAll(col1_id, col2_pname, col3_price, col4_seller);

		List<ProductData> dataList = new ArrayList<>();

		totalSum = 0.0;

		for (Product p : _products) {
			ProductData productData = new ProductData(p.getProductID(), p.getProductName(), p.getPricePerUnit(),
					p.getSellerAddress(), p.getSellerSignature());
			System.out.println("Adding product: " + productData.getProductName());
			dataList.add(productData);
			totalSum += p.getPricePerUnit();
		}

		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		String addrs = loggedIn.getPublicAddress();
		String signt = loggedIn.getUserSignature();
		Double totalBalance = UserLogic.getInstance().getUserBalance(addrs, signt);

		lblOrderSum.setText(totalSum + " BTC");
		lblTotalBalance.setText(totalBalance + " BTC");

		ObservableList<ProductData> data = FXCollections.observableArrayList(dataList);

		tblProducts.setItems(data);

	}

	public static class ProductData {

		private final SimpleIntegerProperty productID;
		private final SimpleStringProperty productName;
		private final SimpleDoubleProperty productPrice;
		private final SimpleIntegerProperty counterID;
		private final SimpleStringProperty productSellerAddress;
		private final SimpleStringProperty productSellerSignature;

		private ProductData(Integer ID, String name, Double price, String productSellerAddress,
				String productSellerSignature) {
			this.productID = new SimpleIntegerProperty(ID);
			this.productName = new SimpleStringProperty(name);
			this.productPrice = new SimpleDoubleProperty(price);
			this.productSellerAddress = new SimpleStringProperty(productSellerAddress);
			this.productSellerSignature = new SimpleStringProperty(productSellerSignature);
			this.counterID = new SimpleIntegerProperty(pd_id++);
		}

		public Integer getCounterID() {
			return counterID.get();
		}

		public Integer getProductID() {
			return productID.get();
		}

		public String getProductName() {
			return productName.get();
		}

		public Double getProductPrice() {
			return productPrice.get();
		}

		public void setProductID(Integer ID) {
			this.productID.set(ID);
		}

		public void setProductName(String name) {
			this.productName.set(name);
		}

		public void setProductPrice(Double price) {
			this.productPrice.set(price);
		}

		public String getProductSellerSignature() {
			return this.productSellerSignature.get();
		}

		public void setProductSellerSignature(String productSellerSignature) {
			this.productSellerSignature.set(productSellerSignature);
		}

		public String getProductSellerAddress() {
			return this.productSellerAddress.get();
		}

		public void setProductSellerAddress(String productSellerAddress) {
			this.productSellerAddress.set(productSellerAddress);
		}
	}
}
