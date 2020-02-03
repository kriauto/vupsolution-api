package ma.vupsolution.api.service;

import java.util.List;

import ma.vupsolution.api.model.Profile;
import ma.vupsolution.api.response.ProfileOut;

public interface ProfileService {
	
	public Profile fetchProfileByLogin(String login);
	public ProfileOut fetchMenuDtoByLogin(String login);
	public Profile fetchProfileByToken(String token);
	public Profile fetchProfileById(Long id);
	public List<Profile> fetchAllProfile();
	public Profile save(Profile profile);
	public void completeProfile(Profile in, Profile out);
}
