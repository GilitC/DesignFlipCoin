package Model;

public class RecommendationToCustomer {

	//This class is a table made from the Many to Many Relationship
	// Between table User and table Recommendation
	// Level of importance - is saved for each recommendation and user
	
	private String level; // The level of the Recommendation for the customer
	private String publicAddress; //Customer F.Key
	private String userSignature; // Customer F.Key
	private int recommedID; // Recommendation F.Key
	
	public RecommendationToCustomer(String level, String publicAddress, String userSignature, int recommedID) {
		super();
		this.level = level;
		this.publicAddress = publicAddress;
		this.userSignature = userSignature;
		this.recommedID = recommedID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
		return "RecommendationToCustomer [level=" + level + ", publicAddress=" + publicAddress + ", userSignature="
				+ userSignature + ", recommedID=" + recommedID + "]";
	}
	
	
}
