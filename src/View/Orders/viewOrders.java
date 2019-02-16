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
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class viewOrders {
	private static Integer od_id = 0;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="viewRecommend"
	private AnchorPane viewRecommend; // Value injected by FXMLLoader

	@FXML
	private TableView<Order> tblOrders;

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert viewRecommend != null : "fx:id=\"viewRecommend\" was not injected: check your FXML file 'viewOrders.fxml'.";

		List<TableColumn<Order, ?>> colList = new ArrayList<>();
		colList.add(new TableColumn<Order, Integer>("ID"));
		colList.get(0).setCellValueFactory(new PropertyValueFactory<>("counterID"));
		colList.add(new TableColumn<Order, Double>("Price"));
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

		public Double getProductName() {
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
	}
	
}