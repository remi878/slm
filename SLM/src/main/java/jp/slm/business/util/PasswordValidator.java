package jp.slm.business.util;

import java.util.regex.Pattern;


public class PasswordValidator {
	
	// private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%\^]).{8,32})";
	// private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,32})";
	private static final PasswordPolicy DEFAULT_PASSWORD_POLICY = PasswordPolicy.getDefault();
	
	private PasswordValidator() {}
	
	/**
	 * Validate password with regular expression and {@link PasswordPolicy}
	 * 
	 * @param password
	 *            password for validation
	 * @param passwordPolicy
	 *            PasswordPolicy for password validation
	 * @return true valid password, false invalid password
	 */
	public static boolean validate(final String password, PasswordPolicy passwordPolicy) {
		return validate(password,passwordPolicy.getRegExpPatern());
	}
	
	/**
	 * Validate password with regular expression (and default application password policy)
	 * 
	 * @param password
	 *            password for validation
	 * @return true valid password, false invalid password
	 */
	public static boolean validate(final String password) {
		return validate(password, DEFAULT_PASSWORD_POLICY);
	}
	
	/**
	 * Validate password with regular expression and {@link PasswordPolicy}
	 * 
	 * @param password
	 *            password for validation
	 * @param passwordPattern
	 *            Pattern (regExp) for password validation
	 * @return true valid password, false invalid password
	 */
	public static boolean validate(final String password, Pattern passwordPattern) {
		return passwordPattern.matcher(password).matches();
	}
	
	/**
	 * Validate password with regular expression and {@link PasswordPolicy}
	 * 
	 * @param password
	 *            password for validation
	 * @param passwordPattern
	 *            String regExp pattern for password validation
	 * @return true valid password, false invalid password
	 */
	public static boolean validate(final String password, String passwordPattern) {
		return Pattern.compile(passwordPattern).matcher(password).matches();
	}
}