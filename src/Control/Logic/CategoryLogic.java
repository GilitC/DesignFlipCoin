package Control.Logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Consts;
import Model.ProductCategory;


public class CategoryLogic {
	private static CategoryLogic _instance;

	private CategoryLogic() {
	}

	public static CategoryLogic getInstance() {
		if (_instance == null)
			_instance = new CategoryLogic();
		return _instance;
	}

	/**
	 * fetches all Categories from DB file.
	 * @return ArrayList of Categories.
	 */
	public ArrayList<ProductCategory> getAllCategories() {
		ArrayList<ProductCategory> results = new ArrayList<ProductCategory>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_CATEGORIES);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new ProductCategory(rs.getInt(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	
	/*----------------------------------------- ADD / REMOVE / UPDATE EMPLOYEE METHODS --------------------------------------------*/

	/**
	 * Adding a new Category with the parameters received from the form.
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addCategory( String CategoryName ) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_CATEGORY)) {
				
				int i = 1;
				stmt.setString(i++, CategoryName); // can't be null
				
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
	 * Editing an existing Category with the parameters received from the form.
	 * return true if the update was successful, else - return false
     * @return 
	 */
	public boolean updateCategory(String name, int SerialNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_CATEGORY)) {
				int i = 1;
			
				stmt.setString(i++, name); // can't be null
				
				stmt.setInt(i++, SerialNumber); // can't be null
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
