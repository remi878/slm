package jp.slm.business.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

public class MailConfirmationToken {
	
	private static final String TOKEN_TYPE = "MAIL_CONFIRMATION_TOKEN";
	
	private static final String TOKEN_SEP = "%#@#%";
	
	private static final long TOKEN_TTL = 1000 * 60 * 60 * 24 * 7; // 7 days
	
	private long userId;
	
	private long timeStamp;
	
	private long randomNumber;
	
	private boolean valid = false;
	
	public static MailConfirmationToken getNewToken(long userId) {
		return new MailConfirmationToken(userId);
	}
	
	public static MailConfirmationToken readToken(String tokenStr) {
		MailConfirmationToken token = null;
		if (isResetPasswordToken(tokenStr)) {
			token = new MailConfirmationToken(tokenStr);
		}
		return token;
	}
	
	private static boolean isResetPasswordToken(String tokenStr) {
		boolean res = false;
		if (StringUtils.isNotBlank(tokenStr)) {
			String decrypted = PasswordCrypter.decodeStr(tokenStr);
			if (StringUtils.isNotBlank(decrypted)) {
				String[] tokens = decrypted.split(TOKEN_SEP);
				if (tokens != null && tokens.length == 4 && StringUtils.isNumeric(tokens[0]) && StringUtils.isNumeric(tokens[1]) && StringUtils.isNumeric(tokens[2])
						&& TOKEN_TYPE.equals(tokens[3])) {
					res = true;
				}
			}
		}
		return res;
	}
	
	private MailConfirmationToken(long userId) {
		this.userId = userId;
		this.randomNumber = RandomUtils.nextLong();
		this.timeStamp = System.currentTimeMillis();
		this.valid = true;
	}
	
	private MailConfirmationToken(String tokenStr) {
		if (StringUtils.isNotBlank(tokenStr)) {
			String decrypted = PasswordCrypter.decodeStr(tokenStr);
			if (StringUtils.isNotBlank(decrypted)) {
				String[] tokens = decrypted.split(TOKEN_SEP);
				if (tokens != null && tokens.length == 4 && StringUtils.isNumeric(tokens[0]) && StringUtils.isNumeric(tokens[1]) && StringUtils.isNumeric(tokens[2])
						&& TOKEN_TYPE.equals(tokens[3])) {
					this.userId = Long.parseLong(tokens[0]);
					this.timeStamp = Long.parseLong(tokens[1]);
					this.randomNumber = Long.parseLong(tokens[2]);
					this.valid = userId >= 0 && (timeStamp > System.currentTimeMillis() - TOKEN_TTL);
				}
			}
		}
	}
	
	public boolean isValid() {
		return valid;
	}
	
	@Override
	public String toString() {
		String res = null;
		if (this.valid) {
			StringBuilder sb = new StringBuilder();
			sb.append(this.userId).append(TOKEN_SEP).append(this.timeStamp).append(TOKEN_SEP).append(this.randomNumber).append(TOKEN_SEP).append(TOKEN_TYPE);
			String temp = sb.toString();
			res = PasswordCrypter.encodeStr(temp);
		}
		return res;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public String getUserAuthToken() {
		String res = null;
		if (this.valid) {
			StringBuilder sb = new StringBuilder();
			sb.append(this.timeStamp).append("-").append(this.randomNumber).append("-").append(TOKEN_TYPE);
			res = sb.toString();
		}
		return res;
	}
}
