package net.fashiongo.webadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.SecurityListIPRepository;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class LoginService {
	
	@Autowired
	private SecurityListIPRepository securityListIPRepository;
	
	public boolean checkIP(String ipAddress) {
		if (StringUtils.isEmpty(ipAddress)) return false;

		String[] addrs = ipAddress.split("\\.");
		if (addrs.length != 4) return false;

		String ipAddressCidr8 = String.join(".", addrs[0], addrs[1], addrs[2], "0");

		return securityListIPRepository.existsByIpAddress(ipAddress) ||
				securityListIPRepository.existsByIpAddress(ipAddressCidr8);
	}
}