package jp.slm.web.validation.form;

public interface PasswordConfirmationForm {
	
	String getPassword();
	
	void setPassword(String password);
	
	String getConfirmPassword();
	
	void setConfirmPassword(String confirmPassword);
}
