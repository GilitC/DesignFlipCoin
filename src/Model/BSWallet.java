package Model;

public class BSWallet extends Wallet {
	
	// -------------------------------Class  Members------------------------------
	
	private int maxTransSize;
	
	// -------------------------------Constructor------------------------------
	//BitCoin Space wallet
	public BSWallet(String uniqueAddress, double price, boolean installedOnComputer, boolean installedOnSmartphone,
			boolean installedOnTablet, double cashFlow, double pendingAmount, String publicAddress,
			String userSignature, int maxTransSize) {
		super(uniqueAddress, price, installedOnComputer, installedOnSmartphone, installedOnTablet, cashFlow,
				pendingAmount, publicAddress, userSignature);
		this.maxTransSize = maxTransSize;
	}
	
	// -------------------------------Getters And Setters------------------------------
	

	public int getMaxTransSize() {
		return maxTransSize;
	}


	public void setMaxTransSize(int maxTransSize) {
		this.maxTransSize = maxTransSize;
	}

	@Override
	public String toString() {
		return "Wallet Unique Address:" + getUniqueAddress() + ",  max Trans Size: " + maxTransSize + ", Price: "
				+ getPrice() + ", Cash Flow: " + getCashFlow() + "BTC , Pending Amount:" + getPendingAmount() + 
				"BTC, Installed On Computer: " + isInstalledOnComputer() + ", Installed On Smartphone:  " + 
				 isInstalledOnSmartphone() + ", Installed On Tablet: " + isInstalledOnTablet();
	}

	
	
	
}
