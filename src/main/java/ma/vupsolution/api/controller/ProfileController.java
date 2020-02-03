package ma.vupsolution.api.controller;


import ma.vupsolution.api.common.ErrorLabel;
import ma.vupsolution.api.model.Profile;
import ma.vupsolution.api.request.AuthenticationIn;
import ma.vupsolution.api.response.ProfileOut;
import ma.vupsolution.api.service.ProfileService;
import ma.vupsolution.api.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.vupsolution.api.exception.CustomErrorType;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProfileController { 

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private UtilityService utilityService;
    
    @CrossOrigin
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationIn auth) {
      log.info("-- Start login : "+auth);
  	  Profile current = profileService.fetchProfileByLogin(auth.getLogin());
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!current.getPassword().equals(auth.getPassword())){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.PASSWORD_MISSING),HttpStatus.NOT_FOUND);
	  }else{ 
		if(null == current.getToken()){
			String token = utilityService.hash256Profile(current);
			current.setToken(token);
	  		profileService.save(current);
		}
	  }
  	  log.info("-- End   login --");
  	  ProfileOut profile = profileService.fetchMenuDtoByLogin(auth.getLogin());
      return new ResponseEntity<ProfileOut>(profile, HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value="Authorization") String authorization, @RequestBody AuthenticationIn auth) {
      log.info("-- Start logout : "+auth);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  log.info("-- End logout --");
  	    return new ResponseEntity(new CustomErrorType(ErrorLabel.LOGOUT_SUCCESS),HttpStatus.OK);
    }

	@CrossOrigin
	@GetMapping("/checktoken")
	public ResponseEntity<?> checktoken(@RequestHeader(value="Authorization") String authorization) {
		log.info("-- Start checktoken : "+authorization);
		String token = authorization.replaceAll("Basic", "");
		Profile profile = profileService.fetchProfileByToken(token);
		if(null == profile){
			return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
		}
		log.info("-- End   checktoken --");
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);

	}
}
