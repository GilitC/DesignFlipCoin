package utils;

/**
 * Enumeration commitment ~ represents the state of a transaction
 */
public enum TXstate {
	// -------------------------------------------------------------Values---------------------------------------------------------------------
	PENDING(1), EXECUTED(2), IRRELEVANT(3), CLOSED(4);
	
	// -------------------------------------------------------------Class Members----------------------------------------------------------------
	private final int state;

	// -------------------------------------------------------------Constructor------------------------------------------------------------------
	TXstate(int state) {
		this.state = state;
	}

	// -------------------------------------------------------------Methods----------------------------------------------------------------------
	public int getState() {
		return state;
	}

	public static TXstate returnLevel(int val) {
		switch (val) {
		case 0:
			return TXstate.PENDING;
		case 1:
			return TXstate.PENDING;
		case 2:
			return TXstate.EXECUTED;
		case 3:
			return TXstate.IRRELEVANT;
		case 4:
			return TXstate.CLOSED;
		default:
			return TXstate.PENDING;
		}
	}
	
	public String returnLevel(TXstate val) {
		switch (val) {
		case PENDING:
			return "PENDING";
		case EXECUTED:
			return "EXECUTED";
		case IRRELEVANT:
			return "IRRELEVANT";
		case CLOSED:
			return "CLOSED";
		default:
			return "PENDING";
		}
	}
	
}// ~ END OF Enum Class 
