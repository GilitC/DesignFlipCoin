package Model;

public class RecommendationToCustomer {

	//This class is a table made from the Many to Many Relationship
	// Between table User and table Recommendation
	// Level of importance - is saved for each recommendation and user
	

	private String publicAddress; //Customer F.Key
	private String userSignature; // Customer F.Key
	private int recommedID; // Recommendation F.Key
	private String commitimentlevel; // The level of the Recommendation for the customer
	
	
	public RecommendationToCustomer(String publicAddress, String userSignature, int recommedID, String commitimentlevel) {
		this.publicAddress = publicAddress;
		this.userSignature = userSignature;
		this.recommedID = recommedID;
		this.commitimentlevel = commitimentlevel;
	}

	public String getLevel() {
		return commitimentlevel;
	}

	public void setLevel(String commitimentlevel) {
		this.commitimentlevel = commitimentlevel;
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

	public int getRecommedID() {
		return recommedID;
	}

	public void setRecommedID(int recommedID) {
		this.recommedID = recommedID;
	}

	@Override
	public String toString() {
		return "Recommendation To Customer with public Address: " + publicAddress + ", User Signature: "
				+ userSignature + "Commitment level: " + commitimentlevel + ", Recommedation: " + recommedID;
	}
	
	
}
