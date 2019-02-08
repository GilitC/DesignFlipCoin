package Control.Logic;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.User;
import Model.Consts;
import Model.Product;

public class ProductLogic {
	private static ProductLogic _instance;

	private ProductLogic() {
	}

	public static ProductLogic getInstance() {
		if (_instance == null)
			_instance = new ProductLogic();
		return _instance;
	}

	/**
	 * fetches all products from DB file.
	 * @return ArrayList of users.
	 */
	public ArrayList<Product> getALLProducts() {
		ArrayList<Product> results = new ArrayList<Product>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_PRODUCTS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Product(rs.getInt(i++), rs.getString(i++), rs.getURL(i++), rs.getString(i++),
							rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++),rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	/*----------------------------------------- ADD / REMOVE / UPDATE USER METHODS --------------------------------------------*/

	/**
	 * Adding a new Recommendation with the parameters received from the form.
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addProduct( String productName, URL picture, String description, double pricePerUnit,
			int quantityInStock, int categoryID, String sellerAddress, String sellerSignature) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_PRODUCT)) {
				
				int i = 1;
				stmt.setString(i++, productName); 
				stmt.setURL(i++, picture); 
				stmt.setString(i++, description);
				stmt.setDouble(i++, pricePerUnit);
				stmt.setInt(i++, quantityInStock);
				stmt.setInt(i++, categoryID);
				stmt.setString(i++, sellerAddress);
				stmt.setString(i++, sellerSignature);
				
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Editing an existing Recommendation with the parameters received from the form.
	 * return true if the update was successful, else - return false
     * @return 
	 */
	public boolean updateProduct(int prodID, String productName, URL picture, String description, double pricePerUnit,
			int quantityInStock, int categoryID) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_PRODUCT)) {
				int i = 1;
			
				stmt.setString(i++, productName); 
				stmt.setURL(i++, picture); 
				stmt.setString(i++, description);
				stmt.setDouble(i++, pricePerUnit);
				stmt.setInt(i++, quantityInStock);
				stmt.setInt(i++, categoryID);
				
				stmt.setInt(i++, prodID); // can't be null
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}