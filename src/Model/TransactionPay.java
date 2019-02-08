package Model;

import java.util.Date;

public class TransactionPay{

	// -------------------------------Class  Members------------------------------
	
	private String transactionID;
	private String description;
	private int sizeInBytes;
	private Date dtCreated; //Date and time field in MS Access
	private Date executionTimeDate;
	private double fee;
	private String state; //STATUS [awaiting approval= default, confirmed, closed, irrelevant]
	private double payValue; // amount payed
	private String creatingAddress;
	private String creatingSignature;
	private String destinationAddress;
	private String destinationSignature;
	private String walletAddress;

	// -------------------------------Constructor------------------------------
	
	
	public TransactionPay(String transactionID, String description, int sizeInBytes, Date dtCreated,
			Date executionTimeDate, double fee, String state, double payValue, String creatingAddress,
			String creatingSignature, String destinationAddress, String destinationSignature, String walletAddress) {
		super();
		this.transactionID = transactionID;
		this.description = description;
		this.sizeInBytes = sizeInBytes;
		this.dtCreated = dtCreated;
		this.executionTimeDate = executionTimeDate;
		this.fee = fee;
		this.state = state;
		this.payValue = payValue;
		this.creatingAddress = creatingAddress;
		this.creatingSignature = creatingSignature;
		this.destinationAddress = destinationAddress;
		this.destinationSignature = destinationSignature;
		this.walletAddress = walletAddress;
	}

	// -------------------------------Getters and Setters------------------------------
	
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

	public Date getExecutionTimeDate() {
		return executionTimeDate;
	}

	public void setExecutionTimeDate(Date executionTimeDate) {
		this.executionTimeDate = executionTimeDate;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getPayValue() {
		return payValue;
	}

	public void setPayValue(double payValue) {
		this.payValue = payValue;
	}

	public String getCreatingAddress() {
		return creatingAddress;
	}

	public void setCreatingAddress(String creatingAddress) {
		this.creatingAddress = creatingAddress;
	}

	public String getCreatingSignature() {
		return creatingSignature;
	}

	public void setCreatingSignature(String creatingSignature) {
		this.creatingSignature = creatingSignature;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationSignature() {
		return destinationSignature;
	}

	public void setDestinationSignature(String destinationSignature) {
		this.destinationSignature = destinationSignature;
	}

	public String getWalletAddress() {
		return walletAddress;
	}

	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transactionID == null) ? 0 : transactionID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionPay other = (TransactionPay) obj;
		if (transactionID == null) {
			if (other.transactionID != null)
				return false;
		} else if (!transactionID.equals(other.transactionID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransactionPay [transactionID=" + transactionID + ", description=" + description + ", sizeInBytes="
				+ sizeInBytes + ", dtCreated=" + dtCreated + ", executionTimeDate=" + executionTimeDate + ", fee=" + fee
				+ ", state=" + state + ", payValue=" + payValue + ", creatingAddress=" + creatingAddress
				+ ", creatingSignature=" + creatingSignature + ", destinationAddress=" + destinationAddress
				+ ", destinationSignature=" + destinationSignature + ", walletAddress=" + walletAddress + "]";
	}
	
	

	
	
	
}
