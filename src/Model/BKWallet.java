package Model;

public class BKWallet extends Wallet {
	
	// -------------------------------Class  Members------------------------------
	
	private double discountPercent;
	
	// -------------------------------Constructor------------------------------
	
	//Default wallet 1st created for each user with no cost--------------------------------
	public BKWallet(String uniqueAddress, String publicAddress, String userSignature, double discountPercent) {
		super(uniqueAddress, publicAddress, userSignature);
		this.discountPercent = discountPercent;
	}

	//When buying a wallet---------------------------------------
	
	public BKWallet(String uniqueAddress, double price, boolean installedOnComputer, boolean installedOnSmartphone,
			boolean installedOnTablet, double cashFlow, double pendingAmount, String publicAddress,
			String userSignature, double discountPercent) {
		super(uniqueAddress, price, installedOnComputer, installedOnSmartphone, installedOnTablet, cashFlow,
				pendingAmount, publicAddress, userSignature);
		this.discountPercent = discountPercent;
	}

	// -------------------------------Getters And Setters------------------------------
	
	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	// -------------------------------Methods------------------------------
	
	@Override
	public String toString() {
		return "BKWallet [discountPercent=" + discountPercent + ", getUniqueAddress()=" + getUniqueAddress()
				+ ", getPrice()=" + getPrice() + ", isInstalledOnComputer()=" + isInstalledOnComputer()
				+ ", isInstalledOnSmartphone()=" + isInstalledOnSmartphone() + ", isInstalledOnTablet()="
				+ isInstalledOnTablet() + ", getCashFlow()=" + getCashFlow() + ", getPendingAmount()="
				+ getPendingAmount() + ", getPublicAddress()=" + getPublicAddress() + ", getUserSignature()="
				+ getUserSignature() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}




	
}
