package jp.slm.common.util;

import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * 
 * @author rDurocher
 *
 */
public class PasswordEncoderHolder {
	
	private static PasswordEncoder passwordEncoder;
	
	public static PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderHolder.passwordEncoder;
	}
	
	public void setPwdEncoder(PasswordEncoder pwdEncoder) {
		PasswordEncoderHolder.passwordEncoder = pwdEncoder;
	}
}
