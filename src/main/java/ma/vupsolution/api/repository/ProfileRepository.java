package ma.vupsolution.api.repository;

import java.util.List;

import ma.vupsolution.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	@Query("SELECT p FROM Profile p WHERE p.login=:login")
	public Profile fetchProfileByLogin(@Param("login") String login);
	
	@Query("SELECT p FROM Profile p WHERE p.token=:token")
	public Profile fetchProfileByToken(@Param("token") String token);
	
	@Query("SELECT p FROM Profile p WHERE p.id=:id")
	public Profile fetchProfileById(@Param("id") Long id);
	
	@Query("SELECT p FROM Profile p")
	public List<Profile> fetchAllProfile();
	
	public Profile save(Profile profile);

}
