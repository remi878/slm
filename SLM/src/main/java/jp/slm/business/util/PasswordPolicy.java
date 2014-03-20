package jp.slm.business.util;

import java.util.regex.Pattern;

public class PasswordPolicy {
	
	private static final PasswordPolicy DEFAULT = PasswordPolicy.newInstance().setShouldHaveOneDigit(true).setShouldHaveOneUppercase(true).setShouldHaveOneLowercase(true)
			.setAllowSpecial(false).setRange(8, 32);
	
	private final static int NOT_DEF = -1;
	
	private static final String DIGIT = "\\d";
	
	private final static String REGEXP_HAVE_DIGIT = "(?=.*[" + DIGIT + "])";
	
	private static final String LOWER_CASE = "a-z";
	
	private final static String REGEXP_HAVE_LOWER_CASE = "(?=.*[" + LOWER_CASE + "])";
	
	private static final String UPPER_CASE = "A-Z";
	
	private final static String REGEXP_HAVE_UPPER_CASE = "(?=.*[" + UPPER_CASE + "])";
	
	private static final String SPECIAL = "@#$%\\^";
	
	private final static String REGEXP_HAVE_SPECIAL = "(?=.*[" + SPECIAL + "])";
	
	public static PasswordPolicy newInstance() {
		return new PasswordPolicy();
	}
	
	public static PasswordPolicy getDefault() {
		return DEFAULT;
	}
	
	private PasswordPolicy() {}
	
	private Pattern pattern;
	
	public String getRegExpString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(').append(mustHave()).append(allowed()).append(range()).append(')');
		return sb.toString();
	}
	
	public Pattern getRegExpPatern() {
		if (pattern == null) {
			pattern = Pattern.compile(getRegExpString());
		}
		return pattern;
	}
	
	private String allowed() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (allowDigit) {
			sb.append(DIGIT);
		}
		if (allowUppercase) {
			sb.append(UPPER_CASE);
		}
		if (allowLowercase) {
			sb.append(LOWER_CASE);
		}
		if (allowSpecial) {
			sb.append(spectialRegExp);
		}
		sb.append(']');
		return sb.toString();
	}
	
	private String range() {
		StringBuilder sb = new StringBuilder();
		if (min > NOT_DEF && max > NOT_DEF) {
			sb.append('{');
			if (min > NOT_DEF) {
				sb.append(min);
			}
			sb.append(',');
			if (max > NOT_DEF) {
				sb.append(max);
			}
			sb.append('}');
		} else {
			sb.append('*');
		}
		return sb.toString();
	}
	
	private String mustHave() {
		StringBuilder sb = new StringBuilder();
		if (shouldHaveOneDigit) {
			sb.append(REGEXP_HAVE_DIGIT);
		}
		if (shouldHaveOneLowercase) {
			sb.append(REGEXP_HAVE_LOWER_CASE);
		}
		if (shouldHaveOneUppercase) {
			sb.append(REGEXP_HAVE_UPPER_CASE);
		}
		if (shouldHaveOneSpecial) {
			if (SPECIAL.equals(spectialRegExp)) {
				sb.append(REGEXP_HAVE_SPECIAL);
			} else {
				sb.append(REGEXP_HAVE_SPECIAL.replace(SPECIAL, spectialRegExp));
			}
		}
		return sb.toString();
	}
	
	private int min = NOT_DEF;
	
	private int max = NOT_DEF;
	
	private boolean allowUppercase = true;
	
	private boolean allowLowercase = true;
	
	private boolean allowDigit = true;
	
	private boolean allowSpecial = true;
	
	private boolean shouldHaveOneUppercase = false;
	
	private boolean shouldHaveOneLowercase = false;
	
	private boolean shouldHaveOneDigit = false;
	
	private boolean shouldHaveOneSpecial = false;
	
	private String spectialRegExp = SPECIAL;
	
	public PasswordPolicy setSpectialRegExp(String spectialRegExp) {
		this.pattern = null;
		this.spectialRegExp = spectialRegExp;
		return this;
	}
	
	public PasswordPolicy setMin(int min) {
		this.pattern = null;
		this.min = min;
		return this;
	}
	
	public PasswordPolicy setMax(int max) {
		this.pattern = null;
		this.max = max;
		return this;
	}
	
	public PasswordPolicy setRange(int min, int max) {
		this.pattern = null;
		this.min = min;
		this.max = max;
		return this;
	}
	
	public PasswordPolicy setShouldHaveOneUppercase(boolean shouldHaveOneUppercase) {
		this.pattern = null;
		if (shouldHaveOneUppercase) {
			this.allowUppercase = true;
		}
		this.shouldHaveOneUppercase = shouldHaveOneUppercase;
		return this;
	}
	
	public PasswordPolicy setShouldHaveOneLowercase(boolean shouldHaveOneLowercase) {
		this.pattern = null;
		if (shouldHaveOneLowercase) {
			this.allowLowercase = true;
		}
		this.shouldHaveOneLowercase = shouldHaveOneLowercase;
		return this;
	}
	
	public PasswordPolicy setShouldHaveOneDigit(boolean shouldHaveOneDigit) {
		this.pattern = null;
		if (shouldHaveOneDigit) {
			this.allowDigit = true;
		}
		this.shouldHaveOneDigit = shouldHaveOneDigit;
		return this;
	}
	
	public PasswordPolicy setShouldHaveOneSpecial(boolean shouldHaveOneSpecial) {
		this.pattern = null;
		if (shouldHaveOneSpecial) {
			this.allowSpecial = true;
		}
		this.shouldHaveOneSpecial = shouldHaveOneSpecial;
		return this;
	}
	
	public PasswordPolicy setAllowUppercase(boolean allowUppercase) {
		this.pattern = null;
		if (!allowUppercase) {
			this.shouldHaveOneUppercase = false;
		}
		this.allowUppercase = allowUppercase;
		return this;
	}
	
	public PasswordPolicy setAllowLowercase(boolean allowLowercase) {
		this.pattern = null;
		if (!allowLowercase) {
			this.shouldHaveOneLowercase = false;
		}
		this.allowLowercase = allowLowercase;
		return this;
	}
	
	public PasswordPolicy setAllowDigit(boolean allowDigit) {
		this.pattern = null;
		if (!allowDigit) {
			this.shouldHaveOneDigit = false;
		}
		this.allowDigit = allowDigit;
		return this;
	}
	
	public PasswordPolicy setAllowSpecial(boolean allowSpecial) {
		this.pattern = null;
		if (!allowSpecial) {
			this.shouldHaveOneSpecial = false;
		}
		this.allowSpecial = allowSpecial;
		return this;
	}
}