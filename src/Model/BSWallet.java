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
		return "BSWallet [maxTransSize=" + maxTransSize + ", getUniqueAddress()=" + getUniqueAddress() + ", getPrice()="
				+ getPrice() + ", isInstalledOnComputer()=" + isInstalledOnComputer() + ", isInstalledOnSmartphone()="
				+ isInstalledOnSmartphone() + ", isInstalledOnTablet()=" + isInstalledOnTablet() + ", getCashFlow()="
				+ getCashFlow() + ", getPendingAmount()=" + getPendingAmount() + ", getPublicAddress()="
				+ getPublicAddress() + ", getUserSignature()=" + getUserSignature() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}

	
	
	
}
