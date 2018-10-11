/**
 * 
 */
package net.fashiongo.webadmin.config.security;

/**
 * @author kcha
 *
 */
public class AccountCredentials {
	private String username;
	private String password;
	private String accessCode;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
}
