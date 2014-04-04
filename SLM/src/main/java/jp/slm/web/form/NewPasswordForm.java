package jp.slm.web.form;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import jp.slm.web.validation.annotation.CaptchaResponseField;
import jp.slm.web.validation.annotation.PasswordField;
import jp.slm.web.validation.annotation.WithPasswordConfirmation;
import jp.slm.web.validation.form.CaptchaForm;
import jp.slm.web.validation.form.PasswordConfirmationForm;
import jp.slm.web.validation.group.FirstValidationGroup;
import jp.slm.web.validation.group.SecondValidationGroup;
import jp.slm.web.validation.group.ThirdValidationGroup;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

@WithPasswordConfirmation(groups = { Default.class })
@GroupSequence({ FirstValidationGroup.class, SecondValidationGroup.class, ThirdValidationGroup.class, NewPasswordForm.class })
public class NewPasswordForm implements PasswordConfirmationForm, CaptchaForm {

	@NotEmpty(groups = { FirstValidationGroup.class })
	private String oldPassword;

	@NotEmpty(groups = { FirstValidationGroup.class })
	@PasswordField(groups = { SecondValidationGroup.class })
	private String password;

	@NotEmpty(groups = { FirstValidationGroup.class })
	private String confirmPassword;
	
	@NotNull(groups = { FirstValidationGroup.class })
	@CaptchaResponseField(groups = { SecondValidationGroup.class })
	private String captchaResponse;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if (StringUtils.isBlank(password)) {
			password = null;
		}
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		if (StringUtils.isBlank(confirmPassword)) {
			confirmPassword = null;
		}
		this.confirmPassword = confirmPassword;
	}
	
	
	public String getOldPassword() {
		return oldPassword;
	}

	
	public void setOldPassword(String oldPassword) {
		if (StringUtils.isBlank(oldPassword)) {
			oldPassword = null;
		}
		this.oldPassword = oldPassword;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}
	
	public void setCaptchaResponse(String captchaResponse) {
		if (StringUtils.isBlank(captchaResponse)) {
			captchaResponse = null;
		}
		this.captchaResponse = captchaResponse;
	}
}
