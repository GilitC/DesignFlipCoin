package Model;

import java.util.Date;

public abstract class Transaction {

	// -------------------------------Class  Members------------------------------
	
	private String transactionID;
	private String description;
	private int sizeInBytes;
	private Date dtCreated;
	private Date dtFinished;
	private String currentStatus; //[awaiting approval= default, confirmed, closed, irrelevant]
	private double taxAmount;
	private String toAddress;
	private String publicAddress;
	private String userSignature;
	private int orderID;
	private String uniqueAddress;
	
	
	// -------------------------------Constructor------------------------------
	
	public Transaction(String transactionID, String description, int sizeInBytes, Date dtCreated, Date dtFinished,
			String currentStatus, double taxAmount, String toAddress, String publicAddress, String userSignature, int orderID, String uniqueAddress) {
		super();
		this.transactionID = transactionID;
		this.description = description;
		this.sizeInBytes = sizeInBytes;
		this.dtCreated = dtCreated;
		this.dtFinished = dtFinished;
		this.currentStatus = currentStatus;
		this.taxAmount = taxAmount;
		this.toAddress = toAddress;
		this.publicAddress = publicAddress;
		this.userSignature = userSignature;
		this.uniqueAddress = uniqueAddress;
	}

	// -------------------------------Getters And Setters------------------------------
	public String getTransactionID() {
		return transactionID;
	}


	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getSizeInBytes() {
		return sizeInBytes;
	}


	public void setSizeInBytes(int sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}


	public Date getDtCreated() {
		return dtCreated;
	}


	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}


	public Date getDtFinished() {
		return dtFinished;
	}


	public void setDtFinished(Date dtFinished) {
		this.dtFinished = dtFinished;
	}


	public String getCurrentStatus() {
		return currentStatus;
	}


	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
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

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getUniqueAddress() {
		return uniqueAddress;
	}

	public void setUniqueAddress(String uniqueAddress) {
		this.uniqueAddress = uniqueAddress;
	}

	public double getTaxAmount() {
		return taxAmount;
	}


	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}


	public String getToAddress() {
		return toAddress;
	}


	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", description=" + description + ", sizeInBytes="
				+ sizeInBytes + ", dtCreated=" + dtCreated + ", dtFinished=" + dtFinished + ", currentStatus="
				+ currentStatus + ", taxAmount=" + taxAmount + ", toAddress=" + toAddress + ", publicAddress="
				+ publicAddress + ", userSignature=" + userSignature + ", orderID=" + orderID + ", uniqueAddress="
				+ uniqueAddress + "]";
	}
	
	

	
}
