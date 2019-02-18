package Control.Logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Control.Main;
import Control.SysData;
import Model.Consts;
import Model.Product;
import Model.User;

public abstract class OrderLogic {

	

	public static Integer createOrder(Double totalSum, List<Product> products) {
		
		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		String addrs = loggedIn.getPublicAddress();
		String signt = loggedIn.getUserSignature();
		
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_ORDER,CallableStatement.RETURN_GENERATED_KEYS)) {

				int i = 1;
				stmt.setDouble(i++, totalSum); // can't be null
				stmt.setString(i++, addrs);
				stmt.setString(i++, signt);
				int orderID = stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs != null && rs.next()){
					orderID = rs.getInt(1);
				}
				
				if(orderID > 0)
				{
					for(Product product : products)
					{
						CallableStatement pstmt = conn.prepareCall(Consts.SQL_ADD_ITEM_TO_ORDER);
						pstmt.setInt(1, orderID);
						pstmt.setInt(2, product.getProductID());
						pstmt.executeUpdate();
					}
				}
				
				return orderID;
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;

	}
	
	public static ResultSet getOrdersByLoggedInUser()
	{
		ResultSet toReturn = null;

		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		String addrs = loggedIn.getPublicAddress();
		String signt = loggedIn.getUserSignature();
		
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_ORDERS)) {

				int i = 1;
				stmt.setString(i++, addrs);
				stmt.setString(i++, signt);
				toReturn = stmt.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public static Double getUnconfirmedPaid(Integer orderID)
	{
		Double toReturn = 0.0;
		
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_ORDER_UNPAID_AMOUNT)) {

				int i = 1;
				stmt.setInt(i++, orderID);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next())
				{
					toReturn = rs.getDouble("unpaid");
					if(toReturn == null)
						toReturn = 0.0;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return toReturn;
		
	}

	public static ResultSet gatherTransactions(Integer orderID) {
		ResultSet toReturn = null;
		
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GATHER_ORDERS_BY_ID)) {

				int i = 1;
				stmt.setInt(i++, orderID);
				toReturn = stmt.executeQuery();
				return toReturn;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public static void updateOrder(Integer txID, Double amount) {
		// TODO Auto-generated method stub
		Integer orderID = TransactionLogic.getOrderID(txID);
		if(orderID == -1)
		{
			return;
		}
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPDATE_ORDER_PAID)) {

				int i = 1;
				stmt.setDouble(i++, amount);
				stmt.setInt(i++, orderID);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * This method is checking for open orders and closes them if needed
	 */
	public static void checkOrders()
	{
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_ORDERS_TO_CHANGE)) {
				conn.setAutoCommit(false);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					System.out.println("Updating order: " + rs.getInt("ID"));
					OrderLogic.finishOrder(rs.getInt("ID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void finishOrder(Integer orderID)
	{
		// SEND MAIL and create confirm trans
		System.out.println("Finishing order #"+orderID);
		
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPDATE_ORDER_STATUS)) {
				stmt.setInt(1, orderID);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String from = Main.getUSER_NAME();
		String pass = Main.getPASSWORD();
		
		User buyerEmail = UserLogic.getInstance().getBuyerEmailByOrder(orderID);
		List<User> sellerEmails = UserLogic.getInstance().getSellerEmailsByOrder(orderID);
		
		sellerEmails.add(buyerEmail);
		
		for(User u : sellerEmails)
		{
			System.out.println("Sending email to " + u.getEmail());
			String[] to = { u.getEmail() }; // list of recipient email addresses
			String subject = "New update on your wallet";
			
			String body = "Hello " + u.getUsername()+"!\nYour balance has been updated due to a completed transaction!\n\nPlease log into our application to view extra details and your updated balance.\n\n Thank you!";
			
			//When a new recommendation is added, we need to send the details to the user
			Main.sendFromGMail(from, pass, to, subject, body);
		}
	
		
		
	}
	

}
