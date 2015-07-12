package com.bowling.util;

/**
 * 
 * @author Indraneel Sanikommu
 *
 */
public class BowlingUtils {

	public static boolean isTrue(String boolStr) {
		boolean isTrue = "true".equalsIgnoreCase(boolStr)
				|| "t".equalsIgnoreCase(boolStr)
				|| "yes".equalsIgnoreCase(boolStr)
				|| "y".equalsIgnoreCase(boolStr);
		return isTrue;
	}

}
