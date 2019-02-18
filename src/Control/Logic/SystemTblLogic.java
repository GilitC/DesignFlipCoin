package Control.Logic;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Consts;
import Model.SystemTbl;


public class SystemTblLogic {
	private static SystemTblLogic _instance;

	private SystemTblLogic() {
	}

	public static SystemTblLogic getInstance() {
		if (_instance == null)
			_instance = new SystemTblLogic();
		return _instance;
	}

	/**
	 * fetches all products from DB file.
	 * @return ArrayList of users.
	 */
	public SystemTbl getALLSystemParameters(String version) {
		SystemTbl results = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_SYSTEM_PARAMETERS);
							) {
				
					stmt.setString(1, version);
					ResultSet rs = stmt.executeQuery();
					
				while (rs.next()) {
					int i = 1;
					results = (new SystemTbl(version, rs.getInt("TransactionMinSize"), rs.getInt("TransactionMaxSize"), rs.getInt("TransactionSizeForExpansion"), rs.getInt("PriceForExpansion"),
							rs.getDouble("DiscountPercentPerFee"), rs.getInt("PriceForDiscount"), rs.getInt("TransactionSizeFree")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	
	
	/*----------------------------------------- ADD / REMOVE / UPDATE  METHODS --------------------------------------------*/

	
	/**
	 * return true if the update was successful, else - return false
     * @return 
	 */
	public boolean updateSystemParameters(String version, int transactionMinSize, int transactionMaxSize, int transactionSizeForExpansion,
			int priceForExpansion, double discountPercentPerFee, int priceForDiscount, int transactionSizeFree) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPDATE_SYSTEM_PARAMETERS)) {
				int i = 1;
			
				stmt.setInt(i++, transactionMinSize);
				stmt.setInt(i++, transactionMaxSize);
				stmt.setInt(i++, transactionSizeForExpansion);
				stmt.setInt(i++, priceForExpansion);
				stmt.setDouble(i++, discountPercentPerFee);
				stmt.setInt(i++, priceForDiscount);
				stmt.setInt(i++, transactionSizeFree);
				
				stmt.setString(i++, version); // can't be null
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
