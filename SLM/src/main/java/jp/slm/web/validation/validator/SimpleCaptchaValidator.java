package jp.slm.web.validation.validator;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.slm.business.util.PasswordValidator;
import jp.slm.web.validation.annotation.CaptchaResponseField;
import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Validate Password using PasswordValidator
 * 
 * @author rDurocher
 * @see PasswordValidator
 * 
 */
public class SimpleCaptchaValidator implements ConstraintValidator<CaptchaResponseField, String> {
	
	public static final Logger LOG = LoggerFactory.getLogger(SimpleCaptchaValidator.class);
	
	@Override
	public void initialize(CaptchaResponseField captchaResponseField) {
	}
	
	@Override
	public boolean isValid(String captchaResponse, ConstraintValidatorContext context) {
		boolean valid = false;
			if (StringUtils.isNotBlank(captchaResponse) && RequestContextHolder.currentRequestAttributes() != null
					&& RequestContextHolder.currentRequestAttributes()instanceof ServletRequestAttributes) {
				ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
				HttpSession session = attr.getRequest().getSession(false);
				if(session!=null){
					Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
					if(captcha!=null){
						valid = captcha.isCorrect(captchaResponse);
					}
					if(!valid){
						AudioCaptcha audioCaptcha = (AudioCaptcha) session.getAttribute(AudioCaptcha.NAME);
						if(audioCaptcha!=null){
							valid = audioCaptcha.isCorrect(captchaResponse);
						}
					}
				}
			}
		return valid;
	}
}