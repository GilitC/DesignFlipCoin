package Model;

import java.util.Date;


public class Recommendation {

	// -------------------------------Class  Members------------------------------
	
	private int recommedID;
	private Date dateCreated;
	private double chanceChosen;
	private double amountTaxRecommended;
	
	private String commitLevel; //Enum
	
	// -------------------------------Constructor------------------------------
	
	//Default
	public Recommendation(int recommedID, Date dateCreated, double chanceChosen, double amountTaxRecommended
			) {
		super();
		this.recommedID = recommedID;
		this.dateCreated = dateCreated;
		this.chanceChosen = chanceChosen;
		this.amountTaxRecommended = amountTaxRecommended;
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
			return "Recommendation:" + recommedID + ", Created On " + dateCreated + ", chance Chosen:"
			+ chanceChosen + ", amount Tax Recommended: " + amountTaxRecommended;
		return "Recommendation: " + recommedID + ", Created On " + dateCreated + ", chance Chosen:"
				+ chanceChosen + ", amount Tax Recommended: " + amountTaxRecommended + ", Commitment Level:" + commitLevel + "";
	}	
	
	
	
	
}
