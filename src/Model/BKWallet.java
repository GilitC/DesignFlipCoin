package Model;

public class BKWallet extends Wallet {
	
	// -------------------------------Class  Members------------------------------
	
	private double taxSale;
	
	// -------------------------------Constructor------------------------------
	
	public BKWallet(String uniqueAddress, double cashFlow, double futureValue, String publicAddress,
			String userSignature, double taxSale) {
		super(uniqueAddress, cashFlow, futureValue, publicAddress, userSignature);
		this.taxSale = taxSale;
	}

	// -------------------------------Getters And Setters------------------------------
	
	public double getTaxSale() {
		return taxSale;
	}


	public void setTaxSale(double taxSale) {
		this.taxSale = taxSale;
	}

	
	
}
