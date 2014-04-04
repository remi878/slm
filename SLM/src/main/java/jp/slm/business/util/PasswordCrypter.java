package jp.slm.business.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * PasswordCrypter for dev purpose only : for prod it's beter to keep password undecryptable with a digester
 * 
 * @see applicationContext-security.xml
 * 
 * @author rDurocher
 * 
 */
public class PasswordCrypter implements PasswordEncoder {
	
	private static final byte[] PASSWORD_TOKEN = new byte[] { 53, 53, 52, 55, 52, 54, 55, 97, 54, 51, 51, 51, 54, 52, 55, 54, 54, 51, 54, 100, 53, 49, 51, 100 };
	
	private static final String ALGORITHM = "Blowfish";
	
	// private static final String ALGORITHM = "RC2";
	// private static final String ALGORITHM = "DES";
	// private static final String ALGORITHM = "AES";
	
	private static final PasswordCrypter INSTANCE = new PasswordCrypter();
	
	private Cipher cipher;
	
	private Key key;
	
	/**
	 * Constructs a standard password encoder with no additional secret value.
	 */
	public PasswordCrypter() {
		try {
			cipher = Cipher.getInstance(ALGORITHM);
			key = new SecretKeySpec(Base64.decode(Hex.decode(Utf8.decode(PASSWORD_TOKEN))), ALGORITHM);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static String encodeStr(String str) {
		return INSTANCE.encode(str);
	}
	
	public static String decodeStr(String str) {
		return INSTANCE.decode(str);
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		String res;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			res = Utf8.decode((Base64.encode(cipher.doFinal(Utf8.encode(rawPassword.toString())))));
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return res;
	}
	
	public String decode(String cryptedPassword) {
		String res;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			res = Utf8.decode(cipher.doFinal(Base64.decode(Utf8.encode(cryptedPassword))));
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return res;
	}
	
	@Override
	public boolean matches(CharSequence rawPassword, String cryptedPassword) {
		return encode(rawPassword).equals(cryptedPassword) && decode(cryptedPassword).equals(rawPassword.toString());
	}
	
}
