/**
 * 
 */
package jp.slm.web.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

/**
 * Fiel to validate with password policy
 * 
 * @author rDurocher
 * @see PasswordFieldValidator
 * @see PasswordValidator
 * 
 */
@Documented
@Constraint(validatedBy = { PasswordFieldValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@NotNull
public @interface PasswordField {
	
	String message() default "PasswordField";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
