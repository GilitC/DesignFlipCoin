package utils;

/**
 * Enumeration commitment ~ represents the levels of Importance of a given recommendation
 */
public enum commitment {
	// -------------------------------------------------------------Values---------------------------------------------------------------------
	WEAK(1), MEDIUM(2), STRONG(3);
	
	// -------------------------------------------------------------Class Members----------------------------------------------------------------
	private final int commitmentLevel;

	// -------------------------------------------------------------Constructor------------------------------------------------------------------
	commitment(int commitmentLevel) {
		this.commitmentLevel = commitmentLevel;
	}

	// -------------------------------------------------------------Methods----------------------------------------------------------------------
	public int getLevel() {
		return commitmentLevel;
	}

	public static commitment returnLevel(int val) {
		switch (val) {
		case 0:
			return commitment.WEAK;
		case 1:
			return commitment.WEAK;
		case 2:
			return commitment.MEDIUM;
		case 3:
			return commitment.STRONG;
		default:
			return commitment.WEAK;
		}
	}
	
	public String returnLevel(commitment val) {
		switch (val) {
		case WEAK:
			return "WEAK";
		case MEDIUM:
			return "MEDIUM";
		case STRONG:
			return "STRONG";
		default:
			return "WEAK";
		}
	}
	
}// ~ END OF Enum Class 
