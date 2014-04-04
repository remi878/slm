package jp.slm.web.form;

import javax.validation.GroupSequence;

import jp.slm.web.validation.group.FirstValidationGroup;
import jp.slm.web.validation.group.SecondValidationGroup;
import jp.slm.web.validation.group.ThirdValidationGroup;

@SuppressWarnings("serial")
@GroupSequence({ FirstValidationGroup.class, SecondValidationGroup.class, ThirdValidationGroup.class, FanRegistrationForm.class, UserRegistrationForm.class })
public class FanRegistrationForm extends UserRegistrationForm {
	
}
