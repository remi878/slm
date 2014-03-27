package jp.slm.web.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.slm.business.util.PasswordValidator;
import jp.slm.web.validation.annotation.PasswordField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validate Password using PasswordValidator
 * 
 * @author rDurocher
 * @see PasswordValidator
 *
 */
public class PasswordFieldValidator implements ConstraintValidator<PasswordField,String>{
	
	public static final Logger LOG = LoggerFactory.getLogger(PasswordFieldValidator.class);
	
	@Override
	public void initialize(PasswordField passwordAnnotation) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		return PasswordValidator.validate(password);
	}
	
}