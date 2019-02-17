package utils;

//**ENUM NAMETOWINDOW was created in order to neatly sort and categorize pages and their paths.

public enum NameToWindow {

	WELCOMESCREEN("welcome"),
	/*------------LOGIN-------------------------------------------------*/
	SIGN_UP("View/add/addUser"),
	
	/*------------BUY-------------------------------------------------*/
	BUY_WALLET("View/buy/buyWallet"),
	CHARGE_WALLET("View/buy/chargeWallet"),

	/*------------CREATE-------------------------------------------------*/
	CREATE_PAYTRANS("View/add/addTeam"),
	CREATE_CONFIRMTRANS("View/add/addTeam"),
	
	/*------------MENUS-------------------------------------------------*/
	MENU_REPORTS("menus/reportsMenu"),
	MENU_PRODUCT("menus/products"),
	MENU_USER("menus/users"),
	MENU_TRANSACTIONS("menus/users"),
	MENU_RECOMMENDATIONS("menus/recMenu"),
	QUERIES("menus/queries"),

	
	/*------------EMPLOYEE LOGIN SCREEN----------------------------------------------------------*/
	VIEW_RECOMMENDATIONS("viewRecommendations"),
	UPDATE_RECOMMENDATIONS("update/updateRecommendation"),
	SEND_REC_TO_USER("sendRecToUser"),
	
	/*------------ADD ADMIN PAGES------------------------------------*/
	ADD_RECOMMENDATION("add/addRecommendation"),
	
	/*------------USER LOGIN SCREEN---------------------------------------------------------------------*/
	VIEW_PRODUCTS_FORSALE("user/viewItemsForSale"),
	PUBLISH_PRODUCT("add/addProduct"),
	UPDATE_PRODUCT("update/editProduct"),
	VIEW_MY_RECOMMENDATIONS("user/viewMyRecommendations"),
	WALLETS_MENU("menus/WalletMenu"),
	VIEW_MY_WALLETS("user/viewMyWallets"),
	BUY_BS_WALLET("user/addBSWallet"),
	MANAGE_ORDERS("Orders/viewOrders"),
	
	
	/*------------QUERIES-------------------------------*/
	QUERY_GETSPM("queries/getSPM");	
	
	
	
	NameToWindow(String s)
	{
		this.myPath = s;
	}
	
	private String myPath;
	
	@Override
	public String toString() {
		return myPath;
	}
}
