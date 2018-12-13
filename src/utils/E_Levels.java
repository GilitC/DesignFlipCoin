package utils;

/**
 * Enumeration Levels ~ represent the levels of Importance of a given recommendation
 */
public enum E_Levels {
	// -------------------------------------------------------------Values---------------------------------------------------------------------
	LOWLEVEL(1), MIDDLE(2), TOPPRIORITY(3);
	
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
			return E_Levels.LOWLEVEL;
		case 1:
			return E_Levels.LOWLEVEL;
		case 2:
			return E_Levels.MIDDLE;
		case 3:
			return E_Levels.TOPPRIORITY;
		default:
			return E_Levels.LOWLEVEL;
		}
	}
	
	public String returnLevel(E_Levels val) {
		switch (val) {
		case LOWLEVEL:
			return "LOWLEVEL";
		case MIDDLE:
			return "MIDDLE";
		case TOPPRIORITY:
			return "TOPPRIORITY";
		default:
			return "LOWLEVEL";
		}
	}
	
}// ~ END OF Enum Class Levels
