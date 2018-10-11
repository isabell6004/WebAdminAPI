/**
 * 
 */
package net.fashiongo.webadmin.config.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @author kjback
 *
 */
public class VendorBlockedException extends AuthenticationException {
	private String detailedMessage;
	
	public VendorBlockedException(String msg) {
		super(msg);
		this.detailedMessage = msg;
	}
	public String getDetailedMessage() {
		return this.detailedMessage;
	}
}
