package Control.Logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.User;
import Model.Consts;

public class UserLogic {
	private static UserLogic _instance;
	private Random _random;
	private UserLogic() {
		_random = new Random();
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
							rs.getString(i++),rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	/**
	 * fetches all users from DB file.
	 * @return ArrayList of users.
	 */
	public Double getUserBalance(String userAddress, String userSig) {
		Double result = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					
					) {
				PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_USER_BALANCE);
				stmt.setString(1, userAddress);
				stmt.setString(2,  userSig);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					result = rs.getDouble(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public User getBuyerEmailByOrder(Integer orderID) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					
					) {
				PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SELECT_BUYER_BY_ORDER);
				stmt.setInt(1, orderID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					return new User(null, null, rs.getString("UserName"), null, null, rs.getString("Email"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<User> getSellerEmailsByOrder(Integer orderID) {
		List<User> toReturn = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					
					) {
				PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SELECT_SELLER_BY_ORDER);
				stmt.setInt(1, orderID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					User u = new User(null, null, rs.getString("UserName"), null, null, rs.getString("Email"));
					toReturn.add(u);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*----------------------------------------- ADD / REMOVE / UPDATE USER METHODS --------------------------------------------*/

	/**
	 * Adding a new Employee with the parameters received from the form.
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addUser(String username, String password,
	String email, String phone) {
		try {
			
			
			String userSignature = generateUserSignature();
			String publicAddress = generatePublicAddress();
			
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_USER)) {
				
				int i = 1;
				stmt.setString(i++, publicAddress); // can't be null
				stmt.setString(i++, userSignature); // can't be null
				stmt.setString(i++, username); // can't be null
				stmt.setString(i++, password); // can't be null
				
				if (phone != null)
					stmt.setString(i++, phone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (email != null)
					stmt.setString(i++, email);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				
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

	private String generateUserSignature() {
		String charPool = "abcdefghijklmnopqrstuvwxyz";
		charPool = charPool.toUpperCase();
		String toReturn = "";
		Boolean finished = false;
		System.out.println("Generating user signature");
		while(!finished)
		{
			// X
			toReturn = String.valueOf(_random.nextInt(9)+1);
			// Y
			toReturn += String.valueOf(_random.nextInt(10));
			// Z
			toReturn += String.valueOf(charPool.charAt(_random.nextInt(charPool.length())));

			System.out.println("Generated signature: " + toReturn);
			// validate
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_CHECK_SIGNATURE);
						) {
					stmt.setString(1, toReturn);
					ResultSet rs = stmt.executeQuery();
					rs.next();
					System.out.println("Received count: " + rs.getInt("count"));
					if (rs.getInt("count") <= 0)
					{
						System.out.println("Didn't find an existing user signature");
						// There is no duplicate Public Address
						finished = true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}
		}
		
		return toReturn;
	}

	private String generatePublicAddress() {
		String charPool = "abcdefghijklmnopqrstuvwxyz0123456789";
		charPool = charPool.toUpperCase();
		Boolean finished = false;
		String toReturn = "";
		while(!finished)
		{
			toReturn = "";
			for(int i = 0; i < 14; i++)
			{
				toReturn += String.valueOf(charPool.charAt(_random.nextInt(charPool.length())));
			}
			// validate
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_CHECK_ADDRESS);
						) {
					stmt.setString(1, toReturn);
					ResultSet rs = stmt.executeQuery();
					rs.next();
					if (rs.getInt("count") <= 0)
					{
						// There is no duplicate Public Address
						finished = true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}
		}
		
		return toReturn;
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
	
//	/**
//	 * Editing a exist employee with the parameters received from the form.
//	 * return true if the update was successful, else - return false
//     * @return 
//	 */
//	public boolean editUser(String publicAddress, String userSignature,String username, String password,
//			String email, String phone, int type) {
//		try {
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_USER)) {
//				int i = 1;
//
//				stmt.setString(i++, publicAddress); // can't be null
//				stmt.setString(i++, userSignature); // can't be null
//				stmt.setString(i++, username); // can't be null
//				stmt.setString(i++, password); // can't be null
//				if (email != null)
//					stmt.setString(i++, email);
//				else
//					stmt.setNull(i++, java.sql.Types.VARCHAR);
//				
//				if (phone != null)
//					stmt.setString(i++, phone);
//				else
//					stmt.setNull(i++, java.sql.Types.VARCHAR);
//				
//				stmt.setInt(i++, type); // can't be null
//				stmt.executeUpdate();
//				return true;
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	
	
}
