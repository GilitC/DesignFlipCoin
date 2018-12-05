package Model;

import java.util.Date;

public class TransactionPay extends Transaction{

	private double amountPayed;

	public TransactionPay(String transactionID, String description, int sizeInBytes, Date dtCreated, Date dtFinished,
			String currentStatus, double taxAmount, String toAddress, String publicAddress, String userSignature,
			int orderID, String uniqueAddress, double amountPayed) {
		super(transactionID, description, sizeInBytes, dtCreated, dtFinished, currentStatus, taxAmount, toAddress,
				publicAddress, userSignature, orderID, uniqueAddress);
		this.amountPayed = amountPayed;
	}

	public double getAmountPayed() {
		return amountPayed;
	}

	public void setAmountPayed(double amountPayed) {
		this.amountPayed = amountPayed;
	}
	
	
}
