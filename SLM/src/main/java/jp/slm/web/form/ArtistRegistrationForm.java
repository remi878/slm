package jp.slm.web.form;

import javax.validation.GroupSequence;
import javax.validation.constraints.Size;

import jp.slm.web.validation.group.FirstValidationGroup;
import jp.slm.web.validation.group.SecondValidationGroup;
import jp.slm.web.validation.group.ThirdValidationGroup;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@SuppressWarnings("serial")
@GroupSequence({ FirstValidationGroup.class, SecondValidationGroup.class, ThirdValidationGroup.class, ArtistRegistrationForm.class, UserRegistrationForm.class })
public class ArtistRegistrationForm extends UserRegistrationForm {

	@URL(groups = { ThirdValidationGroup.class })
	@Size(min = 4, max = 64, groups = { SecondValidationGroup.class })
	private String youtube;

	@URL(groups = { ThirdValidationGroup.class })
	@Size(min = 4, max = 64, groups = { SecondValidationGroup.class })
	private String vimeo;

	@Size(max = 512, groups = { SecondValidationGroup.class })
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String groupPhylos;
	
	public String getYoutube() {
		return youtube;
	}
	
	public void setYoutube(String youtube) {
		if (StringUtils.isBlank(youtube)) {
			youtube = null;
		}
		this.youtube = youtube;
	}
	
	public String getVimeo() {
		return vimeo;
	}
	
	public void setVimeo(String vimeo) {
		if (StringUtils.isBlank(vimeo)) {
			vimeo = null;
		}
		this.vimeo = vimeo;
	}
	
	public String getGroupPhylos() {
		return groupPhylos;
	}
	
	public void setGroupPhylos(String groupPhylos) {
		if (StringUtils.isBlank(groupPhylos)) {
			groupPhylos = null;
		}
		this.groupPhylos = groupPhylos;
	}
	
}
