/**
 * Sample Skeleton for 'viewOrders.fxml' Controller Class
 */

package View.Orders;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Control.Logic.OrderLogic;
import View.user.payController.ProductData;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class viewOrders {
	private static Integer od_id = 1;
	private static Integer tx_id = 1;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	private Label lblDisplay;
	
	@FXML // fx:id="viewRecommend"
	private AnchorPane viewRecommend; // Value injected by FXMLLoader

	@FXML
	private TableView<Order> tblOrders;
	
	@FXML
	private Button btnPay;
	
	@FXML
	private TableView<TX> tblTX;

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert viewRecommend != null : "fx:id=\"viewRecommend\" was not injected: check your FXML file 'viewOrders.fxml'.";
		od_id = 1;
		List<TableColumn<Order, ?>> colList = new ArrayList<>();
		colList.add(new TableColumn<Order, Integer>("ID"));
		colList.get(0).setCellValueFactory(new PropertyValueFactory<>("counterID"));
		colList.add(new TableColumn<Order, Double>("Sum"));
		colList.get(1).setCellValueFactory(new PropertyValueFactory<>("orderSum"));
		colList.add(new TableColumn<Order, Double>("Paid (Confirmed)"));
		colList.get(2).setCellValueFactory(new PropertyValueFactory<>("paidSum"));
		colList.add(new TableColumn<Order, Double>("Paid (Unconfirmed)"));
		colList.get(3).setCellValueFactory(new PropertyValueFactory<>("unpaidSum"));
		colList.add(new TableColumn<Order, String>("Status"));
		colList.get(4).setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

		ResultSet ordersSet = OrderLogic.getOrdersByLoggedInUser();
		List<Order> ordersList = new ArrayList<>();
		try {
			while(ordersSet.next())
			{
				ordersList.add(new Order(ordersSet.getInt("ID"), ordersSet.getDouble("totalSum"), ordersSet.getDouble("paid"), ordersSet.getBoolean("status") ? "Finished" : "Pending"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tblOrders.getColumns().addAll(colList);

		ObservableList<Order> data = FXCollections.observableArrayList(ordersList);
		
		tblOrders.setItems(data);
		
		
		/**
		 * Initiate tblTX
		 */
		List<TableColumn<TX, ?>> txColList = new ArrayList<>();
		txColList.add(new TableColumn<TX, Integer>("ID"));
		txColList.get(0).setCellValueFactory(new PropertyValueFactory<>("txId"));
		txColList.add(new TableColumn<TX, Double>("Amount Required"));
		txColList.get(1).setCellValueFactory(new PropertyValueFactory<>("txPrice"));
		txColList.add(new TableColumn<TX, String>("Seller Address"));
		txColList.get(2).setCellValueFactory(new PropertyValueFactory<>("sellerAddress"));
		txColList.add(new TableColumn<TX, String>("Seller Signature"));
		txColList.get(3).setCellValueFactory(new PropertyValueFactory<>("sellerSignature"));
		
		if(tblTX == null)
		{
			System.err.println("HELP ME ITS NULL");
		} else {
			tblTX.getColumns().addAll(txColList);
		}
		
		
		
		
	}
	
    @FXML
    void openPayWindow(ActionEvent event) {

    	if(tblTX.getSelectionModel().getSelectedItem() == null)
    	{
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Oops!");
			alert.setHeaderText("You didn't choose an item to pay.");
			alert.setContentText("Please choose first!");
			alert.showAndWait();
    	}
    	
    	payController.orderObject = tblOrders.getSelectionModel().getSelectedItem();
    	payController.orderTX = tblTX.getSelectionModel().getSelectedItem();
    	
    	
    	try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pay.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initStyle(StageStyle.UNDECORATED);
		    stage.setTitle("Pay Order #"+payController.orderObject.getOrderID());
		    stage.setScene(new Scene(root1));  
		    stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
	
	private void displayTransactions(Order order)
	{
		tx_id = 1;
		ResultSet gatheredTransactions = OrderLogic.gatherTransactions(order.getOrderID());
		if(gatheredTransactions == null)
		{
			return;
		}
		
		try {
			tblTX.getItems().removeAll();
			List<TX> txList = new ArrayList<>();
			while(gatheredTransactions.next())
			{
				// Generate TX
				Double price = gatheredTransactions.getDouble("totalPrice");
				String sellerAddress = gatheredTransactions.getString("sellerAddress");
				String sellerSignature = gatheredTransactions.getString("sellerSignature");
				
				txList.add(new TX(price, sellerAddress, sellerSignature));
			}
			
			if(txList.size() > 0)
			{
				ObservableList<TX> txData = FXCollections.observableArrayList(txList);
				
				tblTX.setItems(txData);
				
				tblTX.setVisible(true);
				btnPay.setVisible(true);
				
			} else {
				tblTX.setVisible(false);
				btnPay.setVisible(false);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    @FXML
    void openTXes(MouseEvent event) {
    
    	Order orderChosen = tblOrders.getSelectionModel().getSelectedItem();
    	if(orderChosen == null)
    	{
    		return;
    	}
    	System.out.println("Chosen item: " + orderChosen);
    	displayTransactions(orderChosen);
    }
	
    public static class TX {
    	private final SimpleIntegerProperty txId;
    	private final SimpleDoubleProperty txPrice;
    	private final SimpleStringProperty sellerAddress;
    	private final SimpleStringProperty sellerSignature;
    	
    	private TX(Double txPrice, String sellerAddress, String sellerSignature) {
    		this.txId = new SimpleIntegerProperty(tx_id++);
    		this.txPrice = new SimpleDoubleProperty(txPrice);
    		this.sellerAddress = new SimpleStringProperty(sellerAddress);
    		this.sellerSignature = new SimpleStringProperty(sellerSignature);
    		
    	}
    	
    	public Integer getTxId() {
    		return this.txId.get();
    	}
    	public Double getTxPrice() {
    		return this.txPrice.get();
    	}
    	public String getSellerAddress() {
    		return this.sellerAddress.get();
    	}
    	public String getSellerSignature() {
    		return this.sellerSignature.get();
    	}
    }
    
	public static class Order {

		private final SimpleIntegerProperty orderID;
		private final SimpleDoubleProperty orderSum;
		private final SimpleDoubleProperty unpaidSum;
		private final SimpleDoubleProperty paidSum;
		private final SimpleIntegerProperty counterID;
		private final SimpleStringProperty orderStatus;

		private Order(Integer ID, Double orderSum, Double paidSum, String orderStatus) {
			this.orderID = new SimpleIntegerProperty(ID);
			this.orderSum = new SimpleDoubleProperty(orderSum);
			this.unpaidSum = new SimpleDoubleProperty(OrderLogic.getUnconfirmedPaid(ID));
			this.paidSum = new SimpleDoubleProperty(paidSum);
			this.counterID = new SimpleIntegerProperty(od_id++);
			this.orderStatus = new SimpleStringProperty(orderStatus);
		}

		public Integer getCounterID() {
			return counterID.get();
		}

		public Integer getOrderID() {
			return orderID.get();
		}

		public Double getOrderSum() {
			return orderSum.get();
		}

		public Double getUnpaidSum() {
			return unpaidSum.get();
		}

		public void setOrderID(Integer ID) {
			this.orderID.set(ID);
		}

		public void setOrderSum(Double orderSum) {
			this.orderSum.set(orderSum);
		}

		public void setUnpaidSum(Double unpaidSum) {
			this.unpaidSum.set(unpaidSum);
		}

		public Double getPaidSum() {
			return paidSum.get();
		}
		
		public void setPaidSum(Double paidSum) {
			this.paidSum.set(paidSum);
		}
		
		public String getOrderStatus() {
			return orderStatus.get();
		}
		
		public void setOrderStatus(String orderStatus) {
			this.orderStatus.set(orderStatus);
		}
		
		@Override
		public String toString() {
			return "#"+this.orderID.get();
		}
		
		
	}
	
}