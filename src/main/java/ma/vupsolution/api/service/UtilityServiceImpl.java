package ma.vupsolution.api.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


import ma.vupsolution.api.model.Profile;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service
public class UtilityServiceImpl implements UtilityService {

	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);


	@Override
	public String getAddress(Double Lat, Double Lng){
		GeoApiContext gtx = new GeoApiContext().setApiKey("AIzaSyD-w27Lhidw00LPBW7UWHp1TBPv4O3v650");
		GeocodingResult[] gResp = null ;
		try 
		  {
		    gResp = GeocodingApi.newRequest(gtx).latlng(new LatLng(Lat, Lng)).await();
		    if(null != gResp && gResp[0] != null)
		      System.out.println(gResp[0].formattedAddress);
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		String address = (null != gResp && gResp[0] != null ? gResp[0].formattedAddress : "Unnamed Road, Morocco");
	    return address;
	}

	@Override
	public String getYyyyMmDdFromFixTime(Timestamp fixtime) {
		// TODO Auto-generated method stub
		SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
		String date = std.format(fixtime);
		return date;
	}
	
	@Override
	public String getHhMmSsFromFixTime(Timestamp fixtime) {
		// TODO Auto-generated method stub
		SimpleDateFormat std = new SimpleDateFormat("HH:mm:ss");
		String hour = std.format(fixtime);
		return hour;
	}
	
	@Override
	public String getHhFromFixTime(Timestamp fixtime) {
		// TODO Auto-generated method stub
		SimpleDateFormat std = new SimpleDateFormat("HH");
		String hour = std.format(fixtime);
		return hour;
	}
	
	@Override
	public String hash256Profile(Profile profile){
		String textToHash = profile.getLogin()+":"+profile.getPassword(), encoded = null;
		MessageDigest digest;
        try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] byteOfTextToHash = textToHash.getBytes("UTF-8");
		    byte[] hashedByetArray = digest.digest(byteOfTextToHash);
		    encoded = Base64.getEncoder().encodeToString(hashedByetArray);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return encoded;
	}

	@Override
	public long getDiffSecondesFromNow(Timestamp fixtime) throws ParseException {
		Date now = new Date();
		//Date currentdate = sdf2.parse(now.toString());
		//Date fixtimedate = sdf2.parse(fixtime.toString());
		long diffInMillies = now.getTime() - fixtime.getTime();
		long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}

}
