package Model;

import java.util.Date;


public class Recommendation {

	// -------------------------------Class  Members------------------------------
	
	private int recommedID;
	private Date dateCreated;
	private double chanceChosen;
	private double amountTaxRecommended;
	private String publicAddress; //  
	private String userSignature;	// 
	
	private String commitLevel; //Enum
	
	// -------------------------------Constructor------------------------------
	
	//Default
	public Recommendation(int recommedID, Date dateCreated, double chanceChosen, double amountTaxRecommended,
			String publicAddress, String userSignature) {
		super();
		this.recommedID = recommedID;
		this.dateCreated = dateCreated;
		this.chanceChosen = chanceChosen;
		this.amountTaxRecommended = amountTaxRecommended;
		this.publicAddress = publicAddress;
		this.userSignature = userSignature;
	}
	
	//With commitment lvl
	public Recommendation(int recommedID, Date dateCreated, double chanceChosen, double amountTaxRecommended,
			String commitLevel) {
		this.recommedID = recommedID;
		this.dateCreated = dateCreated;
		this.chanceChosen = chanceChosen;
		this.amountTaxRecommended = amountTaxRecommended;
		this.commitLevel = commitLevel;
	}
	
	// -------------------------------Getters And Setters------------------------------
	
	public String getCommitLevel() {
		return commitLevel;
	}

	public void setCommitLevel(String commitLevel) {
		this.commitLevel = commitLevel;
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public double getChanceChosen() {
		return chanceChosen;
	}
	public void setChanceChosen(double chanceChosen) {
		this.chanceChosen = chanceChosen;
	}
	public double getAmountTaxRecommended() {
		return amountTaxRecommended;
	}
	public void setAmountTaxRecommended(double amountTaxRecommended) {
		this.amountTaxRecommended = amountTaxRecommended;
	}

	@Override
	public String toString() {
		if(this.commitLevel==null)
			return "Recommendation [recommedID=" + recommedID + ", dateCreated=" + dateCreated + ", chanceChosen="
			+ chanceChosen + ", amountTaxRecommended=" + amountTaxRecommended + ", publicAddress=" + publicAddress
			+ ", userSignature=" + userSignature + "]";
		return "Recommendation [recommedID=" + recommedID + ", dateCreated=" + dateCreated + ", chanceChosen="
				+ chanceChosen + ", amountTaxRecommended=" + amountTaxRecommended + ", commitLevel=" + commitLevel + "]";
	}	
	
	
	
	
}
