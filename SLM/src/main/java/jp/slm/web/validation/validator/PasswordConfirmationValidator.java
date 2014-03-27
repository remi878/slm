package jp.slm.web.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.slm.web.validation.annotation.WithPasswordConfirmation;
import jp.slm.web.validation.form.PasswordConfirmationForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validate that the password confirmation equals the password
 * 
 * @author rDurocher
 * 
 */
public class PasswordConfirmationValidator implements ConstraintValidator<WithPasswordConfirmation, PasswordConfirmationForm> {
	
	public static final Logger LOG = LoggerFactory.getLogger(PasswordConfirmationValidator.class);
	
	@Override
	public void initialize(WithPasswordConfirmation constraintAnnotation) {}
	
	@Override
	public boolean isValid(PasswordConfirmationForm form, ConstraintValidatorContext context) {
		
		boolean isValid = (form.getPassword() == null ? form.getConfirmPassword() == null : form.getPassword().equals(form.getConfirmPassword()));
		
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{WithPasswordConfirmation}").addPropertyNode("confirmPassword").addConstraintViolation();
		}
		
		return isValid;
	}
	
}