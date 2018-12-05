package Model;

import java.util.Date;

public class TransactionConfirm extends Transaction{

	private String status;
	private Date supplyDate;


	public TransactionConfirm(String transactionID, String description, int sizeInBytes, Date dtCreated,
			Date dtFinished, String currentStatus, double taxAmount, String toAddress, String publicAddress,
			String userSignature, int orderID, String uniqueAddress, String status, Date supplyDate) {
		super(transactionID, description, sizeInBytes, dtCreated, dtFinished, currentStatus, taxAmount, toAddress,
				publicAddress, userSignature, orderID, uniqueAddress);
		this.status = status;
		this.supplyDate = supplyDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getSupplyDate() {
		return supplyDate;
	}


	public void setSupplyDate(Date supplyDate) {
		this.supplyDate = supplyDate;
	}
	
	
}
