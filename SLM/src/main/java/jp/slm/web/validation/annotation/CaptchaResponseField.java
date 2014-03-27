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

import jp.slm.web.validation.validator.SimpleCaptchaValidator;

/**
 * Constraint to validate with form with captcha
 * 
 * @author rDurocher
 * @see SimpleCaptchaValidator
 * 
 */
@Documented
@Constraint(validatedBy = { SimpleCaptchaValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@NotNull
public @interface CaptchaResponseField {
	
	String message() default "CaptchaResponseField";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
