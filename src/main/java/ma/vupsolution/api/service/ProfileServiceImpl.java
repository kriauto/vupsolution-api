package ma.vupsolution.api.service;

import java.util.List;

import ma.vupsolution.api.response.ProfileOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.vupsolution.api.model.Profile;
import ma.vupsolution.api.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
    private ProfileRepository profileRepository;

	@Autowired
    private UtilityService utilityService;
	
	@Override
	public Profile fetchProfileByLogin(String login) {
		return profileRepository.fetchProfileByLogin(login);
	}

	@Override
	public ProfileOut fetchMenuDtoByLogin(String login) {
		ProfileOut profile = new ProfileOut();
		Profile profiletmp = profileRepository.fetchProfileByLogin(login);
		profile.setId(profiletmp.getId());
		profile.setLogin(profiletmp.getLogin());
		profile.setPassword(profiletmp.getPassword());
		profile.setToken(profiletmp.getToken());
		profile.setFirstname(profiletmp.getFirstname());
		profile.setLastname(profiletmp.getLastname());
		profile.setSex(profiletmp.getSex());
		return profile;
	}

	@Override
	public Profile fetchProfileByToken(String token) {
		return profileRepository.fetchProfileByToken(token);
	}
	
	@Override
	public Profile fetchProfileById(Long id) {
		return profileRepository.fetchProfileById(id);
	}
	
	@Override
	public List<Profile> fetchAllProfile() {
		return profileRepository.fetchAllProfile();
	}

	@Override
	public Profile save(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public void completeProfile(Profile profilein, Profile profileout) {
		/*profileout.setLogin(profilein.getLogin() != null ? profilein.getLogin() : profileout.getLogin());
		profileout.setPassword(profilein.getPassword() != null ? profilein.getPassword() : profileout.getPassword());
		profileout.setName(profilein.getName() != null ? profilein.getName() : profileout.getName());
		profileout.setPhone(profilein.getPhone() != null ? profilein.getPhone() : profileout.getPhone());
		profileout.setMail(profilein.getMail() != null ? profilein.getMail() : profileout.getMail());*/
	}
}
