package Model;

import java.io.File;

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
	public static final String SQL_RECS_BY_USER = "SELECT TblRecommendation.recommendId, TblRecommendation.dateCreated, TblRecommendation.chanceChosen, TblRecommendation.amountTaxRecommended, TblRecommendedFor.CommitmentLevel " + 
			"FROM TblRecommendation INNER JOIN TblRecommendedFor ON TblRecommendation.recommendId = TblRecommendedFor.Recommendation " + 
			"WHERE (((TblRecommendedFor.UserAddress)=?) AND ((TblRecommendedFor.UserSignature)=?)) " + 
			"GROUP BY TblRecommendation.recommendId, TblRecommendation.dateCreated, TblRecommendation.chanceChosen, TblRecommendation.amountTaxRecommended, TblRecommendedFor.CommitmentLevel";
	/*----------------------------------------- PRODUCT QUERIES --------------------------------------------*/
	public static final String SQL_SEL_PRODUCTS = "SELECT * FROM TblItem;";
	public static final String SQL_ADD_PRODUCT = "INSERT INTO TblItem ( productName, picture, description, pricePerUnit, quantityInStock, categoryID, sellerAddress, sellerSignature ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? )";
	public static final String SQL_UPD_PRODUCT = "UPDATE TblItem SET TblItem.ItemName = ?, TblItem.Image = ?, TblItem.Description = ?, TblItem.Price = ?, TblItem.Quantity = ?, TblItem.Category = ? WHERE TblItem.CatalogNumber=?" ;
	
	public static final String SQL_SEL_prodByUserID = "SELECT TblItem.productID, TblItem.ItemName, TblItem.Description, TblItem.Price, TblItem.Quantity FROM TblItem Where TblItem.SellerAddress=? AND TblItem.SellerSignature=?";
	
	public static final String SQL_SEL_PRODSwithoutME = "SELECT TblItem.productID, TblItem.ItemName, TblItem.Image, TblItem.Description, TblItem.Price, TblItem.Quantity, TblItem.Category, TblItem.SellerAddress, TblItem.SellerSignature FROM TblItem WHERE ((Not (TblItem.SellerAddress)=?) AND (Not (TblItem.SellerSignature)=?))";

	/*----------------------------------------- PAY TX QUERIES --------------------------------------------*/
	public static final String SQL_SEL_TRANSPAY = "SELECT * FROM TblTransactionPay";
	
	
	/*----------------------------------------- CONFIRM TX QUERIES --------------------------------------------*/
	public static final String SQL_SEL_TRANSCONFIRM = "SELECT * FROM TblTransactionConfirm";
	
	
	/*----------------------------------------- CATEGORY QUERIES ----------------------------------------------*/
	public static final String SQL_SEL_CATEGORIES = "SELECT * FROM TblCategory";
	public static final String SQL_ADD_CATEGORY = "INSERT INTO TblCategory ( CategoryName ) VALUES ( ? )";
	public static final String SQL_UPD_CATEGORY= "UPDATE TblCategory SET TblCategory.CategoryName = ? WHERE TblCategory.SerialNumber=?" ;			
	
	
	/*----------------------------------------- WALLET QUERIES ----------------------------------------------*/
    public static final String SQL_ADD_WALLET = "INSERT INTO TblWallet ( uniqueaddress, price, installedOnComputer, installedOnSmartphone, installedOnTablet, cashFlow, pendingAmount, publicAddress, userSignature ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public static final String SQL_UPD_WALLET_Profit = "UPDATE TblWallet SET TblWallet.Amount = ?, TblWallet.PendingAmount = ? WHERE TblWallet.uniqueaddress=?" ;
	
	public static final String SQL_CHECK_WALLETADDRESS = "SELECT count(*) as count from tblWallet where Uniqueaddress = ?";
	
    public static final String SQL_ADD_BKWALLET = "INSERT INTO TblBitcoinSpace ( WalletUniqueAddress, discountPrecent ) VALUES ( ? , ? )";
    public static final String SQL_ADD_BSWALLET = "INSERT INTO TblBitCoinKnots ( WalletUuniqueAddress, TransactionSize ) VALUES ( ? , ? )";
	

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
