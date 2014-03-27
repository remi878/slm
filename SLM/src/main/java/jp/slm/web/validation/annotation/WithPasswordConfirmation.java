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
import javax.validation.constraints.NotNull;

import jp.slm.web.validation.validator.PasswordConfirmationValidator;

/**
 * Validate that the password confirmation equals the password
 * 
 * @author rDurocher
 * 
 */
@Documented
@Constraint(validatedBy = { PasswordConfirmationValidator.class })
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@NotNull
public @interface WithPasswordConfirmation {
	
	String message() default "WithPasswordConfirmation";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
