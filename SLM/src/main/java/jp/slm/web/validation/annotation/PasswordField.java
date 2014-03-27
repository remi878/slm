/**
 * 
 */
package jp.slm.web.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import jp.slm.business.util.PasswordPolicy;
import jp.slm.business.util.PasswordValidator;
import jp.slm.web.validation.validator.PasswordFieldValidator;

/**
 * Constraint to validate form pasword with {@link PasswordPolicy}
 * 
 * @author rDurocher
 * @see PasswordFieldValidator
 * @see PasswordValidator
 * @see PasswordPolicy
 * 
 */
@Documented
@Constraint(validatedBy = { PasswordFieldValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface PasswordField {
	
	String message() default "PasswordField";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
