package Model;

public class Wallet {

	// -------------------------------Class  Members------------------------------
	
	//Note: every wallet is either a bs wallet or bk wallet, wallet obj can be created for the ease of the queries and code, but not for a user
	
	private String uniqueAddress; // The wallets unique address - is generated automatically
	private double price; // Calculated field
	private boolean installedOnComputer;
	private boolean installedOnSmartphone;
	private boolean installedOnTablet;
	private double cashFlow; // amount in access
	private double pendingAmount; // pending calculated amount
	
	private String publicAddress; //The user who owns the wallet
	private String userSignature;	
	
	// -------------------------------Constructors------------------------------
	
	//Default Wallet -------------------------------------------------------------
	public Wallet(String uniqueAddress, String publicAddress, String userSignature) {
		this.uniqueAddress = uniqueAddress;
		this.publicAddress = publicAddress;
		this.userSignature = userSignature;
	}
	
	public Wallet(String uniqueAddress, double price, boolean installedOnComputer, boolean installedOnSmartphone,
			boolean installedOnTablet, double cashFlow, double pendingAmount, String publicAddress,
			String userSignature) {
		this.uniqueAddress = uniqueAddress;
		this.price = price;
		this.installedOnComputer = installedOnComputer;
		this.installedOnSmartphone = installedOnSmartphone;
		this.installedOnTablet = installedOnTablet;
		this.cashFlow = cashFlow;
		this.pendingAmount = pendingAmount;
		this.publicAddress = publicAddress;
		this.userSignature = userSignature;
	}
	// -------------------------------Getters And Setters------------------------------
	
	public String getUniqueAddress() {
		return uniqueAddress;
	}

	public void setUniqueAddress(String uniqueAddress) {
		this.uniqueAddress = uniqueAddress;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isInstalledOnComputer() {
		return installedOnComputer;
	}

	public void setInstalledOnComputer(boolean installedOnComputer) {
		this.installedOnComputer = installedOnComputer;
	}

	public boolean isInstalledOnSmartphone() {
		return installedOnSmartphone;
	}

	public void setInstalledOnSmartphone(boolean installedOnSmartphone) {
		this.installedOnSmartphone = installedOnSmartphone;
	}

	public boolean isInstalledOnTablet() {
		return installedOnTablet;
	}

	public void setInstalledOnTablet(boolean installedOnTablet) {
		this.installedOnTablet = installedOnTablet;
	}

	public double getCashFlow() {
		return cashFlow;
	}

	public void setCashFlow(double cashFlow) {
		this.cashFlow = cashFlow;
	}

	public double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getPublicAddress() {
		return publicAddress;
	}

	public void setPublicAddress(String publicAddress) {
		this.publicAddress = publicAddress;
	}

	public String getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uniqueAddress == null) ? 0 : uniqueAddress.hashCode());
		return result;
	}

	// -------------------------------Methods------------------------------
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		if (uniqueAddress == null) {
			if (other.uniqueAddress != null)
				return false;
		} else if (!uniqueAddress.equals(other.uniqueAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wallet's Unique Address=" + uniqueAddress + ",  Cash Flow: " + cashFlow + ", Pending Amount: " + pendingAmount ;
	}



	
	
}
