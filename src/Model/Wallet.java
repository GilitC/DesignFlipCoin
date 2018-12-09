package Model;

public class Wallet {

	// -------------------------------Class  Members------------------------------
	
	private String uniqueAddress;
	private double cashFlow;
	private double futureValue;
	private String publicAddress;
	private String userSignature;	
	
	
	// -------------------------------Constructors------------------------------
	
	public Wallet(String uniqueAddress, double cashFlow, double futureValue, String publicAddress, String userSignature) {
		super();
		this.uniqueAddress = uniqueAddress;
		this.cashFlow = cashFlow;
		this.futureValue = futureValue;
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

	public double getCashFlow() {
		return cashFlow;
	}


	public void setCashFlow(double cashFlow) {
		this.cashFlow = cashFlow;
	}


	public double getFutureValue() {
		return futureValue;
	}


	public void setFutureValue(double futureValue) {
		this.futureValue = futureValue;
	}

	@Override
	public String toString() {
		return "Wallet [uniqueAddress=" + uniqueAddress + ", cashFlow=" + cashFlow + ", futureValue=" + futureValue
				+ ", publicAddress=" + publicAddress + ", userSignature=" + userSignature + "]";
	}

	
	
}
