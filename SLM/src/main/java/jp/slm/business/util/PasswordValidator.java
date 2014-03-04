package jp.slm.business.util;

import java.util.regex.Pattern;

public class PasswordValidator {
	
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	
	private PasswordValidator() {}
	
	/**
	 * Validate password with regular expression
	 * 
	 * @param password
	 *            password for validation
	 * @return true valid password, false invalid password
	 */
	public static boolean validate(final String password) {
		return pattern.matcher(password).matches();
	}
}