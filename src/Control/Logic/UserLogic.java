package Control.Logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.User;
import Model.Consts;

public class UserLogic {
	private static UserLogic _instance;

	private UserLogic() {
	}

	public static UserLogic getInstance() {
		if (_instance == null)
			_instance = new UserLogic();
		return _instance;
	}

	/**
	 * fetches all users from DB file.
	 * @return ArrayList of users.
	 */
	public ArrayList<User> getALLUsers() {
		ArrayList<User> results = new ArrayList<User>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_USERS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new User(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++),rs.getString(i++), rs.getInt(i++)));
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
	 * Adding a new Employee with the parameters received from the form.
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addUser(String publicAddress, String userSignature,String username, String password,
	String email, String phone, int type) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_USER)) {
				
				int i = 1;
				stmt.setString(i++, publicAddress); // can't be null
				stmt.setString(i++, userSignature); // can't be null
				stmt.setString(i++, username); // can't be null
				stmt.setString(i++, password); // can't be null
				if (email != null)
					stmt.setString(i++, email);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				
				if (phone != null)
					stmt.setString(i++, phone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				
				stmt.setInt(i++, type); // can't be null
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
	 * Delete the selected user in form.
	 * Return true if the deletion was successful, else - return false
	 * @param userID 
     * @return boolean - if the user was removed from the DB
	 */
	public boolean removeUser(String publicAddress, String userSignature) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_DEL_USER)) {
				
				stmt.setString(1, publicAddress);
				stmt.setString(2, userSignature);
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
	 * Editing a exist employee with the parameters received from the form.
	 * return true if the update was successful, else - return false
     * @return 
	 */
	public boolean editUser(String publicAddress, String userSignature,String username, String password,
			String email, String phone, int type) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_USER)) {
				int i = 1;

				stmt.setString(i++, publicAddress); // can't be null
				stmt.setString(i++, userSignature); // can't be null
				stmt.setString(i++, username); // can't be null
				stmt.setString(i++, password); // can't be null
				if (email != null)
					stmt.setString(i++, email);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				
				if (phone != null)
					stmt.setString(i++, phone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				
				stmt.setInt(i++, type); // can't be null
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
