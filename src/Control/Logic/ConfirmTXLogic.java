package Control.Logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.TransactionConfirm;
import Model.Consts;


public class ConfirmTXLogic {
	private static ConfirmTXLogic _instance;

	private ConfirmTXLogic() {

	}

	public static ConfirmTXLogic getInstance() {
		if (_instance == null)
			_instance = new ConfirmTXLogic();
		return _instance;
	}

	/**
	 * fetches all pay transactions from DB file.
	 * @return ArrayList of users.
	 */
	public ArrayList<TransactionConfirm> getALLPayTXs() {
		ArrayList<TransactionConfirm> results = new ArrayList<TransactionConfirm>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_TRANSCONFIRM);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new TransactionConfirm(rs.getInt(i++), rs.getString(i++), rs.getInt(i++), rs.getDate(i++), rs.getDate(i++), rs.getDouble(i++),
							rs.getString(i++), rs.getDouble(i++),  rs.getString(i++),  rs.getString(i++),  rs.getString(i++),  rs.getString(i++), rs.getString(i++), rs.getBoolean(i++), rs.getDate(i++)));
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
	 * Adding a new Confirm Transaction with the parameters received from the form.
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addConfirmTransaction( String description, int sizeInBytes, double fee, String state, double payValue, String creatingAddress,
			String creatingSignature, String destinationAddress, String destinationSignature, String walletAddress, boolean confirmed,
			Date shipmentTime) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_TRANSCORNFIM)) {
				int i = 1;
				stmt.setString(i++, description); // can't be null
				stmt.setInt(i++, sizeInBytes); // can't be null
				stmt.setDouble(i++, fee); // can't be null
				stmt.setString(i++, state); // can't be null
				stmt.setDouble(i++, payValue); // can't be null
				stmt.setString(i++, creatingAddress); // can't be null
				stmt.setString(i++, creatingSignature); // can't be null
				stmt.setString(i++, destinationAddress); // can't be null
				stmt.setString(i++, destinationSignature); // can't be null
				stmt.setString(i++, walletAddress); // can't be null
				stmt.setBoolean(i++, confirmed); // can't be null
				stmt.setDate(i++, (java.sql.Date) shipmentTime); // can't be null
				
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
