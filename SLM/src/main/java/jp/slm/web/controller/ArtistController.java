package jp.slm.web.controller;

import javax.validation.Valid;

import jp.slm.business.bean.Artist;
import jp.slm.business.bean.User;
import jp.slm.business.service.ArtistService;
import jp.slm.business.service.UserService;
import jp.slm.web.controller.generic.GenericController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = ArtistController.ARTIST_CONTEXT_URL)
public class ArtistController extends GenericController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArtistService artistService;
	
	@RequestMapping(method = RequestMethod.GET, value = PROFILE_EXT_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR })
	public String viewProfile(Model model) {
		String view = ARTIST_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (userDetails != null && userDetails instanceof Artist) {
			Artist artist = (Artist) userDetails;
			artist = artistService.findById(artist.getId());
			refreshUserPrincipal(artist);
			model.addAttribute(ARTIST, artist);
			model.addAttribute(USER_STR, artist.getUser());
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = EDIT_PROFILE_EXT_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR })
	public String editProfile(Model model) {
		String view = ARTIST_EDIT_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (userDetails != null && userDetails instanceof Artist) {
			Artist artist = (Artist) userDetails;
			artist = artistService.findById(artist.getId());
			refreshUserPrincipal(artist);
			model.addAttribute(ARTIST, artist);
			model.addAttribute(USER_STR, artist.getUser());
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = SAVE_PROFILE_EXT_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR })
	public String saveProfile(@Valid @ModelAttribute(ARTIST) Artist artistForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = ARTIST_EDIT_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (!result.hasErrors() && userDetails != null && userDetails instanceof Artist) {
			Artist artist = (Artist) userDetails;
			artist = artistService.findById(artist.getId());
			if (!artist.getUsername().equals(artistForm.getUsername()) && userService.userExists(artistForm.getUsername())) {
				result.rejectValue(EMAIL, "user.already.exists", "Email already used");
			}
			artist.mergeWithForm(artistForm);
			artistService.update(artist);
			refreshUserPrincipal(artist);
			redirectAttributes.addAttribute(MESSAGE, ARTIST_UPDATE_DONE);
			view = REDIRECT + ARTIST_PROFILE_URL;
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
}
