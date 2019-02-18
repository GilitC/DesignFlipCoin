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


	/*----------------------------------------- RECOMMENDATION QUERIES --------------------------------------------*/
	public static final String SQL_SEL_RECOMMENDATION = "SELECT * FROM TblRecommendation";
	
	public static String usersByRecc(int recID) {
		String SQL_SEL_USERSBYRECID = "SELECT TblRecommendedFor.UserAddress, TblRecommendedFor.UserSignature, TblRecommendedFor.Recommendation, TblRecommendedFor.CommitmentLevel\r\n" + 
				"FROM TblRecommendedFor Where Recommendation =" + recID;
		return SQL_SEL_USERSBYRECID;
	}
	
	public static final String SQL_UPD_RECOMMENDATION = "UPDATE TblRecommendation SET TblRecommendation.dateCreated = ?, TblRecommendation.chanceChosen = ?, TblRecommendation.amountTaxRecommended = ? WHERE TblRecommendation.recommendId=?" ;			
	public static final String SQL_ADD_RECOMMENDATION = "INSERT INTO TblRecommendation ( dateCreated, chanceChosen, amountTaxRecommended ) VALUES ( ? , ? , ? )";
	public static final String SQL_SENDRECTOUSER = "INSERT INTO TblRecommendedFor ( UserAddress, UserSignature, Recommendation , CommitmentLevel ) VALUES ( ? , ? , ? , ? )";
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
	public static final String SQL_SEL_TRANSPAY = "SELECT * FROM TblPayTransaction";
	public static final String SQL_SEL_TRANSPAY_PENDING_STATUS = "SELECT * FROM TblPayTransaction Where TblPayTransaction.State=\"Pending\"";
	
    public static final String SQL_ADD_TRANSPAY = "INSERT INTO TblPayTransaction ( Description, Size, ExecutionTime, Fee, State, PayValue, CreatingAddress, CreatingSignature, DestinationAddress, DestinationSignature, walletAddress, orderID ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	
	
	/*----------------------------------------- CONFIRM TX QUERIES --------------------------------------------*/
	public static final String SQL_SEL_TRANSCONFIRM = "SELECT * FROM TblConfirmTransaction";
    public static final String SQL_ADD_TRANSCORNFIM = "INSERT INTO TblConfirmTransaction ( Description, Size, CreationTime, ExecutionTime, Fee, State, PayValue, CreatingAddress, CreatingSignature, DestinationAddress, DestinationSignature, walletAddress, confirmed, shipmentTime ) VALUES ( ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	
	
	/*----------------------------------------- CATEGORY QUERIES ----------------------------------------------*/
	public static final String SQL_SEL_CATEGORIES = "SELECT * FROM TblCategory";
	public static final String SQL_ADD_CATEGORY = "INSERT INTO TblCategory ( CategoryName ) VALUES ( ? )";
	public static final String SQL_UPD_CATEGORY= "UPDATE TblCategory SET TblCategory.CategoryName = ? WHERE TblCategory.SerialNumber=?" ;	
	
	/*----------------------------------------- SYSTEM QUERIES ----------------------------------------------*/
	//public static final String SQL_UPD_SYS_PARAMETERS= "UPDATE TblCategory SET TblCategory.CategoryName = ? WHERE TblCategory.SerialNumber=?" ;	
	
	
	/*----------------------------------------- WALLET QUERIES ----------------------------------------------*/
    public static final String SQL_ADD_WALLET = "INSERT INTO TblWallet ( Uniqueaddress, Price, installedOnComputer, installedOnSmartphone, installedOnTablet, Amount, pendingAmount, UserAddress, UserSignature ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public static final String SQL_UPD_WALLET_Profit = "UPDATE TblWallet SET TblWallet.Amount = ?, TblWallet.PendingAmount = ? WHERE TblWallet.uniqueaddress=?" ;
	
	public static final String SQL_GET_ALLMYWALLETS = "SELECT TblWallet.Uniqueaddress, TblWallet.Price, TblWallet.InstalledOnComputer, TblWallet.InstalledOnSmartphone, TblWallet.InstalledOnTablet, TblWallet.Amount, TblWallet.PendingAmount, TblWallet.UserAddress, TblWallet.UserSignature\r\n" + 
			"FROM TblWallet WHERE TblWallet.UserAddress=? AND TblWallet.UserSignature=?" + 
			"GROUP BY TblWallet.Uniqueaddress, TblWallet.Price, TblWallet.InstalledOnComputer, TblWallet.InstalledOnSmartphone, TblWallet.InstalledOnTablet, TblWallet.Amount, TblWallet.PendingAmount, TblWallet.UserAddress, TblWallet.UserSignature;\r\n" + 
			"";
	
	public static final String SQL_GET_FREETRANSSIZE = "SELECT TblSystem.TransactionSizeFree FROM TblSystem";
	public static final String SQL_GET_PRICEFOREXPANSION = "SELECT TblSystem.PriceForExpansion FROM TblSystem";
	public static final String SQL_GET_PRICE4DISCOUNT = "SELECT TblSystem.PriceForDiscount FROM TblSystem";
	public static final String SQL_GET_TRANSMINSIZE = "SELECT TblSystem.TransactionMinSize FROM TblSystem";
	public static final String SQL_GET_TRANSMAXSIZE = "SELECT TblSystem.TransactionMaxSize FROM TblSystem";
	public static final String SQL_GET_TRASSIZE4EXPANSION = "SELECT TblSystem.TransactionSizeForExpansion FROM TblSystem";
	public static final String SQL_GET_DISCOUNTPRECENTPERFEE = "SELECT TblSystem.DiscountPercentPerFee FROM TblSystem";
	
	public static final String SQL_CHECK_WALLETADDRESS = "SELECT count(*) as count from TblWallet where Uniqueaddress = ?";
	
    public static final String SQL_ADD_BKWALLET = "INSERT INTO TblBitcoinKnots ( WalletUniqueAddress, discountPercent ) VALUES ( ? , ? )";
    public static final String SQL_ADD_BSWALLET = "INSERT INTO TblBitCoinSpace ( WalletUniqueAddress, TransactionSize ) VALUES ( ? , ? )";
    
    public static final String SQL_VIEW_BKWALLET_BYUSER = "SELECT TblWallet.Uniqueaddress, TblWallet.Price, TblWallet.InstalledOnComputer, TblWallet.InstalledOnSmartphone, TblWallet.InstalledOnTablet, TblWallet.Amount, TblWallet.PendingAmount, TblWallet.UserAddress, TblWallet.UserSignature, TblBitCoinKnots.discountPercent\r\n" + 
    		"FROM TblWallet INNER JOIN TblBitCoinKnots ON TblWallet.Uniqueaddress = TblBitCoinKnots.WalletUniqueAddress\r\n" + 
    		"WHERE TblWallet.UserAddress=? AND TblWallet.UserSignature=?" + 
    		"GROUP BY TblWallet.Uniqueaddress, TblWallet.Price, TblWallet.InstalledOnComputer, TblWallet.InstalledOnSmartphone, TblWallet.InstalledOnTablet, TblWallet.Amount, TblWallet.PendingAmount, TblWallet.UserAddress, TblWallet.UserSignature, TblBitCoinKnots.discountPercent;\r\n" + 
    		"";
    public static final String SQL_VIEW_BSWALLET_BYUSER = "SELECT TblWallet.Uniqueaddress, TblWallet.Price, TblWallet.InstalledOnComputer, TblWallet.InstalledOnSmartphone, TblWallet.InstalledOnTablet, TblWallet.Amount, TblWallet.PendingAmount, TblWallet.UserAddress, TblWallet.UserSignature, TblBitcoinSpace.TransactionSize\r\n" + 
    		"FROM TblWallet INNER JOIN TblBitcoinSpace ON TblWallet.Uniqueaddress = TblBitcoinSpace.WalletUniqueAddress\r\n" + 
    		"WHERE TblWallet.UserAddress=? AND TblWallet.UserSignature=?" + 
    		"GROUP BY TblWallet.Uniqueaddress, TblWallet.Price, TblWallet.InstalledOnComputer, TblWallet.InstalledOnSmartphone, TblWallet.InstalledOnTablet, TblWallet.Amount, TblWallet.PendingAmount, TblWallet.UserAddress, TblWallet.UserSignature, TblBitcoinSpace.TransactionSize;\r\n" + 
    		"";
	
    public static final String SQL_SEL_PROD_FILTERED = "SELECT * FROM tblItem WHERE "
    		+ " sellerAddress != ? AND SellerSignature != ?"
    		+ " AND Price > ? AND Price < ? AND itemName like ?";
    
    public static final String SQL_SEL_PROD_FILTERED_CATEGORY = "SELECT * FROM tblItem WHERE "
    		+ " sellerAddress != ? AND SellerSignature != ?"
    		+ " AND Price > ? AND Price < ? AND itemName like ? AND Category = ?";
    

    public static final String SQL_GET_USER_BALANCE = "SELECT sum(Amount) as totalBalance from TblWallet WHERE UserAddress = ? AND UserSignature = ? group by UserAddress, UserSignature ";
    public static final String SQL_ADD_ITEM_TO_ORDER = "INSERT INTO tblItemsInOrder VALUES (?, ?)";
    public static final String SQL_ADD_ORDER	=	"INSERT INTO tblOrders (totalSum, userAddr, userSig) VALUES (?, ?, ?)";
    
    
    public static final String SQL_GET_ORDERS	=	"SELECT O.ID, O.totalSum, O.status, Count(I.orderID) AS items, O.paid \r\n" + 
    		"FROM tblorders AS O INNER JOIN tblItemsInOrder AS I ON O.ID = I.orderID\r\n" + 
    		"WHERE (((O.[userAddr]) = ?) AND ((O.[userSig]) = ?))\r\n" + 
    		"GROUP BY O.ID, O.totalSum, O.status, O.paid \r\n" + 
    		"ORDER BY O.ID DESC;\r\n" + 
    		"";
    
    public static final String SQL_GET_ORDER_UNPAID_AMOUNT = "SELECT sum(payValue) as unpaid from TblPayTransaction where orderID = ? AND State in (\"Pending\", \"Executed\")";
    public static final String SQL_GATHER_ORDERS_BY_ID = "select sum(ITEM.Price) as totalPrice, SellerAddress, SellerSignature FROM tblItemsInOrder as ITEMS inner join TblItem as ITEM on ITEM.productID = ITEMS.itemID where ITEMS.orderID = ? GROUP BY ITEM.SellerAddress, SellerSignature";
    
    
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
