package Control.Logic;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import Model.BKWallet;
import Model.BSWallet;
import Model.Consts;
import Model.Wallet;


public class WalletLogic {

	private static WalletLogic _instance;
	private Random _random;
	
	private WalletLogic() {
		_random = new Random();
	}

	public static WalletLogic getInstance() {
		if (_instance == null)
			_instance = new WalletLogic();
		return _instance;
	}
	
	/**
	 * fetches all wallets from DB file belonging to a specific user
	 * @return ArrayList of wallets.
	 */
	public ArrayList<Wallet> getAllMyWallets(String userAdd, String userSig) {
		ArrayList<Wallet> results = new ArrayList<Wallet>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_ALLMYWALLETS);
					) {
				stmt.setString(1, userAdd);
				stmt.setString(2, userSig);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int i = 1;
					results.add(new Wallet(rs.getString(i++), rs.getDouble(i++), rs.getBoolean(i++), rs.getBoolean(i++), rs.getBoolean(i++), rs.getDouble(i++), rs.getDouble(i++),
							rs.getString(i++), rs.getString(i++)));
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
	 * fetches all BS wallets from DB file belonging to a specific user
	 * @return ArrayList of BitCoin space wallets.
	 */
	public ArrayList<BSWallet> getAllMyBCSpaceWallets(String userAdd, String userSig) {
		ArrayList<BSWallet> results = new ArrayList<BSWallet>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_VIEW_BSWALLET_BYUSER);
					) {
				stmt.setString(1, userAdd);
				stmt.setString(2, userSig);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int i = 1;
					results.add(new BSWallet(rs.getString(i++), rs.getDouble(i++), rs.getBoolean(i++), rs.getBoolean(i++), rs.getBoolean(i++), rs.getDouble(i++), rs.getDouble(i++),
							rs.getString(i++), rs.getString(i++), rs.getInt(i++)));
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
	 * fetches all BK wallets from DB file belonging to a specific user
	 * @return ArrayList of BitCoin Knots wallets.
	 */
	public ArrayList<BKWallet> getAllMyBCKnoltsWallets(String userAdd, String userSig) {
		ArrayList<BKWallet> results = new ArrayList<BKWallet>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_VIEW_BKWALLET_BYUSER);
					) {
				stmt.setString(1, userAdd);
				stmt.setString(2, userSig);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int i = 1;
					results.add(new BKWallet(rs.getString(i++), rs.getDouble(i++), rs.getBoolean(i++), rs.getBoolean(i++), rs.getBoolean(i++), rs.getDouble(i++), rs.getDouble(i++),
							rs.getString(i++), rs.getString(i++), rs.getDouble(i++)));
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
	 * @return what is the free size of a transaction given in the default wallet
	 */
	public int getFreeTranSize() {
		int result = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_FREETRANSSIZE);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					result = rs.getInt(i++);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @return what is the default PriceForDiscount
	 */
	public int getPriceForDiscount() {
		int result = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_PRICE4DISCOUNT);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					result = rs.getInt(i++);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @return what is the default DiscountPercentPerFee
	 */
	public double getDiscountPercentPerFee() {
		double result = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_DISCOUNTPRECENTPERFEE);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					result = rs.getDouble(i++);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @return what is the default PriceForExpansion
	 */
	public int getPriceForExpansion() {
		int result = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_PRICEFOREXPANSION);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					result = rs.getInt(i++);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @return what is the default TransactionSizeForExpansion
	 */
	public int getTransactionSizeForExpansion() {
		int result = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_TRASSIZE4EXPANSION);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					result = rs.getInt(i++);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @return what is the default TransactionMaxSize
	 */
	public int getTransactionMaxSize() {
		int result = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_TRANSMAXSIZE);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					result = rs.getInt(i++);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @return what is the default TransactionMinSize
	 */
	public int getTransactionMinSize() {
		int result = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_TRANSMINSIZE);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					result = rs.getInt(i++);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/*----------------------------------------- ADD / REMOVE / UPDATE  METHODS --------------------------------------------*/

	/**
	 * Adding a new Wallet with the parameters received from the form.
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public String addWallet( double price, boolean installedOnComputer, boolean installedOnSmartphone,
			boolean installedOnTablet, double cashFlow, double pendingAmount, String publicAddress,
			String userSignature) {
		String myUniqueWalletAdd =null;
		try {
			
		    myUniqueWalletAdd = generateUniqueWalletAddress();
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_WALLET)) {
				
				int i = 1;
				stmt.setString(i++, myUniqueWalletAdd); 
				stmt.setDouble(i++, price);
				stmt.setBoolean(i++, installedOnComputer);
				stmt.setBoolean(i++, installedOnSmartphone);
				stmt.setBoolean(i++, installedOnTablet);
				stmt.setDouble(i++, cashFlow);
				stmt.setDouble(i++, pendingAmount);
				stmt.setString(i++, publicAddress);
				stmt.setString(i++, userSignature);
				
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return myUniqueWalletAdd;
	}
	
	/**
	 * Adding a new BK Wallet with the parameters received from the form. - After adding a wallet above
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addBKWallet( String uniqueAddress, double discountPercent) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_BKWALLET)) {
				
				int i = 1;
				stmt.setString(i++, uniqueAddress); 
				stmt.setDouble(i++, discountPercent);
				
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
	 * Adding a new BS Wallet with the parameters received from the form. - After adding a wallet above
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addBSWallet( String uniqueAddress, int TransactionSize) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_BSWALLET)) {
				
				int i = 1;
				stmt.setString(i++, uniqueAddress); 
				stmt.setInt(i++, TransactionSize);
				
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
	 * Method generates a Unique Wallet Address while making sure it does not yet exist
	 * return true if the update was successful, else - return false
     * @return 
	 */
	private String generateUniqueWalletAddress() {
		String charPool = "abcdefghijklmnopqrstuvwxyz0123456789";
		charPool = charPool.toUpperCase();
		Boolean finished = false;
		String toReturn = "";
		while(!finished)
		{
			toReturn = "";
			for(int i = 0; i < 10; i++)
			{
				toReturn += String.valueOf(charPool.charAt(_random.nextInt(charPool.length())));
			}
			// validate
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_CHECK_WALLETADDRESS);
						) {
					stmt.setString(1, toReturn);
					ResultSet rs = stmt.executeQuery();
					rs.next();
					if (rs.getInt("count") <= 0)
					{
						// There is no duplicate Address
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
	 * Editing an existing Wallet Profit with the parameters received from the form.
	 * return true if the update was successful, else - return false
     * @return 
	 */
	public boolean updateWallet(String uniqueAddress, double cashFlow, double pendingAmount) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_WALLET_Profit)) {
				int i = 1;

				stmt.setDouble(i++, cashFlow);
				stmt.setDouble(i++, pendingAmount);
				
				stmt.setString(i++, uniqueAddress); // can't be null
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
