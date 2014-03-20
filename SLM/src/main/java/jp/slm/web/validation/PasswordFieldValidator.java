package jp.slm.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.slm.business.util.PasswordValidator;

/**
 * Validate Password using PasswordValidator
 * 
 * @author rDurocher
 * @see PasswordValidator
 *
 */
public class PasswordFieldValidator implements ConstraintValidator<PasswordField,String>{
	
	@Override
	public void initialize(PasswordField passwordAnnotation) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		return PasswordValidator.validate(password);
	}
	
}