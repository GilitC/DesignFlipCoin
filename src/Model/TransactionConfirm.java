package Model;

import java.util.Date;

public class TransactionConfirm{

	private int TXID;
	private String description;
	private int sizeInBytes;
	private Date dtCreated; //Date and time field in MS Access
	private Date executionTimeDate;
	private double fee;
	private String state; //STATUS [awaiting approval= default, confirmed, closed, irrelevant]
	private double payValue; // what???
	
	private String creatingAddress;
	private String creatingSignature;
	private String destinationAddress;
	private String destinationSignature;
	private String walletAddress;
	
	private boolean confirmed;
    private Date shipmentTime;
    
	// -------------------------------Constructor------------------------------
	
	public TransactionConfirm(int tXID, String description, int sizeInBytes, Date dtCreated, Date executionTimeDate,
			double fee, String state, double payValue, String creatingAddress, String creatingSignature,
			String destinationAddress, String destinationSignature, String walletAddress, boolean confirmed,
			Date shipmentTime) {
		this.TXID = tXID;
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
		this.confirmed = confirmed;
		this.shipmentTime = shipmentTime;
	}

	// -------------------------------Getters And Setters------------------------------
	public int getTXID() {
		return TXID;
	}


	public void setTXID(int tXID) {
		TXID = tXID;
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


	public boolean isConfirmed() {
		return confirmed;
	}


	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}


	public Date getShipmentTime() {
		return shipmentTime;
	}


	public void setShipmentTime(Date shipmentTime) {
		this.shipmentTime = shipmentTime;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TXID;
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
		TransactionConfirm other = (TransactionConfirm) obj;
		if (TXID != other.TXID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransactionConfirm [TXID=" + TXID + ", description=" + description + ", sizeInBytes=" + sizeInBytes
				+ ", dtCreated=" + dtCreated + ", executionTimeDate=" + executionTimeDate + ", fee=" + fee + ", state="
				+ state + ", payValue=" + payValue + ", creatingAddress=" + creatingAddress + ", creatingSignature="
				+ creatingSignature + ", destinationAddress=" + destinationAddress + ", destinationSignature="
				+ destinationSignature + ", walletAddress=" + walletAddress + ", confirmed=" + confirmed
				+ ", shipmentTime=" + shipmentTime + "]";
	}
	
    
	
}
