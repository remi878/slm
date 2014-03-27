package jp.slm.web.form;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import jp.slm.business.bean.generic.GenericBean;
import jp.slm.web.validation.annotation.CaptchaResponseField;
import jp.slm.web.validation.annotation.PasswordField;
import jp.slm.web.validation.annotation.WithPasswordConfirmation;
import jp.slm.web.validation.form.CaptchaForm;
import jp.slm.web.validation.form.PasswordConfirmationForm;
import jp.slm.web.validation.group.FirstValidationGroup;
import jp.slm.web.validation.group.SecondValidationGroup;
import jp.slm.web.validation.group.ThirdValidationGroup;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@WithPasswordConfirmation(groups = { Default.class })
@GroupSequence({ FirstValidationGroup.class, SecondValidationGroup.class, ThirdValidationGroup.class, UserRegistrationForm.class })
public class UserRegistrationForm extends GenericBean implements PasswordConfirmationForm, CaptchaForm {
	
	@NotEmpty(groups = { FirstValidationGroup.class })
	@Email(groups = { ThirdValidationGroup.class })
	@Size(min = 7, max = 64, groups = { SecondValidationGroup.class })
	private String email;
	
	@NotEmpty(groups = { FirstValidationGroup.class })
	@PasswordField(groups = { SecondValidationGroup.class })
	private String password;
	
	private String confirmPassword;
	
	@NotEmpty(groups = { FirstValidationGroup.class })
	@Size(min = 2, max = 64, groups = { SecondValidationGroup.class })
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String firstName;
	
	@NotEmpty(groups = { FirstValidationGroup.class })
	@Size(min = 2, max = 64, groups = { SecondValidationGroup.class })
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String lastName;
	
	private Boolean gender;
	
	@NotNull(groups = { FirstValidationGroup.class })
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Past(groups = { SecondValidationGroup.class })
	private Date birthdate;
	
	@Size(min = 2, max = 32, groups = { SecondValidationGroup.class })
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String nickname;
	
	@Size(max = 512, groups = { SecondValidationGroup.class })
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String description;
	
	@URL(groups = { ThirdValidationGroup.class })
	@Size(min = 4, max = 64, groups = { SecondValidationGroup.class })
	private String websiteUrl;
	
	@NotNull(groups = { FirstValidationGroup.class })
	@CaptchaResponseField(groups = { SecondValidationGroup.class })
	private String captchaResponse;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		if (StringUtils.isBlank(email)) {
			email = null;
		}
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		if (StringUtils.isBlank(firstName)) {
			firstName = null;
		}
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		if (StringUtils.isBlank(lastName)) {
			lastName = null;
		}
		this.lastName = lastName;
	}
	
	public Boolean getGender() {
		return gender;
	}
	
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		if (StringUtils.isBlank(nickname)) {
			nickname = null;
		}
		this.nickname = nickname;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		if (StringUtils.isBlank(description)) {
			description = null;
		}
		this.description = description;
	}
	
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	
	public void setWebsiteUrl(String websiteUrl) {
		if (StringUtils.isBlank(websiteUrl)) {
			websiteUrl = null;
		}
		this.websiteUrl = websiteUrl;
	}
	
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
