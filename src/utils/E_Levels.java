package utils;

/**
 * Enumeration Levels ~ represent the levels of Importance of a given recommendation
 */
public enum E_Levels {
	// -------------------------------------------------------------Values---------------------------------------------------------------------
	LOW_LEVEL(1), MIDDLE(2), TOP_PRIORITY(3);
	
	// -------------------------------------------------------------Class Members----------------------------------------------------------------
	private final int level;

	// -------------------------------------------------------------Constructor------------------------------------------------------------------
	E_Levels(int level) {
		this.level = level;
	}

	// -------------------------------------------------------------Methods----------------------------------------------------------------------
	public int getLevel() {
		return level;
	}

	public static E_Levels returnLevel(int val) {
		switch (val) {
		case 0:
			return E_Levels.LOW_LEVEL;
		case 1:
			return E_Levels.LOW_LEVEL;
		case 2:
			return E_Levels.MIDDLE;
		case 3:
			return E_Levels.TOP_PRIORITY;
		default:
			return E_Levels.LOW_LEVEL;
		}
	}
	
}// ~ END OF Enum Class Levels
