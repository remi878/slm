package jp.slm.web.form;

@SuppressWarnings("serial")
public class ArtistRegistrationForm extends UserRegistrationForm {


    private String youtube;

    private String wimeo;

    private String groupPhylos;

	
	public String getYoutube() {
		return youtube;
	}

	
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	
	public String getWimeo() {
		return wimeo;
	}

	
	public void setWimeo(String wimeo) {
		this.wimeo = wimeo;
	}

	
	public String getGroupPhylos() {
		return groupPhylos;
	}

	
	public void setGroupPhylos(String groupPhylos) {
		this.groupPhylos = groupPhylos;
	}

    
    
}
