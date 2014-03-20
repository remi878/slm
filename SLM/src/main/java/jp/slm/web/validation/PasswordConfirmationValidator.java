package jp.slm.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.slm.web.form.PasswordConfirmationForm;

/**
 * Validate that the password confirmation equals the password
 * 
 * @author rDurocher
 * 
 */
public class PasswordConfirmationValidator implements ConstraintValidator<WithPasswordConfirmation, PasswordConfirmationForm> {
	
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