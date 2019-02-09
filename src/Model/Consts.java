package Model;

import java.io.File;

/**
 * http://www.javapractices.com/topic/TopicAction.do?Id=2
 */
public final class Consts {
	private Consts() {
		throw new AssertionError();
	}

	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
	public static final String[] UCA_LIB = {
			"ucanaccess-3.0.7.jar",
			"lib\\commons-lang-2.6.jar",
			"lib\\commons-logging-1.1.1.jar",
			"lib\\hsqldb.jar",
			"lib\\jackcess-2.1.10.jar"
	};

	/*----------------------------------------- USER QUERIES -----------------------------------------*/
	public static final String SQL_SEL_USERS = "SELECT * FROM TblUser";
	public static final String SQL_CHECK_SIGNATURE = "SELECT count(*) as count from tblUser where Signature = ?";
	public static final String SQL_CHECK_ADDRESS = "SELECT count(*) as count from tblUser where PublicAddress = ?";
	public static final String SQL_DEL_USER = "{ call qryDelUser(?) }";
	public static final String SQL_INS_USER = "INSERT INTO TblUser ( PublicAddress, Signature, UserName, Password, Phone, Email ) VALUES ( ? , ? , ? , ? , ? , ? )";

	
	public static final String SQL_ADD_NEWUSER = "{ call qryInsUser(?,?,?,?,?,?,?) }";
	public static final String SQL_UPD_USER = "{ call qryUpdUser(?,?,?,?,?,?,?) }";

	/*----------------------------------------- RECOMMENDATION QUERIES --------------------------------------------*/
	public static final String SQL_SEL_RECOMMENDATION = "SELECT * FROM TblRecommendation";
	
	public static String usersByRecc(int recID) {
		String SQL_SEL_USERSBYRECID = "SELECT TblUserGetRecommendation.levelOfImportance, TblUserGetRecommendation.publicAddress, TblUserGetRecommendation.userSignature, TblUserGetRecommendation.recommendId\r\n" + 
				"FROM TblUserGetRecommendation Where recommendId =" + recID;
		return SQL_SEL_USERSBYRECID;
	}
	
	public static final String SQL_UPD_RECOMMENDATION = "UPDATE TblRecommendation SET TblRecommendation.dateCreated = ?, TblRecommendation.chanceChosen = ?, TblRecommendation.amountTaxRecommended = ?, TblRecommendation.publicAddress = ?, TblRecommendation.userSignature = ? WHERE TblRecommendation.recommendId=?" ;			
	public static final String SQL_ADD_RECOMMENDATION = "INSERT INTO TblRecommendation ( dateCreated, chanceChosen, amountTaxRecommended, publicAddress, userSignature ) VALUES ( ? , ? , ? , ? , ? )";
	public static final String SQL_SENDRECTOUSER = "INSERT INTO TblRecommendedFor ( UserAddress, UserSignature, Recommendation , CommitimentLevel ) VALUES ( ? , ? , ? , ? )";
	public static final String SQL_RECS_BY_USER = "{ call qryMyRecommends(?,?) }";
	/*----------------------------------------- PRODUCT QUERIES --------------------------------------------*/
	public static final String SQL_SEL_PRODUCTS = "SELECT * FROM TblItem;";
	public static final String SQL_ADD_PRODUCT = "INSERT INTO TblItem ( productName, picture, description, pricePerUnit, quantityInStock, categoryID, sellerAddress, sellerSignature ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? )";
	public static final String SQL_UPD_PRODUCT = "UPDATE TblItem SET TblItem.ItemName = ?, TblItem.Image = ?, TblItem.Description = ?, TblItem.Price = ?, TblItem.Quantity = ?, TblItem.Category = ? WHERE TblItem.CatalogNumber=?" ;
	
	public static final String SQL_SEL_prodByUserID = "SELECT TblItem.productID, TblItem.ItemName, TblItem.Description, TblItem.Price, TblItem.Quantity FROM TblItem Where TblItem.SellerAddress=? AND TblItem.SellerSignature=?";
	
	/*----------------------------------------- PAY TX QUERIES --------------------------------------------*/
	public static final String SQL_SEL_TRANSPAY = "SELECT * FROM TblTransactionPay";
	
	
	/*----------------------------------------- CONFIRM TX QUERIES --------------------------------------------*/
	public static final String SQL_SEL_TRANSCONFIRM = "SELECT * FROM TblTransactionConfirm";
	
	
	/*----------------------------------------- CATEGORY QUERIES ----------------------------------------------*/
	public static final String SQL_SEL_CATEGORIES = "SELECT * FROM TblCategory";
	public static final String SQL_ADD_CATEGORY = "INSERT INTO TblCategory ( CategoryName ) VALUES ( ? )";
	public static final String SQL_UPD_CATEGORY= "UPDATE TblCategory SET TblCategory.CategoryName = ? WHERE TblCategory.SerialNumber=?" ;			
	
	
	/*----------------------------------------- MORE QUERIES ----------------------------------------------*/

	//public static final String SQL_SEL_ORDERS = "SELECT * FROM TblOrder";

	/**
	 * find the correct path of the DB file
     * @return the path of the DB file (from eclipse or with runnable file)
	 */
	private static String getDBPath() {
		File f = new File("src/sources/database.accdb");
		if(f.exists())
		{
			return f.getAbsolutePath();
		}
		
		return new File("sources/database.accdb").getAbsolutePath();
	}
}
