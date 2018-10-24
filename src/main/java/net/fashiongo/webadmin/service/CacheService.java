package net.fashiongo.webadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class CacheService {
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public JsonResponse GetRedisCacheEvict(String cacheName, String key) {
		String request = "cache/".concat(cacheName).concat("/").concat(key);
		JsonResponse result = httpClient.get(request);
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param oldDirName
	 * @param newDirName
	 * @return
	 */
	public JsonResponse GetRedisCacheEvict_ChangeDirName(String oldDirName, String newDirName) {
		String request = "misc/shell/changeDirname/".concat(oldDirName).concat("/").concat(newDirName);
		JsonResponse result = httpClient.get(request);
		
		return result;
	}
}