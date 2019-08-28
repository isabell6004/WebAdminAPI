package net.fashiongo.webadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.SecurityListIPRepository;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class LoginService {
	
	@Autowired
	SecurityListIPRepository securityListIPRepository;
	
	public boolean checkIP(String ipAddress) {
		if (ipAddress.startsWith("10.77.252")
				|| ipAddress.startsWith("10.77.253") || ipAddress.startsWith("10.77.254")) {
			return true;
		}
		boolean result = securityListIPRepository.existsByIpAddress(ipAddress);
		return result;
	}
}