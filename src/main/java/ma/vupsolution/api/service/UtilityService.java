package ma.vupsolution.api.service;

import java.sql.Timestamp;
import java.text.ParseException;

import ma.vupsolution.api.model.Profile;


public interface UtilityService {
	
	public String getHhMmSsFromFixTime(Timestamp fixtime);
	public String getHhFromFixTime(Timestamp fixtime);
	public String getYyyyMmDdFromFixTime(Timestamp fixtime);
	public String getAddress(Double Lat, Double Lng);
	public String hash256Profile(Profile profile);
	public long getDiffSecondesFromNow(Timestamp fixtime) throws ParseException ;

}
