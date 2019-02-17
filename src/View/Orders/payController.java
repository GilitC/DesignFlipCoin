/**
 * Sample Skeleton for 'pay.fxml' Controller Class
 */

package View.Orders;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Control.SysData;
import Control.Logic.OrderLogic;
import Control.Logic.PayTXLogic;
import Control.Logic.UserLogic;
import Control.Logic.WalletLogic;
import Model.Product;
import Model.User;
import Model.Wallet;
import View.Orders.viewOrders.Order;
import View.Orders.viewOrders.TX;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class payController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	public static TX orderTX;
	public static Order orderObject;
	
    @FXML
    private CheckBox boxAutomaticFee;

    @FXML
    private Spinner<Double> numFee;

	
	@FXML
	private Label lblOrderSum;

	@FXML
	private Label lblErr;
	
	private static Integer w_id = 1;

	@FXML // fx:id="btnSearch"
	private Button btnPay; // Value injected by FXMLLoader

	@FXML // fx:id="btnCancel"
	private Button btnCancel; // Value injected by FXMLLoader
	@FXML
	private TableView<WalletData> tblWallets;

	@FXML
	void doCancel(ActionEvent event) {
		// Cancel
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	public static class WalletData {
		private final SimpleIntegerProperty walletID;
		private final SimpleStringProperty walletAddress;
		private final SimpleDoubleProperty walletBalance;

		private WalletData(String walletAddress, Double walletBalance) {
			this.walletID = new SimpleIntegerProperty(w_id++);
			this.walletBalance = new SimpleDoubleProperty(walletBalance);
			this.walletAddress = new SimpleStringProperty(walletAddress);
		}

		public Integer getWalletID() {
			return this.walletID.get();
		}

		public String getWalletAddress() {
			return this.walletAddress.get();
		}

		public Double getWalletBalance() {
			return this.walletBalance.get();
		}

	}

	@FXML
	void doOrder(ActionEvent event) {
		// This will create an order
		System.out.println("Attempting to generate TX!");
		String description = "TX Bundle #"+orderTX.getTxId();
		Integer sizeInBytes = WalletLogic.getInstance().getFreeTranSize();
		Double fee = numFee.getValue();
		String state = "Pending";
		Double payValue = orderTX.getTxPrice();
		
		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		String creatingAddress = loggedIn.getPublicAddress();
		String creatingSignature = loggedIn.getUserSignature();
		String destinationAddress = orderTX.getSellerAddress();
		String destinationSignature = orderTX.getSellerSignature();
		String walletAddress = tblWallets.getSelectionModel().getSelectedItem().getWalletAddress();
		Integer orderID = orderObject.getOrderID();
		Date executionTimeDate = null;
		if(
		PayTXLogic.getInstance().addPayTransaction(description, sizeInBytes, executionTimeDate, fee, state, payValue, creatingAddress, creatingSignature, destinationAddress, destinationSignature, walletAddress, orderID))
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Yay!");
			alert.setHeaderText("A new transaction was successfully created!");
			alert.setContentText("Now you'll have to wait for our miners to pick it up!");
			alert.showAndWait();
			return;
		}
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Oops!");
		alert.setHeaderText("Your transaction could not be created.");
		alert.setContentText("Please try again later!");
		alert.showAndWait();
		
	}
	

    @FXML
    void updateWalletChosen(MouseEvent event) {
    	WalletData chosenWallet = tblWallets.getSelectionModel().getSelectedItem();
    	if(chosenWallet == null)
    		return;
    	updateBalance();
    	
    }

    

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {

		w_id = 1;
		assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'pay.fxml'.";

		/**
		 * Initiate wallet table
		 */
		List<TableColumn<WalletData, ?>> wColList = new ArrayList<>();
		wColList.add(new TableColumn<WalletData, Integer>("ID"));
		wColList.get(0).setCellValueFactory(new PropertyValueFactory<>("walletID"));
		wColList.add(new TableColumn<WalletData, Double>("walletAddress"));
		wColList.get(1).setCellValueFactory(new PropertyValueFactory<>("walletAddress"));
		wColList.add(new TableColumn<WalletData, String>("Available Balance"));
		wColList.get(2).setCellValueFactory(new PropertyValueFactory<>("walletBalance"));

		if (tblWallets == null) {
			System.err.println("HELP ME ITS NULL");
		} else {
			tblWallets.getColumns().addAll(wColList);
		}
		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		String addrs = loggedIn.getPublicAddress();
		String signt = loggedIn.getUserSignature();

		List<Wallet> walletList = WalletLogic.getInstance().getAllMyWallets(addrs, signt);
		List<WalletData> wDataList = new ArrayList<>();
		for (Wallet w : walletList) {
			wDataList.add(new WalletData(w.getUniqueAddress(), w.getCashFlow()));
		}

		ObservableList<WalletData> data = FXCollections.observableArrayList(wDataList);

		tblWallets.setItems(data);

		lblOrderSum.setText(orderTX.getTxPrice()+" BTC");
		btnPay.setDisable(true);
		
		boxAutomaticFee.setSelected(true);
		SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, orderTX.getTxPrice());
		numFee.setValueFactory(valueFactory);
		boxAutomaticFee.setText(boxAutomaticFee.getText() + "("+(orderTX.getTxPrice() * 0.05)+")");
		valueFactory.setValue(orderTX.getTxPrice() * 0.05);
		numFee.setDisable(true);
		
		boxAutomaticFee.setOnAction(handler -> {
			if(boxAutomaticFee.isSelected())
			{
				numFee.setDisable(true);
				numFee.getValueFactory().setValue(orderTX.getTxPrice() * 0.05);
				
			} else {
				numFee.setDisable(false);

			}
			
		});
		lblErr.setText("Not enough balance");
		numFee.valueProperty().addListener((obs, oldValue, newValue) -> 
			updateBalance());
	}

	private void updateBalance() {
		//Update balance
		Double sumTX = orderTX.getTxPrice();
		Double sumFee = numFee.getValue();
		Double totalSum = sumTX + sumFee;
		
		WalletData wd = tblWallets.getSelectionModel().getSelectedItem();
		if(wd != null)
		{
			if(totalSum > wd.getWalletBalance() )
			{
				lblErr.setVisible(true);
				btnPay.setDisable(true);
			} else {
				lblErr.setVisible(false);
				btnPay.setDisable(false);
			}
		}
	
	}

}
