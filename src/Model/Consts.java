package Model;

import java.net.URLDecoder;

/**
 * http://www.javapractices.com/topic/TopicAction.do?Id=2
 */
public final class Consts {
	private Consts() {
		throw new AssertionError();
	}

	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
	
	
	/*----------------------------------------- USER QUERIES -----------------------------------------*/
	public static final String SQL_SEL_USERS = "SELECT * FROM TblUser";
	public static final String SQL_DEL_USER = "{ call qryDelUser(?) }";
	public static final String SQL_INS_USER = "{ call qryInsUser(?,?,?,?,?,?,?) }";
	public static final String SQL_UPD_USER = "{ call qryUpdUser(?,?,?,?,?,?,?) }";

	/*----------------------------------------- RECOMMENDATION QUERIES --------------------------------------------*/
	public static final String SQL_SEL_RECOMMENDATION = "SELECT * FROM TblRecommendation";
	public static final String SQL_UPD_RECOMMENDATION = "{ call qryUpdRecommend(?,?,?,?,?,?,?) }";
	public static final String SQL_ADD_RECOMMENDATION = "{ call qryInsRecommend(?,?,?,?,?,?) }";

	/*----------------------------------------- MORE QUERIES ----------------------------------------------*/
	public static final String SQL_SEL_TRANSPAY = "SELECT * FROM TblTransactionPay";
	public static final String SQL_SEL_TRANSCONFIRM = "SELECT * FROM TblTransactionConfirm";
	public static final String SQL_SEL_PRODUCTS = "SELECT TblProduct.* FROM TblProduct;";
	public static final String SQL_SEL_ORDERS = "SELECT * FROM TblOrder";

	/**
	 * find the correct path of the DB file
     * @return the path of the DB file (from eclipse or with runnable file)
	 */
	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			// System.out.println(decoded) - Can help to check the returned path
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				System.out.println(decoded);

				return decoded + "/database/database.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				return decoded + "/sources/database.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
