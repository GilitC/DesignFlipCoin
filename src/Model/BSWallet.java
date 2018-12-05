package Model;

public class BSWallet extends Wallet {
	
	// -------------------------------Class  Members------------------------------
	
	private int maxTransSize;
	
	// -------------------------------Constructor------------------------------
	
	public BSWallet(String uniqueAddress, double cashFlow, double futureValue, String publicAddress,
			String userSignature, int maxTransSize) {
		super(uniqueAddress, cashFlow, futureValue, publicAddress, userSignature);
		this.maxTransSize = maxTransSize;
	}

	// -------------------------------Getters And Setters------------------------------
	

	public int getMaxTransSize() {
		return maxTransSize;
	}


	public void setMaxTransSize(int maxTransSize) {
		this.maxTransSize = maxTransSize;
	}

	
	
	
}
